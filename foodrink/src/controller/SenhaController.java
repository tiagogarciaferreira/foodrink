package controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import model.Usuario;
import repository.RepositoryGeneric;
import util.Servico;

@Controller
@RequestMapping(value = { "/estabelecimento/perfil"})
public class SenhaController {

	private String mensagem;
	
	@RequestMapping(value = { "/redefinir-senha" }, method = RequestMethod.POST)
	private String atualizarSenha(Model model, HttpServletRequest httpServletRequest) {
		try {
			mensagem = "erro";
			Usuario user = (Usuario) httpServletRequest.getSession().getAttribute("userLogado");
			Usuario usuario = new RepositoryGeneric<Usuario>().getObjetoId(Usuario.class, user.getIdUsuario());
			String novaSenha = httpServletRequest.getParameter("nova_senha");
			usuario.setSenha(Servico.toMd5(novaSenha));
			new RepositoryGeneric<Usuario>().update(usuario);
			mensagem = "atualizoSucesso";
			model.addAttribute("msgAlerta", mensagem);
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = "erro";
			model.addAttribute("msgAlerta", mensagem);
			return "estabelecimento/perfil/redefinir-senha";
		}
		return "estabelecimento/perfil/redefinir-senha";
	}
}
