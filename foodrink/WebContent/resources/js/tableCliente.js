$(document).ready(function() {
	initTableCliente();
});

function updateStatus(obj, param) {
	  var column = $(obj).parents("tr").find("td:nth-child(" + param + ")");
	  document.getElementById("cliente.idCliente").value = column.html();
	  $('#modalExemploC').modal('hide');
}

function initTableCliente() {
	$('#tabelaCliente')
			.DataTable(
					{
						"autoWidth" : true,
						"ordering" : false,
						"deferRender" : true,
						"paging" : true,
						"responsive" : true,
						"pageLength" : 25,
						"lengthChange" : true,
						"lengthMenu" : [ 10, 25, 50, 75, 100 ],
						"pagingType" : 'simple_numbers',
						"searching" : true,
						"search" : {
							"search" : "",
							"smart" : true,
						},
						"searchDelay" : 400,
						"processing" : true,
						"serverSide" : false,
						"ajax" : {
							"url" : 'listarClientes',
							"type" : 'GET',
							"data" : {
								
							}
						},
						"columnDefs" : [ {
							"data" : null,
							"defaultContent" : "<button id='selecao' type='button' class='btn btn-success btn-sm' onclick='updateStatus(this, 1)' >Selecionar</button>",
							"targets" : -1,
						} ],
						"language" : {
							"sEmptyTable" : "Nenhum registro encontrado",
							"sInfo" : "",
							"sInfoEmpty" : "",
							"sInfoFiltered" : "",
							"sInfoPostFix" : "",
							"sInfoThousands" : ".",
							"sLengthMenu" : "",
							"sLoadingRecords" : "Carregando...",
							"sProcessing" : "Processando...",
							"sZeroRecords" : "Nenhum registro encontrado",
							"sSearch" : "Pesquisar",
							"oPaginate" : {
								"sNext" : "Pr\u00f3ximo",
								"sPrevious" : "Anterior",
								"sFirst" : "Primeiro",
								"sLast" : "\u00daltimo"
							},
							"oAria" : {
								"sSortAscending" : ": Ordenar colunas de forma ascendente",
								"sSortDescending" : ": Ordenar colunas de forma descendente"
							}
						}
					});
}
