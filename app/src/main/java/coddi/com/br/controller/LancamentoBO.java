package coddi.com.br.controller;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import coddi.com.br.App.CoddiApplication;
import coddi.com.br.App.Macetes;
import coddi.com.br.dao.CategoriaDAO;
import coddi.com.br.dao.ContaDAO;
import coddi.com.br.dao.LancamentoDAO;
import coddi.com.br.model.Categoria;
import coddi.com.br.model.Conta;
import coddi.com.br.model.Lancamento;
import coddi.com.br.model.ResultadoMensal;
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

    public List<ResultadoMensal> buscarResultados() {
        List<ResultadoMensal> listaResultado = new ArrayList<>();

        List<Lancamento> entradas = getDao().buscarLancamentoPorTipoFinanceiro(TipoFinanceiro.ENTRADA);
        List<Lancamento> saidas = getDao().buscarLancamentoPorTipoFinanceiro(TipoFinanceiro.SAIDA);

        Map<String, ResultadoMensal> mapResultado = new HashMap<>();

        for (Lancamento entrada : entradas) {
            String data = Macetes.dateToString(entrada.getData(), "MM/yyyy");
            if (mapResultado.containsKey(data)) {
                ResultadoMensal resultado = mapResultado.get(data);
                resultado.setReceitas(resultado.getReceitas().add(entrada.getValor()));
            } else {
                ResultadoMensal resultado = new ResultadoMensal();
                resultado.setData(data);
                resultado.setReceitas(entrada.getValor());
                mapResultado.put(data, resultado);
            }
        }

        for (Lancamento saida : saidas) {
            String data = Macetes.dateToString(saida.getData(), "MM/yyyy");
            if (mapResultado.containsKey(data)) {
                ResultadoMensal resultado = mapResultado.get(data);
                resultado.setDespesas(resultado.getDespesas().add(saida.getValor()));
            } else {
                ResultadoMensal resultado = new ResultadoMensal();
                resultado.setData(data);
                resultado.setDespesas(saida.getValor());
                mapResultado.put(data, resultado);
            }
        }

        for (Map.Entry<String, ResultadoMensal> entry : mapResultado.entrySet()) {
            String data = entry.getKey();
            ResultadoMensal resultado = entry.getValue();
            resultado.setSaldo(resultado.getReceitas().subtract(resultado.getDespesas()));
            listaResultado.add(resultado);
        }

        return listaResultado;
    }


    @Override
    protected LancamentoDAO getDao() {
        return (LancamentoDAO) super.getDao();
    }
}
