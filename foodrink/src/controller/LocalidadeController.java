package controller;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;
import model.Cidade;
import model.Estado;
import repository.RepositoryGeneric;

@Controller
@RequestMapping(value = { "/publico","estabelecimento/perfil", "estabelecimento/cardapio" })
public class LocalidadeController {

	private String arrayJson;
	private String objetoJson;
	private Integer index;
	private Gson gson = new Gson();
	private StringBuilder consulta;
	private HashMap<String, String> hashMap;

	@ResponseBody
	@RequestMapping(value = "/listarPaises", method = RequestMethod.GET)
	private String listarPaises() {try {} catch (Exception e) {} return "";}

	@ResponseBody
	@RequestMapping(value = "/listarEstados", method = RequestMethod.GET)
	private String listarEstados() throws Exception {
		arrayJson = "";
		index = 0;
		consulta = new StringBuilder();
		consulta.append("select e.idEstado as idEstado, e.nome as nome from " + Estado.class.getName() + " e");
		hashMap = new HashMap<String, String>();
		hashMap.put("tipo_consulta", "HQL");
		hashMap.put("consulta", consulta.toString());
		try {
			List<Estado> estados = (List<Estado>) new RepositoryGeneric<Estado>().getListaSQL_HQL(Estado.class, hashMap);
			for (Estado objeto : estados) {
				objetoJson = gson.toJson(new Estado(objeto.getIdEstado(), objeto.getNome()));
				objetoJson += (index < estados.size() - 1) ? "," : "";
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
	@RequestMapping(value = "/listarCidades", method = RequestMethod.GET)
	private String listarCidades(@RequestParam(value = "estado", required = true) Integer estado) {
		arrayJson = "";
		index = 0;
		consulta = new StringBuilder();
		consulta.append("select c.idCidade as idCidade, c.nome as nome from " + Cidade.class.getName()); 
		consulta.append(" c where estado_id =:valor");
		hashMap = new HashMap<String, String>();
		hashMap.put("tipo_consulta", "HQL");
		hashMap.put("consulta", consulta.toString());
		hashMap.put("valor",String.valueOf(estado));
		try {
			List<Cidade> cidades = (List<Cidade>) new RepositoryGeneric<Cidade>().getListaSQL_HQL(Cidade.class, hashMap);
			for (Cidade objeto : cidades) {
				objetoJson = gson.toJson(new Cidade(objeto.getIdCidade(), objeto.getNome()));
				objetoJson += (index < cidades.size() - 1) ? "," : "";
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

}
