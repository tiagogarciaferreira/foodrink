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
import model.Categoria;
import model.Empresa;
import model.Usuario;
import repository.RepositoryGeneric;

@Controller
@RequestMapping(value = { "estabelecimento/categoria"})
public class CategoriaController {

	private HashMap<String, String> hashMap;
	private Gson gson = new Gson();
	private StringBuilder consulta;
	private String arrayJson;
	private String objetoJson;
	private Integer index;
	private String mensagem; 
	
	@RequestMapping(value = "/nova-or-update-categoria", method = RequestMethod.POST)
	private String cadastrar(Model model, Categoria categoria, HttpServletRequest httpServletRequest) {
		try {
			categoria.setStatus(categoria.getChaveStatus().equals("ATIVA") ? true : false);
			Usuario usuario = (Usuario) httpServletRequest.getSession(true).getAttribute("userLogado");
			categoria.setEmpresa(new Empresa(new ServicoEmpresa().getId(usuario.getIdUsuario())));
			mensagem = (categoria.getIdCategoria() == null) ? "cadastroSucesso" : "atualizoSucesso";
			categoria.setQuantidadeProdutos((categoria.getQuantidadeProdutos() == null) ? 0 : categoria.getQuantidadeProdutos());
			new RepositoryGeneric<Categoria>().saveOrUpdate(categoria);
			model.addAttribute("categoria",  new Categoria());
			model.addAttribute("msgAlerta", mensagem);
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = "erro";
			model.addAttribute("msgAlerta", mensagem);
			return "estabelecimento/categoria/cadastro-categoria";
		}
		return "estabelecimento/categoria/cadastro-categoria";
	}
	
	@ResponseBody
	@RequestMapping(value = "/listarCategorias", method = RequestMethod.GET)
	private String listarCategorias(HttpServletRequest httpServletRequest) throws Exception {
		arrayJson = "";
		index = 0;
		Usuario usuario = (Usuario) httpServletRequest.getSession(true).getAttribute("userLogado");
		consulta = new StringBuilder();
		consulta.append("select c.id_categoria as \"idCategoria\", c.nome as nome from " + Categoria.class.getSimpleName());
		consulta.append(" c join "+ Empresa.class.getSimpleName() +" e on e.id_empresa = c.empresa_id ");
		consulta.append("where c.empresa_id=:id_empresa and c.status = true");
		hashMap = new HashMap<String, String>();
		hashMap.put("tipo_consulta", "SQL");
		hashMap.put("consulta", consulta.toString());
		hashMap.put("id_empresa", String.valueOf(new ServicoEmpresa().getId(usuario.getIdUsuario())));
		try {
			List<Categoria> categorias = (List<Categoria>) new RepositoryGeneric<Categoria>().getListaSQL_HQL(Categoria.class, hashMap);
			for (Categoria objeto : categorias) {
				objetoJson = gson.toJson(new Categoria(objeto.getIdCategoria(), objeto.getNome()));
				objetoJson += (index < categorias.size() - 1) ? "," : "";
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
	
	@ResponseBody
	@RequestMapping(value = "/listarCategoriasEditar", method = RequestMethod.GET)
	private String listarTabelaCategoria(HttpServletRequest httpServletRequest) {
		arrayJson = "";
		index = 0;
		consulta = new StringBuilder();
		Usuario usuario = (Usuario) httpServletRequest.getSession(true).getAttribute("userLogado");
		consulta.append("select c.id_categoria as \"idCategoria\", c.nome as nome from " + Categoria.class.getSimpleName());
		consulta.append(" c join "+ Empresa.class.getSimpleName() +" e on e.id_empresa = c.empresa_id");
		consulta.append(" where c.empresa_id=:id_empresa");
		hashMap = new HashMap<String, String>();
		hashMap.put("tipo_consulta", "SQL");
		hashMap.put("consulta", consulta.toString());
		hashMap.put("id_empresa", String.valueOf(new ServicoEmpresa().getId(usuario.getIdUsuario())));
		try {
			List<Categoria> categorias = (List<Categoria>) new RepositoryGeneric<Categoria>().getListaSQL_HQL(Categoria.class, hashMap);
			for (Categoria objeto : categorias) {
				arrayJson += "["+"\""+objeto.getIdCategoria()+"\","+"\""+objeto.getNome()+"\""+"]";  
				arrayJson += (index < categorias.size() - 1) ? "," : "";
				index++;
			}
			arrayJson = "{"+"\"draw\": 1,"+"\"data\": ["+arrayJson+"]"+"}";
		} catch (Exception e) {
			e.printStackTrace();
			return arrayJson = "{"+"\"draw\": 1,"+"\"data\": ["+arrayJson+"]"+"}";
		}
		return arrayJson;
	}

	@RequestMapping(value = "/editar-categoria", method = RequestMethod.GET)
	private String getRegistroEditar(Model model, @RequestParam(value = "id", required = true) Integer id) {
		
		try {
			Categoria categoria = new RepositoryGeneric<Categoria>().getObjetoId(Categoria.class, id);
			model.addAttribute("categoria", categoria);
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = "erro";
			model.addAttribute("msgAlerta", mensagem);
			return "estabelecimento/categoria/busca-categoria";
		}
		return "estabelecimento/categoria/cadastro-categoria";
	}
	
	@RequestMapping(value = "/remover-categoria", method = RequestMethod.GET)
	private String remover(Model model, @RequestParam(value = "id", required = true) Integer id) {
		
		try {
			new RepositoryGeneric<Categoria>().delete(new Categoria(id));
			model.addAttribute("msgAlerta", "removidoSucesso");
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = "erro";
			model.addAttribute("msgAlerta", mensagem);
			return "estabelecimento/categoria/busca-categoria";
		}
		return "estabelecimento/categoria/busca-categoria";
	}
	
}
