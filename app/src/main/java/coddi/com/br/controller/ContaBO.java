package coddi.com.br.controller;

import java.sql.SQLException;
import java.util.List;

import coddi.com.br.App.CoddiApplication;
import coddi.com.br.dao.ContaDAO;
import coddi.com.br.model.Conta;

/**
 * Created by Bruno on 02/02/2015.
 */
public class ContaBO extends AbstractBO {

    public ContaBO(CoddiApplication context) throws SQLException {
        super(context, new ContaDAO(context.getBd().getConnectionSource()), "conta");
    }

    public List<Conta> buscarTodosAtivos() {
        return getDao().buscarTodosAtivos();
    }

    @Override
    protected ContaDAO getDao() {
        return (ContaDAO) super.getDao();
    }
}
