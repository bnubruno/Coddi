package coddi.com.br.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import coddi.com.br.App.CoddiApplication;
import coddi.com.br.App.Macetes;
import coddi.com.br.dao.ContaDAO;
import coddi.com.br.integracao.ContaTO;
import coddi.com.br.model.Conta;
import coddi.com.br.model.Fila;
import coddi.com.br.model.TipoIntegracao;
import coddi.com.br.model.TipoStatus;

/**
 * Created by Bruno on 02/02/2015.
 */
public class ContaBO extends AbstractBO<Conta, Long, ContaDAO> {

    public ContaBO(CoddiApplication context) throws SQLException {
        super(context, new ContaDAO(context.getBd().getConnectionSource()), "conta");
    }

    public List<Conta> buscarTodosAtivos() {
        return getDao().buscarTodosAtivos();
    }

    public List<Conta> buscarContasParaRecebimento() {
        return getDao().buscarContasRecebimento();
    }

    public List<Conta> buscarContasParaPagamento() {
        return getDao().buscarContasPagamento();
    }

    @Override
    public void incluir(Conta objeto) throws Exception {
        setaDefinicoesUsuario(objeto);
        super.incluir(objeto);

        Fila fila = new Fila();
        fila.setStatus(TipoStatus.ATIVO);
        fila.setDataInclusao(new Date());
        fila.setTipo(TipoIntegracao.CONTA);
        fila.setIntegrado(0);
        fila.setJson(getContext().getGson().toJson(toContaTO(objeto)));

        BOPool pool = BOPool.getInstance((coddi.com.br.App.CoddiApplication) getContext());

        pool.getFilaBO().incluir(fila);
    }

    private void setaDefinicoesUsuario(Conta objeto) {
        objeto.setIdUsuario(getContext().getUsuarioLogado().getId());
        objeto.setIdUsuarioChave(getContext().getUsuarioLogado().getId());
        objeto.setIdChave(objeto.getId());
    }

    private ContaTO toContaTO(Conta de) {
        ContaTO para = new ContaTO();

        para.setStatus(de.getStatus());
        para.setDataCadastro(Macetes.dateToString(de.getDataCadastro(), "yyyy-MM-dd'T'HH:mm:ss"));
        para.setIdChave(de.getIdChave());
        para.setIdUsuarioChave(de.getIdUsuarioChave());
        para.setIdUsuarioGerador(de.getIdUsuario());
        para.setNome(de.getNome());
        para.setTipoConta(de.getTipoConta());
        para.setVersion(de.getVersion());

        return para;
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

    public List<String> buscarContasParaRecebimentoString() {
        List<Conta> lista = getDao().buscarContasRecebimento();
        List<String> listaString = new ArrayList<>();

        for (Conta conta : lista) {
            listaString.add(conta.getString());
        }
        listaString.add("Destino:");

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
        return super.getDao();
    }
}
