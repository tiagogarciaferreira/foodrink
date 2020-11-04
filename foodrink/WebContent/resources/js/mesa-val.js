$(document).ready(function() {
	aviso();
	validaCadastro();

});

function aviso(){
	if(!$("#alerta").html().includes("novamente")){
		setTimeout(function(){
			$("#alerta").html("")
		}, 3000);
	}
}

function validaCadastro(){
	$("#formNovaMesa").validate({
		rules : {
			numero : {
				campoSelecao : true,
				remote : {
					url : "validarMesa",
					type : "GET",
					dataType : "json",
					data : {
						numeroMesa : function() {
							return $("#numero").val();
						},
						mesaId : function() {
							return $("#idMesa").val();
						}
					},
					dataFilter : function(data) {
						var resultado = JSON.parse(data);
						if (resultado.resposta == 'true') {
							return true;
						} else {
							return '"Numero j\u00e1 cadastrado"';
						}
					},
				}
			},
			lugares : {
				campoSelecao : true
			},
			descricao : {
				required : false
			},
			status : {
				required : true
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	})	
}

jQuery.validator.addMethod("campoSelecao", function(value, element) {
	if(value == "Selecione"){
		return false;
	}
	else{
		return true;
	}
	
}, "Campo obrigat\u00f3rio")