package coddi.com.br.controller;

import java.sql.SQLException;

import coddi.com.br.App.CoddiApplication;
import coddi.com.br.dao.ContaDAO;
import coddi.com.br.dao.LancamentoDAO;

/**
 * Created by Bruno on 12/04/2015.
 */
public class LancamentoBO extends AbstractBO {

    public LancamentoBO(CoddiApplication context) throws SQLException {
        super(context, new ContaDAO(context.getBd().getConnectionSource()), "lancamento");
    }

    @Override
    protected LancamentoDAO getDao() {
        return (LancamentoDAO) super.getDao();
    }
}
