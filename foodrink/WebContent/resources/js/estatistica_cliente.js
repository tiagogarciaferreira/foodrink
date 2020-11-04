$(document).ready(function() {
	atualizarCliente();
});



function atualizarCliente() {
	$("#meuPedidoAtual").html("");
	$.ajax({
		type : "GET",
		url : "../estabelecimento/getEstatisticasCliente",
	}).done(function(resposta) {
		var dados = resposta.split("#");
		var pedidoHtml = "";
		if(dados[0] != "" && dados[0] != undefined && dados[1] != "" && dados[1] != undefined ){
			var pedido = JSON.parse(dados[1]);
		    pedidoHtml = "<label class='margembaixo'>Numero do Pedido: "+ "<label class='rodape'>"+pedido.idPedido+"</label>" +"</label>"  + "<br>" +
			"<label class='margembaixo'>Estabelecimento: "+"<label class='rodape'>"+pedido.empresa.usuario.nome+"</label>"+"</label>"  + "<br>" +
			"<label class='margembaixo'>Hor\u00e1rio do Pedido: "+"<label class='rodape'>"+pedido.hora+"</label>"+"</label>" + "<br>" +
			"<label class='margembaixo'>Mesa: "+"<label class='rodape'>"+pedido.mesa.numero+"</label>"+"</label>" +"<br>" +
			"<label class='rodape margembaixo'>Itens do Pedido: "+"</label>";
			var itens = JSON.parse(dados[0]);
			var itensHtml = "";
			for (var i in itens) {
				itensHtml = itensHtml + "<tr>"+
		    	"<td style='border: 2px solid white;'>"+itens[i].produto.nome + " - " +itens[i].produto.tamanho+"</td>"+
		    	"<td style='border: 2px solid white;'>"+itens[i].quantidade+"</td>"+
	  			"<td style='border: 2px solid white;'>"+itens[i].produto.preco+"</td>"+
			"<tr>"
			}
			
			
			var HTML = "<div class='col-xl-3 col-md-12'>"+
			"<div class='card border-left-success shadow h-100 py-2 invisivel'>"+
				"<div class='card-body'>"+
					"<div class='row no-gutters align-items-center'>"+
						"<div class='col mr-2'>"+
							"<div class='text-xs font-weight-bold rodape text-uppercase mb-1'>Meu pedido atual</div>"+
							"<div class='text-xs font-weight-bold text-gray-800 text-uppercase mb-1'>"+pedidoHtml+
			
			      "<table style='width:100%; border: 1px solid black; overflow: auto;' class='margembaixo'>"+
				  "<tr>"+
				    "<th style='border: 2px solid white;'>Descri\u00e7\u00e3o</th>"+
				    "<th style='border: 2px solid white;'>Quantidade</th>"+
				    "<th style='border: 2px solid white;'>Pre\u00e7o</th>"+
				    "</tr>" + itensHtml +  "</table>"+"<br><label class='margembaixo'>Valor do Pedido (R$): "+"<label class='rodape'>"+pedido.valorPedido+"</label>"+"</label>" + "<br>"+
				    "</div>"+
					"</div>"+
				"</div>"+
			"</div>"+
		"</div>"+
	"</div>"+"<br>";		    
			
			pedidoHtml = HTML;
			atualizarReservas(dados[2],pedidoHtml);
		}
		else{
			pedidoHtml = "<div class='col-xl-3 col-md-12'>"+
			"<div class='card border-left-success shadow h-100 py-2 invisivel'>"+
			"<div class='card-body'>"+
				"<div class='row no-gutters align-items-center'>"+
					"<div class='col mr-2'>"+
						"<div class='text-xs font-weight-bold rodape text-uppercase mb-1'>Meu pedido atual</div>"+
						"<div class='text-xs font-weight-bold text-gray-800 text-uppercase mb-1'>"+
						"Nenhum pedido encontrado..."+	
						"</div>"+
					"</div>"+
				"</div>"+
			"</div>"+
		"</div>"+
	"</div>"
			atualizarReservas(dados[2],pedidoHtml);
		}
		setTimeout(function() {
			atualizarCliente();
		}, 5000);
	}).fail(function() {
		/* fazer algo caso a solicitação de algum erro */
	});
	
function atualizarReservas(reserva, pedidoHtml){
	    var htmlReservas = "";
		if(reserva != "" && reserva != undefined && reserva.length > 20){
			var reservas = JSON.parse(reserva);
			for (var i in reservas) {
				htmlReservas = htmlReservas+ "<div class='col-xl-3 col-md-12'style='margin-top: 3px;'>"+
				"<div class='card border-left-success shadow h-100 py-2 invisivel'>"+
					"<div class='card-body'>"+
						"<div class='row no-gutters align-items-center'>"+
							"<div class='col mr-2'>"+
								"<div class='text-xs font-weight-bold rodape text-uppercase mb-1'>Minha reserva atual</div>"+
								"<div class='text-xs font-weight-bold text-gray-800 text-uppercase mb-1'>"+
									"<label class='margembaixo'>Numero da Reserva: <span class='rodape'>"+reservas[i].idReserva+"</span></label><br>"+
									"<label class='margembaixo'>Data: <span class='rodape'>"+reservas[i].data+"</span></label><br>"+
									"<label class='margembaixo'>Hor\u00e1rio: <span class='rodape'>"+reservas[i].hora+"</span></label><br>"+
									"<label class='margembaixo'>Mesa: <span class='rodape'>N:"+reservas[i].mesa.numero+" - "+reservas[i].mesa.lugares+" Lugares</span></label><br>"+
									"<label class='margembaixo'>Status: <span class='rodape'>"+reservas[i].status+"</span></label><br>"+
									"<label class='margembaixo'>Estabelecimento: <span class='rodape'>"+reservas[i].empresa.usuario.nome+"</span></label><br>"+
									"<a href='reserva/cancelar-reserva?reservaID="+reservas[i].idReserva+"' class='btn btn-outline-danger btn-xs' >Cancelar</a>"+
								"</div>"+
							"</div>"+
						"</div>"+
					"</div>"+
				"</div>"+
			"</div>"
			}
			$("#meusDadosAtuais").html("");
			$("#meusDadosAtuais").html(pedidoHtml+htmlReservas)
			htmlReservas = "";
		}
		else{
			htmlReservas = "<div class='col-xl-3 col-md-12' style='margin-top: 3px;' >" +
			"<div class='card border-left-success shadow h-100 py-2 invisivel'>"+
				"<div class='card-body'>"+
					"<div class='row no-gutters align-items-center'>"+
						"<div class='col mr-2'>"+
							"<div class='text-xs font-weight-bold rodape text-uppercase mb-1'>Minha reserva atual</div>"+
							"<div class='text-xs font-weight-bold text-gray-800 text-uppercase mb-1'>"+
							"Nenhuma reserva encontrada..."+	
							"</div>"+
						"</div>"+
					"</div>"+
				"</div>"+
			"</div>"+
		"</div>";
			$("#meusDadosAtuais").html("");
			$("#meusDadosAtuais").html(pedidoHtml+htmlReservas);
			htmlReservas = "";
		}
	}
}
