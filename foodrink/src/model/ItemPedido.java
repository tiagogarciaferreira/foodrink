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
import javax.persistence.Transient;

@Entity
@Table(name = "item_pedido")
public class ItemPedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_item_pedido")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idItemPedido;

	private Integer quantidade;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;

	/* ATRIBUTO USADO APENAS PARA CONVERÇÃO DO JSON PARA OBJETO */
	@Transient
	private String listaItensJsonPedido;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "produto_id")
	private Produto produto;
	
	public ItemPedido() {
	}

	public ItemPedido(Pedido pedido, Produto produto, Integer quantidade) {
		this.pedido = pedido;
		this.produto = produto;
		this.quantidade = quantidade;
	}
	
	public ItemPedido(Integer quantidade, String produto, String tamanho) {
		this.quantidade = quantidade;
		this.produto = new Produto(null,produto, tamanho);
	}
	
	public ItemPedido(Integer quantidade, String imagem, String produto, String tamanho) {
		this.quantidade = quantidade;
		this.produto = new Produto(imagem,produto, tamanho);
	}

	public ItemPedido(String listaItensJsonPedido) {
		this.listaItensJsonPedido = listaItensJsonPedido;
	}

	public ItemPedido(Integer quantidade, Produto produto) {
		this.quantidade = quantidade;
		this.produto = produto;
	}

	public ItemPedido(Integer idItemPedido, Integer quantidade) {
		this.idItemPedido = idItemPedido;
		this.quantidade = quantidade;
	}

	public ItemPedido(Integer idItemPedido) {
		this.idItemPedido = idItemPedido;
	}
	

	public Integer getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getListaItensJsonPedido() {
		return listaItensJsonPedido;
	}

	public void setListaItensJsonPedido(String listaItensJsonPedido) {
		this.listaItensJsonPedido = listaItensJsonPedido;
	}

	public Integer getIdItemPedido() {
		return idItemPedido;
	}

	public void setIdItemPedido(Integer idItemPedido) {
		this.idItemPedido = idItemPedido;
	}

	public Pedido getPedido() {
		return this.pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idItemPedido == null) ? 0 : idItemPedido.hashCode());
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
		ItemPedido other = (ItemPedido) obj;
		if (idItemPedido == null) {
			if (other.idItemPedido != null)
				return false;
		} else if (!idItemPedido.equals(other.idItemPedido))
			return false;
		return true;
	}

}