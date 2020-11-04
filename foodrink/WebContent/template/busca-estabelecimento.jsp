<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
	<div class="modal fade" id="modal-busca-estabelecimento" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content fundo invisivel">
				<h1 class="h4 text-gray-900 mb-4 alert alert-info text-center">
					<b>Buscar</b>
					<button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
         		 <span aria-hidden="true">&times;</span>
        	</button>
				</h1>

				<div class="text-center"
					style="margin-bottom: -80px; margin-top: -20px;">
					<a href="foodrink.jsp"><img src="../resources/img/logo.png"
						width="100px" height="100px" title="Voltar ao Início"></a><br>
					<div class="text-center row justify-content-center" style="margin: auto;">
					<h6
						class="text-gray-900 mb-4 alert alert-info col-md-9 text-center">
						<b>Campos obrigatórios(<span class="aviso">*</span>).
						</b>
					</h6>
					</div>
				</div>

				<div class="row">
					<div class="col-md-12" style="margin: auto;">
						<div class="p-5">
							<form id="form_filtro_estabelecimento"
								action="pesquisar-estabelecimento" method="POST">
								<br>
								<fieldset class="border p-3 fildset">
									<legend class="w-auto ">Busca</legend>

									<div class="form-group">
									<label id="selecaoEstado" hidden="true" >${filtro.estado.idEstado}</label>
										<label for="estado.idEstado" class="margembaixo">Estado<span
											class="aviso">*</span></label> <select
											class="form-control form-control-user" id="estado.idEstado"
											name="estado.idEstado" aria-describedby="estado.idEstado">
											<option>Selecione</option>
										</select>
									</div>

									<div class="form-group">
									<label id="selecaoCidade" hidden="true" >${filtro.cidade.idCidade}</label>
										<label for="cidade.idCidade" class="margembaixo">Cidade<span
											class="aviso">*</span></label> <select
											class="form-control form-control-user" id="cidade.idCidade"
											name="cidade.idCidade" aria-describedby="cidade.idCidade">
											<option>Selecione</option>
										</select>
									</div>

								</fieldset>
								<br> 
								<button type="submit"
									class="btn btn-primary btn-user btn-block mao"> <i class="fa fa-search"></i> Buscar</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>