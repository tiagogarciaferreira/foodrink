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
@Table(name = "preparo")
public class Preparo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_preparo")
	private Integer idPreparo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "item_pedido_id")
	private ItemPedido itemPedido;
	
	private String status;

	public Preparo() {
	}

	public Preparo(Integer idPreparo, ItemPedido itemPedido, String status) {
		this.idPreparo = idPreparo;
		this.itemPedido = itemPedido;
		this.status = status;
	}
	
	public Preparo(ItemPedido itemPedido, String status) {
		this.itemPedido = itemPedido;
		this.status = status;
	}
	
	public Preparo(Integer idPreparo, Integer numeroPedido, String nomeProduto, String tamanho, Integer quantidade, String observacao) {
		this.idPreparo = idPreparo;
		this.itemPedido = new ItemPedido(new Pedido(numeroPedido, observacao), new Produto(null, nomeProduto, tamanho), quantidade);
	}
	
	public Preparo(Integer idPreparo, Integer numeroPedido, String nomeProduto, String tamanho, Integer quantidade, Integer numeroMesa, String status ) {
		this.idPreparo = idPreparo;
		this.itemPedido = new ItemPedido(new Pedido(numeroPedido, numeroMesa), new Produto(null,nomeProduto, tamanho), quantidade);
		this.status = status;
	}
	
	public Preparo(Integer idPreparo) {
		this.idPreparo = idPreparo;
	}

	public Integer getIdPreparo() {
		return idPreparo;
	}

	public void setIdPreparo(Integer idPreparo) {
		this.idPreparo = idPreparo;
	}

	public ItemPedido getItemPedido() {
		return itemPedido;
	}

	public void setItemPedido(ItemPedido itemPedido) {
		this.itemPedido = itemPedido;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPreparo == null) ? 0 : idPreparo.hashCode());
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
		Preparo other = (Preparo) obj;
		if (idPreparo == null) {
			if (other.idPreparo != null)
				return false;
		} else if (!idPreparo.equals(other.idPreparo))
			return false;
		return true;
	}

}
