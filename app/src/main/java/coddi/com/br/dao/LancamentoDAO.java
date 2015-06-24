package coddi.com.br.dao;

import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import coddi.com.br.model.Lancamento;
import coddi.com.br.model.TipoFinanceiro;
import coddi.com.br.model.TipoOperacao;
import coddi.com.br.model.TipoStatus;

/**
 * Created by Bruno on 12/04/2015.
 */
public class LancamentoDAO extends AbstractDAO<Lancamento, Long> {

    public LancamentoDAO(ConnectionSource connection) throws SQLException {
        super(connection, Lancamento.class);
    }

    public List<Lancamento> buscarLancamentoPorTipoFinanceiro(TipoFinanceiro tipoFinanceiro) {
        List<Lancamento> list = new ArrayList<>();
        try {
            QueryBuilder<Lancamento, Long> qb = queryBuilder();
            Where where = qb.where();
            where.and(where.eq("tipoFinanceiro", tipoFinanceiro), where.eq("status", TipoStatus.ATIVO));
            qb.orderBy("data", false);
            qb.orderBy("id", false);
            list = qb.query();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Lancamento> buscarLancamentoPorTipoOperacao(TipoOperacao tipoOperacao) {
        List<Lancamento> list = new ArrayList<>();
        try {
            QueryBuilder<Lancamento, Long> qb = queryBuilder();
            Where where = qb.where();
            where.and(where.eq("tipoOperacao", tipoOperacao), where.eq("status", TipoStatus.ATIVO));
            qb.orderBy("data", false);
            qb.orderBy("id", false);
            list = qb.query();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Lancamento> buscarLancamentosPorConta(Long idConta) {
        List<Lancamento> list = new ArrayList<>();
        try {
            QueryBuilder<Lancamento, Long> qb = queryBuilder();
            Where where = qb.where();
            where.and(where.eq("idConta", idConta), where.eq("status", TipoStatus.ATIVO));
            qb.orderBy("data", false);
            qb.orderBy("id", false);
            list = qb.query();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
