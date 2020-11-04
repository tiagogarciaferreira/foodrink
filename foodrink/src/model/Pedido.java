package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_pedido")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idPedido;
	private String data;
	private String hora;
	private String observacao;
	private String status;
	
	@Column(name = "valor_pedido")
	private BigDecimal valorPedido;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mesa_id")
	private Mesa mesa;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;
	
	@OneToMany(mappedBy="pedido", fetch = FetchType.EAGER)
	private List<ItemPedido> listaItens = new ArrayList<ItemPedido>();

	public Pedido() {
	}
	
	public Pedido(Integer idPedido, String data, String hora, String observacao, String status, BigDecimal valorPedido,	Integer mesaNumero, String empresa) {
		this.idPedido = idPedido;
		this.data = data;
		this.hora = hora;
		this.observacao = observacao;
		this.status = status;
		this.valorPedido = valorPedido;
		this.mesa = new Mesa(null, mesaNumero, null);
		this.empresa = new Empresa(new Usuario(empresa));
	}
	
	public Pedido(Integer idPedido, Integer mesa, String data, String hora, String status) {
		this.idPedido = idPedido;
		this.mesa = new Mesa(null, mesa, null);
		this.data = data;
		this.hora = hora;
		this.status = status;
	}

	public Pedido(Integer idPedido, BigDecimal valorPedido, String nomeEmpresa, String hora, Integer numeroMesa) {
		this.idPedido = idPedido;
		this.valorPedido = valorPedido;
		this.empresa = new Empresa(new Usuario(nomeEmpresa));
		this.hora = hora;
		this.mesa = new Mesa(null, numeroMesa, null);
	}

	public Pedido(Integer idPedido) {
		this.idPedido = idPedido;
	}
	
	public Pedido(Integer idPedido, Integer numeroMesa) {
		this.idPedido = idPedido;
		this.mesa = new Mesa(null, numeroMesa, null);
	}
	

	public Pedido(Integer numeroPedido, String observacao) {
		this.idPedido = numeroPedido;
		this.observacao = observacao;
	}

	public Integer getIdPedido() {
		return this.idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ItemPedido> getListaItens() {
		return listaItens;
	}

	public void setListaItens(List<ItemPedido> listaItens) {
		this.listaItens = listaItens;
	}

	public BigDecimal getValorPedido() {
		return this.valorPedido;
	}

	public void setValorPedido(BigDecimal valorPedido) {
		this.valorPedido = valorPedido.setScale(2, RoundingMode.HALF_UP);
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Mesa getMesa() {
		return this.mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPedido == null) ? 0 : idPedido.hashCode());
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
		Pedido other = (Pedido) obj;
		if (idPedido == null) {
			if (other.idPedido != null)
				return false;
		} else if (!idPedido.equals(other.idPedido))
			return false;
		return true;
	}

}