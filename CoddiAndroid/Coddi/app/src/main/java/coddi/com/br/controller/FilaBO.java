package coddi.com.br.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import coddi.com.br.App.CoddiApplication;
import coddi.com.br.dao.FilaDAO;
import coddi.com.br.model.Fila;
import coddi.com.br.model.TipoIntegracao;
import coddi.com.br.sincronizacao.ContaSincronizavel;
import coddi.com.br.sincronizacao.Sincronizavel;

/**
 * Created by Bruno on 20/07/2015.
 */
public class FilaBO extends AbstractBO<Fila, Long, FilaDAO> {

    private static Map<TipoIntegracao, Sincronizavel> sync = new HashMap<>();

    public FilaBO(CoddiApplication context) throws SQLException {
        super(context, new FilaDAO(context.getBd().getConnectionSource()), "fila");

        sync.put(TipoIntegracao.CONTA, new ContaSincronizavel(context.getHost(), "conta"));
    }

    public void sincroniza() throws Exception {
        List<Fila> lista = getDao().buscarTodos();

        if (!lista.isEmpty()) {
            for (Fila fila : lista) {
                sync.get(fila.getTipo()).processa(fila);
                
                fila.setIntegrado(1);
                alterar(fila);
            }
        }


    }

    @Override
    protected FilaDAO getDao() {
        return super.getDao();
    }

}
