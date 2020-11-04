package controller;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import constante.EnumStatus;
import implementacao.ServicoCliente;
import implementacao.ServicoReserva;
import model.Pedido;
import model.Reserva;
import model.Usuario;
import repository.RepositoryGeneric;

@Controller
@RequestMapping(value = {"/publico","/estabelecimento/cardapio"})
public class HistoricoController {

	private String mensagem;
	private StringBuilder consulta;
	private HashMap<String, String> hashMap;
	
	/* LISTA O HISTORICO DE PEDIDOS E RESERVAS DO CLIENTE*/
	@RequestMapping(value = {"/get-meu-historico"}, method = RequestMethod.GET)
	private String listaHistorico(Model model, HttpServletRequest httpServletRequest) {
		
		Usuario usuario = (Usuario) httpServletRequest.getSession(true).getAttribute("userLogado");
		Integer clienteID = new ServicoCliente().getId(usuario.getIdUsuario());
		consulta = new StringBuilder();
		consulta.append("from "+Pedido.class.getSimpleName());
		consulta.append(" p where cliente_id=:id_cliente and status=:status");
		hashMap = new HashMap<String, String>();
		hashMap.put("consulta", consulta.toString());
		hashMap.put("id_cliente", String.valueOf(clienteID));
		hashMap.put("status", EnumStatus.FINALIZADO.value());
		try {
			List<Pedido> pedidos = new RepositoryGeneric<Pedido>().getListaTodosHQL(hashMap);
			List<Reserva> reservas = new ServicoReserva().getReservas(clienteID, true);
			mensagem = "stop";
			model.addAttribute("msgAlerta", mensagem);
			model.addAttribute("reserva", reservas);
			model.addAttribute("pedido", pedidos);
		} catch(Exception e) {
			e.printStackTrace();
			mensagem = "sem resultado";
			model.addAttribute("msgAlerta", mensagem);
			return "estabelecimento/cardapio/historico";
		}
		return "estabelecimento/cardapio/historico";
	}
}
