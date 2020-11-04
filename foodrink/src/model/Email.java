package model;

import java.util.List;

public class Email{

	private List<String> destinatario;
	private String assunto;
	private String mensagem;
	
	public Email() {}
	
	public Email(List<String> destinatario, String assunto, String mensagem) {
		this.destinatario = destinatario;
		this.assunto = assunto;
		this.mensagem = mensagem;
	}
	
	public List<String> getDestinatario() {
		return destinatario;
	}
	public String getAssunto() {
		return assunto;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setDestinatario(List<String> destinatario) {
		this.destinatario = destinatario;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
