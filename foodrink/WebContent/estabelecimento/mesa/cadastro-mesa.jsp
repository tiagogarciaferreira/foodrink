<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
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
	<div class="container col-md-12">
		<div class="row justify-content-center" style="margin-top: -50px;">
			<div class="col-md-12">
				<div class="card o-hidden border-0 shadow-lg my-5 invisivel">
					<h5 class="text-gray-900 mb-4 alert alert-info text-center">
						<b>Cadastro e Alteração de Mesa</b>
					</h5>

					<div class="card-body p-0">
						<!-- Nested Row within Card Body -->
						<div class="row">

							<div class="col-md-12" style="margin: auto;">
								<div class="p-5">
									<form id="formNovaMesa" action="nova-or-update-mesa" method="POST"
										style="margin-top: -50px">
										<h6
											class="text-gray-900 mb-4 alert alert-info col-md-12 text-center">
											<b>Campos obrigatórios(<span class="aviso">*</span>).
											</b>
										</h6>
											
										<div class="text-center" id="alerta">
											<c:if test="${!empty msgAlerta}">
													<c:choose>
																<c:when test="${msgAlerta eq 'cadastroSucesso'}"><span style="color: green"> <b>Cadastrado com sucesso.</b></span></c:when>
																<c:when test="${msgAlerta eq 'atualizoSucesso'}"><span style="color: green"> <b>Atualizado com sucesso.</b></span></c:when>
																<c:when test="${msgAlerta eq 'erro'}"><span style="color: red"> <b>Verifique os dados e tente novamente.</b></span></c:when>
													</c:choose>
											</c:if>
										</div>			

										<fieldset class="border p-3 fildset">
											<legend class="w-auto ">Mesa</legend>
											
											<div class="form-group">
											<label for="idMesa" class="margembaixo">ID/Mesa</label>
											<input type="text" class="form-control form-control-user"
												id="idMesa" name="idMesa"
												aria-describedby="idMesa"
												placeholder="Automático" readonly="readonly" value="${mesa.idMesa}">
										</div>

											<div class="form-group">
												<label for="numero" class="margembaixo">Numero<span
													class="aviso">*</span></label> <select
													class="form-control form-control-user" id="numero" name="numero"
													aria-describedby="numero">
													<option>Selecione</option>
													<c:forEach var="i" begin="1" end="1000" >
														<c:choose>
															<c:when test="${i != mesa.numero}">
															     <option value="${i}">${i}</option>  
															</c:when>
															<c:when test="${i == mesa.numero}">
															     <option value="${i}" selected="selected">${i}</option>  
															</c:when>
														</c:choose>
														
													</c:forEach>
											</select>
											</div>

											<div class="form-group">
												<label for="lugares" class="margembaixo">Lugares<span
													class="aviso">*</span></label> <select
													class="form-control form-control-user" id="lugares" name="lugares"
													aria-describedby="lugares">
													<option>Selecione</option>
													<c:forEach var="i" begin="1" end="10" >
														<c:choose>
															<c:when test="${i != mesa.lugares}">
															     <option value="${i}">${i}</option>  
															</c:when>
															<c:when test="${i == mesa.lugares}">
															     <option value="${i}" selected="selected">${i}</option>  
															</c:when>
														</c:choose>
														
													</c:forEach>
											</select>
											</div>

											<div class="form-group">
												<label for="descricao" class="margembaixo">Descrição</label> <input type="text"
													class="form-control form-control-user" id="descricao"
													name="descricao" aria-describedby="descricao"
													placeholder="Informe a descrição da mesa" value="${mesa.descricao}">
											</div>
										</fieldset>
										<br>
										<button id="mesa" type="submit" class="btn btn-primary btn-user btn-block"><i class="fa fa-save"> </i> Salvar</button>
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
		src="../../resources/js/demo/jquery.validate.js"></script>
	<script type="text/javascript"
		src="../../resources/js/demo/additional-methods.js"></script>
	<script type="text/javascript"
		src="../../resources/js/demo/jquery.mask.min.js"></script>
	<script type="text/javascript"
		src="../../resources/js/demo/messages_pt_BR.js"></script>
	<script type="text/javascript" src="../../resources/js/mesa-val.js"></script>

</body>

</html>
