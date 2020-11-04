<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
	<div class="modal fade text-center" id="modalExemplo" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content fundo">
				<div >
					<h5 class="modal-title" id="exampleModalLabel">
					<button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
			         		 <span aria-hidden="true">&times;</span>
			        	</button>
						<span style="color: white;">Facilite seu acesso ao FooDrink</span> .<br>Baixe o APP mobile para
						sistemas Android.<br>Acesse também o FooDrink pelo QR CODE.
						
					</h5>
					<hr>
				</div>
				<a target="_blank" href="https://www.tudocelular.com/curiosidade/noticias/n139751/como-instalar-apps-fontes-desconhecidas-android.html">Ajuda para instalação.</a>
				<div class="modal-body">
					<img src="../resources/img/qr-app.png" width="150px" height="150px" id ="app" name="app" title="Código QR APP">
					<img src="../resources/img/qr-site.png" width="150px" height="150px" id="site" name="site" title="Código QR Sistema">
					<br> <a class="linha " href="baixar-app"> <span>Faça o
							download AQUI ou faça a leitura do QR CODE.</span></a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>