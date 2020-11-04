$(document).ready(function() {
	aviso();
	var pedidoId = document.getElementById("idPedido").value;
	if (pedidoId.length == 0) {
		document.getElementById("valorPedido").value = 0;
		document.getElementById("statusE").checked = true;
		document.getElementById("statusF").disabled = true;
	}
	if (pedidoId.length > 0) {
		document.getElementById("divHora").hidden = false;
		document.getElementById("divData").hidden = false;
	}
	var status = document.getElementById("statusF").checked;
	if (status != false) {
		document.getElementById("pedir").disabled = true;
	}

	listarMesas();
	validaCadastro();

});

function finalizar() {
	var total = document.getElementById("totalValorPedido").value;
	var dinheiro = document.getElementById("dinheiro").value;
	var troco = document.getElementById("troco").value;
	var totalPagar = document.getElementById("pagar").value;
	var desconto = document.getElementById("desconto").value;
	var arredondamento;

	if (dinheiro.trim() != "") {
		if (desconto != "") {
			totalPagar = parseFloat(total) - (parseFloat(desconto) / 100) * parseFloat(total);
			arredondamento = totalPagar.toFixed(2);
			document.getElementById("pagar").value = arredondamento;
			document.getElementById("valorPedido").value = arredondamento;
			troco = parseFloat(dinheiro) - parseFloat(totalPagar);
			arredondamento = troco.toFixed(2);
			document.getElementById("troco").value = arredondamento;
		} else {
			document.getElementById("pagar").value = total;
			troco = parseFloat(dinheiro) - parseFloat(total);
			arredondamento = troco.toFixed(2);
			document.getElementById("troco").value = arredondamento;
		}
	}
	else{
		document.getElementById("dinheiro").placeholder="INFORME UM VALOR";
	}

}

function aviso(buttom) {
	$(buttom).html("OK")
	setTimeout(function() {
		$(buttom).html("Adicionar")
	}, 2000);
}

function validaCadastro() {
	$("#formNovoPedido").validate({
		rules : {
			'cliente.idCliente' : {
				required : true
			},
			'mesa.idMesa' : {
				campoSelect : true
			},
			jsonItens : {
				required : true
			},
			status : {
				required : true
			}
		},
		submitHandler : function(form) {
			form.submit();
			sessionStorage.setItem("produtos", "");
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

function listarMesas() {
	var quantidade = document.getElementById("mesa.idMesa").length;
	var selecaoMesa = $("#selecaoMesa").html();
	if (quantidade == 1) {
		$.ajax({
			type : "GET",
			url : "../mesa/listarMesasPedido",
			dataType : "json",
			data : {
				selecaoMesa : selecaoMesa
			},
		}).done(
				function(resposta) {
					for ( var i in resposta) {
						if (resposta[i]["idMesa"] != selecaoMesa) {
							$('select[name="mesa.idMesa"]').append(
									'<option value=' + resposta[i]["idMesa"]
											+ '>' + resposta[i]["numero"]
											+ '</option>');
						} else {
							$('select[name="mesa.idMesa"]').append(
									'<option selected="selected" value='
											+ resposta[i]["idMesa"] + '>'
											+ resposta[i]["numero"]
											+ '</option>');
						}
					}
				}).fail(function() {
			/* fazer algo caso a solicitação de algum erro */
		});
	}
}