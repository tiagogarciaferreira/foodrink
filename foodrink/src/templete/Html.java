package templete;

import java.util.List;
import model.Contato;
import model.ItemPedido;
import model.Pedido;
import model.Reserva;
import model.Usuario;
import util.Local;

public class Html {

	private static StringBuilder estruturaHtml;
	
	public static String novoCadastro(String url) {
		estruturaHtml = new StringBuilder();
		estruturaHtml.append("<div style=\"background-color: #0B3861;border-radius:10px;width: 100%;height: 100%;font-size:14px;text-align: center;color:white;\">");
		estruturaHtml.append("<img src=\"cid:image\" width=\"130px\" height=\"130px\"/>");
		estruturaHtml.append("<br>" + "<hr>");
		estruturaHtml.append("<div style=\"text-align: left; margin-left: 50px;\">");
		estruturaHtml.append("<label>FooDrink, confirme seu: <b style=\"color: #E6E6E6;opacity: 0.5\">Cadastro.</b></label>");
		estruturaHtml.append("<br> <label>Data do cadastro: <b style=\"color: #E6E6E6;opacity: 0.5\">"+ Local.getDataAtual() +".</b></label>");
		estruturaHtml.append("<br> <label>Hora do cadastro: <b style=\"color: #E6E6E6;opacity: 0.5\">"+ Local.getHoraAtual() +".</b></label>");
		estruturaHtml.append("</div>");
		estruturaHtml.append("<hr>" + "<br>");
		estruturaHtml.append("<button style=\" background-color: #0B610B;height:50px;border-radius:5px;\">" + "<a style=\"text-decoration: none;\" href=\" " + url + " \"+ \"> <b style=\" color:white;\">Confirmar cadastro</b> </a> " + "</button>");
		estruturaHtml.append("<br>");
		estruturaHtml.append("<br>");
		estruturaHtml.append("<footer style=\"color: #E6E6E6;font-size:10px;opacity: 0.3;\">FooDrink - Não responda este email.</footer>");
		estruturaHtml.append("</div>");
		return estruturaHtml.toString();
	}

	
	public static String novaSenha(String url) {
		estruturaHtml = new StringBuilder();
		estruturaHtml.append("<div style=\"background-color: #0B3861;border-radius:10px;width: 100%;height: 100%;font-size:14px;text-align: center;color:white;\">");
		estruturaHtml.append("<img src=\"cid:image\" width=\"130px\" height=\"130px\"/>");
		estruturaHtml.append("<br>" + "<hr>");
		estruturaHtml.append("<div style=\"text-align: left; margin-left: 50px;\">");
		estruturaHtml.append("<label>FooDrink, redefina sua: <b style=\"color: #E6E6E6;opacity: 0.5\">Senha.</b></label>");
		estruturaHtml.append("<br> <label>Data da solicitação: <b style=\"color: #E6E6E6;opacity: 0.5\">"+ Local.getDataAtual() +".</b></label>");
		estruturaHtml.append("<br> <label>Hora da solicitação: <b style=\"color: #E6E6E6;opacity: 0.5\">"+ Local.getHoraAtual() +".</b></label>");
		estruturaHtml.append("<br> <label>Solicitação valida até: <b style=\"color: #E6E6E6;opacity: 0.5\">"+ Local.getDataAtual() +".</b></label>");
		estruturaHtml.append("</div>");
		estruturaHtml.append("<hr>" + "<br>");
		estruturaHtml.append("<button style=\" background-color: #0B610B;height:50px;border-radius:5px;\">" + "<a style=\"text-decoration: none;\" href=\" " + url + " \"+ \"> <b style=\" color:white;\">Redefinir senha</b> </a> " + "</button>");
		estruturaHtml.append("<br>");
		estruturaHtml.append("<br>");
		estruturaHtml.append("<footer style=\"color: #E6E6E6;font-size:10px;opacity: 0.3;\">FooDrink - Não responda este email.</footer>");
		estruturaHtml.append("</div>");
		return estruturaHtml.toString();
	}
	
	public static String novoContato(Contato contato) {
		estruturaHtml = new StringBuilder();
		estruturaHtml.append("<div style=\"background-color: #0B3861;border-radius:10px;width: 100%;height: 100%;font-size:14px;text-align: center;color:white;\">");
		estruturaHtml.append("<img src=\"cid:image\" width=\"130px\" height=\"130px\"/>");
		estruturaHtml.append("<br>" + "<hr>");
		estruturaHtml.append("<div style=\"text-align: left; margin-left: 10px;\">");
		estruturaHtml.append("<label>FooDrink: <b style=\"color: #E6E6E6;opacity: 0.5\">Contato.</b></label>");
		estruturaHtml.append("<br> <label>Nome: <b style=\"color: #E6E6E6;opacity: 0.5\">"+ contato.getNome() +".</b></label>");
		estruturaHtml.append("<br> <label>Email: <b style=\"color: #E6E6E6;opacity: 0.5\">"+ contato.getEmail() +".</b></label>");
		estruturaHtml.append("<br> <label>Assunto: <b style=\"color: #E6E6E6;opacity: 0.5\">"+ contato.getAssunto() +".</b></label>");
		estruturaHtml.append("<br> <label>Mensagem: <b style=\"color: #E6E6E6;opacity: 0.5\">"+ contato.getMensagem() +".</b></label>");
		estruturaHtml.append("</div>");
		estruturaHtml.append("<hr>");
		estruturaHtml.append("<br>");
		estruturaHtml.append("<footer style=\"color: #E6E6E6;font-size:10px;opacity: 0.3;\">FooDrink - Não responda este email.</footer>");
		estruturaHtml.append("</div>");
		return estruturaHtml.toString();
	}
	
	public static String pedidoFinalizado(Pedido pedido) throws Exception {
		List<ItemPedido> lista = pedido.getListaItens();
		estruturaHtml = new StringBuilder();
		estruturaHtml.append("<div style=\"background-color: #0B3861;border-radius:10px;width: 100%;height: 100%;font-size:14px;text-align: center;color:white;\">");
		estruturaHtml.append("<img src=\"cid:image\" width=\"130px\" height=\"130px\"/>");
		estruturaHtml.append("<br>" + "<hr style=\"margin-left: 10px;\">");
		estruturaHtml.append("<div style=\"text-align: left;margin-left: 10px;\">");
		estruturaHtml.append("<label>FooDrink, encerramento do seu: <b style=\"color: #E6E6E6;opacity: 0.5\">Pedido.</b></label>");
		estruturaHtml.append("<br> <label>Data do pedido: <b style=\"color: #E6E6E6;opacity: 0.5\">"+ pedido.getData() +".</b></label>");
		estruturaHtml.append("<br> <label>Hora do pedido: <b style=\"color: #E6E6E6;opacity: 0.5\">"+ pedido.getHora() +".</b></label>");
		estruturaHtml.append("<br> <label>Estabelecimento: <b style=\"color: #E6E6E6;opacity: 0.5\">"+ pedido.getEmpresa().getUsuario().getNome() +".</b></label>");
		estruturaHtml.append("<br> <label>Total do pedido: <b style=\"color: #E6E6E6;opacity: 0.5\">"+ pedido.getValorPedido() +".</b></label>");
		estruturaHtml.append("<br>" + "<hr>");
		estruturaHtml.append("<label> <b style=\"color: #E6E6E6;opacity: 0.5;\"> PRODUTOS: </b> </label><br>");
		for(ItemPedido item: lista){
			estruturaHtml.append("<label>Nome: <b style=\"color: #E6E6E6;opacity: 0.5\">"+ item.getProduto().getNome()+" - " + item.getProduto().getTamanho()+"</b></label>"+"<br><label>Preço: <b style=\"color: #E6E6E6;opacity: 0.5\">"+ "R$:"+item.getProduto().getPreco()+"</b></label>"+  "<br><label>Quantidade: <b style=\"color: #E6E6E6;opacity: 0.5\">"+ item.getQuantidade()+"</b></label>");
			estruturaHtml.append("<br>" + "<hr>");
		}
		estruturaHtml.append("</div>");
		estruturaHtml.append("<br>");
		estruturaHtml.append("<br>");
		estruturaHtml.append("<footer style=\"color: #E6E6E6;font-size:10px;opacity: 0.3;\">FooDrink - Não responda este email.</footer>");
		estruturaHtml.append("</div>");
		return estruturaHtml.toString();
	}
	
	
	public static String novoFuncionario(Usuario usuario) {
		estruturaHtml = new StringBuilder();
		estruturaHtml.append("<div style=\"background-color: #0B3861;border-radius:10px;width: 100%;height: 100%;font-size:14px;text-align: center;color:white;\">");
		estruturaHtml.append("<img src=\"cid:image\" width=\"130px\" height=\"130px\"/>");
		estruturaHtml.append("<br>" + "<hr>");
		estruturaHtml.append("<div style=\"text-align: left; margin-left: 50px;\">");
		estruturaHtml.append("<label>FooDrink, dados de: <b style=\"color: #E6E6E6;opacity: 0.5\">Acesso.</b></label>");
		estruturaHtml.append("<br> <label>Usuário: <b style=\"color: #E6E6E6;opacity: 0.5\">"+ usuario.getEmail() +".</b></label>");
		estruturaHtml.append("<br> <label>Senha: <b style=\"color: #E6E6E6;opacity: 0.5\">"+ usuario.getSenha() +".</b></label>");
		estruturaHtml.append("</div>");
		estruturaHtml.append("<hr>" + "<br>");
		estruturaHtml.append("<br>");
		estruturaHtml.append("<footer style=\"color: #E6E6E6;font-size:10px;opacity: 0.3;\">FooDrink - Não responda este email.</footer>");
		estruturaHtml.append("</div>");
		return estruturaHtml.toString();
	}


	public static String statusReserva(Reserva reserva) {
		estruturaHtml = new StringBuilder();
		estruturaHtml.append("<div style=\"background-color: #0B3861;border-radius:10px;width: 100%;height: 100%;font-size:14px;text-align: center;color:white;\">");
		estruturaHtml.append("<img src=\"cid:image\" width=\"130px\" height=\"130px\"/>");
		estruturaHtml.append("<br>" + "<hr>");
		estruturaHtml.append("<div style=\"text-align: left; margin-left: 10px;\">");
		estruturaHtml.append("<label>FooDrink: <b style=\"color: #E6E6E6;opacity: 0.5\">Reserva.</b></label>");
		estruturaHtml.append("<br> <label>Numero: <b style=\"color: #E6E6E6;opacity: 0.5\">"+ reserva.getIdReserva() +".</b></label>");
		estruturaHtml.append("<br> <label>Data: <b style=\"color: #E6E6E6;opacity: 0.5\">"+ reserva.getData() +".</b></label>");
		estruturaHtml.append("<br> <label>Hora: <b style=\"color: #E6E6E6;opacity: 0.5\">"+ reserva.getHora() +".</b></label>");
		estruturaHtml.append("<br> <label>Status: <b style=\"color: #E6E6E6;opacity: 0.5\">"+ reserva.getStatus() +".</b></label>");
		estruturaHtml.append("<br> <label>Mesa: <b style=\"color: #E6E6E6;opacity: 0.5\">Nº "+reserva.getMesa().getNumero() + reserva.getMesa().getLugares()+" Lugares.</b></label>");
		estruturaHtml.append("<br> <label>Estabelecimento: <b style=\"color: #E6E6E6;opacity: 0.5\">"+ reserva.getEmpresa().getUsuario().getNome() +".</b></label>");
		estruturaHtml.append("</div>");
		estruturaHtml.append("<hr>");
		estruturaHtml.append("<br>");
		estruturaHtml.append("<footer style=\"color: #E6E6E6;font-size:10px;opacity: 0.3;\">FooDrink - Não responda este email.</footer>");
		estruturaHtml.append("</div>");
		return estruturaHtml.toString();
	}

}
