$(document).ready(function() {
	aviso();
	listarCategorias();
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
	$("#formNovoProduto").validate({
		rules : {
			fileImagem : {
				required : true,
				accept : "image/*",
				extension : "png|jpeg|jpg"
				
			},
			nome : {
				required : true
			},
			/*descricao :{
				required : true
			},*/
			tamanho : {
				required : true
			},
			preco : {
				required : true,
				number : true
			},
			'categoria.idCategoria' : {
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

jQuery.validator.addMethod("campoImagem", function(value, element) {
	var campo = document.getElementById("imagem").value;
	if(campo.length > 50){
		return true;
	}
	else{
		return false;
	}
}, "Campo obrigat\u00f3rio")

jQuery.validator.addMethod("campoSelecao", function(value, element) {
	if (value == "Selecione") {
		return false;
	} else {
		return true;
	}

}, "Campo obrigat\u00f3rio")

function listarCategorias(){
	var quantidade = document.getElementById("categoria.idCategoria").length;
	var selecaoCategoria = $("#selecaoCategoria").html();
	if(quantidade == 1){
		$.ajax({
			type : "GET",
			url : "../categoria/listarCategorias",
			dataType: "json",
		}).done(function(resposta){
			for (var i in resposta) {
				if(resposta[i]["idCategoria"] != selecaoCategoria){
					$('select[name="categoria.idCategoria"]').append('<option value='+ resposta[i]["idCategoria"] +'>'+ resposta[i]["nome"] +'</option>');
				}else{
					$('select[name="categoria.idCategoria"]').append('<option selected="selected" value='+ resposta[i]["idCategoria"] +'>'+ resposta[i]["nome"] +'</option>');
				}
			}
		}).fail(function(){
			/* fazer algo caso a solicitação de algum erro */
	    });
	}
}




