package coddi.com.br.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Bruno on 04/04/2015.
 */
public enum TipoStatus {

    ATIVO(1, "Ativo"),
    INATIVO(2, "Inativo");

    private Integer id;
    private String descricao;

    TipoStatus(Integer id, String descricao) {
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

    public static List<TipoStatus> getLista() {
        return Arrays.asList(TipoStatus.values());
    }

    public static TipoStatus converte(String tipoFinanceiro) {
        for (TipoStatus tipo : getLista()) {
            if (tipo.toString().equalsIgnoreCase(tipoFinanceiro)) {
                return tipo;
            }
        }
        return null;
    }

    public static List<String> getListaString() {
        List<String> listaAux = new ArrayList<>();

        for (TipoStatus tipo : getLista()) {
            listaAux.add(tipo.toString());
        }

        listaAux.add("Tipo:");

        return listaAux;
    }

}
