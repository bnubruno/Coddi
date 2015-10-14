package coddi.com.br.integracao;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

import coddi.com.br.model.TipoConta;
import coddi.com.br.model.TipoStatus;


public class ContaTO implements Serializable {

    @Expose
    private Long id;

    @Expose
    private TipoStatus status = TipoStatus.ATIVO;

    @Expose
    private int version;

    @Expose
    private String nome;

    @Expose
    private TipoConta tipoConta;

    @Expose
    private String dataCadastro;

    @Expose
    private Long idUsuarioChave;

    @Expose
    private Long idChave;

    @Expose
    private Long idUsuarioGerador;

    public Long getIdUsuarioGerador() {
        return idUsuarioGerador;
    }

    public void setIdUsuarioGerador(Long idUsuarioGerador) {
        this.idUsuarioGerador = idUsuarioGerador;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoStatus getStatus() {
        return status;
    }

    public void setStatus(TipoStatus status) {
        this.status = status;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }


    public Long getIdUsuarioChave() {
        return idUsuarioChave;
    }

    public void setIdUsuarioChave(Long idUsuarioChave) {
        this.idUsuarioChave = idUsuarioChave;
    }

    public Long getIdChave() {
        return idChave;
    }

    public void setIdChave(Long idChave) {
        this.idChave = idChave;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
