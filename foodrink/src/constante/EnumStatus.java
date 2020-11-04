package constante;

public enum EnumStatus {
	
	INVALIDO("INVALIDO"), 
	VALIDO("VALIDO"),
	
	ATIVA("ATIVA"), 
	INATIVA("INATIVA"),
	
	DISPONIVEL("DISPONIVEL"), 
	INDISPONIVEL("INDISPONIVEL"),
	
	RESERVADA("RESERVADA"), 
	OCUPADA("OCUPADA"),
	
	EM_ANDAMENTO("EM ANDAMENTO"),
	FINALIZADO("FINALIZADO"),
	
	PENDENTE("PENDENTE"),
	CONFIRMADA("CONFIRMADA"),
	CANCELADA("CANCELADA"),
	FINALIZADA("FINALIZADA"),
	
	PRONTO("PRONTO"),
	PREPARANDO("PREPARANDO"),
	ENTREGUE("ENTREGUE");
	
	private String value;

	EnumStatus(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}
}
