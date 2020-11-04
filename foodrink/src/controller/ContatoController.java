package controller;

import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import constante.EnumEmail;
import implementacao.ServicoEmail;
import model.Contato;
import model.Email;
import templete.Html;

@Controller
@RequestMapping(value = { "/publico" })
public class ContatoController {

	private ArrayList<String> destinatarios;
	private String mensagem;

	@RequestMapping(value = { "/enviar-contato" }, method = RequestMethod.POST)
	public String novo(Model model, Contato contato) {
		try {
			mensagem = "erro";
			destinatarios = new ArrayList<String>();
			destinatarios.add(EnumEmail.CONTATO_DESTINO.value());
			new Thread(new ServicoEmail(new Email(destinatarios, EnumEmail.CONTATO.value(), Html.novoContato(contato)))).start();
			mensagem = "contatoSucesso";
			model.addAttribute("msgAlerta", mensagem);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msgAlerta", mensagem);
			return "publico/contato";
		}
		return "confirmacao/resposta";
	}
}
