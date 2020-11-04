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
		<div class="row justify-content-center">
			<div class="col-md-9">
				<div class="card o-hidden border-0 shadow-lg my-5 invisivel">
					<div class="card-body p-0">
						<div class="row">
							<div class="col-md-12">
								<h1 class="h4 text-gray-900 mb-4 alert alert-info text-center">
									<b>Atualização de Senha</b>
								</h1>
								<div class="text-center"
									style="margin-bottom: -80px; margin-top: -20px;">
									<br><br><br><br>
								</div>

									<div class="text-center textsize">
										<c:if test="${!empty msgAlerta}"> <span style="color: red"> <b> Verifique seus dados e tente novamente. </b></span> </c:if>
									</div>
									
								<div class="p-5">
									<form id="form_nova_senha" class="" action="redefinir-senha" method="POST"
										style="margin-top: -50px">
										<br>
										<h6
											class="text-gray-900 mb-4 alert alert-info col-md-12 text-center">
											<b>Campos obrigatórios(<span class="aviso">*</span>).</b>
										</h6>

										<fieldset class="border p-3 fildset ">
											<legend class="w-auto">Nova Senha</legend>
											<div class="form-group margembaixo">
												<label for="id_usuario" class="margembaixo" hidden="true">ID<span
													class="aviso ">*</span></label> <input type="text" readonly="readonly" hidden="true"
													class="form-control form-control-user text-center"
													id="id_usuario" name="id_usuario" value="${id}">
											</div>
											
											<div class="form-group">
												<label for="nova_senha" class="margembaixo"
													style="margin-top: -50px">Nova
													senha<span
													class="aviso margembaixo">*</span></label> <input type="password"
													class="form-control form-control-user"
													id="nova_senha" name="nova_senha"
													aria-describedby="emailHelp"
													placeholder="Informe sua nova senha">
											</div>
											<div class="form-group">
												<label for="confirme_nova_senha" class="margembaixo">Confirme a nova senha<span
													class="aviso margembaixo">*</span></label>
												<input type="password"
													class="form-control form-control-user"
													id="confirme_nova_senha" name="confirme_nova_senha"
													aria-describedby="emailHelp"
													placeholder="Informe sua senha novamente">
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
