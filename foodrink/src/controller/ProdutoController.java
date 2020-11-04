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

import implementacao.ServicoCategoria;
import implementacao.ServicoEmpresa;
import model.Categoria;
import model.Produto;
import model.Usuario;
import repository.RepositoryGeneric;

@Controller
@RequestMapping(value = { "estabelecimento/produto" })
public class ProdutoController {
	
	private String arrayJson;
	private Integer index;
	private StringBuilder consulta;
	private HashMap<String, String> hashMap;
	private String mensagem; 

	@RequestMapping(value = "/novo-or-update-produto", method = RequestMethod.POST)
	private String cadastrar(Model model, Produto produto) {
		try {
			mensagem = "erro";
			produto.setStatus(produto.getChaveStatus().equals("DISPONIVEL") ? true : false);
			mensagem = (produto.getIdProduto() == null) ? "cadastroSucesso" : "atualizoSucesso";
		    new RepositoryGeneric<Produto>().saveOrUpdate(produto);
		    if(mensagem.equals("cadastroSucesso")) {
		    	new Thread(new ServicoCategoria(produto.getCategoria().getIdCategoria(), 1)).start();
		    }
			model.addAttribute("msgAlerta", mensagem);
			model.addAttribute("produto", new Produto());
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msgAlerta", mensagem);
			return "estabelecimento/produto/cadastro-produto";
		}
		return "estabelecimento/produto/cadastro-produto";
	}
	
	@ResponseBody
	@RequestMapping(value = "/listarProdutosPedido", method = RequestMethod.GET)
	private String listarProdutoPedido(HttpServletRequest httpServletRequest) {
		arrayJson = "";
		index = 0;
		Usuario usuario = (Usuario) httpServletRequest.getSession(true).getAttribute("userLogado");
		Integer empresaID = new ServicoEmpresa().getId(usuario.getIdUsuario());
		consulta = new StringBuilder();
		consulta.append("select p.id_produto as \"idProduto\", p.nome as nome, p.preco as preco, p.tamanho as tamanho from " + Produto.class.getSimpleName());
		consulta.append(" p join "+ Categoria.class.getSimpleName() +" c on c.id_categoria = p.categoria_id");
		consulta.append(" where c.empresa_id =:id_empresa");
		hashMap = new HashMap<String, String>();
		hashMap.put("tipo_consulta", "SQL");
		hashMap.put("consulta", consulta.toString());
		hashMap.put("id_empresa", String.valueOf(empresaID));
		try {
			List<Produto> produtos = (List<Produto>) new RepositoryGeneric<Produto>().getListaSQL_HQL(Produto.class, hashMap);
			for (Produto objeto : produtos) {
				arrayJson += "["+"\""+objeto.getIdProduto()+"\","+"\""+objeto.getNome()+" - "+objeto.getTamanho()+"\","+"\""+objeto.getPreco()+"\""+"]";  
				arrayJson += (index < produtos.size() - 1) ? "," : "";
				index++;
			}
			arrayJson = "{"+"\"draw\": 1,"+"\"data\": ["+arrayJson+"]"+"}";
		} catch (Exception e) {
			e.printStackTrace();
			return arrayJson = "{"+"\"draw\": 1,"+"\"data\": ["+arrayJson+"]"+"}";
		}
		return arrayJson;
	}

	@ResponseBody
	@RequestMapping(value = "/listarProdutosEditar", method = RequestMethod.GET)
	private String listarTabelaProduto(HttpServletRequest httpServletRequest) {
		arrayJson = "";
		index = 0;
		Usuario usuario = (Usuario) httpServletRequest.getSession(true).getAttribute("userLogado");
		Integer empresaID = new ServicoEmpresa().getId(usuario.getIdUsuario());
		consulta = new StringBuilder();
		consulta.append("select p.id_produto as \"idProduto\", p.nome as nome from " + Produto.class.getSimpleName());
		consulta.append(" p join "+ Categoria.class.getSimpleName() +" c on c.id_categoria = p.categoria_id");
		consulta.append(" where c.empresa_id =:id_empresa");
		hashMap = new HashMap<String, String>();
		hashMap.put("tipo_consulta", "SQL");
		hashMap.put("consulta", consulta.toString());
		hashMap.put("id_empresa", String.valueOf(empresaID));
		try {
			List<Produto> produtos = (List<Produto>) new RepositoryGeneric<Produto>().getListaSQL_HQL(Produto.class, hashMap);
			for (Produto objeto : produtos) {
				arrayJson += "["+"\""+objeto.getIdProduto()+"\","+"\""+objeto.getNome()+"\""+"]";  
				arrayJson += (index < produtos.size() - 1) ? "," : "";
				index++;
			}
			arrayJson = "{"+"\"draw\": 1,"+"\"data\": ["+arrayJson+"]"+"}";
		} catch (Exception e) {
			e.printStackTrace();
			return arrayJson = "{"+"\"draw\": 1,"+"\"data\": ["+arrayJson+"]"+"}";
		}
		return arrayJson;
	}

	@RequestMapping(value = "/editar-produto", method = RequestMethod.GET)
	private String getRegistroEditar(Model model, @RequestParam(value = "id", required = true) Integer id) {
		
		try {
			Produto produto = new RepositoryGeneric<Produto>().getObjetoId(Produto.class, id);
			model.addAttribute("produto", produto);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msgAlerta", "erro");
			return "estabelecimento/produto/busca-produto";
		}
		return "estabelecimento/produto/cadastro-produto";
	}
	
	@RequestMapping(value = "/remover-produto", method = RequestMethod.GET)
	private String remover(Model model, @RequestParam(value = "id", required = true) Integer id) {
		try {
			Produto produto = new RepositoryGeneric<Produto>().getObjetoId(Produto.class, id);
			new RepositoryGeneric<Produto>().clearSession();
			new RepositoryGeneric<Produto>().delete(new Produto(id));
			new Thread(new ServicoCategoria(produto.getCategoria().getIdCategoria(), -1)).start();
			model.addAttribute("msgAlerta", "removidoSucesso");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msgAlerta", "erro");
			return "estabelecimento/produto/busca-produto";
		}
		return "estabelecimento/produto/busca-produto";
	}
}
