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
<link rel="icon" 	 type="image/png" href="../../resources/img/icone.ico" />
<title>FooDrink</title>

</head>

<body id="page-top" class="fundo">

<c:import url="../../template/sidbar-a.jsp" />
	<div class="container" style="margin: auto;">
		<div class="row justify-content-center" style="margin-top: -50px;">
			<div class="col-md-12">
				<div class="card o-hidden border-0 shadow-lg my-5 invisivel">
					<div class="card-body p-0">
						<div class="row">
							<div class="col-md-12">
								<h5 class="text-gray-900 mb-4 alert alert-info text-center">
									<b>Atualização de Senha</b>
								</h5>

								<div class="p-5">
									<form id="form_nova_senha" class="" action="redefinir-senha" method="POST"
										style="margin-top: -50px">
										<br>
										<h6
											class="text-gray-900 mb-4 alert alert-info col-md-12 text-center">
											<b>Campos obrigatórios(<span class="aviso">*</span>).
											</b>
										</h6>
										
										<div class="text-center" id="alerta">
											<c:if test="${!empty msgAlerta}">
															<c:choose>
																		<c:when test="${msgAlerta eq 'atualizoSucesso'}"><span style="color: green"> <b>Atualizado com sucesso.</b></span></c:when>
																		<c:when test="${msgAlerta eq 'erro'}"><span style="color: red"> <b>Verifique os dados e tente novamente.</b></span></c:when>
															</c:choose>
													</c:if>
											</div>

										<fieldset class="border p-3 fildset">
											<legend class="w-auto ">Senha</legend>
											
											<div class="form-group">
												<label for="nova_senha" class="margembaixo"
													style="margin-top: -50px">Nova
													senha<span class="aviso">*</span>
												</label> <input type="password"
													class="form-control form-control-user"
													id="nova_senha" name="nova_senha"
													aria-describedby="emailHelp"
													placeholder="Informe sua nova senha">
											</div>
											<div class="form-group">
												<label for="confirme_nova_senha"
													class="margembaixo">Confirme a nova senha<span
													class="aviso">*</span></label> <input type="password"
													class="form-control form-control-user "
													id="confirme_nova_senha" name="confirme_nova_senha"
													aria-describedby="emailHelp"
													placeholder="Informe sua senha novamente">
											</div>
										</fieldset>
										<br>
										<button type="submit"
												class="btn btn-primary btn-user btn-block mao"><i class="fa fa-save"> </i> Salvar alteração</button>
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
	<script type="text/javascript" src="../../resources/js/validacao_menor.js"></script>
	
</body>

</html>
