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
<header style="position: fixed; z-index: 1" class="tamanho">
	<c:import url="../template/menu.jsp" />
</header>

<body class=" fundo" style="z-index: -1">
	<c:import url="../template/app.jsp" />
	<br>
	<br>
	<div class="container" style="margin: auto;">
		<div class="row justify-content-center">
			<div class="col-md-9">
				<div class="card o-hidden border-0 shadow-lg my-5 invisivel ">
					<div class="card-body p-0">
						<div class="row">
							<div class=" col-md-12" style="margin: auto;">
								<h1 class="h4 text-gray-900 mb-4 alert alert-info text-center">
									<b>Redefinição de Senha</b>
								</h1>
								<div class="text-center"
									style="margin-bottom: -80px; margin-top: -40px;">
									<br><br>	
									<div class="row justify-content-center">
										<p class="mb-4 text-center pad col-md-9 ">Basta informar
											seu endereço de e-mail (cadastrado) abaixo e voçê receberá um link para
											redefinir sua senha.</p>
									</div>
									<br>
									<br>

								</div>

								<div class="p-5">
									<form id="form_email_redefinicao_senha" class=""
										action="email-redefinir-senha" method="POST"
										style="margin-top: -50px">
										<div class="text-center textsize" id="alerta">
											<br> 
											<c:if test="${!empty msgAlerta}">
												<c:choose>
														<c:when test="${msgAlerta eq 'erro'}"><span style="color: red"> <b>Falha ao enviar o email, o mesmo não corresponde a nenhum usuário.</b></span></c:when>
												</c:choose>
											</c:if>
										</div>
										<br>
										<h6
											class="text-gray-900 mb-4 alert alert-info col-md-12 text-center">
											<b>Campos obrigatórios(<span class="aviso">*</span>).
											</b>
										</h6>
										<fieldset class="border p-3 fildset">
											<legend class="w-auto margembaixo">Solicitação</legend>
											<div class="form-group">
												<label for="email" class="margembaixo">Email<span
													class="aviso">*</span></label> <input type="email"
													class="form-control form-control-user"
													id="email_redefinicao" name="email_redefinicao"
													aria-describedby="email" placeholder="Informe seu email">
											</div>
										</fieldset>
										<br> 
										<button type="submit"
											class="btn btn-primary btn-user btn-block mao"> <i class="fa fa-check"> </i> Confirmar</button>
									</form>
								</div>
							</div>
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

</body>

</html>
