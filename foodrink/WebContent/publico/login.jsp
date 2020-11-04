<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="pt-br">

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

<body class=" fundo"  style="z-index: -1">
<c:import url="../template/app.jsp"/>
<br><br>
  <div class="container col-md-9" style="margin: auto;">
    <div class="row justify-content-center">
      <div class="col-md-6" >
        <div class="card o-hidden border-0 shadow-lg my-5 fundo invisivel" > 
        <h1 class="h4 text-gray-900 mb-4 alert alert-info text-center"><b>LOGIN</b></h1>
        <div class="text-center" style="margin-bottom:-80px;margin-top: -20px"> <a href="foodrink.jsp" ><img src="../resources/img/logo.png" width="120px" height="120px"></a><br><br></div>
           <c:if test="${!empty param.acesso}">              
                <c:if test="${SPRING_SECURITY_LAST_EXCEPTION.message == 'Bad credentials'}"> 
              		 <span class="text-center" style="color: red;margin-bottom: -25px"> <br><br><br> <b>Login ou senha inválidos.</b></span>
          		</c:if>
          		
          		<c:if test="${SPRING_SECURITY_LAST_EXCEPTION.message == 'Maximum sessions of 1 for this principal exceeded'}"> 
              		 <span class="text-center" style="color: red;margin-bottom: -25px"> <br><br><br> <b>Você já está logado em outra sessão(Dispositivo).</b></span>
          		</c:if>
          		
          </c:if>
          <div class="card-body p-0">
            <div class="row">
              <div class="col-md-12" style="margin: auto;">
                <div class="p-5">
                  <form id="form_login" action="../publico/login" method="POST">
                    <div class="form-group">
                    <label for="usuario" class="margembaixo ">Usuário</label>
                      <input type="email" class="form-control form-control-user text-center" id="username" name="username" placeholder="Informe seu email"  >
                    </div>
                    <div class="form-group">
                  	  <label for="password" class="margembaixo ">Senha</label>
                      <input type="password" class="form-control form-control-user text-center" id="password" name="password" value="1234567899"  placeholder="Informe sua senha" >
                      <a class="small" href="../confirmacao/email-redefinir-senha.jsp">Esqueceu sua senha?</a>
                    </div>
                    <button  type="submit" class="btn btn-primary btn-user btn-block mao"><i class="fa fa-check-circle"></i> Entrar</button>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  

  <script type="text/javascript" src="../resources/js/demo/jquery.validate.js"></script>
  <script type="text/javascript" src="../resources/js/demo/additional-methods.js"></script>
  <script type="text/javascript" src="../resources/js/demo/jquery.mask.min.js"></script>
  <script type="text/javascript" src="../resources/js/demo/messages_pt_BR.js"></script>
  <script type="text/javascript" src="../resources/js/validacao_menor.js"></script>

</body>

</html>
