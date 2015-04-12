package coddi.com.br.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Bruno on 04/04/2015.
 */
public enum TipoConta {
    POUPANCA(1, "Poupança"), //
    CORRENTE(2, "Conta-corrente"),
    CARTAO_CREDITO(3, "Cartão de crédito"),
    CARTAO_DEBITO(4, "Cartão de débito");

    private Integer id;
    private String descricao;

    TipoConta(Integer id, String descricao) {
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

    public static List<TipoConta> getLista() {
        return Arrays.asList(TipoConta.values());
    }

    public static TipoConta converte(String tipoFinanceiro) {
        for (TipoConta tipo : getLista()) {
            if (tipo.toString().equalsIgnoreCase(tipoFinanceiro)) {
                return tipo;
            }
        }
        return null;
    }

    public static List<String> getListaString() {
        List<String> listaAux = new ArrayList<>();

        for (TipoConta tipo : getLista()) {
            listaAux.add(tipo.toString());
        }

        listaAux.add("Tipo da conta:");

        return listaAux;
    }

}
