// Call the dataTables jQuery plugin
$(document).ready(function() {
	initTableProduto();
});

function initTableProduto(){
	$('#dataTableProduto').DataTable({
		"autoWidth": true,
		"ordering":  false,
		"deferRender": true,
		"paging": true,
		"responsive": true,
		"pageLength": 10,
		"lengthChange": true,
		"lengthMenu": [ 10, 25, 50, 100 ],
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
        "url": '', //pedido de carregamento de dados
        "type": 'POST',
        "data": {busca : ""}
		},
		 "columnDefs": [
			    {
			      "data": null,
			      "defaultContent": "<a href=''><button id='editar' name='editar' type='submit' onClick='clicar();' class='btn btn-info btn-circle'>Editar</button></a>"+" "+" <a> <button id='deletar' name='deletar' type='submit' onClick='clicar();' class='btn btn-danger btn-sm'>Excluir</button></a>",
			      "targets": -1,
			    }
			  ],
        "language": {
            "sEmptyTable": "Nenhum registro encontrado",
            "sInfo": "Mostrando de _START_ at\u00e9 _END_ de _TOTAL_ registros",
            "sInfoEmpty": "Mostrando 0 at\u00e9 0 de 0 registros",
            "sInfoFiltered": "(Filtrados de _MAX_ registros)",
            "sInfoPostFix": "",
            "sInfoThousands": ".",
            "sLengthMenu": "Resultados/P\u00e1gina _MENU_",//_MENU_ Resultados/P\u00e1gina
            "sLoadingRecords": "Carregando...",
            "sProcessing": "Processando...",
            "sZeroRecords": "Nenhum registro encontrado",
            "sSearch":"Pesquisar",
            "searchPlaceholder": "Por nome ou categoria",
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

