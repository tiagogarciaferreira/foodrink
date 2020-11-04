<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="icon" 	 type="image/png" href="../resources/img/icone.ico" />

<title>FooDrink</title>

</head>

<header style="position: fixed; z-index: 1" class="tamanho">
	<c:import url="../template/menu.jsp" />
</header>

<body id="corpo" class=" fundo" style="z-index: -1">
	<c:import url="../template/app.jsp" />
	<br>
	<br>
	<div class="container " style="margin: auto;">
		<div class="row justify-content-center ">
			<div class="col-md-9">
				<div class="card o-hidden border-0 shadow-lg my-5 invisivel">
					<h1 class="h4 text-gray-900 mb-4 alert alert-info text-center">
						<b>Cadastro de Cliente</b>
					</h1>
					<div class="text-center"
						style="margin-bottom: -80px; margin-top: -20px;">
						<br>
						<br><br>
					</div>
					<div class="card-body p-0 ">
						<div class="row">
							<div class="col-md-12" style="margin: auto;">
								<div class="p-5">
									<div class="text-center textsize ">
										<h4 >
											 <a class="small col-md-4" href="cadastro-empresa.jsp" style="margin-left: -5px;">
												<i class="fas fa-home"> </i> Empresa
											</a> <br>
										</h4>
									</div>
									<div class="text-center textsize">
										<c:if test="${!empty msgAlerta}">
											<span style="color: red"> <b> Verifique seus dados
													e tente novamente. </b></span>
										</c:if>
									</div>
									<br>
									<h6
										class="text-gray-900 mb-4 alert alert-info col-md-12 text-center">
										<b>Campos obrigatórios(<span class="aviso">*</span>).
										</b>
									</h6>
									<form id="form_novo_cliente" name="form_novo_cliente"
										method="POST" action="novo-cliente">


										<fieldset class="border p-3 fildset ">
											<legend class="w-auto ">Imagem</legend>
											<div class="form-group text-center">
												
													<c:if test="${empty usuario.imagem}">   
														<img class="img-profile rounded-circle" alt=" " name="image" src="../resources/img/semimg.png"
															id="image" width="100px" height="100px" style="cursor: pointer;">
													</c:if> 
													
													<c:if test="${!empty usuario.imagem}">   
														<img class="img-profile rounded-circle" alt=" " name="image" src="${usuario.imagem}"
															id="image" width="100px" height="100px" style="cursor: pointer;"> 
													</c:if>
													<br><br>
													
												<input class="btn btn-secondary col-md-3 btn-sm" type="file"
													id="fileImagem" name="fileImagem"><span	class="aviso"> *</span>
												<textarea id="imagem" name="imagem" hidden="true">${usuario.imagem}</textarea>
											</div>
										</fieldset>

										<fieldset class="border p-3 fildset">
											<legend class="w-auto ">Identificação</legend>
											<div class="form-group">
												<label for="nome" class="margembaixo">Nome Completo<span
													class="aviso">*</span></label> <input type="text"
													class="form-control form-control-user" id="nome"
													name="nome" aria-describedby="nome"
													placeholder="Informe seu nome completo" value="${usuario.nome}">
											</div>

											<div class="form-group">
												<label for="cpf" class="margembaixo">Cpf<span
													class="aviso">*</span></label> <input type="text"
													class="form-control form-control-user" id="cpf" name="cpf"
													aria-describedby="cpf" placeholder="Informe seu cpf" value="${cliente.cpf}">
											</div>


										</fieldset>
										<br>
										<fieldset class="border p-3 fildset">
											<legend class="w-auto ">Acesso</legend>
											<div class="form-group">
												<label for="email" class="margembaixo">Email<span
													class="aviso">*</span>(Será utilizado como usuário)
												</label> <input type="email" class="form-control form-control-user"
													id="email" name="email" aria-describedby="email"
													placeholder="Informe seu email" value="${usuario.email}">
											</div>

											<div class="form-group">
												<label for="senha" class="margembaixo">Senha<span
													class="aviso">*</span></label> <input type="password"
													class="form-control form-control-user" id="senha"
													name="senha" placeholder="Informe sua senha" value="${usuario.senha}">
											</div>

											<div class="form-group">
												<label for="confirme_senha" class="margembaixo">Confirme
													sua senha<span class="aviso">*</span>
												</label> <input type="password"
													class="form-control form-control-user" id="confirme_senha"
													name="confirme_senha" placeholder="Confirme sua senha " value="${usuario.senha}">
											</div>

										</fieldset>
										<br>
										<fieldset class="border p-3 fildset">
											<legend class="w-auto ">Contato</legend>

											<div class="form-group">
												<label for="telefone" class="margembaixo">Telefone<span
													class="aviso">*</span></label> <input type="text"
													class="form-control form-control-user" id="telefone"
													name="telefone" aria-describedby="telefone"
													placeholder="Informe seu telefone" value="${usuario.telefone}">
											</div>
										</fieldset>
										<br>
										<fieldset class="border p-3 fildset">
											<legend class="w-auto ">Endereço</legend>
											<div class="form-group" hidden="true">
												<label for="pais.idPais" class="margembaixo">País<span
													class="aviso">*</span></label> <select
													class="form-control form-control-user" id="pais.idPais"
													name="pais.idPais" aria-describedby="pais.idPais">
													<option>Selecione</option>
													<option value="1" selected="selected">Brasil</option>
												</select>
											</div>

											 <div class="form-group">
												<label id="selecaoEstado" hidden="true" >${endereco.estado.idEstado}</label>				
												<label for="estado.idEstado" class="margembaixo">Estado<span
													class="aviso">*</span></label> <select
													class="form-control form-control-user" id="estado.idEstado"
													name="estado.idEstado" aria-describedby="estado.idEstado" >
													<option >Selecione</option>
												</select>
											</div>

											<div class="form-group">
											    <label id="selecaoCidade" hidden="true" >${endereco.cidade.idCidade}</label>	
												<label for="cidade.idCidade" class="margembaixo">Cidade<span
													class="aviso">*</span></label> <select
													class="form-control form-control-user" id="cidade.idCidade"
													name="cidade.idCidade" aria-describedby="cidade.idCidade">
													<option>Selecione</option>
												</select>
											</div> 

											<div class="form-group">
												<label for="cep" class="margembaixo">Cep<span
													class="aviso">*</span></label> <input type="text"
													class="form-control form-control-user" id="cep" name="cep"
													aria-describedby="cep" placeholder="Informe seu cep" value="${endereco.cep}">
											</div>

											<div class="form-group">
												<label for="bairro" class="margembaixo">Bairro<span
													class="aviso">*</span></label> <input type="text"
													class="form-control form-control-user" id="bairro"
													name="bairro" aria-describedby="bairro"
													placeholder="Informe seu bairro" value="${endereco.bairro}">
											</div>

											<div class="form-group">
												<label for="rua" class="margembaixo">Rua<span
													class="aviso">*</span></label> <input type="text"
													class="form-control form-control-user" id="rua" name="rua"
													aria-describedby="rua" placeholder="Informe sua rua" value="${endereco.rua}">
											</div>

											<div class="form-group">
												<label for="numero" class="margembaixo">Numero<span
													class="aviso">*</span></label> <input type="text"
													class="form-control form-control-user" id="numero"
													name="numero" aria-describedby="numero"
													placeholder="Informe seu numero" value="${endereco.numero}">
											</div>

											<div class="form-group">
												<label for="complemento" class="margembaixo">Complemento</label>
												<input type="text" class="form-control form-control-user"
													id="complemento" name="complemento"
													aria-describedby="complemento"
													placeholder="Informe um complemento do seu endereço" value="${endereco.complemento}">
											</div>
										</fieldset>
										<br>
										<button type="submit"
											class="btn btn-primary btn-user btn-block mao"><i class="fa fa-save"></i> Cadastrar</button>
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
	<script type="text/javascript" src="../resources/js/cliente-val.js"></script>
	<script type="text/javascript" src="../resources/js/load.js">  </script>

</body>

</html>
