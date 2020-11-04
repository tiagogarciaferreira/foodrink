package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mesa")
public class Mesa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_mesa")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idMesa;

	private Integer lugares;
	private Integer numero;
	private String descricao;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;
	
	public Mesa() {
	}

	public Mesa(Integer idMesa, Integer numero, Integer lugares, String descricao) {
		this.idMesa = idMesa;
		this.numero = numero;
		this.lugares = lugares;
		this.descricao = descricao;
	}

	public Mesa(Integer idMesa) {
		this.idMesa = idMesa;
	}

	public Mesa(Integer idMesa, Integer numero, Integer lugares) {
		this.idMesa = idMesa;
		this.numero = numero;
		this.lugares = lugares;
	}
	
	public Mesa(Integer idMesa, Integer numero) {
		this.idMesa = idMesa;
		this.numero = numero;
	}

	public Integer getIdMesa() {
		return this.idMesa;
	}

	public void setIdMesa(Integer idMesa) {
		this.idMesa = idMesa;
	}

	public Integer getLugares() {
		return this.lugares;
	}

	public void setLugares(Integer lugares) {
		this.lugares = lugares;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idMesa == null) ? 0 : idMesa.hashCode());
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
		Mesa other = (Mesa) obj;
		if (idMesa == null) {
			if (other.idMesa != null)
				return false;
		} else if (!idMesa.equals(other.idMesa))
			return false;
		return true;
	}

}