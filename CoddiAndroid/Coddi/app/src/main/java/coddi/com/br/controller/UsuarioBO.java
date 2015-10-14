package coddi.com.br.controller;

import android.util.Log;

import com.google.gson.JsonParser;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import coddi.com.br.App.CoddiApplication;
import coddi.com.br.App.Rest;
import coddi.com.br.dao.UsuarioDAO;
import coddi.com.br.json.JsonController;
import coddi.com.br.model.Usuario;

/**
 * Created by Bruno on 14/03/2015.
 */
public class UsuarioBO extends AbstractBO<Usuario, Long, UsuarioDAO> {

    public UsuarioBO(CoddiApplication context) throws SQLException {
        super(context, new UsuarioDAO(context.getBd().getConnectionSource()), "usuarios");
    }

    public Usuario buscarPorLoginSenha(String login, String senha) {
        return getDao().buscarPorLoginSenha(login, senha);
    }

    @Override
    protected UsuarioDAO getDao() {
        return super.getDao();
    }

    public void incluir(Usuario objeto) throws Exception {
        validar(objeto);
        super.incluir(objeto);
    }

    private void validar(Usuario objeto) {
    }

    public void integrarTodos() throws Exception {
        List<Usuario> usuarios = buscarTodos(getContext().getUsuarioLogado().getId());
        for (Usuario usuario : usuarios) {
            if (usuario.getIntegrado() == 0) {
                usuario.setId(null);
                integrar(usuario);
            }
        }
    }

    public void integrar(Usuario usuario) throws Exception {
        try {
            String usuarioJson = getContext().getGson().toJson(usuario);
            String usuarioGravadoJSon = Rest.post(getHost(getPath()), usuarioJson);

        } catch (Throwable e) {
            e.printStackTrace();
            throw new Exception("Ops! Sem conexão.", e);
        }
    }

    public Usuario logar(String email) throws Exception {
        Usuario usuario = buscarNoServidorPorEmail(email);
        usuario.setCodigoReferencia(usuario.getId());
        usuario.setId(null);

        Usuario usuarioExistente = buscarPorId(usuario.getCodigoReferencia());

        if (usuarioExistente != null) {
            usuario.setId(usuarioExistente.getId());
            alterar(usuario);
        } else {
            incluir(usuario);
        }
        return usuario;
    }

    private Usuario buscarNoServidorPorEmail(String email) {
        Usuario usuario = new Usuario();

        String json = "";
        try {
            String host = getHost(getPath()) + "email?email=" + email;
            json = Rest.get(host);
            if ("".equals(json)) {
                json = "[]";
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        JsonParser parser = new JsonParser();
        try {
            usuario = JsonController.usuarioJson.fromJson(parser.parse(json).getAsJsonObject(), Usuario.class);

        } catch (Throwable e) {
            Log.e("teste", "Falha a buscar o usuário de login", e);
        }


        return usuario;
    }

    public List<Usuario> buscarNoServidorTodos() {
        List<Usuario> usuarios = new ArrayList<>();

        String json = "";
        try {
            json = Rest.get(getHost(getPath()));
            if ("".equals(json)) {
                json = "[]";
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        JsonParser parser = new JsonParser();
        try {
            usuarios = JsonController.usuarioJson.fromJson(parser.parse(json).getAsJsonArray(), Usuario.class);

        } catch (Throwable e) {
            Log.e("teste", "Falha ao buscar usuários", e);
        }

        return usuarios;
    }
}
