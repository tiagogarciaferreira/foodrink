package controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import implementacao.ServicoEmpresa;
import model.Cliente;
import model.Empresa;
import model.Mesa;
import model.Usuario;
import repository.RepositoryGeneric;
import services.WebService;
import util.Servico;

@Controller
@RequestMapping(value = { "/confirmacao", "/publico", "/estabelecimento/perfil", "/estabelecimento/funcionario", "/estabelecimento/mesa"})
public class ValidacaoController {

	private Integer numeroOcorrencias;
	private boolean retorno;
	private StringBuilder consulta;
	private HashMap<String, String> hashMap;
	
	@ResponseBody
	@RequestMapping(value = "/validarCpf", method = RequestMethod.GET)
	private String validarCpf(@RequestParam(value = "cpf", required = true) String cpf) {
		try {
			consulta = new StringBuilder();
			consulta.append("select count(c.cpf) from " + Cliente.class.getSimpleName());
			consulta.append(" c where c.cpf=:cpf");
			hashMap = new HashMap<String, String>();
			hashMap.put("consulta", consulta.toString());
			hashMap.put("cpf", cpf.trim());
			numeroOcorrencias = new RepositoryGeneric<Cliente>().getInteger(hashMap);
			retorno = (numeroOcorrencias < 1) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			return Servico.toJson("resposta", "false");
		}
		return Servico.toJson("resposta", String.valueOf(retorno));
	}

	@ResponseBody
	@RequestMapping(value = "/validarEmail", method = RequestMethod.GET)
	private String validarEmail(@RequestParam(value = "email", required = true) String email) {
		try {
			consulta = new StringBuilder();
			consulta.append("select count(u.email) from " + Usuario.class.getSimpleName());
			consulta.append(" u where u.email=:email");
			hashMap = new HashMap<String, String>();
			hashMap.put("consulta", consulta.toString());
			hashMap.put("email", email.trim());
			numeroOcorrencias = new RepositoryGeneric<Usuario>().getInteger(hashMap);
			retorno = (numeroOcorrencias < 1) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			return Servico.toJson("resposta", "false");
		}
		return Servico.toJson("resposta", String.valueOf(retorno));
	}

	@ResponseBody
	@RequestMapping(value = "/validarCnpj", method = RequestMethod.GET)
	private String validarCnpj(@RequestParam(value = "cnpj", required = true) String cnpj) {
		try {
			consulta = new StringBuilder();
			consulta.append("select count(e.cnpj) from " + Empresa.class.getSimpleName());
			consulta.append(" e where e.cnpj=:cnpj");
			hashMap = new HashMap<String, String>();
			hashMap.put("consulta", consulta.toString());
			hashMap.put("cnpj", cnpj.trim());
			retorno = (new WebService().buscaCnpj(cnpj.replaceAll("[^0-9]", "")).isEmpty()) ? true : false;
			if(!retorno) {
				return "{\"resposta\": 0}";
			}
			retorno = (retorno) ? existeCnpj() : false;
		} catch (Exception e) {
			e.printStackTrace();
			return Servico.toJson("resposta", "false");
		}
		return Servico.toJson("resposta", String.valueOf(retorno));
	}

	private boolean existeCnpj() {
		try {
			numeroOcorrencias = new RepositoryGeneric<Empresa>().getInteger(hashMap);
			retorno = (numeroOcorrencias < 1) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return retorno;
	}

	@ResponseBody
	@RequestMapping(value = "/validarCep", method = RequestMethod.GET)
	private String validarCep(@RequestParam(value = "cep", required = true) String cep) {
		retorno = (new WebService().buscaCep(cep.replaceAll("[^0-9]", "")).isEmpty()) ? true : false;
		return Servico.toJson("resposta", String.valueOf(retorno));
	}
	
	@ResponseBody
	@RequestMapping(value = "/validarMesa", method = RequestMethod.GET)
	private String validarMesa(HttpServletRequest httpServletRequest, 
								@RequestParam(value = "numeroMesa", required = true) String numeroMesa, 
								@RequestParam(value = "mesaId", required = true) String mesaId) {
		
		try {
			Usuario usuario = (Usuario) httpServletRequest.getSession(true).getAttribute("userLogado");
			Integer empresaId = new ServicoEmpresa().getId(usuario.getIdUsuario());
			mesaId = (mesaId.isEmpty()) ? "0" : mesaId;
			consulta = new StringBuilder();
			consulta.append("select new model.Mesa(m.idMesa, m.numero) from model.Mesa");
			consulta.append(" m where m.numero=:numero and m.empresa.idEmpresa=:empresa_id");
			hashMap = new HashMap<String, String>();
			hashMap.put("consulta", consulta.toString());
			hashMap.put("empresa_id", String.valueOf(empresaId));
			hashMap.put("numero", numeroMesa);
			Mesa mesa = new RepositoryGeneric<Mesa>().getObjetoHQLConstrutor(hashMap);
			retorno = (mesa == null || String.valueOf(mesa.getIdMesa()).equals(mesaId)) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			return Servico.toJson("resposta", "false");
		}
		return Servico.toJson("resposta", String.valueOf(retorno));
	}

}
