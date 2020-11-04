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
	href="../../resources/vendor/bootstrap/css/bootstrap-datepicker.css">
<link type="text/css" rel="stylesheet"
	href="../../resources/vendor/bootstrap/css/bootstrap-datetimepicker.min.css">
<link rel="icon" 	 type="image/png" href="../../resources/img/icone.ico" />

<title>FooDrink</title>

</head>

<body id="page-top" class="fundo">

	<c:import url="../../template/sidbar-a.jsp" />
	<div class="container col-md-12">
		<div class="row justify-content-center" style="margin-top: -50px;">
			<div class="col-md-12">
				<div class="card o-hidden border-0 shadow-lg my-5 invisivel">
					<h5 class="text-gray-900 mb-4 alert alert-info text-center">
						<b>Cadastro e Alteração de Reserva</b>
					</h5>

					<div class="card-body p-0">
						<!-- Nested Row within Card Body -->
						<div class="row">

							<div class="col-md-12" style="margin: auto;">
								<div class="p-5">
									<div class="text-center"></div>
									<form id="formNovaReserva" method="POST" action="nova-or-update-reserva" style="margin-top: -50px">
										<h6
											class="text-gray-900 mb-4 alert alert-info col-md-12 text-center">
											<b>Campos obrigatórios(<span class="aviso">*</span>).
											</b>
										</h6>
										<span class="text-center"><span
													class="aviso">**</span>Reservas com atraso apartir de 20 minutos serão canceladas automaticamente.<span
													class="aviso">**</span></span>										
										<div class="text-center" id="alerta">
											<c:if test="${!empty msgAlerta}">
													<c:choose>
																<c:when test="${msgAlerta eq 'cadastroSucesso'}"><span style="color: green"><b>Cadastrado com sucesso.</b></span></c:when>
																<c:when test="${msgAlerta eq 'atualizoSucesso'}"><span style="color: green"><b>Atualizado com sucesso.</b></span></c:when>
																<c:when test="${msgAlerta eq 'erro'}"><span style="color: red"><b>Verifique os dados e tente novamente.</b></span></c:when>
													</c:choose>
											</c:if>
										</div>
										<fieldset class="border p-3 fildset ">
											<legend class="w-auto ">Reserva</legend>
											<input type="text" id="empresa_identificador" name="empresa_identificador" value="${param.empresaID}" hidden="hidden">
											<div class="form-group">
												<label for="idReserva" class="margembaixo">ID/Reserva</label>
												<input type="text" class="form-control form-control-user"
													id="idReserva" name="idReserva"
													aria-describedby="idReserva"
													placeholder="Automático" readonly="readonly"
													value="${reserva.idReserva}">
											</div>

										   <div class="form-group">
												<label for="cliente.idCliente" class="margembaixo">Cliente<span
													class="aviso">*</span></label> <input type="text"
													class="form-control form-control-user"
													id="cliente.idCliente" data-toggle="modal"
													data-target="#modalExemploC" name="cliente.idCliente"
													aria-describedby="cliente.idCliente" readonly="readonly"
													placeholder="Identificação do cliente "
													value="${reserva.cliente.idCliente}${param.usuarioID}">
											</div>
											<!--  -->

											 <div class="form-group">
											 <label id="data" hidden="true">${reserva.data}</label>
												<label for="data" class="margembaixo">Data<span
													class="aviso">*</span></label> <input type="text"
													class="form-control form-control-user" id="dataString"
													name="data" aria-describedby="data"
													placeholder="Informe uma data" 
													value="${reserva.data}" readonly="readonly">
											</div>
											
											<div class="form-group">
												<label id="selecaoMesa" hidden="true">${reserva.mesa.idMesa}</label>
												<label for="mesa.idMesa" class="margembaixo">Mesa<span
													class="aviso">*</span></label> <select
													class="form-control form-control-user"
													id="mesa.idMesa" name="mesa.idMesa"
													aria-describedby="mesa.idMesa" >
													<option>Selecione</option>
													
												</select>
											</div>		

											 <div class="form-group">
											  <label id="hora" hidden="true">${reserva.hora}</label>
												<label for="hora" class="margembaixo">Hora<span
													class="aviso">*</span></label> <input type="text"
													class="form-control form-control-user" id="horaString"
													name="hora" aria-describedby="hora"
													placeholder="Informe um horário" readonly="readonly"
													value="${reserva.hora}">
											</div> 
 
											<div class="form-group" style="margin-bottom: -15px;">
												<label for="chaveStatus" class="margembaixo">Status<span
													class="aviso">*</span></label><br>
												<div
													class="custom-control custom-radio custom-control-inline">
													<input type="radio" class="custom-control-input"
														id="statusP" name="status" value="PENDENTE"
														<c:if test="${reserva.status == 'PENDENTE'}"><c:out value="checked='checked'"/></c:if>>
													<label class="custom-control-label" for="statusP">Pendente</label>
												</div>
												<sec:authorize access="isAuthenticated() and hasRole('ROLE_EMPRESA')">
												<div
													class="custom-control custom-radio custom-control-inline">
													<input type="radio" class="custom-control-input"
														id="statusC" name="status" value="CONFIRMADA"
														<c:if test="${reserva.status == 'CONFIRMADA'}"><c:out value="checked='checked'"/></c:if>>
													<label class="custom-control-label" for="statusC">Confirmada</label>
												</div>
												<div
													class="custom-control custom-radio custom-control-inline">
													<input type="radio" class="custom-control-input"
														id="statusCA" name="status" value="CANCELADA"
														<c:if test="${reserva.status == 'CANCELADA'}"><c:out value="checked='checked'"/></c:if>>
													<label class="custom-control-label" for="statusCA">Cancelada</label>
												</div>
												<div
													class="custom-control custom-radio custom-control-inline">
													<input type="radio" class="custom-control-input"
														id="statusF" name="status" value="FINALIZADA"
														<c:if test="${reserva.status == 'FINALIZADA'}"><c:out value="checked='checked'"/></c:if>>
													<label class="custom-control-label" for="statusF">Finalizada</label>
												</div>
												</sec:authorize>
												<br> <label for="status" class="error"></label>
											</div>
										</fieldset>
										<br>
										<button id="reserva" type="submit"
											class="btn btn-primary btn-user btn-block">
											<i class="fa fa-save"> </i> Salvar
										</button>
									</form>
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

	</div></div></div>

	<script type="text/javascript"
		src="../../resources/vendor/bootstrap/js/bootstrap-datepicker.min.js"></script>
	<script type="text/javascript"
		src="../../resources/vendor/bootstrap/js/bootstrap-datepicker.pt-BR.min.js"></script>
	<script type="text/javascript"
		src="../../resources/vendor/bootstrap/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript"
		src="../../resources/vendor/bootstrap/js/bootstrap-datetimepicker.pt-BR.js"></script>

	<script type="text/javascript"
		src="../../resources/js/demo/jquery.validate.js"></script>
	<script type="text/javascript"
		src="../../resources/js/demo/additional-methods.js"></script>
	<script type="text/javascript"
		src="../../resources/js/demo/jquery.mask.min.js"></script>
	<script type="text/javascript"
		src="../../resources/js/demo/messages_pt_BR.js"></script>
	<script type="text/javascript" src="../../resources/js/reserva-val.js">	</script>

<sec:authorize access="isAuthenticated() and hasRole('ROLE_EMPRESA')">
	<div class="modal fade text-center" id="modalExemploC" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content fundo">
				<h1 class="h4 text-gray-900 mb-4 alert alert-info text-center">
					<b>Selecionar Cliente</b>
					<button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
         		 <span aria-hidden="true">&times;</span>
        	</button>
				</h1>
				<div class="modal-body" style="overflow: auto;">
					<c:import url="../tabela-clientes.jsp" />
				</div>
			</div>
		</div>
	</div>
</sec:authorize>


</body>

</html>
