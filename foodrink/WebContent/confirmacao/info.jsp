<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
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
  <div class="container"style="margin: auto;">
    <div class="row justify-content-center">
      <div class="col-md-6" >
        <div class="card o-hidden border-0 shadow-lg my-5 invisivel">
          <div class="card-body p-0">
            <div class="row" >
              <div class="col-md-12" style="margin: auto;">
              <h1 class="h4 text-gray-900 mb-4 alert alert-info text-center"><b>Confirmação de Cadastro</b></h1>
                <div class="p-4 text-center"  style="margin-top: -10px;">
                    <p class="mb-4 pad">Para confirmar seu cadastro basta clicar no link que foi enviado para o seu email.</p>
                   <div class="text-center">
                    <a class="small btn btn-primary btn-user btn-block" href="../publico/login.jsp">Ir para o login</a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

</body>

</html>
