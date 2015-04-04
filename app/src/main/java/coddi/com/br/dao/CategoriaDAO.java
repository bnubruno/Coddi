package coddi.com.br.dao;

import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

import coddi.com.br.model.Categoria;
import coddi.com.br.model.TipoFinanceiro;

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
            where.or(where.eq("tipoFinanceiro", tipoFinanceiro), where.eq("tipoFinanceiro", TipoFinanceiro.AMBOS));
            list = qb.query();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

}
