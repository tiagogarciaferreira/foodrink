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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import model.TipoAcesso;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_usuario")
	private Integer idUsuario;

	private String imagem;
	private String nome;
	private String email;
	private String senha;
	@Transient
	private String chaveStatus;
	private Boolean status;
	private String telefone;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipo_acesso_id")
	private TipoAcesso tipoAcesso;
	
	@OneToOne(mappedBy="usuario",fetch = FetchType.EAGER)
	private Cliente cliente;

	@OneToOne(mappedBy="usuario",fetch = FetchType.EAGER)
	private Empresa empresa;

	@OneToOne(mappedBy="usuario",fetch = FetchType.EAGER)
	private Endereco endereco;

	@OneToOne(mappedBy="usuario",fetch = FetchType.EAGER)
	private Funcionario funcionario;

	public Usuario() {}
	
	public Usuario(String imagem, String nome, String telefone) {
		this.imagem = imagem;
		this.nome = nome;
		this.telefone = telefone;
	}
	
	public Usuario(String nome, Integer empresaID) {
		this.empresa = new Empresa(empresaID);
		this.nome = nome;
	}

	public Usuario(String nome) {
		this.nome = nome;
	}
	
	public Usuario(Integer idUsuario, String imagem, String nome, String email, String nomeAcesso) {
		this.idUsuario = idUsuario;
		this.imagem = imagem;
		this.nome = nome;
		this.email = email;
		this.tipoAcesso = new TipoAcesso(nomeAcesso);
	}
	
	public Usuario(String nome, TipoAcesso tipoAcesso) {
		this.nome = nome;
		this.tipoAcesso = tipoAcesso;
	}
	
	public Usuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getChaveStatus() {
		return chaveStatus;
	}

	public void setChaveStatus(String chaveStatus) {
		this.chaveStatus = chaveStatus;
	}


	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public TipoAcesso getTipoAcesso() {
		return this.tipoAcesso;
	}

	public void setTipoAcesso(TipoAcesso tipoAcesso) {
		this.tipoAcesso = tipoAcesso;
	}

	public Cliente getCliente() {
		return cliente;
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
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
		Usuario other = (Usuario) obj;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		return true;
	}

}