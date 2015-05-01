package coddi.com.br.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Bruno on 28/03/2015.
 */
public enum TipoOperacao {

    PAGAMENTO(1, "Pagamento"), //
    SAQUE(2, "Saque"),
    TRANSFERENCIA(3, "Transferencia"),
    RECEBIMENTO(4, "Recebimento");

    private Integer id;
    private String descricao;

    TipoOperacao(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return id + " - " + descricao;
    }

    public static List<TipoOperacao> getLista() {
        List<TipoOperacao> lista = Arrays.asList(TipoOperacao.values());
        List<TipoOperacao> nova = new ArrayList<>();

        for (TipoOperacao tipo : lista) {
            nova.add(tipo);
        }
        return nova;
    }

    public static TipoOperacao converte(String tipoFinanceiro) {
        for (TipoOperacao tipo : getLista()) {
            if (tipo.toString().equalsIgnoreCase(tipoFinanceiro)) {
                return tipo;
            }
        }
        return null;
    }

    public static List<String> getListaString() {
        List<String> listaAux = new ArrayList<>();

        for (TipoOperacao tipo : getLista()) {
            listaAux.add(tipo.toString());
        }

        listaAux.add("Tipo:");

        return listaAux;
    }

}
