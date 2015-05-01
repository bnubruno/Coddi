package coddi.com.br.controller;

import java.sql.SQLException;
import java.util.ArrayList;
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

    public List<Conta> buscarContasParaPagamento() {
        return getDao().buscarContasPagamento();
    }

    public List<String> buscarContasParaPagamentoString() {
        List<Conta> lista = getDao().buscarContasPagamento();
        List<String> listaString = new ArrayList<>();

        for (Conta conta : lista) {
            listaString.add(conta.getString());
        }
        listaString.add("Forma de pagamento:");

        return listaString;
    }

    public List<Conta> buscarContasSaqueOrigem() {
        List<Conta> lista = getDao().buscarContasSaqueOrigem();
        return lista;
    }

    public List<String> buscarContasSaqueOrigemString() {
        List<Conta> lista = buscarContasSaqueOrigem();
        List<String> listaString = new ArrayList<>();

        for (Conta conta : lista) {
            listaString.add(conta.getString());
        }
        listaString.add("Conta de origem:");

        return listaString;
    }


    public List<Conta> buscarContasTransferencia() {
        List<Conta> lista = getDao().buscarContasTransferencia();
        return lista;
    }

    public List<String> buscarContasTransferenciaOrigemString() {
        List<Conta> lista = buscarContasTransferencia();
        List<String> listaString = new ArrayList<>();

        for (Conta conta : lista) {
            listaString.add(conta.getString());
        }
        listaString.add("Conta de origem:");

        return listaString;
    }

    public List<String> buscarContasTransferenciaDestinoString() {
        List<Conta> lista = buscarContasTransferencia();
        List<String> listaString = new ArrayList<>();

        for (Conta conta : lista) {
            listaString.add(conta.getString());
        }
        listaString.add("Conta de destino:");

        return listaString;
    }

    public List<Conta> buscarContasSaqueDestino() {
        List<Conta> lista = getDao().buscarContasSaqueDestino();
        return lista;
    }

    public List<String> buscarContasSaqueDestinoString() {
        List<Conta> lista = buscarContasSaqueDestino();
        List<String> listaString = new ArrayList<>();

        for (Conta conta : lista) {
            listaString.add(conta.getString());
        }
        listaString.add("Conta de destino:");

        return listaString;
    }


    @Override
    protected ContaDAO getDao() {
        return (ContaDAO) super.getDao();
    }
}
