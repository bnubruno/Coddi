package coddi.com.br.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Bruno on 28/03/2015.
 */
public enum TipoFinanceiro {

    ENTRADA(1, "Entrada"), //
    SAIDA(2, "Saída"),
    AMBOS(3, "Ambos"),
    MOVIMENTACAO(4, "Movimentação");



    private Integer id;
    private String descricao;

    TipoFinanceiro(Integer id, String descricao) {
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

    public static List<TipoFinanceiro> getLista() {
        List<TipoFinanceiro> lista = Arrays.asList(TipoFinanceiro.values());
        List<TipoFinanceiro> nova = new ArrayList<>();

        for (TipoFinanceiro tipo : lista){
            if(tipo != MOVIMENTACAO){
                nova.add(tipo);
            }
        }


        return nova;
    }

    public static TipoFinanceiro converte(String tipoFinanceiro) {
        for (TipoFinanceiro tipo : getLista()) {
            if (tipo.toString().equalsIgnoreCase(tipoFinanceiro)) {
                return tipo;
            }
        }
        return null;
    }

    public static List<String> getListaString() {
        List<String> listaAux = new ArrayList<>();

        for (TipoFinanceiro tipo : getLista()) {
            listaAux.add(tipo.toString());
        }

        listaAux.add("Tipo:");

        return listaAux;
    }

}
