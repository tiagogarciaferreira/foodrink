$(document).ready(function(){
	var idSelecionado;
	initTableAEntregar();
	atualizar();
});

function atualizar(){
	setTimeout(function(){
		$('#tabelaAEntregar').DataTable().destroy();
		initTableAEntregar();
		atualizar();
	}, 5000);
}

function pronto(objeto, parametro){
   	 idSelecionado = null;
	 var column = $(objeto).parents("tr").find("td:nth-child(" + parametro + ")");	
	 idSelecionado = column.html();
	 $('#modalConfirme').modal('show');
}

function verificaOpcao(valor){
	if(valor.value == 'SIM'){
		document.getElementById("solicitacao").href = "confirmar-produto-entregue?id="+idSelecionado;
		document.getElementById("solicitacao").click();
		$('#modalConfirme').modal('hide');
	}
}

function initTableAEntregar(){
	$('#tabelaAEntregar').DataTable({
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
        "url": 'produtosEmPreparo', 
        "type": 'GET',
        "data": {}
		},
		 "columnDefs": [
			    {
			      "data": null,
			      "defaultContent": "<a ><button id='pronto' name='pronto' type='submit' onClick='pronto(this,1);' class='btn btn-success btn-sm'>Entregar</button> </a>",
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
