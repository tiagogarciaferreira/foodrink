package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "permissao")
public class Permissao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_permissao")
	private Integer idPermissao;

	private String nome;

	@ManyToMany(mappedBy = "permissaos", fetch = FetchType.LAZY)
	private List<TipoAcesso> tipoAcessos;

	public Permissao() {
	}

	public Integer getIdPermissao() {
		return this.idPermissao;
	}

	public void setIdPermissao(Integer idPermissao) {
		this.idPermissao = idPermissao;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<TipoAcesso> getTipoAcessos() {
		return this.tipoAcessos;
	}

	public void setTipoAcessos(List<TipoAcesso> tipoAcessos) {
		this.tipoAcessos = tipoAcessos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPermissao == null) ? 0 : idPermissao.hashCode());
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
		Permissao other = (Permissao) obj;
		if (idPermissao == null) {
			if (other.idPermissao != null)
				return false;
		} else if (!idPermissao.equals(other.idPermissao))
			return false;
		return true;
	}

}