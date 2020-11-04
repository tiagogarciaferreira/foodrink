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
import model.Preparo;
import model.Usuario;
import repository.RepositoryGeneric;

@Controller
@RequestMapping(value = { "/estabelecimento", "/estabelecimento/cozinha" })
public class PreparoController {

	private HashMap<String, String> hashMap;
	private StringBuilder consulta;
	private String arrayJson;
	private Integer index;

	/* LISTA OS PRODUTOS EM PREPARO */
	@ResponseBody
	@RequestMapping(value = { "/produtosAPreparar" }, method = RequestMethod.GET)
	public String listaPreparo(HttpServletRequest httpServletRequest) {
		arrayJson = "";
		index = 0;
		Usuario usuario = (Usuario) httpServletRequest.getSession(true).getAttribute("userLogado");
		Integer empresaID = new ServicoEmpresa().getId(usuario.getIdUsuario());
		consulta = new StringBuilder();
		consulta.append("select new model.Preparo(pr.idPreparo, pr.itemPedido.pedido.idPedido, ");
		consulta.append("pr.itemPedido.produto.nome, pr.itemPedido.produto.tamanho, pr.itemPedido.quantidade, pr.itemPedido.pedido.observacao) from model.Preparo pr ");
		consulta.append("where pr.itemPedido.pedido.empresa.idEmpresa=:id_empresa and pr.status=:status");
		hashMap = new HashMap<String, String>();
		hashMap.put("consulta", consulta.toString());
		hashMap.put("id_empresa", String.valueOf(empresaID));
		hashMap.put("status", EnumStatus.PREPARANDO.value());
		try {
			List<Preparo> produtosPreparo = new RepositoryGeneric<Preparo>().getListaHQLConstrutor(hashMap);
			for (Preparo preparo : produtosPreparo) {
				arrayJson += "["+"\""+preparo.getIdPreparo()+"\","+"\""+preparo.getItemPedido().getPedido().getIdPedido()+"\","+"\""+preparo.getItemPedido().getProduto().getNome()+" - "+preparo.getItemPedido().getProduto().getTamanho()+"\","+"\""+preparo.getItemPedido().getQuantidade()+"\","+"\""+preparo.getItemPedido().getPedido().getObservacao()+"\""+"]";   
				arrayJson += (index < produtosPreparo.size() - 1) ? "," : "";
				index++;
			}
			arrayJson = "{"+"\"draw\": 1,"+"\"data\": ["+arrayJson+"]"+"}";
			
		} catch (Exception e) {
			e.printStackTrace();
			return arrayJson = "{"+"\"draw\": 1,"+"\"data\": ["+arrayJson+"]"+"}";
		}
		return arrayJson;
	}
	
	@RequestMapping(value = { "/confirmar-produto-pronto" }, method = RequestMethod.GET)
	public String confirmaProdutoPronto(Model model, @RequestParam(value = "id", required = true) Integer id) {
		Preparo preparo = new RepositoryGeneric<Preparo>().getObjetoId(Preparo.class, id);
		try {
			preparo.setStatus(EnumStatus.PRONTO.value());
			new RepositoryGeneric<Preparo>().update(preparo);
		}
		catch (Exception e) {
			e.printStackTrace();
			return "redirect:../estabelecimento.jsp";
		}
		return "redirect:../estabelecimento.jsp";
	}
	
	/* LISTA OS TODOS OS PRODUTOS EM PREPARO */
	@ResponseBody
	@RequestMapping(value = { "/produtosEmPreparo" }, method = RequestMethod.GET)
	public String listaTodosPreparo(HttpServletRequest httpServletRequest) {
		arrayJson = "";
		index = 0;
		Usuario usuario = (Usuario) httpServletRequest.getSession(true).getAttribute("userLogado");
		Integer empresaID = new ServicoEmpresa().getId(usuario.getIdUsuario());
		consulta = new StringBuilder();
		consulta.append("select new model.Preparo(pr.idPreparo, pr.itemPedido.pedido.idPedido, ");
		consulta.append("pr.itemPedido.produto.nome, pr.itemPedido.produto.tamanho, pr.itemPedido.quantidade, pr.itemPedido.pedido.mesa.numero, pr.status) from model.Preparo pr ");
		consulta.append("where pr.itemPedido.pedido.empresa.idEmpresa=:id_empresa");
		hashMap = new HashMap<String, String>();
		hashMap.put("consulta", consulta.toString());
		hashMap.put("id_empresa", String.valueOf(empresaID));
		try {
			List<Preparo> produtosPreparo = new RepositoryGeneric<Preparo>().getListaHQLConstrutor(hashMap);
			for (Preparo preparo : produtosPreparo) {
				arrayJson += "["+"\""+preparo.getIdPreparo()+"\","+"\""+preparo.getItemPedido().getPedido().getIdPedido()+"\","+"\""+preparo.getItemPedido().getProduto().getNome()+" - "+preparo.getItemPedido().getProduto().getTamanho()+"\","+"\""+preparo.getItemPedido().getQuantidade()+"\","+"\""+preparo.getItemPedido().getPedido().getMesa().getNumero()+"\","+"\""+preparo.getStatus()+"\""+"]";   
				arrayJson += (index < produtosPreparo.size() - 1) ? "," : "";
				index++;
			}
			arrayJson = "{"+"\"draw\": 1,"+"\"data\": ["+arrayJson+"]"+"}";
			
		} catch (Exception e) {
			e.printStackTrace();
			return arrayJson = "{"+"\"draw\": 1,"+"\"data\": ["+arrayJson+"]"+"}";
		}
		return arrayJson;
	}
	
	/* CONFIRMA A ENTREGA DO PRODUTO DO PEDIDO */
	@RequestMapping(value = { "/confirmar-produto-entregue" }, method = RequestMethod.GET)
	public String confirmaProdutoEntregue(@RequestParam(value = "id", required = true) Integer id) {
		Preparo preparo = new RepositoryGeneric<Preparo>().getObjetoId(Preparo.class, id);
		try {
			preparo.setStatus(EnumStatus.ENTREGUE.value());
			new RepositoryGeneric<Preparo>().update(preparo);
		}
		catch (Exception e) {
			e.printStackTrace();
			return "redirect:busca-pedidos-a-entregar.jsp";
		}
		return "redirect:busca-pedidos-a-entregar.jsp";
	}
}
