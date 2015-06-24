package coddi.com.br.controller;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import coddi.com.br.App.CoddiApplication;
import coddi.com.br.dao.CategoriaDAO;
import coddi.com.br.dao.ContaDAO;
import coddi.com.br.dao.LancamentoDAO;
import coddi.com.br.model.Categoria;
import coddi.com.br.model.Conta;
import coddi.com.br.model.Lancamento;
import coddi.com.br.model.TipoFinanceiro;
import coddi.com.br.model.TipoOperacao;

/**
 * Created by Bruno on 12/04/2015.
 */
public class LancamentoBO extends AbstractBO<Lancamento, Long> {

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

    public BigDecimal buscarSaldoConta(Long idConta) {
        List<Lancamento> lancamentos = getDao().buscarLancamentosPorConta(idConta);

        BigDecimal saldo = BigDecimal.ZERO;

        for (Lancamento lancamento : lancamentos) {
            if (lancamento.getTipoFinanceiro() == TipoFinanceiro.ENTRADA) {
                saldo = saldo.add(lancamento.getValor());
            } else {
                saldo = saldo.subtract(lancamento.getValor());
            }
        }

        return saldo;
    }

    @Override
    public void incluir(Lancamento lancamento) throws Exception {
        BigDecimal saldoDisponivel = BOPool.getInstance(getContext()).getLancamentoBO().buscarSaldoConta(lancamento.getIdConta());

        if (lancamento.getTipoFinanceiro() == TipoFinanceiro.SAIDA && saldoDisponivel.compareTo(lancamento.getValor()) < 0) {
            throw new Exception("Ops! Conta não possui saldo disponível. Seu saldo é de R$ " + saldoDisponivel.toString() + ".");
        }

        super.incluir(lancamento);
    }


    @Override
    protected LancamentoDAO getDao() {
        return (LancamentoDAO) super.getDao();
    }
}
