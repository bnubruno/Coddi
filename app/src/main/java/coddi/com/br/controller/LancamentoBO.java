package coddi.com.br.controller;

import java.sql.SQLException;
import java.util.List;

import coddi.com.br.App.CoddiApplication;
import coddi.com.br.dao.CategoriaDAO;
import coddi.com.br.dao.ContaDAO;
import coddi.com.br.dao.LancamentoDAO;
import coddi.com.br.model.Categoria;
import coddi.com.br.model.Conta;
import coddi.com.br.model.Lancamento;
import coddi.com.br.model.TipoOperacao;

/**
 * Created by Bruno on 12/04/2015.
 */
public class LancamentoBO extends AbstractBO {

    private ContaDAO contaDAO;
    private CategoriaDAO categoriaDAO;

    public LancamentoBO(CoddiApplication context) throws SQLException {
        super(context, new LancamentoDAO(context.getBd().getConnectionSource()), "lancamento");
        contaDAO = new ContaDAO(context.getBd().getConnectionSource());
        categoriaDAO = new CategoriaDAO(context.getBd().getConnectionSource());
    }

    public List<Lancamento> buscarLancamentosPagamento(TipoOperacao tipoOperacao) {
        List<Lancamento> lancamentos = getDao().buscarLancamentoPorTipoOperacao(tipoOperacao);

        for (Lancamento lancamento : lancamentos) {
            Categoria categoria = categoriaDAO.buscarPorId(lancamento.getIdCategoria());
            Conta conta = contaDAO.buscarPorId(lancamento.getIdConta());
            lancamento.setCategoria(categoria);
            lancamento.setConta(conta);
        }

        return lancamentos;
    }


    @Override
    protected LancamentoDAO getDao() {
        return (LancamentoDAO) super.getDao();
    }
}
