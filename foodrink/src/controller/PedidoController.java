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
import implementacao.ServicoEmpresa;
import implementacao.ServicoPedido;
import implementacao.ServicoPreparo;
import model.Empresa;
import model.ItemPedido;
import model.Pedido;
import model.Preparo;
import model.Usuario;
import repository.RepositoryGeneric;
import util.Local;

@Controller
@RequestMapping(value = { "/estabelecimento/pedido" })
public class PedidoController {

	private HashMap<String, String> hashMap;
	private StringBuilder consulta;
	private String arrayJson;
	private Integer index;
	private String mensagem;

	@RequestMapping(value = { "/novo-or-update-pedido" }, method = RequestMethod.POST)
	public String cadastrar(Model model, Pedido pedido, ItemPedido itemPedido, 
			HttpServletRequest httpServletRequest) {
		try {
			Usuario usuario = (Usuario) httpServletRequest.getSession(true).getAttribute("userLogado");
			pedido.setEmpresa(new Empresa(new ServicoEmpresa().getId(usuario.getIdUsuario())));
			if (pedido.getIdPedido() == null) {
				pedido.setData(Local.getDataAtual());
				pedido.setHora(Local.getHoraAtual());
				pedido = new RepositoryGeneric<Pedido>().merge(pedido);
				mensagem = "cadastroSucesso";
			} else {
				new RepositoryGeneric<Pedido>().update(pedido);
				mensagem = "atualizoSucesso";
			}
			List<ItemPedido> listaItens = new ServicoPedido().getItensPedidoObjeto(itemPedido.getListaItensJsonPedido(),
					pedido.getIdPedido());
			for (ItemPedido item : listaItens) {
				if (item.getIdItemPedido() == null) {
					ItemPedido itemAtual = new RepositoryGeneric<ItemPedido>().merge(item);
					new RepositoryGeneric<Preparo>().save(
							new Preparo(new ItemPedido(itemAtual.getIdItemPedido()), EnumStatus.PREPARANDO.value()));
				} else {
					new RepositoryGeneric<ItemPedido>().update(item);
				}
			}

			if (pedido.getStatus().equals(EnumStatus.FINALIZADO.value())) {
				new ServicoPreparo().remover(pedido.getIdPedido());
				new Thread(new ServicoPedido(pedido)).start();
				mensagem = "finalizadoSucesso";
			}

			model.addAttribute("msgAlerta", mensagem);
			model.addAttribute("pedido", new Pedido());
			model.addAttribute("item", new ItemPedido());
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = "erro";
			model.addAttribute("msgAlerta", mensagem);
			return "estabelecimento/pedido/cadastro-pedido";
		}
		return "estabelecimento/pedido/cadastro-pedido";
	}

	@ResponseBody
	@RequestMapping(value = "/listarPedidosEditar", method = RequestMethod.GET)
	private String listarTabelaPedido(HttpServletRequest httpServletRequest,
			@RequestParam(value = "listarTodos", required = true) boolean listarTodos) {
		arrayJson = "";
		index = 0;
		consulta = new StringBuilder();
		Usuario usuario = (Usuario) httpServletRequest.getSession(true).getAttribute("userLogado");
		consulta.append("select new model.Pedido(p.idPedido, p.mesa.numero, p.data, p.hora, p.status) from "
				+ Pedido.class.getSimpleName());
		consulta.append(" p where p.empresa.idEmpresa=:id_empresa");
		consulta.append((!listarTodos) ? " and p.status=:status" : "");
		hashMap = new HashMap<String, String>();
		hashMap.put("consulta", consulta.toString());
		hashMap.put("id_empresa", String.valueOf(new ServicoEmpresa().getId(usuario.getIdUsuario())));
		if (!listarTodos) {
			hashMap.put("status", EnumStatus.EM_ANDAMENTO.value());
		}
		try {
			List<Pedido> pedidos = (List<Pedido>) new RepositoryGeneric<Pedido>().getListaHQLConstrutor(hashMap);
			for (Pedido objeto : pedidos) {
				arrayJson += "[" + "\"" + objeto.getIdPedido() + "\"," + "\"" + objeto.getMesa().getNumero() + "\","
						+ "\"" + objeto.getData() + " - " + objeto.getHora() + "\"," + "\"" + objeto.getStatus() + "\""
						+ "]";
				arrayJson += (index < pedidos.size() - 1) ? "," : "";
				index++;
			}
			arrayJson = "{" + "\"draw\": 1," + "\"data\": [" + arrayJson + "]" + "}";
		} catch (Exception e) {
			e.printStackTrace();
			return arrayJson = "{" + "\"draw\": 1," + "\"data\": [" + arrayJson + "]" + "}";
		}
		return arrayJson;
	}

	@RequestMapping(value = "/editar-pedido", method = RequestMethod.GET)
	private String getRegistroEditar(Model model, @RequestParam(value = "id", required = true) Integer id) {

		try {
			Pedido pedido = new RepositoryGeneric<Pedido>().getObjetoId(Pedido.class, id);
			List<ItemPedido> itens = pedido.getListaItens();
			ItemPedido itemPedido = new ItemPedido(new ServicoPedido().setItensPedidoJson(itens));
			model.addAttribute("pedido", pedido);
			model.addAttribute("item", itemPedido);
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = "erro";
			model.addAttribute("msgAlerta", mensagem);
			return "estabelecimento/pedido/busca-pedido";
		}
		return "estabelecimento/pedido/cadastro-pedido";
	}

	@RequestMapping(value = "/remover-pedido", method = RequestMethod.GET)
	private String remover(Model model, @RequestParam(value = "id", required = true) Integer id) {

		try {
			Pedido pedido = new RepositoryGeneric<Pedido>().getObjetoId(Pedido.class, id);
			List<ItemPedido> itens = pedido.getListaItens();
			new RepositoryGeneric<Pedido>().clearSession();
			for (ItemPedido item : itens) {
				new RepositoryGeneric<Preparo>()
						.deletar("delete from preparo where item_pedido_id=" + item.getIdItemPedido());
				new RepositoryGeneric<ItemPedido>()
						.deletar("delete from item_pedido where id_item_pedido=" + item.getIdItemPedido());
			}
			new RepositoryGeneric<Pedido>().delete(new Pedido(id));
			model.addAttribute("msgAlerta", "removidoSucesso");
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = "erro";
			model.addAttribute("msgAlerta", mensagem);
			return "estabelecimento/pedido/busca-pedido";
		}
		return "estabelecimento/pedido/busca-pedido";
	}
}
