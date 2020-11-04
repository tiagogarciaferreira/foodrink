<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link type="text/css" rel="stylesheet"
	href="../resources/vendor/fontawesome-free/css/all.min.css">
<link type="text/css" rel="stylesheet"
	href="../resources/css/sb-admin-2.min.css">
<link rel="icon" type="image/png" href="../resources/img/icone.ico" />
<title>FooDrink</title>
</head>

<body id="page-top" class="fundo">
	<c:if test="${empty userLogado.nome}">
		<script type="text/javascript">
			location.href = 'load-perfil'
		</script>
	</c:if>
	<div id="wrapper" class="invisivel">
		<ul class="navbar-nav  sidebar sidebar-dark accordion"
			id="accordionSidebar">
			<li
				class="sidebar-brand d-flex align-items-center justify-content-center">
				<div class="text-center d-none d-md-inline">
					<a><img src="../resources/img/logo.png" width="100px"
						height="100px" class="rounded-circle border-0" id="sidebarToggle"></a>
				</div>
			</li>
			<li>
				<hr class="sidebar-divider my-0">
			</li>
			<li class="nav-item active"><a class="nav-link"
				href="estabelecimento.jsp"> <i
					class="fas fa-fw fa-tachometer-alt"></i> <span>Inicío</span></a></li>
			<sec:authorize access="isAuthenticated() and hasRole('ROLE_EMPRESA')">

				<li class="nav-item active"><a class="nav-link" href="#"
					data-toggle="modal" data-target="#modal-cadastra-horario"> <i
						class="fas fa-fw fa-clock"></i> <span>Funcionamento</span></a></li>


				<li>
					<hr class="sidebar-divider">
				</li>
				<li class="sidebar-heading">Produtos</li>
				<li class="nav-item"><a class="nav-link collapsed" href="#"
					data-toggle="collapse" data-target="#collapseTwo"
					aria-expanded="true" aria-controls="collapseTwo"> <i
						class="fas fa-fw fa-folder"></i> <span>Produto</span>
				</a>
					<div id="collapseTwo" class="collapse "
						aria-labelledby="headingTwo" data-parent="#accordionSidebar">
						<div class="menu_invisivel py-2 collapse-inner rounded ">
							<h6 class="collapse-header">Opções:</h6>
							<a class=" collapse-item " href="produto/cadastro-produto.jsp">Novo</a>
							<a class=" collapse-item " href="produto/busca-produto.jsp">Pesquisar</a>
						</div>
					</div></li>
			</sec:authorize>
			<sec:authorize access="isAuthenticated() and hasRole('ROLE_CLIENTE')">
				<li>
					<hr class="sidebar-divider">
				</li>

				<li class="sidebar-heading">Estabelecimentos</li>
				<li class="nav-item"><a class="nav-link collapsed" href="#"
					data-toggle="collapse" data-target="#collapseEstabelecimento"
					aria-expanded="true" aria-controls="collapseEstabelecimento"> <i
						class="fas fa-fw fa-folder"></i> <span>Estabelecimento</span>
				</a>
					<div id="collapseEstabelecimento" class="collapse "
						aria-labelledby="headingEstabelecimento"
						data-parent="#accordionSidebar">
						<div class="menu_invisivel py-2 collapse-inner rounded ">
							<h6 class="collapse-header">Opções:</h6>
							<a class="collapse-item"
								href="cardapio/busca-estabelecimento.jsp">Pesquisar</a> <a
								class="collapse-item" href="cardapio/historico.jsp">Histórico</a>
						</div>
					</div></li>
			</sec:authorize>

			<sec:authorize access="isAuthenticated() and hasRole('ROLE_EMPRESA')">
				<li>
					<hr class="sidebar-divider">
				</li>
				<li class="sidebar-heading">Categorias</li>
				<li class="nav-item"><a class="nav-link collapsed" href="#"
					data-toggle="collapse" data-target="#collapseUtilities"
					aria-expanded="true" aria-controls="collapseUtilities"> <i
						class="fas fa-fw fa-folder"></i> <span>Categoria</span>
				</a>
					<div id="collapseUtilities" class="collapse"
						aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
						<div class="menu_invisivel py-2 collapse-inner rounded ">
							<a class="collapse-item" href="categoria/cadastro-categoria.jsp">Nova</a>
							<a class="collapse-item" href="categoria/busca-categoria.jsp">Pesquisar</a>
						</div>
					</div></li>
			</sec:authorize>
			<sec:authorize
				access="isAuthenticated() and hasRole({'ROLE_GARCOM'})">
				<li>
					<hr class="sidebar-divider">
				</li>
				<li class="sidebar-heading">Pedidos</li>
				<li class="nav-item"><a class="nav-link collapsed" href="#"
					data-toggle="collapse" data-target="#collapsePedido"
					aria-expanded="true" aria-controls="collapsePedido"> <i
						class="fas fa-fw fa-folder"></i> <span>Pedido</span>
				</a>
					<div id="collapsePedido" class="collapse"
						aria-labelledby="headingPages" data-parent="#accordionSidebar">
						<div class="menu_invisivel py-2 collapse-inner rounded ">
							<h6 class="collapse-header">Opções:</h6>
							<a class="collapse-item" href="pedido/cadastro-pedido.jsp">Novo</a>
							<a class="collapse-item" href="pedido/busca-pedido.jsp">Pesquisar</a>
						</div>
					</div></li>
			</sec:authorize>
			<sec:authorize access="isAuthenticated() and hasRole('ROLE_EMPRESA')">
				<li>
					<hr class="sidebar-divider">
				</li>
				<li class="sidebar-heading">Mesas</li>
				<li class="nav-item"><a class="nav-link collapsed" href="#"
					data-toggle="collapse" data-target="#collapseMesa"
					aria-expanded="true" aria-controls="collapseMesa"> <i
						class="fas fa-fw fa-folder"></i> <span>Mesa</span>
				</a>
					<div id="collapseMesa" class="collapse"
						aria-labelledby="headingPages" data-parent="#accordionSidebar">
						<div class="menu_invisivel py-2 collapse-inner rounded ">
							<h6 class="collapse-header">Opções:</h6>
							<a class="collapse-item" href="mesa/cadastro-mesa.jsp">Nova</a> <a
								class="collapse-item" href="mesa/busca-mesa.jsp">Pesquisar</a>
						</div>
					</div></li>
				<li>
					<hr class="sidebar-divider">
				</li>
				<li class="sidebar-heading">Reservas</li>
				<li class="nav-item"><a class="nav-link collapsed" href="#"
					data-toggle="collapse" data-target="#collapseReserva"
					aria-expanded="true" aria-controls="collapseReserva"> <i
						class="fas fa-fw fa-folder"></i> <span>Reserva</span>
				</a>
					<div id="collapseReserva" class="collapse"
						aria-labelledby="headingPages" data-parent="#accordionSidebar">
						<div class="menu_invisivel py-2 collapse-inner rounded ">
							<h6 class="collapse-header">Opções:</h6>
							<a class="collapse-item" href="reserva/cadastro-reserva.jsp">Nova</a>
							<a class="collapse-item" href="reserva/busca-reserva.jsp">Pesquisar</a>
						</div>
					</div></li>
				<li>
					<hr class="sidebar-divider d-none d-md-block">
				</li>
				<li class="sidebar-heading">Funcionários</li>
				<li class="nav-item"><a class="nav-link collapsed" href="#"
					data-toggle="collapse" data-target="#collapseFuncionario"
					aria-expanded="true" aria-controls="collapseFuncionario"> <i
						class="fas fa-fw fa-folder"></i> <span>Funcionário</span>
				</a>
					<div id="collapseFuncionario" class="collapse"
						aria-labelledby="headingPages" data-parent="#accordionSidebar">
						<div class="menu_invisivel py-2 collapse-inner rounded ">
							<h6 class="collapse-header">Opções:</h6>
							<a class="collapse-item"
								href="funcionario/cadastro-funcionario.jsp">Novo</a> <a
								class="collapse-item" href="funcionario/busca-funcionario.jsp">Pesquisar</a>
						</div>
					</div></li>
			</sec:authorize>

			<sec:authorize
				access="isAuthenticated() and hasRole({'ROLE_GARCOM'})">
				<li>
					<hr class="sidebar-divider">
				</li>
				<li class="sidebar-heading">Preparos</li>
				<li class="nav-item"><a class="nav-link collapsed" href="#"
					data-toggle="collapse" data-target="#collapseCozinha"
					aria-expanded="true" aria-controls="collapseCozinha"> <i
						class="fas fa-fw fa-folder"></i> <span>Preparo</span>
				</a>
					<div id="collapseCozinha" class="collapse"
						aria-labelledby="headingPages" data-parent="#accordionSidebar">
						<div class="menu_invisivel py-2 collapse-inner rounded ">
							<h6 class="collapse-header">Opções:</h6>
							<a class="collapse-item"
								href="cozinha/busca-pedidos-a-entregar.jsp">Pesquisar</a>
						</div>
					</div></li>
			</sec:authorize>
			<li>
				<hr class="sidebar-divider d-none d-md-block">
			</li>

			<li
				class="sidebar-brand d-flex align-items-center justify-content-center">
				<div class="text-center d-none d-md-inline">
					<!-- <button class="rounded-circle border-0" id="sidebarToggle"></button> -->
					<a><img src="../resources/img/logo.png" width="100px"
						height="100px" class="rounded-circle border-0" id="sidebarToggle"></a>
				</div>
			</li>
			<br>
			<br>
		</ul>

		<div id="content-wrapper" class="d-flex flex-column ">
			<div id="content">

				<nav
					class="navbar navbar-expand navbar-light topbar mb-4 static-top shadow invisivel">
					<button id="sidebarToggleTop"
						class="btn btn-link d-md-none rounded-circle mr-3">
						<i class="fa fa-bars"></i>
					</button>

					<ul class="navbar-nav ml-auto rodape ">
						<li class="nav-item dropdown no-arrow "><a
							class="nav-link dropdown-toggle " href="#" id="userDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <span
								class="mr-2 d-none d-lg-inline small rodape">${userLogado.nome}</span>
								<img class="img-profile rounded-circle"
								src="${userLogado.imagem}" width="100px" height="100px">
						</a>
							<div
								class="dropdown-menu dropdown-menu-right shadow animated--grow-in menu_invisivel"
								aria-labelledby="userDropdown">

								<a class="dropdown-item rodape" href="#"> <i
									class="fas fa-male fa-sm fa-fw mr-2 text-gray-400 rodape"></i>
									${userLogado.nome}
								</a>
								<sec:authorize
									access="isAuthenticated() and hasRole('ROLE_EMPRESA')">
									<div class="dropdown-divider"></div>

									<a class="dropdown-item rodape"
										href="perfil/perfil-empresa.jsp"> <i
										class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> Editar
										Perfil
									</a>
								</sec:authorize>
								<sec:authorize
									access="isAuthenticated() and hasRole('ROLE_CLIENTE')">
									<div class="dropdown-divider"></div>
									<a class="dropdown-item rodape"
										href="perfil/perfil-cliente.jsp"> <i
										class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> Editar
										Perfil
									</a>
								</sec:authorize>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item rodape"
									href="perfil/redefinir-senha.jsp"> <i
									class="fas fa-key fa-sm fa-fw mr-2 text-gray-400"></i> Alterar
									senha
								</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item rodape" onclick="sair('sair');"
									href="../logout"> <i
									class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
									Sair
								</a>
							</div></li>

					</ul>

				</nav>

				<div class="container col-md-12">
					<div class="row justify-content-center " style="margin-top: -50px">
						<div class="col-md-12">
							<div class="card o-hidden border-0 shadow-lg my-5 invisivel">
								<h5 class="text-gray-900 mb-4 alert alert-info text-center">
									<b>Dados Atuais</b>
								</h5>
								<div class="card-body p-0 ">
									<div class="row form-group">
										<div class="col-md-12" style="margin: auto;">
											<sec:authorize
												access="isAuthenticated() and hasRole('ROLE_COZINHA')">
												<c:import url="cozinha/pedidos-a-fazer.jsp" />
											</sec:authorize>
											<div class="p-5">

												<div class="row" id="meusDadosAtuais">
													<sec:authorize
														access="isAuthenticated() and hasRole('ROLE_EMPRESA')">
														<div class="col-xl-3 col-md-6 mb-4 ">
															<div
																class="card border-left-success shadow h-100 py-2 invisivel">
																<div class="card-body">
																	<div class="row no-gutters align-items-center">
																		<div class="col mr-2">
																			<div
																				class="text-xs font-weight-bold rodape text-uppercase mb-1">Pedidos
																				Em Andamento</div>
																			<div class="h5 mb-0 font-weight-bold text-gray-800"
																				id="pedidosAndamento"></div>
																		</div>
																	</div>
																</div>
															</div>
														</div>
														<div class="col-xl-3 col-md-6 mb-4">
															<div
																class="card border-left-success shadow h-100 py-2 invisivel">
																<div class="card-body">
																	<div class="row no-gutters align-items-center">
																		<div class="col mr-2">
																			<div
																				class="text-xs font-weight-bold rodape text-uppercase mb-1">Mesas
																				Disponíveis</div>
																			<div class="h5 mb-0 font-weight-bold text-gray-800"
																				id="mesasDisponiveis"></div>
																		</div>
																	</div>
																</div>
															</div>
														</div>
														<div class="col-xl-3 col-md-6 mb-4">
															<div
																class="card border-left-success shadow h-100 py-2 invisivel">
																<div class="card-body">
																	<div class="row no-gutters align-items-center">
																		<div class="col mr-2">
																			<div
																				class="text-xs font-weight-bold rodape text-uppercase mb-1">Reservas(Hoje)</div>
																			<div class="h5 mb-0 font-weight-bold text-gray-800"
																				id="reservasHoje"></div>
																		</div>
																	</div>
																</div>
															</div>
														</div>
														<div class="col-xl-3 col-md-6 mb-4"
															onclick="detalhes('funcionamento');"
															style="cursor: pointer;">
															<div
																class="card border-left-success shadow h-100 py-2 invisivel">
																<div class="card-body">
																	<div class="row no-gutters align-items-center">
																		<div class="col mr-2">
																			<div
																				class="text-xs font-weight-bold rodape text-uppercase mb-1"
																				title="Mais detalhes">Funcionamento</div>
																			<div
																				class="text-xs mb-0 font-weight-bold text-gray-800"
																				id="funcionamento" style="display: none;"></div>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</sec:authorize>

												</div>
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

	<script type="text/javascript"
		src="../resources/vendor/jquery/jquery.min.js"></script>
	<script type="text/javascript"
		src="../resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script type="text/javascript" src="../resources/js/sb-admin-2.min.js"></script>
	<sec:authorize access="isAuthenticated() and hasRole('ROLE_EMPRESA')">
		<script type="text/javascript"
			src="../resources/js/estatistica_empresa.js"></script>
	</sec:authorize>
	<sec:authorize access="isAuthenticated() and hasRole('ROLE_CLIENTE')">
		<script type="text/javascript"
			src="../resources/js/estatistica_cliente.js"></script>
	</sec:authorize>
	<sec:authorize access="isAuthenticated() and hasRole('ROLE_COZINHA')">
		<script type="text/javascript"
			src="../resources/vendor/datatables/datatable.js"></script>
		<script type="text/javascript"
			src="../resources/js/table-a-cozinha-fazer.js"></script>
	</sec:authorize>

	<div class="modal fade" id="modal-cadastra-horario" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content invisivel">
				<h1 class="h4 text-gray-900 mb-4 alert alert-info text-center">
					<b>Cadastrar</b>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Fechar">
						<span aria-hidden="true">&times;</span>
					</button>
				</h1>

				<div class="text-center"
					style="margin-bottom: -80px; margin-top: -20px;">
					<br>
					<div class="text-center row justify-content-center"
						style="margin: auto;">
						<h6
							class="text-gray-900 mb-4 alert alert-info col-md-9 text-center">
							<b>Campos obrigatórios(<span class="aviso">*</span>).
							</b>
						</h6>
						<div class="text-center" id="alerta">
							<c:if test="${!empty msgAlerta}">
								<c:choose>
									<c:when test="${msgAlerta eq 'atualizoSucesso'}">
										<span style="color: green"> <b>Atualizado com
												sucesso.</b></span>
									</c:when>
									<c:when test="${msgAlerta eq 'erro'}">
										<span style="color: red"> <b>Verifique os dados e
												tente novamente.</b></span>
									</c:when>
								</c:choose>
							</c:if>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-12" style="margin: auto;">
						<div class="p-5">
							<form id="form_atendimento" action="save-ou-update-horario"
								method="POST">
								<br>
								<fieldset class="border p-3 fildset">
									<legend class="w-auto margembaixo">Horário</legend>

									<div class="form-group ">
										<label for="segunda" class="margembaixo">Segunda-Feira<span
											class="aviso">*</span>
										</label> <input type="text" class="form-control form-control-user"
											id="segunda" name="segunda" required="required"
											placeholder="Ex:08:00-12:00/13:30-17:30/18:00-23:00">
									</div>

									<div class="form-group">
										<label for="terca" class="margembaixo">Terça-Feira<span
											class="aviso">*</span>
										</label> <input type="text" class="form-control form-control-user"
											id="terca" name="terca" required="required"
											placeholder="Ex:08:00-12:00/13:30-17:30/18:00-23:00">
									</div>


									<div class="form-group">
										<label for="quarta" class="margembaixo">Quarta-Feira<span
											class="aviso">*</span>
										</label> <input type="text" class="form-control form-control-user"
											id="quarta" name="quarta" required="required"
											placeholder="Ex:08:00-23:00">
									</div>


									<div class="form-group ">
										<label for="quinta" class="margembaixo">Quinta-Feira<span
											class="aviso">*</span>
										</label> <input type="text" class="form-control form-control-user"
											id="quinta" name="quinta" required="required"
											placeholder="Ex:08:00-12:00/Fechado/18:00-23:00">
									</div>


									<div class="form-group ">
										<label for="sexta" class="margembaixo">Sexta-Feira<span
											class="aviso">*</span>
										</label> <input type="text" class="form-control form-control-user"
											id="sexta" name="sexta" required="required"
											placeholder="Ex:Fechado/13:30-17:30/18:00-23:00">
									</div>

									<div class="form-group ">
										<label for="sabado" class="margembaixo">Sabádo<span
											class="aviso">*</span>
										</label> <input type="text" class="form-control form-control-user"
											id="sabado" name="sabado" required="required"
											placeholder="Ex:08:00-12:00/13:30-17:30/Fechado">
									</div>

									<div class="form-group ">
										<label for="domingo" class="margembaixo">Domingo<span
											class="aviso">*</span>
										</label> <input type="text" class="form-control form-control-user"
											id="domingo" name="domingo" required="required"
											placeholder="Ex:Fechado">
									</div>

								</fieldset>
								<br>
								<button type="submit"
									class="btn btn-primary btn-user btn-block mao">
									<i class="fa fa-save"></i> Cadastrar
								</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>

</html>
