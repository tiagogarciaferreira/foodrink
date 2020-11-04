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
<link rel="icon" type="image/png" href="../../resources/img/icone.ico" />
<title>FooDrink</title>

</head>

<body id="page-top" class="fundo">
	<c:import url="../../template/sidbar-a.jsp" />
	<div class="container col-md-12">

		<!-- Outer Row -->
		<div class="row justify-content-center" style="margin-top: -50px;">
			<div class="col-md-12">
				<div class="card o-hidden border-0 shadow-lg my-5 invisivel">

					<h5 class="text-gray-900 mb-4 alert alert-info text-center">
						<b>Cadastro e Alteração de Pedidos</b>
					</h5>

					<div class="card-body p-0">
						<div class="row">
							<div class="col-md-12" style="margin: auto;">
								<div class="p-5">
									<div class="text-center"></div>
									<form id="formNovoPedido" action="novo-or-update-pedido"
										method="POST" style="margin-top: -50px">
										<h6
											class="text-gray-900 mb-4 alert alert-info col-md-12 text-center">
											<b>Campos obrigatórios(<span class="aviso">*</span>).
											</b>
										</h6>
										<div class="text-center" id="alerta">
											<c:if test="${!empty msgAlerta}">
												<c:choose>
													<c:when test="${msgAlerta eq 'cadastroSucesso'}">
														<span style="color: green"><b>Cadastrado com
																sucesso.</b></span>
													</c:when>
													<c:when test="${msgAlerta eq 'finalizadoSucesso'}">
														<span style="color: green"> <b>Finalizado com
																sucesso.</b></span>
													</c:when>
													<c:when test="${msgAlerta eq 'atualizoSucesso'}">
														<span style="color: green"><b>Atualizado com
																sucesso.</b></span>
													</c:when>
													<c:when test="${msgAlerta eq 'erro'}">
														<span style="color: red"><b>Verifique os dados
																e tente novamente.</b></span>
													</c:when>
												</c:choose>
											</c:if>
										</div>
										<fieldset class="border p-3 fildset">
											<legend class="w-auto ">Pedido</legend>
											<div class="form-group">
												<label for="idPedido" class="margembaixo">ID/Pedido</label>
												<input type="text" class="form-control form-control-user"
													id="idPedido" name="idPedido" aria-describedby="idPedido"
													placeholder="Automático" readonly="readonly"
													value="${pedido.idPedido}">
											</div>

											<div class="form-group">
												<label id="selecaoMesa" hidden="true">${pedido.mesa.idMesa}</label>
												<label for="mesa.idMesa" class="margembaixo">Mesa<span
													class="aviso">*</span></label> <select
													class="form-control form-control-user" id="mesa.idMesa"
													name="mesa.idMesa" aria-describedby="mesa.idMesa">
													<option>Selecione</option>
												</select>
											</div>

											<div class="form-group">
												<label for="cliente.idCliente" class="margembaixo">Cliente<span
													class="aviso">*</span></label> <input type="text"
													class="form-control form-control-user"
													id="cliente.idCliente" data-toggle="modal"
													data-target="#modalExemploC" name="cliente.idCliente"
													aria-describedby="cliente.idCliente" readonly="readonly"
													placeholder="Identificação do cliente "
													value="${pedido.cliente.idCliente}">
											</div>

											<div class="form-group">
												<label for="jsonItens" class="margembaixo">Produto<span
													class="aviso">*</span></label><br> <input type="text"
													data-toggle="modal" onClick="adicionaSelect();"
													data-target="#modalExemplo" id="jsonItens" name="jsonItens"
													class="form-control form-control-user" readonly="readonly"
													placeholder="ADICIONAR">
												<textarea rows="" cols="" id="listaItensJsonPedido" class="col-md-12"
													 hidden="hidden" name="listaItensJsonPedido">${item.listaItensJsonPedido}</textarea>
												<button type="button"
													class="btn btn-primary btn-user btn-block"
													data-toggle="modal" data-target="#modalExemploPA"
													onclick="carrinhoAtual();">Produtos adicionados</button>

											</div>


											<div class="form-group" id="divData" hidden="true">
												<label for="data" class="margembaixo">Data<span
													class="aviso">*</span>
												</label> <input type="text" class="form-control form-control-user"
													id="data" name="data" aria-describedby="data"
													readonly="readonly" placeholder="Automático"
													value="${pedido.data}">
											</div>

											<div class="form-group" id="divHora" hidden="true">
												<label for="hora" class="margembaixo">Hora<span
													class="aviso">*</span>
												</label> <input type="text" class="form-control form-control-user"
													id="hora" name="hora" aria-describedby="hora"
													readonly="readonly" placeholder="Automático"
													value="${pedido.hora}">
											</div>



											<div class="form-group">
												<label for="observacao" class="margembaixo">Observação
												</label> <input type="text" class="form-control form-control-user"
													id="observacao" name="observacao"
													aria-describedby="observacao"
													placeholder="Informe uma observação do pedido"
													value="${pedido.observacao}">
											</div>

											<div class="form-group">
												<label for="valorPedido" class="margembaixo">Valor
													Total do Pedido<span class="aviso">*</span>
												</label> <input type="text" class="form-control form-control-user"
													id="valorPedido" name="valorPedido"
													aria-describedby="valorPedido" readonly="readonly"
													value="${pedido.valorPedido}">
											</div>

											<div class="form-group" style="margin-bottom: -15px;">
												<label for="statusLabel" class="margembaixo">Status<span
													class="aviso">*</span></label><br>
												<div
													class="custom-control custom-radio custom-control-inline">
													<input type="radio" class="custom-control-input"
														id="statusE" name="status" value="EM ANDAMENTO"
														<c:if test="${pedido.status == 'EM ANDAMENTO'}"><c:out value="checked='checked'"/></c:if>>
													<label class="custom-control-label" for="statusE">Em
														Andamento</label>
												</div>

												<div
													class="custom-control custom-radio custom-control-inline">
													<input type="radio" class="custom-control-input"
														id="statusF" name="status" value="FINALIZADO"
														data-toggle="modal" data-target="#modal-finalizar-pedido"
														<c:if test="${pedido.status == 'FINALIZADO'}"><c:out value="checked='checked'"/></c:if>>
													<label class="custom-control-label" for="statusF">Finalizado</label>
												</div>

												<br>
												<label for="status" class="error"></label>
											</div>

										</fieldset>
										<br>
										<button id="pedir" type="submit"
											class="btn btn-primary btn-user btn-block">
											<i class="fa fa-save"> </i> Salvar
										</button>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>

		</div>

	</div>
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	</div>
	</div>
	</div>

	<div class="modal fade text-center " id="modalExemplo" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content invisivel">
				<h1 class="h4 text-gray-900 mb-4 alert alert-info text-center">
					<b>Selecionar Produtos</b>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Fechar">
						<span aria-hidden="true">&times;</span>
					</button>
				</h1>

				<div class="modal-body" style="overflow: auto;">
					<c:import url="tabela-produtos.jsp" />
				</div>
			</div>
		</div>
	</div>


	<div class="modal fade text-center " id="modal-finalizar-pedido"
		tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content invisivel">
				<h1 class="h4 text-gray-900 mb-4 alert alert-info text-center">
					<b>Fechar Pedido</b>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Fechar">
						<span aria-hidden="true">&times;</span>
					</button>
				</h1>

				<div class="modal-body col-md-12 justify-content-center" style="overflow: auto;text-align: left;">

					<div class="form-group">
						<label for="totalValorPedido" class="margembaixo">Total</label> <input
							type="text" class="form-control form-control-user"
							id="totalValorPedido" name="totalValorPedido" readonly="readonly"
							value="${pedido.valorPedido}">
					</div>
					
					<div class="form-group">
						<label for="desconto" class="margembaixo">Desconto(%)</label> <input
							type="text" class="form-control form-control-user"
							id="desconto" name="desconto" placeholder="Informe somente numeros">
					</div>
					
					<div class="form-group">
						<label for="pagar" class="margembaixo">Total/Pagar</label> <input
							type="text" class="form-control form-control-user"
							id="pagar" name="pagar" readonly="readonly">
					</div>
					
					<div class="form-group">
						<label for="dinheiro" class="margembaixo">Dinheiro</label> <input
							type="text" class="form-control form-control-user"
							id="dinheiro" name="dinheiro" placeholder="Informe somente numeros" required="required">
					</div>
					
					<div class="form-group">
						<label for="troco" class="margembaixo">Troco</label> <input
							type="text" class="form-control form-control-user"
							id="troco" name="troco" readonly="readonly">
					</div>

					

					<button type="submit" class="btn btn-primary btn-user btn-block col-md-12"
						value="Calcular" onclick="finalizar();">Calcular</button>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript"
		src="../../resources/js/demo/jquery.validate.js"></script>
	<script type="text/javascript"
		src="../../resources/js/demo/additional-methods.js"></script>
	<script type="text/javascript"
		src="../../resources/js/demo/jquery.mask.min.js"></script>
	<script type="text/javascript"
		src="../../resources/js/demo/messages_pt_BR.js"></script>
	<script type="text/javascript" src="../../resources/js/pedido-val.js">
		
	</script>


	<div class="modal fade text-center" id="modalExemploC" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content invisivel">
				<h1 class="h4 text-gray-900 mb-4 alert alert-info text-center">
					<b>Selecionar Cliente</b>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Fechar">
						<span aria-hidden="true">&times;</span>
					</button>
				</h1>
				<div class="modal-body" style="overflow: auto;">
					<c:import url="../tabela-clientes.jsp" />
				</div>
			</div>
		</div>
	</div>


	<div class="modal fade text-center" id="modalExemploPA" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog fundo" role="document">
			<div class="modal-content fundo">
				<h1 class="h4 text-gray-900 mb-4 alert alert-info text-center">
					<b>Produtos Adicionados</b>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Fechar">
						<span aria-hidden="true">&times;</span>
					</button>
				</h1>
				<b style="color: gray;">Total(R$): <b id="valorTotal"
					class="rodape">${pedido.valorPedido}</b></b>
				<div id="listaItensProdutos" class="modal-body"
					style="overflow: auto;"></div>
			</div>
		</div>
	</div>


</body>

</html>
