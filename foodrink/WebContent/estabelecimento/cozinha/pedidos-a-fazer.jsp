<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

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
	href="../resources/vendor/datatables/datatable.css">
	<link rel="icon" 	 type="image/png" href="../../resources/img/icone.ico" />
<title>FooDrink</title>



</head>

<body id="page-top" class="fundo ">
	<div class=" shadow mb-4 invisivel col-md-12" style="margin-left: 0px; margin-right: 5px;border-radius: 5px">
		<h1 class="h4 text-gray-900 mb-4 alert alert-info text-center ">
			<b>Pedidos a Fazer</b>
		</h1>
		<div class="text-center" id="alerta">
				<c:if test="${!empty msgAlerta}">
					<c:choose>
						<c:when test="${msgAlerta eq 'confirmadoSucesso'}"><span style="color: green"> <b>Confirmado com sucesso.</b></span></c:when>
				        <c:when test="${msgAlerta eq 'erro'}"><span style="color: red"> <b>Erro ao confirmar o pedido, tente novamente.</b></span></c:when>
					</c:choose>
			 </c:if>
		</div>
		
		<div class="card-body invisivel col-md-12" style="border-radius: 5px;">
			<div class="table-responsive invisivel">
			<a href="" hidden="true" id="solicitacao"></a> 
				<table class="table table-bordered" id="tabelaACozinhafazer" style="width: 100%; overflow: auto;">
					<thead class="text-center">
						<tr>
							<th style="width: 1px;">ID</th>
							<th style="width: 1px;">Pedido</th>
							<th>Produto</th>
							<th style="width: 1px;">Quantidade</th>
							<th style="width: 1px;">Observação</th>
							<th style="width: 1px;">Ações</th>
						</tr>
					</thead>
					<tbody class="text-center">
						
					</tbody>
					<tfoot class="text-center">
					     <tr>
							<th style="width: 1px;">ID</th>
							<th style="width: 1px;">Pedido</th>
							<th>Produto</th>
							<th style="width: 1px;">Quantidade</th>
							<th style="width: 1px;">Observação</th>
							<th style="width: 1px;">Ações</th>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

<div class="modal" tabindex="-1" role="dialog" id="modalConfirme">
  <div class="modal-dialog" role="document">
    <div class="modal-content fundo">
      <div class="modal-header">
        <h5 class="modal-title">Confirmação</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p>Realmente deseja marcar o item como pronto?</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="verificaOpcao(this);" value="NAO" >Cancelar</button>
        <button type="button" class="btn btn-primary" onclick="verificaOpcao(this);" value="SIM" >Confirmar</button>
      </div>
    </div>
  </div>
</div>

	</div></div></div>
</body>

</html>
