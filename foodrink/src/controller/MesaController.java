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
import com.google.gson.Gson;
import implementacao.ServicoEmpresa;
import implementacao.ServicoMesa;
import implementacao.ServicoReserva;
import model.Empresa;
import model.Mesa;
import model.Usuario;
import repository.RepositoryGeneric;
import util.Local;

@Controller
@RequestMapping(value = { "estabelecimento/mesa" })
public class MesaController {

	private String arrayJson;
	private String objetoJson;
	private Integer index;
	private Gson gson = new Gson();
	private StringBuilder consulta;
	private HashMap<String, String> hashMap;
	private String mensagem;
	
	/* SALVA OU ATUALIZA UM REGISTRO DE MESA */
	@RequestMapping(value = "/nova-or-update-mesa", method = RequestMethod.POST)
	private String cadastrar(Model model, Mesa mesa, HttpServletRequest httpServletRequest) {
		try {
			Usuario usuario = (Usuario) httpServletRequest.getSession(true).getAttribute("userLogado");
			mesa.setEmpresa(new Empresa(new ServicoEmpresa().getId(usuario.getIdUsuario())));
			mensagem = (mesa.getIdMesa() == null) ? "cadastroSucesso" : "atualizoSucesso";
			new RepositoryGeneric<Mesa>().saveOrUpdate(mesa);
			model.addAttribute("msgAlerta", mensagem);
			model.addAttribute("mesa", new Mesa());
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = "erro";
			model.addAttribute("msgAlerta", mensagem);
			return "estabelecimento/mesa/cadastro-mesa";
		}
		return "estabelecimento/mesa/cadastro-mesa";
	}

	/* LISTA MESAS PARA UMA NOVA RESERVA */
	@ResponseBody
	@RequestMapping(value = "/listarMesas", method = RequestMethod.GET)
	private String listarMesas(HttpServletRequest httpServletRequest,
			@RequestParam(value = "selecaoMesa", required = true) String selecaoMesa,
			@RequestParam(value = "empresa_identificador", required = true) String empresa_identificador,
			@RequestParam(value = "data", required = true) String data) throws Exception {
		new Thread(new ServicoReserva(0,null)).start();
		arrayJson = "";index = 0;
		Integer empresaID;
		if(empresa_identificador.isEmpty()) {
			Usuario usuario = (Usuario) httpServletRequest.getSession(true).getAttribute("userLogado");
			empresaID = new ServicoEmpresa().getId(usuario.getIdUsuario());
		}
		else {
			empresaID = Integer.parseInt(empresa_identificador);
		}
		try {
			List<Mesa> mesas = new ServicoMesa().getMesasDisponiveis(empresaID, data, selecaoMesa);
			for (Mesa objeto : mesas) {
				objetoJson = gson.toJson(new Mesa(objeto.getIdMesa(), objeto.getNumero(), objeto.getLugares(), objeto.getDescricao()));
				objetoJson += (index < mesas.size() - 1) ? "," : "";
				index++;
				arrayJson += objetoJson;
			}
			arrayJson = "[" + arrayJson + "]";
		} catch (Exception e) {
			e.printStackTrace();
			return arrayJson = "[" + arrayJson + "]";
		}
		return arrayJson;
	}

	/* LISTA MESAS PARA EDITAR OU EXCLUIR */
	@ResponseBody
	@RequestMapping(value = "/listarMesasEditar", method = RequestMethod.GET)
	private String listarTabelaMesa(HttpServletRequest httpServletRequest) {
		arrayJson = "";
		index = 0;
		Usuario usuario = (Usuario) httpServletRequest.getSession(true).getAttribute("userLogado");
		consulta = new StringBuilder();
		consulta.append("select m.id_mesa as \"idMesa\", m.numero as numero from " + Mesa.class.getSimpleName());
		consulta.append(" m join " + Empresa.class.getSimpleName() + " e on e.id_empresa = m.empresa_id");
		consulta.append(" where m.empresa_id =:id_empresa");
		hashMap = new HashMap<String, String>();
		hashMap.put("tipo_consulta", "SQL");
		hashMap.put("consulta", consulta.toString());
		hashMap.put("id_empresa", String.valueOf(new ServicoEmpresa().getId(usuario.getIdUsuario())));
		try {
			List<Mesa> mesas = (List<Mesa>) new RepositoryGeneric<Mesa>().getListaSQL_HQL(Mesa.class, hashMap);
			for (Mesa objeto : mesas) {
				arrayJson += "[" + "\"" + objeto.getIdMesa() + "\", " + "\"" + objeto.getNumero() + "\"" + "]";
				arrayJson += (index < mesas.size() - 1) ? "," : "";
				index++;
			}
			arrayJson = "{" + "\"draw\": 1," + "\"data\": [" + arrayJson + "]" + "}";
		} catch (Exception e) {
			e.printStackTrace();
			return arrayJson = "{" + "\"draw\": 1," + "\"data\": [" + arrayJson + "]" + "}";
		}
		return arrayJson;
	}

	/* LISTA MESAS DISPONIVEIS PARA UM NOVO PEDIDO */
	@ResponseBody
	@RequestMapping(value = "/listarMesasPedido", method = RequestMethod.GET)
	private String listarMesa(HttpServletRequest httpServletRequest, @RequestParam(value = "selecaoMesa", required = true) String selecaoMesa) {
		new Thread(new ServicoReserva(0,null)).start();
		arrayJson = "";index = 0;
		Usuario usuario = (Usuario) httpServletRequest.getSession(true).getAttribute("userLogado");
		Integer empresaID = new ServicoEmpresa().getId(usuario.getIdUsuario());
		try {
			List<Mesa> mesas = new ServicoMesa().getMesasDisponiveis(empresaID, Local.getDataAtual(), selecaoMesa);
			for (Mesa objeto : mesas) {
				objetoJson = gson.toJson(new Mesa(objeto.getIdMesa(), objeto.getNumero(),null));
				objetoJson += (index < mesas.size() - 1) ? "," : "";
				index++;
				arrayJson += objetoJson;
			}
			arrayJson = "[" + arrayJson + "]";
		} catch (Exception e) {
			e.printStackTrace();
			return arrayJson = "[" + arrayJson + "]";
		}
		return arrayJson;
	}

	/* BUSCA O OBJETO PARA EDIÇÃO E MANDA PARA A TELA */
	@RequestMapping(value = "/editar-mesa", method = RequestMethod.GET)
	private String getRegistroEditar(Model model, @RequestParam(value = "id", required = true) Integer id) {

		try {
			Mesa mesa = new RepositoryGeneric<Mesa>().getObjetoId(Mesa.class, id);
			model.addAttribute("mesa", mesa);
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = "erro";
			model.addAttribute("msgAlerta", mensagem);
			return "estabelecimento/mesa/busca-mesa";
		}
		return "estabelecimento/mesa/cadastro-mesa";
	}

	/* EXCLUI UMA MESA */
	@RequestMapping(value = "/remover-mesa", method = RequestMethod.GET)
	private String remover(Model model, @RequestParam(value = "id", required = true) Integer id) {
		try {
			new RepositoryGeneric<Mesa>().delete(new Mesa(id));
			mensagem = "removidoSucesso";
			model.addAttribute("msgAlerta", mensagem);
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = "erro";
			model.addAttribute("msgAlerta", mensagem);
			return "estabelecimento/mesa/busca-mesa";
		}
		return "estabelecimento/mesa/busca-mesa";
	}

}
