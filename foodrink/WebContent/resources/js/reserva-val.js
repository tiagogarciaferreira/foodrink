$(document).ready(function() {
	
	$('#dataString').on('change',function(){
		var data = document.getElementById("dataString").value;
		if(data != ""){
			listarMesas();
		}
	});
	
	
	var identificador = document.getElementById("empresa_identificador").value;
	var statusCancela = document.getElementById("statusCA");
	if(identificador == "" && statusCancela != null){
		var statusCancela = document.getElementById("statusCA").checked;
		var statusFinal = document.getElementById("statusF").checked;
		if(statusCancela != false || statusFinal != false){
			document.getElementById("reserva").disabled = true;
		}
		
	}
	else{
		var statusFinal = document.getElementById("statusP").checked = true;
	}
	aviso();
	dataHora();
	validaCadastro();

});

function aviso(){
	if(!$("#alerta").html().includes("novamente")){
		setTimeout(function(){
			$("#alerta").html("")
		}, 3000);
	}
}

function dataHora() {
	$('#dataString').datepicker({
		format : "dd-mm-yyyy",
		language : "pt-BR",
		startDate : '100d',
		endDate : '+2d'
	});
	var data = document.getElementById("dataString").value;
	$("#dataString").datepicker("setValue", data);

	
	$('#horaString').datetimepicker({
		use24hours : true,
		format : 'HH:ii:ii',
		language : "pt-BR",
		startView : 1
	});
	var hora = document.getElementById("horaString").value;
	$('#horaString').datetimepicker('update', hora);
}

function validaCadastro() {
	$("#formNovaReserva").validate({
		rules : {
			'cliente.idCliente' : {
				required : true
			},
			'mesa.idMesa' : {
				campoSelectMesa : true,
				campoSelect : true
			},
			data : {
				required : true
			},
			hora : {
				required : true
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

jQuery.validator.addMethod("campoSelect", function(value, element) {
	if (value == "Selecione") {
		return false;
	} else {
		return true;
	}

}, "Campo obrigat\u00f3rio")

jQuery.validator.addMethod("campoSelectMesa", function(value, element) {
	var aviso;
	var quantidadeMesa = document.getElementById("mesa.idMesa").length;
	var dataReserva = document.getElementById("dataString").value;
	if (quantidadeMesa < 2 && dataReserva == "") {
		aviso = "Selecione uma data primeiro";
		return false;
	}
	else if(dataReserva != "" && quantidadeMesa < 2) {
		aviso = "N\u00e3o há mesas dispon\u00edveis";
		return false;
	}
	else{
		return true;
	}

}, aviso)


function listarMesas(){
	var quantidade = document.getElementById("mesa.idMesa").length;
	var empresa_identificador = document.getElementById("empresa_identificador").value;
	var data = document.getElementById("dataString").value;
	var selecaoMesa = $("#selecaoMesa").html();
	if(quantidade == 1){
		$.ajax({
			type : "GET",
			url : "../mesa/listarMesas",
			dataType: "json",
			data : {selecaoMesa: selecaoMesa, empresa_identificador: empresa_identificador, data: data},
		}).done(function(resposta){
			for (var i in resposta) {
				var descricao = "Lugares-"+ resposta[i]["descricao"];
				if(resposta[i]["descricao"] == ""){
					descricao = "Lugares";
				}
				if(resposta[i]["idMesa"] != selecaoMesa){
					$('select[name="mesa.idMesa"]').append('<option value='+ resposta[i]["idMesa"] +'>'+"N: "+ resposta[i]["numero"] +"-"+ resposta[i]["lugares"] +descricao+'</option>');
				}else{
					$('select[name="mesa.idMesa"]').append('<option selected="selected" value='+ resposta[i]["idMesa"] +'>'+"n "+ resposta[i]["numero"] +" - "+ resposta[i]["lugares"] +descricao+'</option>');
				}
			}
		}).fail(function(){
			/* fazer algo caso a solicitação de algum erro */
	    });
	}
}