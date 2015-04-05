package coddi.com.br.controller;

import java.sql.SQLException;

import coddi.com.br.App.CoddiApplication;
import coddi.com.br.dao.UsuarioDAO;
import coddi.com.br.model.Usuario;

/**
 * Created by Bruno on 14/03/2015.
 */
public class UsuarioBO extends AbstractBO {

    public UsuarioBO(CoddiApplication context) throws SQLException {
        super(context, new UsuarioDAO(context.getBd().getConnectionSource()), "usuario");
    }

    public Usuario buscarPorLoginSenha(String login, String senha) {
        return getDao().buscarPorLoginSenha(login, senha);
    }

    @Override
    protected UsuarioDAO getDao() {
        return (UsuarioDAO) super.getDao();
    }
}
