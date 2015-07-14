package coddi.com.br.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bruno on 08/02/2015.
 */
public class AbstractDAO<E, F> extends BaseDaoImpl<E, F> implements IDao<E, F> {

    public AbstractDAO(ConnectionSource connection, Class<E> clazz) throws SQLException {
        super(clazz);
        setConnectionSource(connection);
        initialize();
    }

    public List<E> buscarTodos() {
        List<E> lista = null;
        try {
            lista = queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista != null ? lista : new ArrayList<E>();
    }

    @Override
    public List<E> buscarTodos(String orderBy) {
        try {
            QueryBuilder<E, F> qb = queryBuilder();
            qb.orderBy(orderBy, false);
            List<E> lista = qb.query();

            if (lista != null) {
                return lista;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void incluir(E objeto) {
        try {
            create(objeto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void alterar(E objeto) {
        try {
            update(objeto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(E objeto) {
        try {
            delete(objeto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public E buscarPorId(F id) {
        try {
            return queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
