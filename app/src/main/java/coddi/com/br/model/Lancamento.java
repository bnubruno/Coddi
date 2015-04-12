package coddi.com.br.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Bruno on 12/04/2015.
 */
@DatabaseTable(tableName = "Lancamento")
public class Lancamento extends AbstractPojo {

    @DatabaseField(width = 40)
    private String descricao;

    @DatabaseField
    private TipoFinanceiro tipoFinanceiro;

    @DatabaseField
    private Date data;

    @DatabaseField
    private Date dataCadastro;

    @DatabaseField
    private BigDecimal valor;

    @DatabaseField
    private Long idCategoria;

    @DatabaseField
    private Long idContaOrigem;

    @DatabaseField
    private Long idContaDestino;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoFinanceiro getTipoFinanceiro() {
        return tipoFinanceiro;
    }

    public void setTipoFinanceiro(TipoFinanceiro tipoFinanceiro) {
        this.tipoFinanceiro = tipoFinanceiro;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Long getIdContaOrigem() {
        return idContaOrigem;
    }

    public void setIdContaOrigem(Long idContaOrigem) {
        this.idContaOrigem = idContaOrigem;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Long getIdContaDestino() {
        return idContaDestino;
    }

    public void setIdContaDestino(Long idContaDestino) {
        this.idContaDestino = idContaDestino;
    }
}
