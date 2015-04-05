package coddi.com.br.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Bruno on 03/04/2015.
 */
public abstract class AbstractPojo {

    @DatabaseField(generatedId = true)
    protected Long id;

    @DatabaseField(dataType = DataType.ENUM_INTEGER)
    protected TipoStatus status = TipoStatus.ATIVO;

    @DatabaseField
    protected int version;

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


}
