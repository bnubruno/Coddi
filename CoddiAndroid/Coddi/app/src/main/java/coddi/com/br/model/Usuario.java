package coddi.com.br.model;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Bruno on 01/02/2015.
 */
@DatabaseTable(tableName = "Usuario")
public class Usuario extends AbstractPojo {

    @Expose
    @DatabaseField(canBeNull = false)
    private String nome;

    @Expose
    @DatabaseField(canBeNull = false)
    private String sobrenome;

    @Expose
    @DatabaseField(canBeNull = false)
    private String email;

    @Expose
    @DatabaseField(canBeNull = false)
    private String senha;

    @DatabaseField(canBeNull = false)
    private Long codigoReferencia;

    private transient String senhaAux;

    public String getEmail() {
        return email;
    }

    public void setEmail(String login) {
        this.email = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getSenhaAux() {
        if (senhaAux == null) {
            senhaAux = senha;
        }
        return senhaAux;
    }

    public void setSenhaAux(String senhaAux) {
        this.senhaAux = senhaAux;
    }

    public Long getCodigoReferencia() {
        return codigoReferencia;
    }

    public void setCodigoReferencia(Long codigoReferencia) {
        this.codigoReferencia = codigoReferencia;
    }
}
