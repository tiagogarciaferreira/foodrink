<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link type="text/css" rel="stylesheet"
	href="../../resources/vendor/fontawesome-free/css/all.min.css">
<link type="text/css" rel="stylesheet"
	href="../../resources/css/sb-admin-2.min.css">
</head>
<body id="page-top">
	<c:if test="${empty userLogado.nome}">
		<script type="text/javascript">
			location.href = '../load-perfil'
		</script>
	</c:if>
	<div id="wrapper" class="invisivel">
		<ul class="navbar-nav  sidebar sidebar-dark accordion"
			id="accordionSidebar">
			<li
				class="sidebar-brand d-flex align-items-center justify-content-center">
				<div class="text-center d-none d-md-inline">
					<a><img src="../../resources/img/logo.png" width="100px"
						height="100px" class="rounded-circle border-0" id="sidebarToggle"></a>
				</div>
			</li>
			<li>
				<hr class="sidebar-divider my-0">
			</li>
			<li class="nav-item active"><a class="nav-link"
				href="../estabelecimento.jsp"> <i
					class="fas fa-fw fa-tachometer-alt"></i> <span>Inicío</span></a></li>
			<sec:authorize access="isAuthenticated() and hasRole('ROLE_EMPRESA')">

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
							<a class=" collapse-item " href="../produto/cadastro-produto.jsp">Novo</a>
							<a class=" collapse-item " href="../produto/busca-produto.jsp">Pesquisar</a>
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
							<a class=" collapse-item "
								href="../cardapio/busca-estabelecimento.jsp">Pesquisar</a> <a
								class="collapse-item" href="../cardapio/historico.jsp">Histórico</a>
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
							<a class="collapse-item"
								href="../categoria/cadastro-categoria.jsp">Nova</a> <a
								class="collapse-item" href="../categoria/busca-categoria.jsp">Pesquisar</a>
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
							<a class="collapse-item" href="../pedido/cadastro-pedido.jsp">Novo</a>
							<a class="collapse-item" href="../pedido/busca-pedido.jsp">Pesquisar</a>
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
							<a class="collapse-item" href="../mesa/cadastro-mesa.jsp">Nova</a>
							<a class="collapse-item" href="../mesa/busca-mesa.jsp">Pesquisar</a>
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
							<a class="collapse-item" href="../reserva/cadastro-reserva.jsp">Nova</a>
							<a class="collapse-item" href="../reserva/busca-reserva.jsp">Pesquisar</a>
						</div>
					</div></li>

				<li>
					<hr class="sidebar-divider d-none d-md-block">
				</li>

				<li class="sidebar-heading">Funcionários</li>
				<li class="nav-item"><a class="nav-link collapsed" href="#"
					data-toggle="collapse" data-target="#collapseFuncionario"
					aria-expanded="true" aria-controls="collapseFuncionario"> <i
						class="fas fa-fw fa-folder"></i> <span>Funcionários</span>
				</a>
					<div id="collapseFuncionario" class="collapse"
						aria-labelledby="headingPages" data-parent="#accordionSidebar">
						<div class="menu_invisivel py-2 collapse-inner rounded ">
							<h6 class="collapse-header">Opções:</h6>
							<a class="collapse-item"
								href="../funcionario/cadastro-funcionario.jsp">Novo</a> <a
								class="collapse-item"
								href="../funcionario/busca-funcionario.jsp">Pesquisar</a>
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
								href="../cozinha/busca-pedidos-a-entregar.jsp">Pesquisar</a>
						</div>
					</div></li>
			</sec:authorize>
			<li>
				<hr class="sidebar-divider d-none d-md-block">
			</li>

			<li
				class="sidebar-brand d-flex align-items-center justify-content-center">
				<div class="text-center d-none d-md-inline">
					<a><img src="../../resources/img/logo.png" width="100px"
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
					<script type="text/javascript">
						document.getElementById("sidebarToggleTop").click();
					</script>
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
									class="fas fa-male fa-sm fa-fw mr-2 text-gray-400"></i>
									${userLogado.nome}
								</a>
								<sec:authorize
									access="isAuthenticated() and hasRole('ROLE_EMPRESA')">
									<div class="dropdown-divider"></div>
									<a class="dropdown-item rodape"
										href="../perfil/perfil-empresa.jsp"> <i
										class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> Editar
										Perfil
									</a>
								</sec:authorize>
								<sec:authorize
									access="isAuthenticated() and hasRole('ROLE_CLIENTE')">
									<div class="dropdown-divider"></div>
									<a class="dropdown-item rodape"
										href="../perfil/perfil-cliente.jsp"> <i
										class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> Editar
										Perfil
									</a>
								</sec:authorize>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item rodape"
									href="../perfil/redefinir-senha.jsp"> <i
									class="fas fa-key fa-sm fa-fw mr-2 text-gray-400"></i> Alterar
									senha
								</a>
								<div class="dropdown-divider"></div>

								<a class="dropdown-item rodape" href="../../logout"
									onclick="sair('../../estabelecimento/sair');"> <i
									class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
									Sair
								</a>
							</div></li>

					</ul>

				</nav>

				<script type="text/javascript"
					src="../../resources/vendor/jquery/jquery.min.js"></script>
				<script type="text/javascript"
					src="../../resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
				<script type="text/javascript"
					src="../../resources/js/sb-admin-2.min.js"></script>

				
</body>
</html>