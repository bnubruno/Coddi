package coddi.com.br.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import coddi.com.br.App.CoddiApplication;
import coddi.com.br.dao.CategoriaDAO;
import coddi.com.br.model.Categoria;
import coddi.com.br.model.TipoFinanceiro;

/**
 * Created by Bruno on 02/02/2015.
 */
public class CategoriaBO extends AbstractBO {

    public CategoriaBO(CoddiApplication context) throws SQLException {
        super(context, new CategoriaDAO(context.getBd().getConnectionSource()), "categoria");
    }

    public List<Categoria> buscarPorTipoFinanceiro(TipoFinanceiro tipoFinanceiro) {
        return getDao().buscarPorTipoFinanceiro(tipoFinanceiro);
    }

    public List<String> buscarPorTipoFinanceiroString(TipoFinanceiro tipoFinanceiro) {
        List<Categoria> categorias = getDao().buscarPorTipoFinanceiro(tipoFinanceiro);
        List<String> categoriasAux = new ArrayList<>();

        for (Categoria categoria : categorias) {
            categoriasAux.add(categoria.getNome());
        }

        categoriasAux.add("Categoria:");

        return categoriasAux;
    }

    public Categoria buscarPorTipoMovimentacao() {
        return getDao().buscarPorMovimentacao();
    }


    @Override
    protected CategoriaDAO getDao() {
        return (CategoriaDAO) super.getDao();
    }
}
