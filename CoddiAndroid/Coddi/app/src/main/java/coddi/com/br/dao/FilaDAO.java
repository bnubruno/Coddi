package coddi.com.br.dao;

import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import coddi.com.br.model.Fila;

/**
 * Created by Bruno on 20/07/2015.
 */
public class FilaDAO extends AbstractDAO<Fila, Long> {

    public FilaDAO(ConnectionSource connection) throws SQLException {
        super(connection, Fila.class);
    }

    public List<Fila> buscarNaoIntegrados() {
        List<Fila> lista = new ArrayList<>();
        try {
            QueryBuilder<Fila, Long> qb = queryBuilder();
            Where where = qb.where();
            where.eq("integrado", 0);
            lista = qb.query();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

}
