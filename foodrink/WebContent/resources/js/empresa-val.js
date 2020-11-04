$(document).ready(function() {
	mascaras();
	validaCadastroEmpresa();

});

function mascaras() {
	$("#cnpj").mask("00.000.000/0000-00");
	$("#cep").mask("00000-000");
	$("#telefone").mask("(00) 0 0000-0000");
	$("#telefone").blur(function(event) {
		if ($(this).val().length == 16) {
			$("#telefone").mask("(00) 0 0000-0000");
		} 
		else {
			$("#telefone").mask("(00) 0 000-00000");
		}
	});
}

function validaCadastroEmpresa() {
	var enderecoAtual = window.location.href;
	$("#form_nova_empresa").validate({
		rules : {
			fileImagem : {
				required : true,
				accept: "image/*",
				extension : "png|jpeg|jpg"
			},
			nome : {
				required : true
			},
			cnpj : {
				required : true,
				minlength : 18,
				maxlength : 18,
				remote : {
					 url : "validarCnpj",
					 type : "GET",
					 dataType : "json",
					 data : {cnpj:function(){return $("#cnpj").val();}
					 },
					 dataFilter : function(data) {
						 var resultado = JSON.parse(data);
						 if (resultado.resposta == 'true' || enderecoAtual.includes("/FooDrink/estabelecimento/perfil/perfil-empresa")) {
							 return true;
						 }else if(resultado.resposta == '0'){
							 return '"Cnpj inv\u00e1lido"';
						 }
						 else {
							 return '"Cnpj j\u00e1 cadastrado"';
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
					 data : {email:function(){return $("#email").val();}
					 },
					 dataFilter : function(data) {
						 var resultado = JSON.parse(data);
						 if (resultado.resposta == 'true' || enderecoAtual.includes("/FooDrink/estabelecimento/perfil/perfil-empresa")) {
							 return true;
						 }
						 else {
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
					 data : {cep:function(){return $("#cep").val();}
					 },
					 dataFilter : function(data) {
						 var resultado = JSON.parse(data);
						 if (resultado.resposta == 'true') {
							 return true;
						 }
						 else {
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
			},
			complemento : {
				required : false
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