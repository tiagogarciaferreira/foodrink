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
import constante.EnumEmail;
import constante.EnumSolicitacao;
import constante.EnumStatus;
import contexto.Contexto;
import implementacao.ServicoEmail;
import implementacao.ServicoEmpresa;
import implementacao.ServicoMesa;
import implementacao.ServicoUsuario;
import model.Email;
import model.Empresa;
import model.Endereco;
import model.Mesa;
import model.Pedido;
import model.Reserva;
import model.Semana;
import model.TipoAcesso;
import model.Url;
import model.Usuario;
import repository.RepositoryGeneric;
import seguranca.Senha;
import templete.Html;
import util.Servico;
import util.Local;

@Controller
@RequestMapping(value = { "/publico", "/estabelecimento", "/estabelecimento/perfil" })
public class EmpresaController {

	private ArrayList<String> destinatarios;
	private StringBuilder consulta;
	private HashMap<String, String> hashMap;
	private String mensagem;

	@RequestMapping(value = "/nova-empresa", method = RequestMethod.POST)
	private String cadastrar(Model model, Usuario usuario, Endereco endereco, Empresa empresa,
			HttpServletRequest httpServletRequest) {

		try {
			mensagem = "erro";
			usuario.setTipoAcesso(new TipoAcesso(2));/* CODIGO 2 IGUAL A EMPRESA */
			usuario.setStatus(false);
			usuario.setEmail(usuario.getEmail().toLowerCase());
			usuario = new RepositoryGeneric<Usuario>().merge(usuario);
			empresa.setUsuario(new Usuario(usuario.getIdUsuario()));
			empresa.setFuncionamento("");
			new RepositoryGeneric<Empresa>().save(empresa);
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
			model.addAttribute("msgAlerta", mensagem);
			return "publico/cadastro-empresa";
		}
		return "redirect:../confirmacao/info.jsp";
	}

	@RequestMapping(value = "/atualizar-empresa", method = RequestMethod.POST)
	private String atualizar(Model model, Usuario usuario, Endereco endereco, Empresa empresa) {

		try {
			usuario.setTipoAcesso(new TipoAcesso(2));/* CODIGO 2 IGUAL A EMPRESA */
			usuario.setStatus(true);
			usuario.setEmail(usuario.getEmail().toLowerCase());
			String senhaAtual = new ServicoUsuario().senhaAtual(usuario.getIdUsuario());
			boolean trocarSenha = (senhaAtual.equals(usuario.getSenha()));
			usuario.setSenha((!trocarSenha) ? Senha.senhaToMD5(usuario.getSenha()) : usuario.getSenha());
			new RepositoryGeneric<Usuario>().update(usuario);
			empresa.setUsuario(new Usuario(usuario.getIdUsuario()));
			empresa.setFuncionamento(new ServicoEmpresa().funcionamento(empresa.getIdEmpresa()));
			new RepositoryGeneric<Empresa>().update(empresa);
			endereco.setUsuario(new Usuario(usuario.getIdUsuario()));
			new RepositoryGeneric<Endereco>().update(endereco);
			mensagem = "atualizoSucesso";
			model.addAttribute("msgAlerta", mensagem);

		} catch (Exception e) {
			e.printStackTrace();
			mensagem = "erro";
			model.addAttribute("msgAlerta", mensagem);
			return "estabelecimento/perfil/perfil-empresa";
		}
		return "estabelecimento/perfil/perfil-empresa";
	}

	@RequestMapping(value = { "/perfil-empresa" }, method = RequestMethod.GET)
	public String carregarPerfil(Model model, HttpServletRequest httpServletRequest) {
		try {
			Usuario usuario = (Usuario) httpServletRequest.getSession().getAttribute("userLogado");
			usuario = new RepositoryGeneric<Usuario>().getObjetoId(Usuario.class, usuario.getIdUsuario());
			Endereco endereco = usuario.getEndereco();
			Empresa empresa = usuario.getEmpresa();
			model.addAttribute("usuario", usuario);
			model.addAttribute("empresa", empresa);
			model.addAttribute("endereco", endereco);
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = "erro";
			model.addAttribute("msgAlerta", mensagem);
			return "estabelecimento/perfil/perfil-empresa";
		}
		return "estabelecimento/perfil/perfil-empresa";
	}
	
	
	@RequestMapping(value = { "/save-ou-update-horario" }, method = RequestMethod.POST)
	public String setHorarioAtendimento(Model model, Semana semana, HttpServletRequest httpServletRequest) {
		Usuario usuario = (Usuario) httpServletRequest.getSession(true).getAttribute("userLogado");
		Integer empresaID = new ServicoEmpresa().getId(usuario.getIdUsuario());
		try {
			Empresa empresa = new RepositoryGeneric<Empresa>().getObjetoId(Empresa.class, empresaID);
			empresa.setFuncionamento(semana.toString());
			new RepositoryGeneric<Empresa>().update(empresa);
			mensagem = "atualizoSucesso";
			model.addAttribute("msgAlerta", mensagem);
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = "erro";
			model.addAttribute("msgAlerta", mensagem);
			return "estabelecimento/estabelecimento";
		}
		return "estabelecimento/estabelecimento";
	}

	@ResponseBody
	@RequestMapping(value = { "/getEstatisticasEmpresa" }, method = RequestMethod.GET)
	public String carregarEstatisticas(Model model, HttpServletRequest httpServletRequest) throws Exception {

		Usuario usuario = (Usuario) httpServletRequest.getSession(true).getAttribute("userLogado");
		Integer empresaID = new ServicoEmpresa().getId(usuario.getIdUsuario());

		/* NUMERO DE PEDIDOS EM ANDAMENTO IGUAL A MESAS COM PEDIDOS*/
		consulta = new StringBuilder();
		consulta.append("select count(p.status) from " + Pedido.class.getSimpleName());
		consulta.append(" p where p.status=:status and p.empresa_id=:id_empresa");
		hashMap = new HashMap<String, String>();
		hashMap.put("consulta", consulta.toString());
		hashMap.put("status", EnumStatus.EM_ANDAMENTO.value());
		hashMap.put("id_empresa", String.valueOf(empresaID));
		Integer pedidosAtivos = new RepositoryGeneric<Pedido>().getInteger(hashMap);

		/* NUMERO DE MESAS RESERVADAS E PENDENTES JUNTO */
		hashMap = new HashMap<String, String>();
		consulta = new StringBuilder();
		consulta.append("select count(r.id_reserva) from " + Reserva.class.getSimpleName());
		consulta.append(" r where r.empresa_id=:id_empresa and r.status in (:status_A, :status_B) and r.data=:data_hoje");
		hashMap.put("consulta", consulta.toString());
		hashMap.put("status_A", EnumStatus.RESERVADA.value());
		hashMap.put("status_B", EnumStatus.PENDENTE.value());
		hashMap.put("data_hoje", Local.getDataAtual());
		hashMap.put("id_empresa", String.valueOf(empresaID));
		Integer reservasDoDia = new RepositoryGeneric<Reserva>().getInteger(hashMap);
		
		/* NUMERO DAS MESAS DISPONIVEIS */
		List<Mesa> lista = new ServicoMesa().getMesasDisponiveis(empresaID, Local.getDataAtual(), "");
		Integer mesasDisponiveis = lista.size();
		String numeroMesas = "";
		for(Mesa mesa:lista) {
			numeroMesas = numeroMesas  + mesa.getNumero() + " - ";
		}
		numeroMesas = (numeroMesas.length() > 0) ? numeroMesas.substring(0, numeroMesas.length() - 3) : numeroMesas;
		
		/* HORARIO DE FUNCIONAMENTO */
		hashMap = new HashMap<String, String>();
		consulta = new StringBuilder();
		consulta.append("select e.funcionamento as funcionamento from " + Empresa.class.getSimpleName());
		consulta.append(" e where e.id_empresa=:id_empresa");
		hashMap.put("tipo_consulta", "SQL");
		hashMap.put("consulta", consulta.toString());
		hashMap.put("id_empresa", String.valueOf(empresaID));
		Empresa empresa = new RepositoryGeneric<Empresa>().getObjetoSQL_HQL(Empresa.class, hashMap);
		String funcionamento = empresa.getFuncionamento();
		return String.valueOf(pedidosAtivos + "#" + reservasDoDia + "#" + mesasDisponiveis+"#"+funcionamento+"#"+numeroMesas);
	}

}
