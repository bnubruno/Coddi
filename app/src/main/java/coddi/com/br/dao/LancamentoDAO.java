package coddi.com.br.dao;

import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import coddi.com.br.model.Lancamento;

/**
 * Created by Bruno on 12/04/2015.
 */
public class LancamentoDAO extends AbstractDAO<Lancamento, Long> {

    public LancamentoDAO(ConnectionSource connection) throws SQLException {
        super(connection, Lancamento.class);
    }
}
