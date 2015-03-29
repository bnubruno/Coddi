package coddi.com.br.model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Bruno on 28/03/2015.
 */
public enum TipoFinanceiro {

    ENTRADA("E", "Entrada"), //
    SAÍDA("S", "Saída");

    private String id;
    private String descricao;

    TipoFinanceiro(String id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public String getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return id + " " + descricao;
    }

    public static List<TipoFinanceiro> getLista() {
        return Arrays.asList(TipoFinanceiro.values());
    }

    public static TipoFinanceiro converte(String tipoFinanceiro) {
        for (TipoFinanceiro tipo : getLista()) {
            if (tipo.getDescricao().equalsIgnoreCase(tipoFinanceiro)) {
                return tipo;
            }
        }
        return null;
    }

}
