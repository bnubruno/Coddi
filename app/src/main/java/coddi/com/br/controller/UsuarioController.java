package coddi.com.br.controller;

import android.util.Log;

import com.google.gson.JsonParser;

import java.util.List;

import coddi.com.br.App.CoddiApplication;
import coddi.com.br.App.Rest;
import coddi.com.br.json.JsonController;
import coddi.com.br.model.Usuario;

/**
 * Created by Bruno on 02/02/2015.
 */
public class UsuarioController extends AbstractController {

    public UsuarioController(CoddiApplication context) {
        super(context, "usuarios");
    }

    public synchronized List<Usuario> buscarTodos() {
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
        List<Usuario> list = null;
        try {
            list = JsonController.usuarioJson.fromJson(parser.parse(json).getAsJsonArray(), Usuario.class);
        } catch (Throwable e) {
            Log.e("teste", "falha ao criar a categoria", e);
        }
        return list;
    }

    public void create(Usuario usuario) {
        String json = getContext().getGson().toJson(usuario);

        try {
            json = Rest.post(getHost(getPath()), json);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
