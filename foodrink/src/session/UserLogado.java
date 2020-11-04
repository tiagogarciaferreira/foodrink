package session;

import java.io.Serializable;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import model.Usuario;
import repository.RepositoryGeneric;

public class UserLogado implements Serializable {

	private static final long serialVersionUID = 1L;

	private StringBuilder consulta;
	private HashMap<String, String> hashMap;

	/* RETORNA AS PERMISSOES DO USUARIO LOGADO */
	public static String getPermissaoUserLogado() {
		Authentication authentication = getAuthentication();
		String permissoes = authentication.getAuthorities().toString();
		return permissoes;
	}

	/* RETORNA O ATRIBUTO USUARIO DO USER LOGADO EX: garcia007 */
	public static String getUserLogado() {
		Object principal = getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername().toString();
		} else {
			return principal.toString();
		}
	}

	/* RETORNA O OBJETO AUTHENTICATION DO SPRING SECURITY */
	private static Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	/* ADICIONA UM OBJETO USUARIO AUTENTICADO A UMA SESSAO HTTP */
	public HttpServletRequest adicionar(HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession(true);
		Usuario usuario = (Usuario) httpSession.getAttribute("userLogado");
		String login = getUserLogado();
		try {
			if (usuario == null) {
				consulta = new StringBuilder();
				consulta.append("select new model.Usuario(u.idUsuario, u.imagem, u.nome, u.email, u.tipoAcesso.nome) from model.Usuario u where u.email=:valor");
				hashMap = new HashMap<String, String>();
				hashMap.put("consulta", consulta.toString());
				hashMap.put("valor", login);
				usuario = new RepositoryGeneric<Usuario>().getObjetoHQLConstrutor(hashMap);
				httpSession.setAttribute("userLogado", usuario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return httpServletRequest;
	}

	/* REMOVE UM OBJETO USUARIO AUTENTICADO DE UMA SESSAO HTTP */
	public HttpServletRequest remover(HttpServletRequest httpServletRequest) {
		httpServletRequest.getSession(true).invalidate();
		return httpServletRequest;
	}

}
