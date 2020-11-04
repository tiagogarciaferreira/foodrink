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
	<div class="container col-md-12">
		<div class="row justify-content-center "
			style="margin-top: -50px">
			<div class="col-md-12">
				<div class="card o-hidden border-0 shadow-lg my-5 invisivel">
					<h5 class="text-gray-900 mb-4 alert alert-info text-center">
						<b>Cadastro e Alteração de Categoria</b>
					</h5>
					<div class="card-body p-0">
						<div class="row">
							<div class="col-md-12" style="margin: auto;">
								<div class="p-5">
									<form id="formNovaCategoria" action="nova-or-update-categoria" method="POST"
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
											<legend class="w-auto ">Categoria</legend>
											
											<div class="form-group">
											<label for="idCategoria" class="margembaixo" >ID/Categoria</label>
											<input type="text" class="form-control form-control-user"
												id="idCategoria" name="idCategoria"
												aria-describedby="idCategoria"
												placeholder="Automático" readonly="readonly" value="${categoria.idCategoria}">
										</div>
											
											<div class="form-group">
												<label for="nome" class="margembaixo">Nome<span
													class="aviso">*</span></label> <input type="text"
													class="form-control form-control-user" id="nome"
													name="nome" aria-describedby="nome"
													placeholder="Nome da categoria" value="${categoria.nome}">
											</div>
											
											<div class="form-group">
												<label for="quantidadeProdutos" class="margembaixo">Quantidade/Produtos</label> <input type="text"
													class="form-control form-control-user" id="quantidadeProdutos"
													name="quantidadeProdutos" value="${categoria.quantidadeProdutos}" readonly="readonly">
											</div>
											
											<div class="form-group margembaixo">
											<label for="status" class="margembaixo">Status<span	class="aviso">*</span></label><br>
												<div class="custom-control custom-radio custom-control-inline">
												  <input type="radio" class="custom-control-input" id="statusA" name="chaveStatus" value="ATIVA" 
												  <c:if test="${categoria.status == true}"><c:out value="checked='checked'"/></c:if>	>
												  <label class="custom-control-label" for="statusA">Ativa</label>
												</div>
												<div class="custom-control custom-radio custom-control-inline">
												  <input type="radio" class="custom-control-input" id="statusI" name="chaveStatus" value="INATIVA" 
												  <c:if test="${categoria.status == false}"><c:out value="checked='checked'"/></c:if>  >
												  <label class="custom-control-label" for="statusI">Inativa</label>
												</div>
												<label for="chaveStatus" class="error"></label>
											</div>
										</fieldset>
										<br>
										<button type="submit" class="btn btn-primary btn-user btn-block"><i class="fa fa-save"> </i> Salvar</button>
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
	<script type="text/javascript" src="../../resources/js/categoria-val.js"></script>

</body>

</html>
