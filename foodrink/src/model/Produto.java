package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
@Table(name = "produto")
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_produto")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idProduto;

	private String descricao;
	private String imagem;
	private String nome;
	private BigDecimal preco;
	private Boolean status;
	@Transient
	private String chaveStatus;
	private String tamanho;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	public Produto() {
	}

	public Produto(Integer idProduto, BigDecimal preco) {
		this.idProduto = idProduto;
		this.preco = preco;
	}
	
	public Produto(String nome, String tamanho, BigDecimal preco) {
		this.nome = nome;
		this.tamanho = tamanho;
		this.preco = preco;
	}

	public Produto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public Produto(String imagem, String nomeProduto, String tamanho) {
		this.imagem = imagem;
		this.nome = nomeProduto;
		this.tamanho = tamanho;
	}

	public Integer getIdProduto() {
		return this.idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getImagem() {
		return this.imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return this.preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco.setScale(2, RoundingMode.HALF_UP);
	}

	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getChaveStatus() {
		return chaveStatus;
	}

	public void setChaveStatus(String chaveStatus) {
		this.chaveStatus = chaveStatus;
	}

	public String getTamanho() {
		return this.tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProduto == null) ? 0 : idProduto.hashCode());
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
		Produto other = (Produto) obj;
		if (idProduto == null) {
			if (other.idProduto != null)
				return false;
		} else if (!idProduto.equals(other.idProduto))
			return false;
		return true;
	}

}