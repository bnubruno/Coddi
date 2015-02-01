package coddi.com.br.dao;

import android.content.Context;

import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class Persistencia {

    private static Persistencia instance;
    private ConnectionSource db = null;

    private static UsuarioDAO usuarioDAO;

    protected Persistencia() {
    }

    public static Persistencia getInstance() {
        if (instance == null) {
            instance = new Persistencia();
        }
        return instance;
    }

    public ConnectionSource getDatabase() {
        return db;
    }

    public void abrirOuCriarBanco(Context context) {
        if (db == null) {
            DatabaseHelper dbh = new DatabaseHelper(context);
            db = dbh.getConnectionSource();
            try {
                usuarioDAO = new UsuarioDAO(dbh.getConnectionSource());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

}
