package model;

public class Semana {
	
	private String segunda;
	private String terca;
	private String quarta;
	private String quinta;
	private String sexta;
	private String sabado;
	private String domingo;

	public Semana() {}

	public Semana(String diasSemana) {
		String dias[] = diasSemana.split("@");
		this.segunda = dias[0];
		this.terca = dias[1];
		this.quarta = dias[2];
		this.quinta = dias[3];
		this.sexta = dias[4];
		this.sabado = dias[5];
		this.domingo = dias[6];
	}

	public String getSegunda() {
		return segunda;
	}

	public void setSegunda(String segunda) {
		this.segunda = segunda;
	}

	public String getTerca() {
		return terca;
	}

	public void setTerca(String terca) {
		this.terca = terca;
	}

	public String getQuarta() {
		return quarta;
	}

	public void setQuarta(String quarta) {
		this.quarta = quarta;
	}

	public String getQuinta() {
		return quinta;
	}

	public void setQuinta(String quinta) {
		this.quinta = quinta;
	}

	public String getSexta() {
		return sexta;
	}

	public void setSexta(String sexta) {
		this.sexta = sexta;
	}

	public String getSabado() {
		return sabado;
	}

	public void setSabado(String sabado) {
		this.sabado = sabado;
	}

	public String getDomingo() {
		return domingo;
	}

	public void setDomingo(String domingo) {
		this.domingo = domingo;
	}

	@Override
	public String toString() {
		return segunda + "@" + terca + "@" + quarta + "@" + quinta + "@" + sexta + "@" + sabado + "@" + domingo;
	}
}
