$(document).ready(function() {
	
	document.getElementById("statusI").checked = "";
	aviso();
	validaCadastro();
	
});

function aviso(){
	if(!$("#alerta").html().includes("novamente")){
		setTimeout(function(){
			$("#alerta").html("")
		}, 3000);
	}
}

function validaCadastro(){
	$("#formNovaCategoria").validate({
		rules : {
			nome : {
				required : true
			},
			chaveStatus :{
				required : true
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	})	
}
