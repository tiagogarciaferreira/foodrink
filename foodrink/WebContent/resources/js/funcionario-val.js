$(document).ready(function() {

	aviso();
	var enderecoAtual = window.location.href;
	if(document.getElementById("idFuncionario").value == ""){
		document.getElementById("statusA").disabled = true;
	}
	else{
		document.getElementById("email").readOnly = true;
	}
	mascaras();
	validaCadastro();

	$('img[name=image]').on('click', function() {
		document.getElementById("fileImagem").click();
	});
	$('input[name=fileImagem]').on('change', function() {
		loadImagem();
	});

});

function aviso(){
	if(!$("#alerta").html().includes("novamente")){
		setTimeout(function(){
			$("#alerta").html("")
		}, 3000);
	}
}


function mascaras() {
	$("#telefone").mask("(00) 0 0000-0000");
}

function loadImagem() {
	var carregaIMG = document.getElementById("image");
	var file = document.querySelector("input[type=file]").files[0];
	var reader = new FileReader();
	reader.onloadend = function() {
		carregaIMG.src = reader.result;
		document.getElementById("imagem").value = reader.result;
	};
	if (file) {
		reader.readAsDataURL(file);
	} else {
		carregaIMG.src = "";
	}
}

function validaCadastro() {
	var idUsuario = document.getElementById("idUsuario").value;
	$("#formNovoFuncionario").validate({
		rules : {
			fileImagem : {
				required : true,
				accept : "image/*",
				extension : "png|jpeg|jpg|PNG"
				
			},
			imagem : {
				campoImagem : true
			},
			nome : {
				required : true
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
						if (resultado.resposta == 'true' || idUsuario != "") {
							return true;
						} else {
							return '"Email j\u00e1 cadastrado"';
						}
					},
				}
			},
			telefone : {
				required : true,
				minlength : 16,
				maxlength : 16
			},
			'tipoAcesso.idTipoAcesso' : {
				campoSelecao : true
			},
			chaveStatus :{
				required : true
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	})
}

jQuery.validator.addMethod("campoSelecao", function(value, element) {
	if (value == "Selecione") {
		return false;
	} else {
		return true;
	}
}, "Campo obrigat\u00f3rio")




