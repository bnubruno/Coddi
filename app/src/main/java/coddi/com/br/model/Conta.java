package coddi.com.br.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by Bruno on 04/04/2015.
 */
@DatabaseTable(tableName = "Conta")
public class Conta extends AbstractPojo {

    @DatabaseField
    private String nome;

    @DatabaseField(dataType = DataType.ENUM_INTEGER)
    private TipoConta tipoConta;

    @DatabaseField
    private Date dataCadastro;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public String getString() {
        switch (getTipoConta()) {
            case CARTAO_CREDITO:
                return "Cartão de crédito (" + getNome() + ")";
            case CARTAO_DEBITO:
                return "Cartão de débito (" + getNome() + ")";
            case CARTEIRA:
                return "Dinheiro (" + getNome() + ")";
            case CORRENTE:
                return "Conta corrente (" + getNome() + ")";
            case POUPANCA:
                return "Conta poupança (" + getNome() + ")";
        }
        return nome;
    }

    @Override
    public String toString() {
        return tipoConta.getDescricao() + "(" + getNome() + ")";
    }
}
