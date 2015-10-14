package coddi.com.br.model;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Bruno on 03/04/2015.
 */
public abstract class AbstractPojo {

    @Expose
    @DatabaseField(generatedId = true)
    protected Long id;

    @Expose
    @DatabaseField
    protected Long idUsuario;

    @Expose
    @DatabaseField
    protected Long idUsuarioChave;

    @Expose
    @DatabaseField
    protected Long idChave;

    @Expose
    @DatabaseField(dataType = DataType.ENUM_INTEGER)
    protected TipoStatus status = TipoStatus.ATIVO;

    @Expose
    @DatabaseField
    protected int version;

    @DatabaseField
    protected int integrado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStatus(TipoStatus status) {
        this.status = status;
    }

    public TipoStatus getStatus() {
        return status;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getIntegrado() {
        return integrado;
    }

    public void setIntegrado(int integrado) {
        this.integrado = integrado;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
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
}
