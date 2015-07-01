package coddi.com.br.model;

import java.math.BigDecimal;

/**
 * Created by Bruno on 27/06/2015.
 */
public class ResultadoMensal {

    private String data;
    private BigDecimal saldo;
    private BigDecimal receitas;
    private BigDecimal despesas;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public BigDecimal getDespesas() {
        if (despesas == null) {
            despesas = BigDecimal.ZERO;
        }
        return despesas;
    }

    public void setDespesas(BigDecimal despesas) {
        this.despesas = despesas;
    }

    public BigDecimal getReceitas() {
        if (receitas == null) {
            receitas = BigDecimal.ZERO;
        }
        return receitas;
    }

    public void setReceitas(BigDecimal receitas) {
        this.receitas = receitas;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}
