package coddi.com.br.dao;

import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import coddi.com.br.model.Usuario;

/**
 * Created by Bruno on 05/02/2015.
 */
public class UsuarioDAO extends AbstractDAO<Usuario, Long> {

    public UsuarioDAO(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Usuario.class);
    }

    public Usuario buscarPorLoginSenha(String login, String senha) {
        Usuario usuario = null;
        try {
            QueryBuilder<Usuario, Long> qb = queryBuilder();
            Where where = qb.where();
            where.and(where.eq("login", login), where.eq("senha", senha));

            usuario = qb.queryForFirst();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }

}
