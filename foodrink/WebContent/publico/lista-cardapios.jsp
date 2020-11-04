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
					class="card o-hidden border-0 shadow-lg my-5 fundo invisivel text-center bg-transparent">
					<a href="#" data-toggle="modal"
						data-target="#modal-busca-estabelecimento"
						class="mb-4 alert alert-success col-md-12 invisivel btn btn-info text-center"
						style="cursor: pointer; color: white"> <b>Categorias de
							Produtos</b><br></a>
					<div class="card-body p-0">
						<div class="text-center textsize">
							<c:if test="${!empty msgAlerta}">
								<span style="color: red"> <b> Nenhum resultado
										encontrado. </b></span>
							</c:if>
						</div>

						<div class="row form-group">
							<div class="col-md-12" style="margin: auto;">
								<div class="p-5">
									<div class="row">
									 <c:forEach var="produto" items="${produto}">

										<div class="col-xl-3 col-md-5 mb-4">
											<div
												class="card border-left-success shadow h-100 py-2 invisivel">
												<div class="card-body">
													<div class="row no-gutters align-items-center">
														
														<div class="col mr-2" style="text-align: left; margin-left: 5px;">
															<label class="text-xs font-weight-bold rodape">
																Nome: <span class="text-gray-800">${produto.nome}</span><br>
																Tamanho: <span class="text-gray-800">${produto.tamanho}</span><br>
																Preço(R$):	<span class="text-gray-800">${produto.preco}</span><br>
																Descrição: <span class="text-gray-800">${produto.descricao}</span><br>
															</label>
														</div>
														<div><i> <img alt="Imagem" class="img-profile rounded-circle"
																src="${produto.imagem}" width="60px" height="60px">
															</i>
														</div>
													</div>
												</div>

											</div>
										</div>
									
									 </c:forEach>
									</div>
									<div class="text-center" style="">
										<c:if test="${empty produto}">
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
	</div>

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<div class="modal fade" id="modal-busca-estabelecimento" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content invisivel">
				<h1 class="h4 text-gray-900 mb-4 alert alert-info text-center">
					<b>Categorias</b>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Fechar">
						<span aria-hidden="true">&times;</span>
					</button>
				</h1>

				<div class="row col-md-12 justify-content-center"
					style="margin: auto;">
					<c:if test="${!empty categoria}">
						 <c:set var="categoria" scope="session" value="${categoria}"/>		
					</c:if>
					<c:forEach var="categoria" items="${categoria}">
										<div
											class="font-weight-bold text-success text-uppercase mb-1 text-center">
											<a 	href="produtos-categoria-estabelecimento?categoriaID=${categoria.idCategoria}"
												class="btn btn-outline-info btn-lg">${categoria.nome}<b class="invisivel bola " > ${categoria.quantidadeProdutos}</b></a>
										</div>
									
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	</div>

	<script type="text/javascript"
		src="../../resources/js/demo/jquery.validate.js"></script>

	</div>
	</div>
	</div>


</body>

</html>
