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
		<link rel="icon" 	 type="image/png" href="../resources/img/icone.ico" />
	<title>FooDrink</title>
</head>

<header style="position: fixed;z-index: 1" class="tamanho" >
	<c:import url="../template/menu.jsp"/>
</header>

<body class=" fundo" style="z-index: -1">
<c:import url="../template/app.jsp"/>
<br><br>
	<div class="container" style="margin: auto;">
		<div class="row justify-content-center ">
			<div class="col-md-6">
				<div class="card o-hidden border-0 shadow-lg my-5 invisivel">
					<div class="card-body p-0 ">
						<div class="row">
							<div class="col-md-12 " style="margin: auto;">
							<h1 class="h4 text-gray-900 mb-4 alert alert-info text-center"><b>Informação</b></h1>
                				<div class="p-4 text-center">
									<div class="text-center textsize">
										<c:if test="${!empty msgAlerta}">
												<c:choose>
														<c:when test="${msgAlerta eq 'sucessoCadastro'}"><span style="color: green"> <b>Cadastro confirmado com sucesso.</b></span></c:when>
														<c:when test="${msgAlerta eq 'sucessoSenha'}"><span style="color: green"> <b>Senha redefinida com sucesso.</b></span></c:when>
														<c:when test="${msgAlerta eq 'solicitacaoInvalida'}"><span style="color: red"> <b>Solicitação inválida tente novamente.</b></span></c:when>
														<c:when test="${msgAlerta eq 'emailSucesso'}"><span style="color: green"> <b>Email enviado com sucesso, verifique sua caixa de emails.</b></span></c:when>
														<c:when test="${msgAlerta eq 'contatoSucesso'}"><span style="color: green"> <b>Contato enviado com sucesso.</b></span></c:when>
												</c:choose>
											</c:if>
									 	<br><br><a class="small btn btn-primary btn-user btn-block" href="../publico/login.jsp">Ir para o login</a>
									</div>
								</div>
						</div>
					</div>
				</div>
			</div>
	</div>
  </div>
</div>

</body>

</html>
