package coddi.com.br.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import coddi.com.br.model.Usuario;

/**
 * Created by Bruno on 01/02/2015.
 */
public class UsuarioDAO extends BaseDaoImpl<Usuario, Long> {

    public UsuarioDAO(ConnectionSource connection) throws SQLException {
        super(Usuario.class);
        setConnectionSource(connection);
        initialize();
    }
}
