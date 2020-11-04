package controller;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import model.Categoria;
import model.Produto;
import repository.RepositoryGeneric;

@Controller
@RequestMapping(value = {"/publico","/estabelecimento/cardapio"})
public class CardapioController {

	private String mensagem;
	private StringBuilder consulta;
	private HashMap<String, String> hashMap;
	private String localSolicitacao;
	private String redirecionar = "";
	
	/* LISTA AS CATEGORIAS DE PRODUTOS DO ESTABELECIMENTO */
	@RequestMapping(value = {"/categorias-produto-estabelecimento"}, method = RequestMethod.GET)
	private String listarCategorias(Model model, @RequestParam(value = "empresaID", required = true) Integer empresaID, HttpServletRequest httpServletRequest) {
		localSolicitacao = httpServletRequest.getRequestURI();
		consulta = new StringBuilder();
		consulta.append("select c.id_categoria as \"idCategoria\", c.nome as nome, c.quantidade_produtos as \"quantidadeProdutos\" from " + Categoria.class.getSimpleName());
		consulta.append(" c where c.empresa_id=:id_empresa and c.status=true");
		hashMap = new HashMap<String, String>();
		hashMap.put("tipo_consulta", "SQL");
		hashMap.put("consulta", consulta.toString());
		hashMap.put("id_empresa", String.valueOf(empresaID));
		
		try {
			List<Categoria> lista = new RepositoryGeneric<Categoria>().getListaSQL_HQL(Categoria.class, hashMap);
			mensagem = (!lista.isEmpty() ? "" : "sem resultado");
			redirecionar = (localSolicitacao.contains("cardapio")) ? "estabelecimento/cardapio/lista-categorias" : "publico/lista-cardapios";
			model.addAttribute("msgAlerta", mensagem);
			model.addAttribute("categoria", lista);
		} catch(Exception e) {
			e.printStackTrace();
			mensagem = "sem resultado";
			model.addAttribute("msgAlerta", mensagem);
			return redirecionar;
		}
		return redirecionar;
	}
	
	/* LISTA OS PRODUTOS DE UMA CATEGORIA DO ESTABELECIMENTO */
	@RequestMapping(value = {"/produtos-categoria-estabelecimento"}, method = RequestMethod.GET)
	private String listarprodutos(Model model, @RequestParam(value = "categoriaID", required = true) Integer categoriaID, HttpServletRequest httpServletRequest) {
		localSolicitacao = httpServletRequest.getRequestURI();
		consulta = new StringBuilder();
		consulta.append("select p.imagem as imagem, p.nome as nome, p.tamanho as tamanho, p.preco as preco, p.descricao as descricao from " + Produto.class.getSimpleName());
		consulta.append(" p where p.categoria_id=:id_categoria and p.status=true");
		hashMap = new HashMap<String, String>();
		hashMap.put("tipo_consulta", "SQL");
		hashMap.put("consulta", consulta.toString());
		hashMap.put("id_categoria", String.valueOf(categoriaID));
		
		try {
			List<Produto> lista = new RepositoryGeneric<Produto>().getListaSQL_HQL(Produto.class, hashMap);
			mensagem = (!lista.isEmpty() ? "" : "sem resultado");
			redirecionar = (localSolicitacao.contains("cardapio")) ? "estabelecimento/cardapio/lista-categorias" : "publico/lista-cardapios";
			model.addAttribute("msgAlerta", mensagem);
			model.addAttribute("produto", lista);
		} catch(Exception e) {
			e.printStackTrace();
			mensagem = "sem resultado";
			model.addAttribute("msgAlerta", mensagem);
			return redirecionar;
		}
		return redirecionar;
	}
	
}
