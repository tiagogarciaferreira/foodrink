package controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import implementacao.ServicoReserva;
import session.UserLogado;

@Controller
@RequestMapping(value = { "/estabelecimento" })
public class HomeController {
	
	@RequestMapping(value = { "/load-perfil" }, method = RequestMethod.GET)
	public String loadSession(Model model, HttpServletRequest httpServletRequest) throws Exception {
		new Thread(new ServicoReserva(0,null)).start();
		httpServletRequest = new UserLogado().adicionar(httpServletRequest);
		return "estabelecimento/estabelecimento";
	}
	
	@RequestMapping(value = { "/sair" }, method = RequestMethod.GET)
	public String sair(Model model, HttpServletRequest httpServletRequest) throws Exception {
		httpServletRequest = new UserLogado().remover(httpServletRequest);
		return "redirect:/publico/login.jsp?acesso=negado";
	}
}
