package coddi.com.br.dao;

import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

import coddi.com.br.model.Categoria;
import coddi.com.br.model.TipoFinanceiro;
import coddi.com.br.model.TipoStatus;

/**
 * Created by Bruno on 14/03/2015.
 */
public class CategoriaDAO extends AbstractDAO<Categoria, Long> {

    public CategoriaDAO(ConnectionSource connection) throws SQLException {
        super(connection, Categoria.class);
    }

    public List<Categoria> buscarPorTipoFinanceiro(TipoFinanceiro tipoFinanceiro) {
        List<Categoria> list = null;
        try {
            QueryBuilder<Categoria, Long> qb = queryBuilder();
            Where where = qb.where();
            where.and(where.or(where.eq("tipoFinanceiro", tipoFinanceiro), where.eq("tipoFinanceiro", TipoFinanceiro.AMBOS)), where.eq("status", TipoStatus.ATIVO));
            qb.orderBy("nome", true);
            list = qb.query();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public Categoria buscarPorMovimentacao() {
        List<Categoria> list = null;
        try {
            QueryBuilder<Categoria, Long> qb = queryBuilder();
            Where where = qb.where();
            where.and(where.eq("tipoFinanceiro", TipoFinanceiro.MOVIMENTACAO), where.eq("status", TipoStatus.ATIVO));
            qb.orderBy("nome", true);
            list = qb.query();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list.get(0);
    }

}
