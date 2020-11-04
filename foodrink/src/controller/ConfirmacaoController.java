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
import constante.EnumEmail;
import constante.EnumSolicitacao;
import contexto.Contexto;
import implementacao.ServicoEmail;
import model.Email;
import model.Url;
import model.Usuario;
import repository.RepositoryGeneric;
import templete.Html;
import util.Local;
import util.Servico;

@Controller
@RequestMapping(value = { "/confirmacao" })
public class ConfirmacaoController {

	private List<String> destinatarios;
	private Usuario usuario;
	private Url url;
	private StringBuilder consulta;
	private String mensagem;
	private HashMap<String, String> hashMap;

	@RequestMapping(value = { "/email-redefinir-senha" }, method = RequestMethod.POST)
	private String verificarEmail(Model model, HttpServletRequest httpServletRequest) {
		mensagem = "erro";
		String emailInformado = httpServletRequest.getParameter("email_redefinicao").trim().toLowerCase();
		consulta = new StringBuilder();
		consulta.append("select u.idUsuario as idUsuario, u.email as email, u.status as status from " + Usuario.class.getName());
		consulta.append(" u where u.email =:valor");
		hashMap = new HashMap<String, String>();
		hashMap.put("tipo_consulta", "HQL");
		hashMap.put("consulta", consulta.toString());
		hashMap.put("valor", emailInformado);
		try {
			usuario = new RepositoryGeneric<Usuario>().getObjetoSQL_HQL(Usuario.class, hashMap);
			if(usuario.getEmail() != null && usuario.getStatus() == true) {
				destinatarios = new ArrayList<String>();
				destinatarios.add(emailInformado);
				new ServicoEmail( new Email(destinatarios, EnumEmail.REDEFINICAO_SENHA.value(),
				Html.novaSenha(Contexto.getDominio(httpServletRequest) + Servico.toCifraUrl(
				new Url(usuario.getIdUsuario().toString(), EnumSolicitacao.SENHA.name(), Local.getDataAtual()))))) .enviar();
				mensagem = "emailSucesso";
				model.addAttribute("msgAlerta", mensagem);
				return "confirmacao/resposta";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msgAlerta", mensagem);
			return "confirmacao/email-redefinir-senha";
		}
		
		model.addAttribute("msgAlerta", mensagem);
		return "confirmacao/email-redefinir-senha";
	}

	@RequestMapping(value = { "/redefinir-senha" }, method = RequestMethod.POST)
	private String atualizarSenha(Model model, HttpServletRequest httpServletRequest) {
		try {
			mensagem = "erro";
			usuario = new RepositoryGeneric<Usuario>().getObjetoId(Usuario.class, Integer.parseInt(httpServletRequest.getParameter("id_usuario")));
			usuario.setSenha(httpServletRequest.getParameter("nova_senha"));
			new RepositoryGeneric<Usuario>().update(usuario);
			mensagem = "sucessoSenha";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msgAlerta", mensagem);
			model.addAttribute("id", httpServletRequest.getParameter("id_usuario"));
			return "confirmacao/nova-senha";
		}
		model.addAttribute("msgAlerta", mensagem);
		return "confirmacao/resposta";
	}

	@RequestMapping(value = { "/solicitacao" }, method = RequestMethod.GET)
	private String verificaSolicitacao(Model model, 
								@RequestParam(value = "id", required = true) String id,
								@RequestParam(value = "tipo", required = true) String tipo,
								@RequestParam(value = "validade", required = true) String validade) {
		
	    url = new Url(id, tipo, validade);
		url = Servico.toDecifraUrl(url);
		boolean dataValida = (Local.getDataAtual().equals(url.getValidade())) ? true : false;
		mensagem = "solicitacaoInvalida";
		if ((dataValida) && (url.getTipo().equals(EnumSolicitacao.CADASTRO.name())) || (!dataValida) && (url.getTipo().equals(EnumSolicitacao.CADASTRO.name()))) {
			usuario = new RepositoryGeneric<Usuario>().getObjetoId(Usuario.class, Integer.parseInt(url.getId()));
			if (usuario.getStatus() == false) {
				try {
					usuario.setStatus(true);
					new RepositoryGeneric<Usuario>().update(usuario);
					mensagem = (url.getTipo().equals(EnumSolicitacao.CADASTRO.name())) ? "sucessoCadastro" : (url.getTipo().equals(EnumSolicitacao.SENHA.name())) ? "sucessoSenha" : "";
				}
				catch (Exception e) {
					e.printStackTrace();
					mensagem = "solicitacaoInvalida";
					return "confirmacao/resposta";
				}
			}
		}
		else if((dataValida) && (url.getTipo().equals(EnumSolicitacao.SENHA.name()))) {
			model.addAttribute("id", url.getId());
			return "confirmacao/nova-senha";
		}
		model.addAttribute("msgAlerta", mensagem);
		return "confirmacao/resposta";
	}

}
