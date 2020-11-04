<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="pt-br">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link type="text/css" rel="stylesheet"
	href="../../resources/vendor/datatables/datatable.css">
<title>FooDrink</title>



</head>

<body id="page-top" class="">
	<div class=" shadow mb-4 invisivel"
		style="margin-left: 5px; margin-right: 5px; border-radius: 5px">
		<div class="card-body invisivel " style="border-radius: 5px;">
			<div class="table-responsive invisivel">
				<table class="table table-bordered invisivel" id="tabelaCliente" style="width: 100%; overflow: auto;">
					<thead class="text-center">
						<tr> 
							<th style="width: 1px;">ID</th>
							<th>Nome</th>
							<th style="width: 1px;">Ação</th>
						</tr>
					</thead>
					<tbody class="text-center">
					</tbody>
					<tfoot class="text-center" >
						<tr>
							<th style="width: 1px;">ID</th>
							<th>Nome</th>
							<th style="width: 1px;">Ação</th>
						</tr>
					</tfoot>
					<tbody>

					</tbody>
				</table>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="../../resources/vendor/datatables/datatable.js"></script>
	<script type="text/javascript" src="../../resources/js/tableCliente.js"></script>
</body>

</html>
