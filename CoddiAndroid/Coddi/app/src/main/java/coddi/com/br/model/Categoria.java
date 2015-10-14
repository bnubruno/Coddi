package coddi.com.br.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Bruno on 02/02/2015.
 */
@DatabaseTable(tableName = "Categoria")
public class Categoria extends AbstractPojo {

    @DatabaseField
    private String nome;

    @DatabaseField(dataType = DataType.ENUM_INTEGER)
    private TipoFinanceiro tipoFinanceiro;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoFinanceiro getTipoFinanceiro() {
        return tipoFinanceiro;
    }

    public void setTipoFinanceiro(TipoFinanceiro tipoFinanceiro) {
        this.tipoFinanceiro = tipoFinanceiro;
    }


}
