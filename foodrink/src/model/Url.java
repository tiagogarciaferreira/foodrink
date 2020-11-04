package model;

public class Url {

	private String id;
	private String tipo;
	private String validade;

	public Url() {
	}

	public Url(String id, String tipo, String validade) {
		this.id = id;
		this.tipo = tipo;
		this.validade = validade;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}

}
