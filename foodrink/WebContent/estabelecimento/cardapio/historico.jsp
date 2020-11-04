<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="pt-br">

<head>

<meta charset="ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" 	 type="image/png" href="../../resources/img/icone.ico" />
<title>FooDrink</title>

</head>

<body id="page-top" class="fundo">

	<c:import url="../../template/sidbar-a.jsp" />
	<c:if test="${empty reserva && empty pedido && empty msgAlerta}">
		<script type="text/javascript">
			location.href = 'get-meu-historico'
		</script>
	</c:if>
	<div class="container col-md-12">
		<div class="row justify-content-center " style="margin-top: -50px">
			<div class="col-md-12">
				<div
					class="card o-hidden border-0 shadow-lg my-5 invisivel text-center bg-transparent">
					<div class="card-body p-0" style="background-color: #0B3861;">
						<h5 class="text-gray-900 mb-4 alert alert-info text-center">
							<b>Meu Histórico</b>
						</h5>
						<div class="row form-group">
							<div class="col-md-12" style="margin: auto;">
								<div class="p-5">
									<div class="row">										
										<c:if test="${empty pedido}">
											<div class="col-xl-3 col-md-12 mb-4">
												<div
													class="card border-left-success shadow h-100 py-2 invisivel">
													<div class="card-body">
														<div class="row no-gutters align-items-center">
														<label class=" text-xs font-weight-bold rodape" style="margin-left: 5px;"> 
																<span class="text-gray-800">Nenhum pedido encontrado...</span>
															</label>
															<br>
														</div>
													</div>
												</div>
											</div>
										</c:if>
										
										<c:forEach var="pedido" items="${pedido}">
											<div class="col-xl-3 col-md-12 mb-4">
												<div
													class="card border-left-success shadow h-100 py-2 invisivel">
													<div class="card-body">
														<div class="row no-gutters align-items-center">
														<label class="text-xs  font-weight-bold rodape" style="margin-left: 5px;">Tipo: 
																<span class="" style="color:green;"> Pedido</span>
															</label>
															<br>
															<label class="text-xs  font-weight-bold rodape" style="margin-left: 5px;">Numero:
																<span class="text-gray-800">${pedido.idPedido}</span>
															</label>
															<br>
															<label class="text-xs  font-weight-bold rodape" style="margin-left: 5px;">Data:
																<span class="text-gray-800">${pedido.data}</span>
															</label>
															<br>
															<label class="text-xs  font-weight-bold rodape" style="margin-left: 5px;">Hora:
																<span class="text-gray-800">${pedido.hora}</span>
															</label>
															<br>
															<label class="text-xs  font-weight-bold rodape" style="margin-left: 5px;">Observação:
																<span class="text-gray-800">${pedido.observacao}</span>
															</label>
															<br>
															<label class="text-xs  font-weight-bold rodape" style="margin-left: 5px;">Status:
																<span class="text-gray-800">${pedido.status}</span>
															</label>
															<br>
															<label class="text-xs  font-weight-bold rodape" style="margin-left: 5px;">Mesa:
																<span class="text-gray-800">${pedido.mesa.numero}</span>
															</label>
															<br>
															<label class="text-xs  font-weight-bold rodape" style="margin-left: 5px;">Estabelecimento:
																<span class="text-gray-800">${pedido.empresa.usuario.nome}</span>
															</label>
															<br>
															
															<table class="text-xs" style="width:100%; border: 1px solid black; overflow: auto;margin-left: 5px;" class="margembaixo">
															  <tr>
															    <th style="border: 2px solid white;">Imagem</th>
															    <th style="border: 2px solid white;">Produto</th>
															    <th style="border: 2px solid white;">Quantidade</th>
															  </tr>
															  <c:forEach var="item" items="${pedido.listaItens}">
																  <tr>
																    <td style="border: 2px solid white;">
																    <i> <img class="img-profile rounded-circle"
																		src="${item.produto.imagem}" alt="000" width="30px" height="30px">
																	</i>
																    </td>
																    <td style="border: 2px solid white;">${item.produto.nome} - ${item.produto.tamanho}</td>
																    <td style="border: 2px solid white;">${item.quantidade}</td>
																  </tr>
															  </c:forEach>
															</table>
															<br>
															<label class="text-xs  font-weight-bold rodape" style="margin-left: 5px;">Valor do Pedido(R$):
																<span class="text-gray-800">${pedido.valorPedido}</span>
															</label>
														</div>
													</div>
													<br>
												</div>
											</div><br>
										</c:forEach>
										
										<c:if test="${empty reserva}">
											<div class="col-xl-3 col-md-12 mb-4">
												<div
													class="card border-left-success shadow h-100 py-2 invisivel">
													<div class="card-body">
														<div class="row no-gutters align-items-center">
														<label class=" text-xs font-weight-bold rodape" style="margin-left: 5px;"> 
																<span class="text-gray-800">Nenhuma reserva encontrada...</span>
															</label>
															<br>
														</div>
													</div>
												</div>
											</div>
										</c:if>
										
										<c:forEach var="reserva" items="${reserva}">
											<div class="col-xl-3 col-md-12 mb-4">
												<div
													class="card border-left-success shadow h-100 py-2 invisivel">
													<div class="card-body">
														<div class="row no-gutters align-items-center">
														<label class="text-xs  font-weight-bold rodape" style="margin-left: 5px;">Tipo: 
																<span class="" style="color:green;"> Reserva</span>
															</label>
															<br>
															<label class="text-xs  font-weight-bold rodape" style="margin-left: 5px;">Numero:
																<span class="text-gray-800">${reserva.idReserva}</span>
															</label>
															<br>
															<label class="text-xs  font-weight-bold rodape" style="margin-left: 5px;">Data:
																<span class="text-gray-800">${reserva.data}</span>
															</label>
															<br>
															<label class="text-xs  font-weight-bold rodape" style="margin-left: 5px;">Hora:
																<span class="text-gray-800">${reserva.hora}</span>
															</label>
															<br>
															<label class="text-xs  font-weight-bold rodape" style="margin-left: 5px;">Status:
																<span class="text-gray-800">${reserva.status}</span>
															</label>
															<br>
															<label class="text-xs  font-weight-bold rodape" style="margin-left: 5px;">Mesa: 
																<span class="text-gray-800">Nº ${reserva.mesa.numero} : ${reserva.mesa.lugares} Lugares </span>
															</label>
															<br>
															<label class="text-xs  font-weight-bold rodape" style="margin-left: 5px;">Estabelecimento:
																<span class="text-gray-800">${reserva.empresa.usuario.nome}</span>
															</label>
															<br>
														</div>
													</div>
													<br>
												</div>
											</div><br>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>





	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	</div>
	</div>
	</div>


</body>

</html>
