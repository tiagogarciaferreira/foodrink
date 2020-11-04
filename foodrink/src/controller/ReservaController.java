package controller;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import constante.EnumStatus;
import implementacao.ServicoCliente;
import implementacao.ServicoEmpresa;
import implementacao.ServicoReserva;
import model.Empresa;
import model.Reserva;
import model.Usuario;
import repository.RepositoryGeneric;

@Controller
@RequestMapping(value = { "estabelecimento/reserva" })
public class ReservaController {
	
	private String arrayJson;
	private Integer index;
	private StringBuilder consulta;
	private HashMap<String, String> hashMap;
	private String mensagem; 
		
	@RequestMapping(value = "/nova-or-update-reserva", method = RequestMethod.POST)
	private String cadastrar(Model model, Reserva reserva, HttpServletRequest httpServletRequest) {
		String identificador = httpServletRequest.getParameter("empresa_identificador");
		try {
			if(!identificador.isEmpty()) {
				reserva.getCliente().setIdCliente(new ServicoCliente().getId(reserva.getCliente().getIdCliente()));
				reserva.setEmpresa(new Empresa(Integer.parseInt(identificador.trim())));
			}
			else {
				Usuario usuario = (Usuario) httpServletRequest.getSession(true).getAttribute("userLogado");
				reserva.setEmpresa(new Empresa(new ServicoEmpresa().getId(usuario.getIdUsuario())));
			}
			mensagem = (reserva.getIdReserva() == null) ? "cadastroSucesso" : "atualizoSucesso";
			new RepositoryGeneric<Reserva>().saveOrUpdate(reserva);
			new Thread(new ServicoReserva(1, reserva)).start();
			model.addAttribute("msgAlerta", mensagem);
			model.addAttribute("reserva", new Reserva());
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = "erro"; 
			model.addAttribute("msgAlerta", mensagem);
			return "estabelecimento/reserva/cadastro-reserva";
		}
		return "estabelecimento/reserva/cadastro-reserva";
	}

	@ResponseBody
	@RequestMapping(value = "/listarReservasEditar", method = RequestMethod.GET)
	private String listarTabelaReserva(HttpServletRequest httpServletRequest, @RequestParam(value = "listarTodos", required = true) boolean listarTodos) {
		arrayJson = "";
		index = 0;
		Usuario usuario = (Usuario) httpServletRequest.getSession(true).getAttribute("userLogado");
		Integer empresaID = new ServicoEmpresa().getId(usuario.getIdUsuario());
		consulta = new StringBuilder();
		consulta.append("select new model.Reserva(r.idReserva, r.data, r.hora, r.status, r.cliente.usuario.nome, r.mesa.numero) from model.Reserva");
		consulta.append(" r where r.empresa.idEmpresa =:id_empresa");
		consulta.append((!listarTodos) ? " and r.status in (:status_A,:status_B)" : "");
		hashMap = new HashMap<String, String>();
		hashMap.put("consulta", consulta.toString());
		hashMap.put("id_empresa", String.valueOf(empresaID));
		if(!listarTodos) {
			hashMap.put("status_A", EnumStatus.PENDENTE.value());
			hashMap.put("status_B", EnumStatus.CONFIRMADA.value());
		}
		try {
			List<Reserva> reservas = (List<Reserva>) new RepositoryGeneric<Reserva>().getListaHQLConstrutor(hashMap);
			for (Reserva objeto : reservas) {
				arrayJson += "["+"\""+objeto.getIdReserva()+"\","+"\""+objeto.getCliente().getUsuario().getNome()+"\","+"\""+objeto.getMesa().getNumero()+"\","+"\""+objeto.getData()+" - "+objeto.getHora()+"\","+"\""+objeto.getStatus()+"\""+"]";  
				arrayJson += (index < reservas.size() - 1) ? "," : "";
				index++;
			}
			arrayJson = "{"+"\"draw\": 1,"+"\"data\": ["+arrayJson+"]"+"}";
		} catch (Exception e) {
			e.printStackTrace();
			return arrayJson = "{"+"\"draw\": 1,"+"\"data\": ["+arrayJson+"]"+"}";
		}
		return arrayJson;
	}
	
	@RequestMapping(value = "/cancelar-reserva", method = RequestMethod.GET)
	private String cancelar(Model model, @RequestParam(value = "reservaID", required = true) Integer reservaID) {
		
		try {
			Reserva reserva = new RepositoryGeneric<Reserva>().getObjetoId(Reserva.class, reservaID);
			reserva.setStatus(EnumStatus.CANCELADA.value());
			new RepositoryGeneric<Reserva>().update(reserva);
			new ServicoReserva(1, reserva).avisoStatus();
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = "erro"; 
			model.addAttribute("msgAlerta", mensagem);
			return "estabelecimento/estabelecimento";
		}
		return "estabelecimento/estabelecimento";
	}
	

	@RequestMapping(value = "/editar-reserva", method = RequestMethod.GET)
	private String getRegistroEditar(Model model, @RequestParam(value = "id", required = true) Integer id) {
		
		try {
			Reserva reserva = new RepositoryGeneric<Reserva>().getObjetoId(Reserva.class, id);
			model.addAttribute("reserva", reserva);
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = "erro"; 
			model.addAttribute("msgAlerta", mensagem);
			return "estabelecimento/reserva/busca-produto";
		}
		return "estabelecimento/reserva/cadastro-reserva";
	}
	
	@RequestMapping(value = "/remover-reserva", method = RequestMethod.GET)
	private String remover(Model model, @RequestParam(value = "id", required = true) Integer id) {
		try {
			new RepositoryGeneric<Reserva>().delete(new Reserva(id));
			mensagem = "removidoSucesso"; 
			model.addAttribute("msgAlerta", mensagem);
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = "erro";
			model.addAttribute("msgAlerta", mensagem);
			return "estabelecimento/reserva/busca-reserva";
		}
		return "estabelecimento/reserva/busca-reserva";
	}

}
