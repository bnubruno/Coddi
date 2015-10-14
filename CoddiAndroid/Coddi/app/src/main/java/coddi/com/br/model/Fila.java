package coddi.com.br.model;

import com.j256.ormlite.field.DatabaseField;

import java.util.Date;

/**
 * Created by Bruno on 20/07/2015.
 */
public class Fila extends AbstractPojo {

    @DatabaseField
    private String json;
    @DatabaseField
    private TipoIntegracao tipo;
    @DatabaseField
    private Date dataInclusao;
    @DatabaseField
    private Date dataEnvio;

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public Date getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public TipoIntegracao getTipo() {
        return tipo;
    }

    public void setTipo(TipoIntegracao tipo) {
        this.tipo = tipo;
    }
}
