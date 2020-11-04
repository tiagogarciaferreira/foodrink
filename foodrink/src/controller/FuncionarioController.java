package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import constante.EnumEmail;
import constante.EnumSolicitacao;
import contexto.Contexto;
import implementacao.ServicoEmail;
import implementacao.ServicoEmpresa;
import implementacao.ServicoUsuario;
import model.Email;
import model.Empresa;
import model.Funcionario;
import model.Url;
import model.Usuario;
import repository.RepositoryGeneric;
import seguranca.Senha;
import templete.Html;
import util.Servico;
import util.Local;

@Controller
@RequestMapping(value = { "estabelecimento/funcionario"})
public class FuncionarioController {

	private ArrayList<String> destinatarios;
	private HashMap<String, String> hashMap;
	private StringBuilder consulta;
	private String arrayJson;
	private Integer index;
	private String mensagem; 
	
	
	@RequestMapping(value = "/novo-or-update-funcionario", method = RequestMethod.POST)
	private String cadastrar(Model model, Usuario usuario, Funcionario funcionario, HttpServletRequest httpServletRequest) {
		try {
			Usuario user = (Usuario) httpServletRequest.getSession(true).getAttribute("userLogado");
			funcionario.setEmpresa(new Empresa(new ServicoEmpresa().getId(user.getIdUsuario())));
			if(usuario.getIdUsuario() == null) {
				usuario.setStatus(false);
				String senhaTxt = Senha.gerarNova();
				usuario.setSenha(Senha.senhaToMD5(senhaTxt));
				usuario = new RepositoryGeneric<Usuario>().merge(usuario);
				funcionario.setUsuario(new Usuario(usuario.getIdUsuario()));
				new RepositoryGeneric<Funcionario>().save(funcionario);
				mensagem = "cadastroSucesso";
				destinatarios = new ArrayList<String>();
				destinatarios.add(usuario.getEmail());
				new Thread(new ServicoEmail(new Email(destinatarios, EnumEmail.CONFIRMACAO_CADASTRO.value(), Html.novoCadastro(Contexto.getDominio(httpServletRequest) +
				Servico.toCifraUrl(new Url(usuario.getIdUsuario().toString(), EnumSolicitacao.CADASTRO.name(), Local.getDataAtual())))))).start();
				usuario.setSenha(senhaTxt);
				new Thread(new ServicoEmail(new Email(destinatarios, EnumEmail.DADOS_ACESSO.value(), Html.novoFuncionario(usuario)))).start();
				
			}else { 
				usuario.setStatus(usuario.getChaveStatus().equals("ATIVO") ? true : false);
				String senhaAtual = new ServicoUsuario().senhaAtual(usuario.getIdUsuario());
				usuario.setSenha(senhaAtual);
				new RepositoryGeneric<Usuario>().update(usuario);
				funcionario.setUsuario(new Usuario(usuario.getIdUsuario()));
				new RepositoryGeneric<Funcionario>().update(funcionario);
				mensagem = "atualizoSucesso";
			}
			model.addAttribute("msgAlerta", mensagem);
			model.addAttribute("usuario", new Usuario());
			model.addAttribute("funcionario", new Funcionario());
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = "erro";
			model.addAttribute("msgAlerta", mensagem);
			return "estabelecimento/funcionario/cadastro-funcionario";
		}
		return "estabelecimento/funcionario/cadastro-funcionario";
	}
	
	@ResponseBody
	@RequestMapping(value = "/listarFuncionariosEditar", method = RequestMethod.GET)
	private String listarTodos(HttpServletRequest httpServletRequest) {
		arrayJson = "";
		index = 0;
		consulta = new StringBuilder();
		Usuario usuario = (Usuario) httpServletRequest.getSession(true).getAttribute("userLogado");
		Integer empresaID = new ServicoEmpresa().getId(usuario.getIdUsuario());
		consulta.append("select new model.Funcionario(f.idFuncionario, f.usuario.nome, f.usuario.tipoAcesso.nome) from model.Funcionario f ");
		consulta.append("where f.empresa.idEmpresa=:id_empresa");
		hashMap = new HashMap<String, String>();
		hashMap.put("consulta", consulta.toString());
		hashMap.put("id_empresa", String.valueOf(empresaID));
		try {
			List<Funcionario> funcionarios = (List<Funcionario>) new RepositoryGeneric<Funcionario>().getListaHQLConstrutor(hashMap);
			for (Funcionario objeto : funcionarios) {
				arrayJson += "["+"\""+objeto.getIdFuncionario()+"\","+"\""+objeto.getUsuario().getNome()+"\","+"\""+objeto.getUsuario().getTipoAcesso().getNome()+"\""+"]";  
				arrayJson += (index < funcionarios.size() - 1) ? "," : "";
				index++;
			}
			arrayJson = "{"+"\"draw\": 1,"+"\"data\": ["+arrayJson+"]"+"}";
		} catch (Exception e) {
			e.printStackTrace();
			return arrayJson = "{"+"\"draw\": 1,"+"\"data\": ["+arrayJson+"]"+"}";
		}
		return arrayJson;
	}

	@RequestMapping(value = "/editar-funcionario", method = RequestMethod.GET)
	private String getRegistro(Model model, @RequestParam(value = "id", required = true) Integer id) {
		
		try {
			Funcionario funcionario = new RepositoryGeneric<Funcionario>().getObjetoId(Funcionario.class, id);
			Usuario usuario = new RepositoryGeneric<Usuario>().getObjetoId(Usuario.class, funcionario.getUsuario().getIdUsuario());
			model.addAttribute("funcionario", funcionario);
			model.addAttribute("usuario", usuario);
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = "erro";
			model.addAttribute("msgAlerta", mensagem);
			return "estabelecimento/funcionario/cadastro-funcionario";
		}
		return "estabelecimento/funcionario/cadastro-funcionario";
	}
	
	@RequestMapping(value = "/remover-funcionario", method = RequestMethod.GET)
	private String remover(Model model, @RequestParam(value = "id", required = true) Integer id) {
		try {
			Funcionario funcionario = new RepositoryGeneric<Funcionario>().getObjetoId(Funcionario.class, id);
			new RepositoryGeneric<Funcionario>().clearSession();
			new RepositoryGeneric<Funcionario>().delete(new Funcionario(id));
			new RepositoryGeneric<Usuario>().delete(new Usuario(funcionario.getUsuario().getIdUsuario()));
			mensagem = "removidoSucesso";
			model.addAttribute("msgAlerta", mensagem);
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = "erro";
			model.addAttribute("msgAlerta", "erro");
			return "estabelecimento/funcionario/busca-funcionario";
		}
		return "estabelecimento/funcionario/busca-funcionario";
	}
	
}
