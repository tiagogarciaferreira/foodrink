var modalShow = false;
$(document).ready(function() {
	aviso();
	atualizarEmpresa();
	isModal();
});

function isModal() {
	$('#modal-cadastra-horario').on('show.bs.modal', function(event) {
		modalShow = true;
	})

	$('#modal-cadastra-horario').on('hide.bs.modal', function(event) {
		modalShow = false;
	})

}

function aviso() {
	if (!$("#alerta").html().includes("novamente")) {
		setTimeout(function() {
			$("#alerta").html("")
		}, 10000);
	}
}

function atualizarEmpresa() {
	$("#pedidosAndamento").html("");
	$.ajax({
		type : "GET",
		url : "../estabelecimento/getEstatisticasEmpresa",
	}).done(function(resposta) {
		if (resposta != "") {
			var dados = resposta.split("#");
			$("#pedidosAndamento").html(dados[0]);
			$("#reservasHoje").html(dados[1]);
			$("#mesasDisponiveis").html(dados[2]+" (" + dados[4]+")");
			setFuncionamento(dados[3]);
		} else {
			$("#pedidosAndamento").html("0");
			$("#reservasHoje").html("0");
			$("#mesasDisponiveis").html("0");
			$("#funcionamento").html("Indisponível...");
		}
		setTimeout(function() {
			atualizarEmpresa();
		}, 5000);
	}).fail(function() {
		/* fazer algo caso a solicitação de algum erro */
	});
}

function setFuncionamento(funcionamento) {
	var dias = funcionamento.split("@");
	if (modalShow == false) {
		document.getElementById("segunda").value = dias[0];
		document.getElementById("terca").value = dias[1];
		document.getElementById("quarta").value = dias[2];
		document.getElementById("quinta").value = dias[3];
		document.getElementById("sexta").value = dias[4];
		document.getElementById("sabado").value = dias[5];
		document.getElementById("domingo").value = dias[6];
	}
	var horario = "Segunda-Feira: " + "<span style='color:white;'>" + dias[0]
			+ "</span>" + "<br> Ter\u00e7a-Feira: "
			+ "<span style='color:white;'>" + dias[1] + "</span>"
			+ "<br> Quarta-Feira: " + "<span style='color:white;'>" + dias[2]
			+ "</span>" + "<br> Quinta-Feira: " + "<span style='color:white;'>"
			+ dias[3] + "</span>" + "<br> Sexta-Feira: "+"<span style='color:white;'>"+ dias[4] +"</span>"+ "<br> S\u00e1bado: "+"<span style='color:white;'>"+ dias[5] +"</span>"+ "<br> Domingo: " +"<span style='color:white;'>"+ dias[6] +"</span>";
	$("#funcionamento").html(horario);
}

function detalhes(divHorario) {
	var display = document.getElementById(divHorario).style.display;
	if (display == "none") {
		document.getElementById(divHorario).style.display = 'block';
	} else {
		document.getElementById(divHorario).style.display = 'none';
	}
}
