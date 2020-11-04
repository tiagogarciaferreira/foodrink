$(document).ready(function() {
	mascaras();
	validaCadastroCliente();

});

function mascaras() {
	$("#cpf").mask("000.000.000-00");
	$("#telefone").mask("(00) 0 0000-0000");
	$("#cep").mask("00000-000");

	$("#telefone").blur(function(event) {
		if ($(this).val().length == 16) {
			$("#telefone").mask("(00) 0 0000-0000");
		} 
		else {
			$("#telefone").mask("(00) 0 000-00000");
		}
	});

}

function validaCadastroCliente() {
	var enderecoAtual = window.location.href;
	$("#form_novo_cliente").validate({
		rules : {
			fileImagem : {
				required : true,
				accept : "image/*",
				extension : "png|jpeg|jpg"
			},
			nome : {
				required : true
			},
			cpf : {
				required : true,
				cpfBR : true,
				remote : {
					url : "validarCpf",
					type : "GET",
					dataType : "json",
					data : {
						cpf : function() {
							return $("#cpf").val();
						}
					},
					dataFilter : function(data) {
						var resultado = JSON.parse(data);
						if (resultado.resposta == 'true' || enderecoAtual.includes("/FooDrink/estabelecimento/perfil/perfil-cliente")) {
							return true;
						} else {
							return '"Cpf j\u00e1 cadastrado"';
						}
					},
				}
			},
			email : {
				required : true,
				email : true,
				remote : {
					url : "validarEmail",
					type : "GET",
					dataType : "json",
					data : {
						email : function() {
							return $("#email").val();
						}
					},
					dataFilter : function(data) {
						var resultado = JSON.parse(data);
						if (resultado.resposta == 'true' || enderecoAtual.includes("/FooDrink/cliente/perfil/perfil-cliente")) {
							return true;
						} else {
							return '"Email j\u00e1 cadastrado"';
						}
					},
				}
			},
			senha : {
				required : true,
				minlength : 10
			},
			confirme_senha : {
				required : true,
				equalTo : "#senha"
			},
			telefone : {
				required : true,
				minlength : 15,
				maxlength : 16
			},
			'pais.idPais' : {
				campoSelect : true
			},
			'estado.idEstado' : {
				campoSelect : true
			},
			'cidade.idCidade' : {
				campoSelect : true
			},
			cep : {
				required : true,
				maxlength : 9,
				minlength : 9,
				remote : {
					url : "validarCep",
					type : "GET",
					dataType : "json",
					data : {
						cep : function() {
							return $("#cep").val();
						}
					},
					dataFilter : function(data) {
						var resultado = JSON.parse(data);
						if (resultado.resposta == 'true') {
							return true;
						} else {
							return '"Cep inv\u00e1lido"';
						}
					},
				}
			},
			bairro : {
				required : true
			},
			rua : {
				required : true
			},
			numero : {
				required : true
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	})
}

jQuery.validator.addMethod("campoSelect", function(value, element) {
	if (value == "Selecione") {
		return false;
	} else {
		return true;
	}

}, "Campo obrigat\u00f3rio")
