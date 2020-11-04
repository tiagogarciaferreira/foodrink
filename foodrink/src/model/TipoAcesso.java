package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "tipo_acesso")
public class TipoAcesso implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_tipo_acesso")
	private Integer idTipoAcesso;

	private String nome;

	@ManyToMany
	@JoinTable(name = "tipo_acesso_permissao", joinColumns = {
			@JoinColumn(name = "tipo_acesso_id") }, inverseJoinColumns = { @JoinColumn(name = "permissao_id") })

	private List<Permissao> permissaos;

	public TipoAcesso() {
	}

	public TipoAcesso(String nome) {
		this.nome = nome;
	}

	public TipoAcesso(Integer idTipoAcesso) {
		this.idTipoAcesso = idTipoAcesso;
	}

	public Integer getIdTipoAcesso() {
		return this.idTipoAcesso;
	}

	public void setIdTipoAcesso(Integer idTipoAcesso) {
		this.idTipoAcesso = idTipoAcesso;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Permissao> getPermissaos() {
		return this.permissaos;
	}

	public void setPermissaos(List<Permissao> permissaos) {
		this.permissaos = permissaos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTipoAcesso == null) ? 0 : idTipoAcesso.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoAcesso other = (TipoAcesso) obj;
		if (idTipoAcesso == null) {
			if (other.idTipoAcesso != null)
				return false;
		} else if (!idTipoAcesso.equals(other.idTipoAcesso))
			return false;
		return true;
	}

}