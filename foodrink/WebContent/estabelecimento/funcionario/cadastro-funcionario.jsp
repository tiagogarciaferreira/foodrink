<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="pt-br">

<head>

  <meta charset="ISO-8859-1">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
<link rel="icon" 	 type="image/png" href="../../resources/img/icone.ico" />
  <title>FooDrink</title>

</head>

<body id="page-top" class="fundo">

<c:import url="../../template/sidbar-a.jsp"/>
      <div class="container col-md-12 ">
		<div class="row justify-content-center " style="margin-top: -50px;">
			<div class="col-md-12 ">
				<div class="card o-hidden border-0 shadow-lg my-5 invisivel">
						<h5 class="text-gray-900 mb-4 alert alert-info text-center">
									<b>Cadastro e Altera��o de Funcion�rio</b>
								</h5>
					<div class="card-body p-0">
						<div class="row">
							<div class="col-md-12" style="margin: auto;">
								<div class="p-5">
									<div class="text-center"></div>
									<form id="formNovoFuncionario" action="novo-or-update-funcionario" method="POST" style="margin-top: -50px">
										<h6
											class="text-gray-900 mb-4 alert alert-info col-md-12 text-center">
											<b>Campos obrigat�rios(<span class="aviso">*</span>).</b>
										</h6>
										<div class="text-center" id="alerta">
											<c:if test="${!empty msgAlerta}">
													<c:choose>
																<c:when test="${msgAlerta eq 'cadastroSucesso'}"><span style="color: green"><b>Cadastrado com sucesso.</b></span></c:when>
																<c:when test="${msgAlerta eq 'atualizoSucesso'}"><span style="color: green"><b>Atualizado com sucesso.</b></span></c:when>
																<c:when test="${msgAlerta eq 'erro'}"><span style="color: red"><b>Verifique os dados e tente novamente.</b></span></c:when>
													</c:choose>
											</c:if>
										</div>
										<fieldset class="border p-3 fildset">
											<legend class="w-auto ">Funcion�rio</legend>
											<div class="form-group text-center">
												
												<c:if test="${empty usuario.imagem}">
														<img class="img-profile rounded-circle" name="image" src="../../resources/img/semimg.png"
															id="image" width="100px" height="100px" style="cursor: pointer;">
													</c:if>
													<c:if test="${!empty usuario.imagem}">
														<img class="img-profile rounded-circle" name="image" src="${usuario.imagem}"
															id="image" width="100px" height="100px" style="cursor: pointer;">
													</c:if>
													<br><br>
												<input class="btn btn-secondary col-md-3 btn-sm" type="file"
													id="fileImagem" name="fileImagem"><span	class="aviso"> *</span>
												<textarea id="imagem" name="imagem" hidden="true">${usuario.imagem}</textarea>
												<label for="imagem" class="error"><br></label>
											</div>
											
											<div class="form-group" hidden="true">
											<label for="idUsuario" class="margembaixo" >ID/Usuario</label> <input type="text"
												class="form-control form-control-user" id="idUsuario" name="idUsuario"
												aria-describedby="idUsuario" placeholder="Autom�tico" readonly="readonly" value="${usuario.idUsuario}">
											</div>											
											
											<div class="form-group">
											<label for="idFuncionario" class="margembaixo" >ID/Funcion�rio</label> <input type="text"
												class="form-control form-control-user" id="idFuncionario" name="idFuncionario"
												aria-describedby="idFuncionario" placeholder="Autom�tico" readonly="readonly" value="${funcionario.idFuncionario}">
											</div>
											
										<div class="form-group">
												<label for="nome" class="margembaixo">Nome Completo<span
													class="aviso">*</span></label> <input type="text"
													class="form-control form-control-user" id="nome"
													name="nome" aria-describedby="nome"
													placeholder="Informe o nome completo" value="${usuario.nome}">
											</div>
											
											<div class="form-group">
												<label for="email" class="margembaixo">Email<span
													class="aviso">*</span>(Ser� utilizado como usu�rio)
												</label> <input type="email" class="form-control form-control-user"
													id="email" name="email" aria-describedby="email"
													placeholder="Informe um email" value="${usuario.email}" >
											</div>
											
											<div class="form-group">
												<label for="telefone" class="margembaixo">Telefone<span
													class="aviso">*</span></label> <input type="text"
													class="form-control form-control-user" id="telefone"
													name="telefone" aria-describedby="telefone"
													placeholder="Informe um telefone" value="${usuario.telefone}">
											</div>
											
											<div class="form-group">
												<label for="tipoAcesso.idTipoAcesso" class="margembaixo">Tipo<span
													class="aviso">*</span></label> <select
													class="form-control form-control-user" id="tipoAcesso.idTipoAcesso"
													name="tipoAcesso.idTipoAcesso" aria-describedby="tipoAcesso.idTipoAcesso">
													<option>Selecione</option>
													<c:if test="${empty usuario.tipoAcesso.idTipoAcesso}">
														<option value="3">Gar�om</option>
													    <option value="4">Cozinheiro(a)</option>
													</c:if>
													<c:if test="${usuario.tipoAcesso.idTipoAcesso == 3}">
														<option value="3" selected="selected">Gar�om</option>
													    <option value="4">Cozinheiro(a)</option>
													</c:if>
													<c:if test="${usuario.tipoAcesso.idTipoAcesso == 4}">
														<option value="3">Gar�om</option>
													    <option value="4" selected="selected">Cozinheiro(a)</option>
													</c:if>
												</select>
											</div> 
											
											
											<div class="form-group margembaixo">
											<label for="status" class="margembaixo">Status<span	class="aviso">*</span></label><br>
												<div class="custom-control custom-radio custom-control-inline">
												  <input type="radio" class="custom-control-input" id="statusA" name="chaveStatus" value="ATIVO" 
												  <c:if test="${usuario.status == true}"><c:out value="checked='checked'"/></c:if>	>
												  <label class="custom-control-label" for="statusA">Ativo</label>
												</div>
												<div class="custom-control custom-radio custom-control-inline">
												  <input type="radio" class="custom-control-input" id="statusI" name="chaveStatus" value="INATIVO" 
												  <c:if test="${usuario.status == false}"><c:out value="checked='checked'"/></c:if>  >
												  <label class="custom-control-label" for="statusI">Inativo</label>
												</div>
												<label for="chaveStatus" class="error"></label>
											</div>
										</fieldset>
										<br>
										<button type="submit" class="btn btn-primary btn-user btn-block"> <i class="fa fa-save"> </i> Salvar</button>
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
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
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
	<script type="text/javascript" src="../../resources/js/funcionario-val.js"></script>

</body>

</html>
