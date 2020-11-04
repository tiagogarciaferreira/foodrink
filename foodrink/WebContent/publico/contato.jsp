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
				<div class="card o-hidden border-0 shadow-lg my-5  invisivel">
					<div class="card-body p-0">
						<div class="row">
							<div class="col-md-12">
								<h1 class="h4 text-gray-900 mb-4 alert alert-info text-center">
									<b>Contato</b>
								</h1>
									<div class="text-center textsize">
										<c:if test="${!empty msgAlerta}"> <span style="color: red"> <b><br> Verifique seus dados e tente novamente. </b></span> </c:if>
									</div>
									<br>
								<div class="p-5">
									<form id="form_contato" action="enviar-contato" method="POST"
										style="margin-top: -50px">
									<h6
											class="text-gray-900 mb-4 alert alert-info col-md-12 text-center">
											<b>Campos obrigatórios(<span class="aviso">*</span>).</b>
										</h6>
										<fieldset class="border p-3 fildset">
											<legend class="w-auto ">Contato</legend>
											<div class="form-group">
												<label for="nome" class="margembaixo">Nome<span
													class="aviso">*</span></label> <input type="text"
													class="form-control form-control-user" id="nome"
													name="nome" aria-describedby="nome"
													placeholder="Informe seu nome">
											</div>
											<div class="form-group">
												<label for="email" class="margembaixo">Email<span
													class="aviso">*</span></label> <input type="email"
													class="form-control form-control-user" id="email"
													name="email" aria-describedby="email"
													placeholder="Informe seu email">
											</div>

											<div class="form-group">
												<label for="assunto" class="margembaixo">Assunto<span
													class="aviso">*</span></label> <input type="text"
													class="form-control form-control-user" id="assunto"
													name="assunto" placeholder="Informe o assunto">
											</div>

											<div class="form-group">
												<label for="mensagem" class="margembaixo">Mensagem<span class="aviso">*</span>
												</label> <textarea placeholder="Escreva sua mensagem" class="form-control form-control-user" id="mensagem"
													name="mensagem" ></textarea>
											</div>
										</fieldset>
										<br>
										<button type="submit"
											class="btn btn-primary btn-user btn-block mao"><i class="fa fa-location-arrow"></i> Enviar</button>
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
