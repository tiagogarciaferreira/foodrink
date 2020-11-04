package controller;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import model.Empresa;
import model.Filtro;
import repository.RepositoryGeneric;

@Controller
@RequestMapping(value = { "/publico", "/estabelecimento/cardapio" })
public class FiltroController {

	private String mensagem;
	private StringBuilder consulta;
	private HashMap<String, String> hashMap;
	private String localSolicitacao;
	private String redirecionar = "";

	/* LISTA OS ESTABELECIMENTOS */
	@RequestMapping(value = { "/pesquisar-estabelecimento" }, method = RequestMethod.POST)
	private String buscar(Model model, Filtro filtro, HttpServletRequest httpServletRequest) {
		localSolicitacao = httpServletRequest.getRequestURI();
		consulta = new StringBuilder();
		consulta.append("select new model.Empresa(e.idEmpresa, e.funcionamento, e.usuario.imagem, ");
		consulta.append("e.usuario.nome, e.usuario.telefone, e.usuario.endereco.estado.nome, e.usuario.endereco.cidade.nome, ");
		consulta.append("e.usuario.endereco.bairro, e.usuario.endereco.rua, e.usuario.endereco.numero, e.usuario.endereco.cep, e.usuario.endereco.complemento) from model.Empresa e ");
		consulta.append(" where e.usuario.endereco.estado.idEstado=:estado and e.usuario.endereco.cidade.idCidade=:cidade and e.usuario.status=true ");
		hashMap = new HashMap<String, String>();
		hashMap.put("consulta", consulta.toString());
		hashMap.put("estado",String.valueOf(filtro.getEstado().getIdEstado()));
		hashMap.put("cidade", String.valueOf(filtro.getCidade().getIdCidade()));
		try {
			List<Empresa> lista = new RepositoryGeneric<Empresa>().getListaHQLConstrutor(hashMap);
			mensagem = (!lista.isEmpty() ? "" : "sem resultado");
			redirecionar = (localSolicitacao.contains("cardapio")) ? "estabelecimento/cardapio/busca-estabelecimento" : "publico/foodrink";
			model.addAttribute("msgAlerta", mensagem);
			model.addAttribute("empresa", lista);
			model.addAttribute("filtro", filtro);
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = "sem resultado";
			model.addAttribute("msgAlerta", mensagem);
			return redirecionar;
		}
		return redirecionar;
	}
}
