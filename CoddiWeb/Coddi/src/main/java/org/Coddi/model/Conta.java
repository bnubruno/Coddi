package org.Coddi.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.lang.Override;
import javax.persistence.Enumerated;
import org.Coddi.model.TipoConta;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.Coddi.model.TipoStatus;

@Entity
@XmlRootElement
public class Conta implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = "version")
	private int version;

	@Column
	private String nome;

	@Enumerated
	private TipoConta tipoConta;

	@Column
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;

	@Column
	private Long idUsuarioGerador;

	@Column
	private Long idChave;

	@Column
	private Long idUsuarioChave;

	@Enumerated
	private TipoStatus status;

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Conta)) {
			return false;
		}
		Conta other = (Conta) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
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

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Long getIdUsuarioGerador() {
		return idUsuarioGerador;
	}

	public void setIdUsuarioGerador(Long idUsuarioGerador) {
		this.idUsuarioGerador = idUsuarioGerador;
	}

	public Long getIdChave() {
		return idChave;
	}

	public void setIdChave(Long idChave) {
		this.idChave = idChave;
	}

	public Long getIdUsuarioChave() {
		return idUsuarioChave;
	}

	public void setIdUsuarioChave(Long idUsuarioChave) {
		this.idUsuarioChave = idUsuarioChave;
	}

	public TipoStatus getStatus() {
		return status;
	}

	public void setStatus(TipoStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (id != null)
			result += "id: " + id;
		result += ", version: " + version;
		if (nome != null && !nome.trim().isEmpty())
			result += ", nome: " + nome;
		if (tipoConta != null)
			result += ", tipoConta: " + tipoConta;
		if (dataCadastro != null)
			result += ", dataCadastro: " + dataCadastro;
		if (idUsuarioGerador != null)
			result += ", idUsuarioGerador: " + idUsuarioGerador;
		if (idChave != null)
			result += ", idChave: " + idChave;
		if (idUsuarioChave != null)
			result += ", idUsuarioChave: " + idUsuarioChave;
		if (status != null)
			result += ", status: " + status;
		return result;
	}
}