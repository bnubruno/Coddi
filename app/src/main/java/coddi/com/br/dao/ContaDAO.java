package coddi.com.br.dao;

import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

import coddi.com.br.model.Conta;
import coddi.com.br.model.TipoStatus;

/**
 * Created by Bruno on 05/02/2015.
 */
public class ContaDAO extends AbstractDAO<Conta, Long> {

    public ContaDAO(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Conta.class);
    }

    public List<Conta> buscarTodosAtivos() {
        List<Conta> list = null;
        try {
            QueryBuilder<Conta, Long> qb = queryBuilder();
            Where where = qb.where();
            where.eq("status", TipoStatus.ATIVO);
            qb.orderBy("nome", true);
            list = qb.query();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

}
