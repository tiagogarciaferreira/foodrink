$(document).ready(function(){
	avisoAlerta();
	initTableProdutoPedido();
	var pedidoId = document.getElementById("idPedido").value;
	if(pedidoId.length != 0){
		var lista = document.getElementById('listaItensJsonPedido').value;
		sessionStorage.setItem("produtos",lista)
	}
	if(pedidoId.length == 0){
		sessionStorage.clear();
	}
	
	 if(sessionStorage.getItem("produtos") != null && sessionStorage.getItem("produtos") != ""){
		 document.getElementById('jsonItens').value = "Existem produtos adicionados.";
	 }
	 else{
		 document.getElementById('jsonItens').value = "";
	 }
});

function avisoAlerta(){
	if(!$("#alerta").html().includes("novamente")){
		setTimeout(function(){
			$("#alerta").html("")
		}, 3000);
	}
}

function adicionaItem(objeto, parametro){
	
	 var produtoJson;
	 var verificaCarrinho;
	 if(sessionStorage.getItem("produtos") != null){
		 produtoJson = sessionStorage.getItem("produtos");
		 document.getElementById('jsonItens').value = "Existem produtos adicionados.";
	 }
	 if(sessionStorage.getItem("produtos") == null || sessionStorage.getItem("produtos") == ""){
		 document.getElementById('jsonItens').value = "";
	 }
	
	 var column = $(objeto).parents("tr").find("td:nth-child(" + parametro + ")");	
	 var idProduto = column.html();
	 var column = $(objeto).parents("tr").find("td:nth-child(" + 2 + ")");	
	 var nome = column.html();
	 var column = $(objeto).parents("tr").find("td:nth-child(" + 3 + ")");	
	 var preco = column.html();
	 column = $(objeto).parents("tr").find("td:nth-child(" + 4 + ") select");	
	 var quantidade = column.val();
	 var produto = {idItemPedido: null, idProduto: idProduto, nome: nome, preco: preco, quantidade: quantidade};

	 if(produtoJson == undefined){
		 produtoJson = JSON.stringify(produto);
		 aviso(objeto);
		 document.getElementById('jsonItens').value = "Existem produtos adicionados.";
	 }else{
		 verificaCarrinho = produtoJson;
		 verificaCarrinho = JSON.parse("["+verificaCarrinho+"]");
		 for(var i in verificaCarrinho){
			 if(verificaCarrinho[i]["idProduto"] != undefined && verificaCarrinho[i]["idProduto"] == idProduto){
				 var ItemPedido = verificaCarrinho[i]["idItemPedido"];
				 quantidade = parseInt(quantidade) + parseInt(verificaCarrinho[i]["quantidade"]);
				 verificaCarrinho = removerObjetoJson(verificaCarrinho, "idProduto", idProduto);
				 produto = {idItemPedido: ItemPedido, idProduto: idProduto, nome: nome, preco: preco, quantidade: quantidade};
				 produtoJson = objectToStringJson(verificaCarrinho);
				 break;
			 }
		 }
		 
		 if(produtoJson == ""){
			 produtoJson = JSON.stringify(produto);
			 document.getElementById('jsonItens').value = "Existem produtos adicionados.";
		 }
		 else{
			 produtoJson = produtoJson + "," + JSON.stringify(produto);
			 document.getElementById('jsonItens').value = "Existem produtos adicionados.";
		 }
		 aviso(objeto);
	 }
	 sessionStorage.setItem("produtos", produtoJson);
	 document.getElementById('listaItensJsonPedido').value = produtoJson;
	 carrinhoAtual();
}

function objectToStringJson(lista){
	var listaJson = "";
	for(var i in lista){
		produto = {idItemPedido: lista[i]["idItemPedido"], idProduto: lista[i]["idProduto"], nome: lista[i]["nome"], preco: lista[i]["preco"], quantidade: lista[i]["quantidade"]};
		if(listaJson != ""){
			listaJson = listaJson + "," + JSON.stringify(produto);
		}else{
			listaJson = JSON.stringify(produto);
		}
	 }
	return listaJson;
}	


function removerObjetoJson(jsonArquivo, chave, valor){
	jsonArquivo = jsonArquivo.filter(function(jsonObject) {
	        return jsonObject[chave] != valor;
	    });
	return jsonArquivo
}


function aviso(textoBotao){
	$(textoBotao).html("OK")
	setTimeout(function(){
		$(textoBotao).html("Adicionar")
	}, 1000);
}

function removerItem(valorChave){
	var produtos = sessionStorage.getItem("produtos");
	produtos = JSON.parse("["+produtos+"]");
	 for(var i in produtos){
		 if(produtos[i]["idProduto"] == valorChave.value){
			 if(produtos[i]["idItemPedido"] == null || produtos[i]["idItemPedido"] == undefined){
				 produtos = removerObjetoJson(produtos, "idProduto", valorChave.value);
				 produtos = objectToStringJson(produtos);
				 break;
			 }
			 else{
				 var produto = {idItemPedido: produtos[i]["idItemPedido"], idProduto: null, nome: "", preco: "-1", quantidade: "-1"};
				 produtos = removerObjetoJson(produtos, "idProduto", valorChave.value);
				 produtos = objectToStringJson(produtos);
				 if(produtos == ""){
					 produtos = JSON.stringify(produtos);
				 }
				 else{
					 produtos = produtos + "," + JSON.stringify(produto);
				 }
				break;				 
			 }
		 }
	 }
	sessionStorage.setItem("produtos", produtos);
	document.getElementById('listaItensJsonPedido').value = produtos;
	carrinhoAtual();
}

function carrinhoAtual(){
	$("#listaItensProdutos").html("");
	var listaProdutosJson;
	var item = ""; 
	var total = 0;
	if(sessionStorage.getItem("produtos") != null && sessionStorage.getItem("produtos") != ""){
		listaProdutosJson = sessionStorage.getItem("produtos");
		listaProdutosJson = JSON.parse("["+listaProdutosJson+"]");
		 for (var i in listaProdutosJson) {
			 if(listaProdutosJson[i]["idProduto"] != null && listaProdutosJson[i]["idProduto"] != undefined){
				 total = parseFloat(total) + parseFloat(listaProdutosJson[i]["quantidade"] * listaProdutosJson[i]["preco"]);
				 item = item + "<tr>"+
			    	"<td style='border: 2px solid white;'>"+listaProdutosJson[i]["nome"]+"</td>"+
	      			"<td style='border: 2px solid white;'>"+listaProdutosJson[i]["preco"]+"</td>"+
	      			"<td style='border: 2px solid white;'>"+listaProdutosJson[i]["quantidade"]+"</td>"+
	     			"<td style='border: 2px solid white;'><button onclick='removerItem(this);' class='btn btn-danger btn-sm' value='"+listaProdutosJson[i]["idProduto"]+"'> Remover </button></td>"+
				"<tr>"
			 }
		 }
		 var table = "<table style='width:100%; border: 1px solid black; overflow: auto;'>"+
				  "<tr>"+
				    "<th style='border: 2px solid white;'>Item</th>"+
				    "<th style='border: 2px solid white;'>Pre\u00e7o(R$)</th>"+
				    "<th style='border: 2px solid white;'>Quantidade</th>"+
				    "<th style='border: 2px solid white;'>A\u00e7\u00e3o</th>"+
				    "</tr>" + item +
				    
		 "</table>";		    
		 document.getElementById('listaItensProdutos').innerHTML = table;
		 $("#valorTotal").html(total);
		 document.getElementById('valorPedido').value = total;
	}
	else{
		 document.getElementById('jsonItens').value = "";
		 $("#valorTotal").html(total);
		 document.getElementById('valorPedido').value = 0;
	 }
}

function adicionaSelect(){
	var campo = document.getElementById("quantidadeProduto").length;
	if(campo < 10){
		for(var i = 1; i <= 100;i++){
			$('select[name="quantidadeProduto"]').append('<option value='+i+'>'+i+'</option>');
		}
	}
}

function initTableProdutoPedido(){
	$('#tabelaProdutosPedido').DataTable({
		"autoWidth": true,
		"ordering":  false,
		"deferRender": true,
		"paging": true,
		"responsive": true,
		"pageLength": 15,
		"lengthChange": true,
		"lengthMenu": [ 10, 25, 50, 75, 100 ],
		"pagingType": 'simple_numbers',
		"searching": true,
		"search": {
			"search": "",
		"smart": true,
		},
		"searchDelay": 400,
        "processing": true,
        "serverSide": false,
		"ajax": {
        "url": '../produto/listarProdutosPedido', 
        "type": 'GET',
		},
		 "columnDefs": [
			 	{
			      "data": null,
			      "defaultContent": "<a><button id='adiciona' name='adiciona' type='submit' onClick='adicionaItem(this,1);' class='btn btn-success btn-sm'>Adicionar</button></a>",
			      "targets": -1,
			    },
			    {
				  "data": null,
				  "defaultContent": "<select name='quantidadeProduto' id='quantidadeProduto' style='border-radius: 5px;'></select>",
				  "targets": -2,
				},
			  ],
        "language": {
            "sEmptyTable": "Nenhum registro encontrado",
            "sInfo": "Mostrando de _START_ at\u00e9 _END_ de _TOTAL_ registros",
            "sInfoEmpty": "",
            "sInfoFiltered": "",
            "sInfoPostFix": "",
            "sInfoThousands": ".",
            "sLengthMenu": "",
            "sLoadingRecords": "Carregando...",
            "sProcessing": "Processando...",
            "sZeroRecords": "Nenhum registro encontrado",
            "sSearch": "Pesquisar",
            "oPaginate": {
                "sNext": "Pr\u00f3ximo",
                "sPrevious": "Anterior",
                "sFirst": "Primeiro",
                "sLast": "\u00daltimo"
            },
            "oAria": {
                "sSortAscending": ": Ordenar colunas de forma ascendente",
                "sSortDescending": ": Ordenar colunas de forma descendente"
            }
        }
    });
}
