$(document).ready(function(){
	aviso();
	var removerRegistro;
	initTableProduto();
	
});

function aviso(){
	if(!$("#alerta").html().includes("novamente")){
		setTimeout(function(){
			$("#alerta").html("")
		}, 3000);
	}
}

function editar(objeto, parametro){
	 var column = $(objeto).parents("tr").find("td:nth-child(" + parametro + ")");	
	 var idSelecionado = column.html();
	 document.getElementById("solicitacao").href = "editar-produto?id="+idSelecionado;
	 document.getElementById("solicitacao").click();
}

function verificaRemover(valor){
	if(valor.value == 'SIM'){
		document.getElementById("solicitacao").href = "remover-produto?id="+removerRegistro;
		document.getElementById("solicitacao").click();
		$('#modalConfirme').modal('hide');
	}
}

function remover(objeto, parametro){
	 removerRegistro = null;
	 var column = $(objeto).parents("tr").find("td:nth-child(" + parametro + ")");	
	 removerRegistro = column.html();
	 $('#modalConfirme').modal('show');
}

function initTableProduto(){
	$('#tabelaProduto').DataTable({
		"autoWidth": true,
		"ordering":  false,
		"deferRender": true,
		"paging": true,
		"responsive": true,
		"pageLength": 10,
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
        "url": '../produto/listarProdutosEditar', 
        "type": 'GET',
        "data": {}
		},
		 "columnDefs": [
			    {
			      "data": null,
			      "defaultContent": "<a style='margin-left: 2px;' class='row'><button id='editar' name='editar' type='submit' onClick='editar(this,1);' class='btn btn-primary btn-sm'>Editar</button> <button id='deletar' name='deletar' type='submit' onClick='remover(this,1);' class='btn btn-danger btn-sm'>Excluir</button></a>",
			      "targets": -1,
			    }
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
