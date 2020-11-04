package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;
import constante.EnumEmail;
import constante.EnumSolicitacao;
import constante.EnumStatus;
import contexto.Contexto;
import implementacao.ServicoCliente;
import implementacao.ServicoEmail;
import implementacao.ServicoReserva;
import implementacao.ServicoUsuario;
import model.Cliente;
import model.Email;
import model.Endereco;
import model.ItemPedido;
import model.Pedido;
import model.Produto;
import model.Reserva;
import model.TipoAcesso;
import model.Url;
import model.Usuario;
import repository.RepositoryGeneric;
import seguranca.Senha;
import templete.Html;
import util.Servico;
import util.Local;

@Controller
@RequestMapping(value = { "/publico", "/estabelecimento/reserva", "/estabelecimento/pedido", "/estabelecimento" , "/estabelecimento/perfil" })
public class ClienteController {

	private ArrayList<String> destinatarios;
	private HashMap<String, String> hashMap;
	private StringBuilder consulta;
	private String arrayJson;
	private String objetoJson;
	private Gson gson = new Gson();
	private Integer index;
	private String mensagem;

	@RequestMapping(value = "/novo-cliente", method = RequestMethod.POST)
	private String cadastrar(Model model, Usuario usuario, Endereco endereco, Cliente cliente,
			HttpServletRequest httpServletRequest) {

		try {
			usuario.setTipoAcesso(new TipoAcesso(1));/* CODIGO 1 IGUAL A CLIENTE */
			usuario.setStatus(false);
			usuario.setEmail(usuario.getEmail().toLowerCase());
			usuario = new RepositoryGeneric<Usuario>().merge(usuario);
			cliente.setUsuario(new Usuario(usuario.getIdUsuario()));
			new RepositoryGeneric<Cliente>().save(cliente);
			endereco.setUsuario(new Usuario(usuario.getIdUsuario()));
			new RepositoryGeneric<Endereco>().save(endereco);
			destinatarios = new ArrayList<String>();
			destinatarios.add(usuario.getEmail());
			new Thread(new ServicoEmail(new Email(destinatarios, EnumEmail.CONFIRMACAO_CADASTRO.value(),
					Html.novoCadastro(Contexto.getDominio(httpServletRequest)
							+ Servico.toCifraUrl(new Url(usuario.getIdUsuario().toString(),
									EnumSolicitacao.CADASTRO.name(), Local.getDataAtual())))))).start();

		} catch (Exception e) {
			e.printStackTrace();
			mensagem = "erro";
			model.addAttribute("msgAlerta", mensagem);
			return "publico/cadastro-cliente";
		}
		return "redirect:../confirmacao/info.jsp";
	}

	@RequestMapping(value = "/atualizar-cliente", method = RequestMethod.POST)
	private String atualizar(Model model, Usuario usuario, Endereco endereco, Cliente cliente) {

		try {
			usuario.setTipoAcesso(new TipoAcesso(1));/* CODIGO 1 IGUAL A CLIENTE */
			usuario.setStatus(true);
			usuario.setEmail(usuario.getEmail());
			String senhaAtual = new ServicoUsuario().senhaAtual(usuario.getIdUsuario());
			boolean trocarSenha = (senhaAtual.equals(usuario.getSenha()));
			usuario.setSenha((!trocarSenha) ? Senha.senhaToMD5(usuario.getSenha()) : usuario.getSenha());
			new RepositoryGeneric<Usuario>().update(usuario);
			cliente.setUsuario(new Usuario(usuario.getIdUsuario()));
			new RepositoryGeneric<Cliente>().update(cliente);
			endereco.setUsuario(new Usuario(usuario.getIdUsuario()));
			new RepositoryGeneric<Endereco>().update(endereco);
			mensagem = "atualizoSucesso";
			model.addAttribute("msgAlerta", mensagem);

		} catch (Exception e) {
			e.printStackTrace();
			mensagem = "erro";
			model.addAttribute("msgAlerta", mensagem);
			return "estabelecimento/perfil/perfil-cliente";
		}
		return "estabelecimento/perfil/perfil-cliente";
	}
	
	@RequestMapping(value = { "/perfil-cliente" }, method = RequestMethod.GET)
	public String carregarPerfil(Model model, HttpServletRequest httpServletRequest) {
		try {
			Usuario usuario = (Usuario) httpServletRequest.getSession().getAttribute("userLogado");
			usuario = new RepositoryGeneric<Usuario>().getObjetoId(Usuario.class, usuario.getIdUsuario());
			Cliente cliente = usuario.getCliente();
			Endereco endereco = usuario.getEndereco();
			model.addAttribute("usuario", usuario);
			model.addAttribute("cliente", cliente);
			model.addAttribute("endereco", endereco);
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = "erro";
			model.addAttribute("msgAlerta", mensagem);
			return "estabelecimento/perfil/perfil-cliente";
		}
		return "estabelecimento/perfil/perfil-cliente";
	}

	@ResponseBody
	@RequestMapping(value = "/listarClientes", method = RequestMethod.GET)
	private String listarClientes() throws Exception {
		arrayJson = "";
		index = 0;
		consulta = new StringBuilder();
		consulta.append("select new model.Cliente(c.idCliente, c.usuario.nome) from model.Cliente c");
		hashMap = new HashMap<String, String>();
		hashMap.put("consulta", consulta.toString());
		try {
			List<Cliente> clientes = (List<Cliente>) new RepositoryGeneric<Cliente>().getListaHQLConstrutor(hashMap);
			for (Cliente objeto : clientes) {
				arrayJson += "[" + "\"" + objeto.getIdCliente() + "\"," + "\"" + objeto.getUsuario().getNome() + "\""+ "]";
				arrayJson += (index < clientes.size() - 1) ? "," : "";
				index++;
			}
			arrayJson = "{" + "\"draw\": 1," + "\"data\": [" + arrayJson + "]" + "}";
		} catch (Exception e) {
			e.printStackTrace();
			return arrayJson = "{" + "\"draw\": 1," + "\"data\": [" + arrayJson + "]" + "}";
		}
		return arrayJson;
	}

	@ResponseBody
	@RequestMapping(value = { "/getEstatisticasCliente" }, method = RequestMethod.GET)
	public String carregarEstatisticas(HttpServletRequest httpServletRequest) throws Exception {
		new Thread(new ServicoReserva(0,null)).start();
		arrayJson = "";index = 0;
		Usuario usuario = (Usuario) httpServletRequest.getSession().getAttribute("userLogado");
		Integer clienteID = new ServicoCliente().getId(usuario.getIdUsuario());
		String jsonPedido = "";
		String jsonCompleto = "";
		consulta = new StringBuilder();
		consulta.append("from " + Pedido.class.getSimpleName());
		consulta.append(" p where cliente_id=:id_cliente and status=:status");
		hashMap = new HashMap<String, String>();
		hashMap.put("consulta", consulta.toString());
		hashMap.put("id_cliente", String.valueOf(clienteID));
		hashMap.put("status", EnumStatus.EM_ANDAMENTO.value());
		Pedido pedido = new RepositoryGeneric<Pedido>().getObjetoHQL(hashMap);
		List<ItemPedido> itens = new ArrayList<ItemPedido>();
		if (pedido != null) {
			itens = pedido.getListaItens();
			jsonPedido = gson.toJson(new Pedido(pedido.getIdPedido(), pedido.getValorPedido(), pedido.getEmpresa().getUsuario().getNome(), pedido.getHora(), pedido.getMesa().getNumero()));
		}
		try {
			for (ItemPedido item : itens) {
				objetoJson = gson.toJson(new ItemPedido(item.getQuantidade(), new Produto(item.getProduto().getNome(), item.getProduto().getTamanho(), item.getProduto().getPreco())));
				objetoJson += (index < itens.size() - 1) ? "," : "";
				index++;
				arrayJson += objetoJson;
			}
			arrayJson = "[" + arrayJson + "]";
			jsonCompleto = arrayJson + "#" + jsonPedido;

			objetoJson = "";index = 0;arrayJson = "";
			List<Reserva> listaReservas = new ServicoReserva().getReservas(clienteID, false);
			for (Reserva reserva : listaReservas) {
				objetoJson = gson.toJson(reserva);
				objetoJson += (index < listaReservas.size() - 1) ? "," : "";
				index++;
				arrayJson += objetoJson;
			}
			arrayJson = "[" + arrayJson + "]";
			jsonCompleto = jsonCompleto + "#" + arrayJson;

		} catch (Exception e) {
			e.printStackTrace();
			return jsonCompleto;
		}
		return jsonCompleto;
	}
}
