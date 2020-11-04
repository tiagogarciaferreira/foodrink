<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="pt-br">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" 	 type="image/png" href="../resources/img/icone.ico" />
<title>FooDrink</title>


</head>

<header style="position: fixed; z-index: 1" class="tamanho">
	<c:import url="../template/menu.jsp" />
</header>

<body class="fundo" style="z-index: -1">

	<c:import url="../template/app.jsp" />

	<br>
	<br>
	<br>
	<br>
	<div class="container col-md-12">
		<div class="row justify-content-center " style="margin-top: -50px">
			<div class="col-md-12">
				<div
					class="card o-hidden border-0 shadow-lg my-5 fundo invisivel text-center bg-transparent ">
					<a href="#" data-toggle="modal"
						data-target="#modal-busca-estabelecimento"
						class="mb-4 alert alert-success col-md-12 invisivel btn btn-info text-center"
						style="cursor: pointer; color: white"> <b>Pesquisar
							Estabelecimentos</b><br></a>
					<div class="card-body p-0">
						<div class="text-center textsize">
							<c:if test="${!empty msgAlerta}">
								<span style="color: red"> <b> Nenhum resultado encontrado. </b></span>
							</c:if>
						</div>
						
						<div class="row">
							<div class="col-md-7" style="margin: auto;">
								<div class="p-5">
									<c:if test="${!empty empresa}">
										 <c:set var="empresa" scope="session" value="${empresa}"/>
									</c:if>
										<c:forEach  var="empresa" items="${empresa}">
											<div class="col-xl-3 col-md-12 mb-4 ">
												<div class="text-xs mb-0 font-weight-bold text-gray-800"
													style="text-align: left; margin-top: 10px;">
													<a href="categorias-produto-estabelecimento?empresaID=${empresa.idEmpresa}" class="btn btn-outline-info btn-xs" id="cardapio"
														style="text-align: right;">Cardápio</a>
												</div>
												<div
													class="card border-left-success shadow h-100 py-2 invisivel justify-content-center">
													<div class="card-body">
														<div class="row no-gutters align-items-center">
															<div>
																<i> <img class="img-profile rounded-circle"
																	src="${empresa.usuario.imagem}" width="60px" height="60px">
																</i>
															</div>
															<div class="col mr-2">
																<div
																	class="font-weight-bold text-success text-uppercase mb-1">${empresa.usuario.nome}</div>
															</div>
														</div>
													</div>
														<label class="text-xs  font-weight-bold rodape"
															style="text-align: left; margin-left: 5px;margin-right: 5px;" >Contato: <span class="text-gray-800">${empresa.usuario.telefone}</span>
														</label>
														<label class="text-xs  font-weight-bold rodape"
															style="text-align: left; margin-left: 5px;margin-right: 5px;cursor: pointer;" onclick="detalhes(${empresa.idEmpresa});" title="Mais detalhes">Endereço <span class="text-gray-800" id="${empresa.idEmpresa}" style="display: none;">${empresa.usuario.endereco.estado.nome} (${empresa.usuario.endereco.cidade.nome}) - Bairro: ${empresa.usuario.endereco.bairro} - Rua: ${empresa.usuario.endereco.rua} - Nº ${empresa.usuario.endereco.numero} - Cep: ${empresa.usuario.endereco.cep} - Referência: ${empresa.usuario.endereco.complemento} </span>
														</label>
														<label class="text-xs  font-weight-bold rodape margembaixo"
															style="text-align: left; margin-left: 5px;margin-right: 5px;cursor: pointer;" onclick="detalhes(${empresa.idEmpresa + 1});" title="Mais detalhes">Atendimento <span class="text-gray-800"  id="${empresa.idEmpresa + 1}" style="display: none;">
															<c:set var="dias" value="${fn:split(empresa.funcionamento,'@')}" />
															Segunda Feira: <span style="color:white;">${dias[0]}</span> <br>
															Terça Feira: <span style="color:white;">${dias[1]}</span> <br>
															Quarta Feira: <span style="color:white;">${dias[2]}</span> <br>
															Quinta Feira: <span style="color:white;">${dias[3]}</span> <br>
															Sexta Feira: <span style="color:white;">${dias[4]}</span> <br>
															Sábado: <span style="color:white;">${dias[5]}</span> <br>
															Domingo: <span style="color:white;">${dias[6]}</span>
															</span>
														</label>
												</div> 
											</div>
										</c:forEach>
									</div>
									<div class="text-center" style="">
										<c:if test="${empty empresa}">
											<img alt="" src="../resources/img/logo.png"
												style="opacity: 0.5;">
										</c:if>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

<div class="modal fade" id="modal-busca-estabelecimento" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content  invisivel">
				<h1 class="h4 text-gray-900 mb-4 alert alert-info text-center">
					<b>Buscar</b>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Fechar">
						<span aria-hidden="true">&times;</span>
					</button>
				</h1>

				<div class="text-center"
					style="margin-bottom: -80px; margin-top: -20px;">
					<br>
					<div class="text-center row justify-content-center"
						style="margin: auto;">
						<h6
							class="text-gray-900 mb-4 alert alert-info col-md-9 text-center">
							<b>Campos obrigatórios(<span class="aviso">*</span>).
							</b>
						</h6>
					</div>
				</div>

				<div class="row">
					<div class="col-md-12" style="margin: auto;">
						<div class="p-5">
							<form id="form_filtro_estabelecimento"
								action="pesquisar-estabelecimento" method="POST">
								<br>
								<fieldset class="border p-3 fildset">
									<legend class="w-auto ">Busca</legend>

									<div class="form-group">
										<label id="selecaoEstado" hidden="true">${filtro.estado.idEstado}</label>
										<label for="estado.idEstado" class="margembaixo">Estado<span
											class="aviso">*</span></label> <select
											class="form-control form-control-user" id="estado.idEstado"
											name="estado.idEstado" aria-describedby="estado.idEstado">
											<option>Selecione</option>
										</select>
									</div>

									<div class="form-group">
										<label id="selecaoCidade" hidden="true">${filtro.cidade.idCidade}</label>
										<label for="cidade.idCidade" class="margembaixo">Cidade<span
											class="aviso">*</span></label> <select
											class="form-control form-control-user" id="cidade.idCidade"
											name="cidade.idCidade" aria-describedby="cidade.idCidade">
											<option>Selecione</option>
										</select>
									</div>

								</fieldset>
								<br>
								<button type="submit"
									class="btn btn-primary btn-user btn-block mao">
									<i class="fa fa-search"></i> Buscar
								</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript"
		src="../resources/js/demo/jquery.validate.js"></script>
	<script type="text/javascript"
		src="../resources/js/demo/additional-methods.js"></script>
	<script type="text/javascript"
		src="../resources/js/demo/jquery.mask.min.js"></script>
	<script type="text/javascript"
		src="../resources/js/demo/messages_pt_BR.js"></script>
	<script type="text/javascript" src="../resources/js/validacao_menor.js"></script>
	<script type="text/javascript" src="../resources/js/load.js"></script>
</body>


</html>
