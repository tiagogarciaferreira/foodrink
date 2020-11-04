$(document).ready(function() {
	
	listarEstados();
	pegaEstadoSelecionado();
	
	$('img[name=image]').on('click',function(){
		document.getElementById("fileImagem").click();
	});
	$('input[name=fileImagem]').on('change',function(){
		loadImagem();
	});
	
});

function detalhes(labelHorario) {
	var display = document.getElementById(labelHorario).style.display;
	if (display == "none") {
		document.getElementById(labelHorario).style.display = 'block';
	} else {
		document.getElementById(labelHorario).style.display = 'none';
	}
}


function loadImagem(){
	var carregaIMG = document.getElementById("image");
	var file = document.querySelector("input[type=file]").files[0];
	var reader = new FileReader();
	reader.onloadend = function(){
		carregaIMG.src = reader.result;
		document.getElementById("imagem").value = reader.result;
	};
	if(file){
		reader.readAsDataURL(file);
	}
	else{
		carregaIMG.src = "";
	}
}

function listarEstados(){
	var quantidade = document.getElementById("estado.idEstado").length;
	var selecaoEstado = $("#selecaoEstado").html();
	if(quantidade == 1){
		$.ajax({
			type : "GET",
			url : "listarEstados",
			dataType: "json",
		}).done(function(resposta){
			for (var i in resposta) {
				if(resposta[i]["idEstado"] != selecaoEstado){
					$('select[name="estado.idEstado"]').append('<option value='+ resposta[i]["idEstado"] +'>'+ resposta[i]["nome"] +'</option>');
				}else{
					$('select[name="estado.idEstado"]').append('<option selected="selected" value='+ resposta[i]["idEstado"] +'>'+ resposta[i]["nome"] +'</option>');
					listarCidades(resposta[i]["idEstado"])
				}
			}
		}).fail(function(){
			/* fazer algo caso a solicitação de algum erro */
	    });
	}
}

function pegaEstadoSelecionado(){
	$('select[name="estado.idEstado"]').on('change',function(){
		var selecao = document.getElementById("estado.idEstado").value;
		if(selecao != "Selecione"){
			$('select[name="cidade.idCidade"]').html("");
			$('select[name="cidade.idCidade"]').append('<option>'+ "Selecione" +'</option>');
			listarCidades(selecao);
		}
	});
}

function listarCidades(estadoSelecionado){
	var selecaoCidade = $("#selecaoCidade").html();
	$.ajax({
		type : "GET",
		url : "listarCidades",
		dataType: "json",
		data : {estado: estadoSelecionado},
	}).done(function(resposta){
		for (var i in resposta) {
			if(resposta[i]["idCidade"] != selecaoCidade){
				$('select[name="cidade.idCidade"]').append('<option value='+ resposta[i]["idCidade"] +'>'+ resposta[i]["nome"] +'</option>');
			}else{
				$('select[name="cidade.idCidade"]').append('<option selected="selected" value='+ resposta[i]["idCidade"] +'>'+ resposta[i]["nome"] +'</option>');
			}
		}
	}).fail(function(){
		/* fazer algo caso a solicitação de algum erro */
    });	
}