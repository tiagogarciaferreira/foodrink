$(document).ready(function() {
	validaLogin();
	validaNovaSenha();
	validaEmailNovaSenha();
	validaContato();
	validaBuscaEstabelecimento();

});

function validaLogin() {
	$("#form_login").validate({
		rules : {
			username : {
				required : true,
				email : true
			},
			password : {
				required : true
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	})
}

function validaEmailNovaSenha() {
	$("#form_email_redefinicao_senha").validate({
		rules : {
			email_redefinicao : {
				required : true,
				email : true
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	})
}

function validaNovaSenha() {
	$("#form_nova_senha").validate({
		rules : {
			nova_senha : {
				required : true,
				minlength : 10
			},
			confirme_nova_senha : {
				required : true,
				equalTo : "#nova_senha"
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	})
}

function validaContato() {
	$("#form_contato").validate({
		rules : {
			nome : {
				required : true
			},
			email : {
				required : true,
				email : true
			},
			assunto : {
				required : true
			},
			mensagem : {
				required : true
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	})
}

function validaBuscaEstabelecimento() {
	$("#form_filtro_estabelecimento").validate({
		rules : {
			'estado.idEstado' : {
				campoSelect : true
			},
			'cidade.idCidade' : {
				campoSelect : true
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
