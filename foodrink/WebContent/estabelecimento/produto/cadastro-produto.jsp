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
									<b>Cadastro e Alteração de Produto</b>
								</h5>
					<div class="card-body p-0">
						<div class="row">
							<div class="col-md-12" style="margin: auto;">
								<div class="p-5">
									<div class="text-center"></div>
									<form id="formNovoProduto" action="novo-or-update-produto" method="POST" style="margin-top: -50px">
										<h6
											class="text-gray-900 mb-4 alert alert-info col-md-12 text-center">
											<b>Campos obrigatórios(<span class="aviso">*</span>).</b>
										</h6>
										<div class="text-center" id="alerta">
											<c:if test="${!empty msgAlerta}" >
													<c:choose>
																<c:when test="${msgAlerta eq 'cadastroSucesso'}"><span style="color: green"><b >Cadastrado com sucesso.</b></span></c:when>
																<c:when test="${msgAlerta eq 'atualizoSucesso'}"><span style="color: green"><b >Atualizado com sucesso.</b></span></c:when>
																<c:when test="${msgAlerta eq 'erro'}"><span style="color: red"><b>Verifique os dados e tente novamente.</b></span></c:when>
													</c:choose>
											</c:if>
										</div>
										<fieldset class="border p-3 fildset">
											<legend class="w-auto ">Produto</legend>
											<div class="form-group text-center">
												
													<c:if test="${empty produto.imagem}">
														<img class="img-profile rounded-circle" name="image" src="../../resources/img/semimg.png"
															id="image" width="100px" height="100px" style="cursor: pointer;">
													</c:if>
													<c:if test="${!empty produto.imagem}">
														<img class="img-profile rounded-circle" name="image" src="${produto.imagem}"
															id="image" width="100px" height="100px" style="cursor: pointer;">
													</c:if>
													<br><br>
												<input class="btn btn-secondary col-md-3 btn-sm" type="file"
													id="fileImagem" name="fileImagem"><span	class="aviso"> *</span>
												<textarea id="imagem" name="imagem" hidden="true">${usuario.imagem}</textarea>
											</div>
											
											<div class="form-group">
											<label for="idProduto" class="margembaixo" >ID/Produto</label> <input type="text"
												class="form-control form-control-user" id="idProduto" name="idProduto"
												aria-describedby="idProduto" placeholder="Automático" readonly="readonly" value="${produto.idProduto}">
											</div>
											
										<div class="form-group">
											<label for="nome" class="margembaixo">Nome<span
												class="aviso">*</span></label> <input type="text"
												class="form-control form-control-user" id="nome" name="nome"
												aria-describedby="nome" placeholder="Informe o nome do produto" value="${produto.nome}">
										</div>
										
										<div class="form-group">
											<label for="descricao" class="margembaixo">Descrição</label> <input type="text"
												class="form-control form-control-user" id="descricao" name="descricao"
												aria-describedby="descricao" placeholder="Informe uma descrição do produto" value="${produto.descricao}">
										</div>

										<div class="form-group">
											<label for="tamanho" class="margembaixo">Tamanho<span
												class="aviso">*</span></label> <input type="text"
												class="form-control form-control-user" id="tamanho" name="tamanho"
												aria-describedby="tamanho" placeholder="Informe o tamanho do produto" value="${produto.tamanho}">
										</div>
										<div class="form-group" >
											<label id="selecaoCategoria" hidden="true" >${produto.categoria.idCategoria}</label>	
											<label for="categoria.idCategoria" class="margembaixo">Categoria<span
												class="aviso">*</span></label> <select
												class="form-control form-control-user" id="categoria.idCategoria" name="categoria.idCategoria"
												aria-describedby="categoria.idCategoria">
												<option>Selecione</option>
											</select>
										</div>

										<div class="form-group">
											<label for="preco" class="margembaixo">Preço<span class="aviso">*</span>
											</label> <input type="text" class="form-control form-control-user"
												id="preco" name="preco" aria-describedby="preco"
												placeholder="Informe o preço do produto" value="${produto.preco}">
										</div>
										
										<div class="form-group margembaixo">
											<label for="status" class="margembaixo">Status<span	class="aviso">*</span></label><br>
												<div class="custom-control custom-radio custom-control-inline">
												  <input type="radio" class="custom-control-input" id="statusD" name="chaveStatus" value="DISPONIVEL" 
												  <c:if test="${produto.status == true}"><c:out value="checked='checked'"/></c:if>	>
												  <label class="custom-control-label" for="statusD">Disponível</label>
												</div>
												<div class="custom-control custom-radio custom-control-inline">
												  <input type="radio" class="custom-control-input" id="statusI" name="chaveStatus" value="INDISPONIVEL" 
												  <c:if test="${produto.status == false}"><c:out value="checked='checked'"/></c:if>  >
												  <label class="custom-control-label" for="statusI">Indisponível</label>
												</div>
												
												<label for="chaveStatus" class="error margembaixo"></label>
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
	<script type="text/javascript" src="../../resources/js/produto-val.js"></script>

</body>

</html>
