package constante;

public enum EnumEmail{

	REMETENTE("servico.automatico.foodrink@gmail.com"),
	SENHA_REMETENTE("servico.automatico.foodrink007"),
	
	CONTATO_DESTINO("foodrink.br@gmail.com"),
	
	CONFIRMACAO_CADASTRO("Confirmação de Cadastro"),
	REDEFINICAO_SENHA("Redefinição de Senha"),
	PEDIDO_FINALIZADO("Pedido Finalizado"),
	RESERVA_CANCELADA("Reserva Cancelada"),
	RESERVA_CONFIRMADA("Reserva Confirmada"),
	DADOS_ACESSO("Dados de Acesso"),
	CONTATO("Contato");

	private String value;

	EnumEmail(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}
}

