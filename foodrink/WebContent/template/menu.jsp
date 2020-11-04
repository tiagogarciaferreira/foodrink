<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link type="text/css" rel="stylesheet"
	href="../resources/vendor/fontawesome-free/css/all.min.css">
<link type="text/css" rel="stylesheet"
	href="../resources/css/sb-admin-2.min.css">
</head>
<header>

	<nav
		class="navbar navbar-expand navbar-light topbar mb-4 static-top shadow invisivel">
		<div class="text-center"
			style="margin-bottom: -80px; margin-top: -60px;">
			<br> <a href="../publico/foodrink.jsp"><img	src="../resources/img/logo.png" width="70px" height="70px"></a><br> <br> <br>
		</div>


		<ul class="navbar-nav ml-auto rodape ">
			<li class="nav-item dropdown no-arrow "><a class="nav-link"
				id="userDropdown" role="button" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false" style="cursor: pointer;">
					<span class="mr-2 d-none d-lg-inline small rodape textsize"></span><i
					class="fas fa-bars  mr-2 text-gray-400"></i>Menu
			</a>
				<div
					class="dropdown-menu dropdown-menu-right shadow animated--grow-in menu_invisivel"
					aria-labelledby="menu_click">
					<a class="dropdown-item rodape" href="../publico/foodrink.jsp">
						<i class="fas fa-home fa-sm fa-fw mr-2 text-gray-400"></i> Início
					</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item rodape"
						href="../publico/cadastro-cliente.jsp"> <i
						class="fas fa-user fa-fw mr-2 text-gray-400"></i> Cadastrar-se
					</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item rodape" href="../publico/login.jsp"> <i
						class="fas fa-lock fa-sm fa-fw mr-2 text-gray-400"></i> Entrar
					</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item rodape" href="#" data-toggle="modal"
						data-target="#modalExemplo"> <i
						class="fas fa-mobile fa-sm fa-fw mr-2 text-gray-400"></i> Acesso
					</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item rodape" href="../publico/contato.jsp">
						<i class="fas fa-envelope fa-sm fa-fw mr-2 text-gray-400"></i>
						Contato
					</a>
				</div></li>

		</ul>

	</nav>


</header>
<body>

	<script type="text/javascript"
		src="../resources/vendor/jquery/jquery.min.js"></script>
	<script type="text/javascript"
		src="../resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script type="text/javascript" src="../resources/js/sb-admin-2.min.js"></script>
</body>
</html>