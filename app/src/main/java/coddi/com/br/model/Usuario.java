package coddi.com.br.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Bruno on 01/02/2015.
 */
@DatabaseTable(tableName = "Usuario")
public class Usuario extends AbstractPojo {
    @DatabaseField(canBeNull = false)
    private String login;

    @DatabaseField(canBeNull = false)
    private String senha;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
