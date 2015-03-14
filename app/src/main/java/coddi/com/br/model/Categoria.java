package coddi.com.br.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Bruno on 02/02/2015.
 */
@DatabaseTable(tableName = "Categoria")
public class Categoria {

    @DatabaseField(id = true)
    private Long id;
    @DatabaseField
    private String nome;
    @DatabaseField
    private int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
