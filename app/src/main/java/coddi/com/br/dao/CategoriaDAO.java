package coddi.com.br.dao;

import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import coddi.com.br.model.Categoria;
import coddi.com.br.model.Usuario;

/**
 * Created by Bruno on 14/03/2015.
 */
public class CategoriaDAO extends AbstractDAO<Categoria, Long> {

    public CategoriaDAO(ConnectionSource connection) throws SQLException {
        super(connection, Categoria.class);
    }

}
