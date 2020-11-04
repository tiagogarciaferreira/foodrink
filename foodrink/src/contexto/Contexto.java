package contexto;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class Contexto extends ContextLoaderListener implements Serializable {
	
	/* EVITA INCOMPATIBILIDADE */
	private static final long serialVersionUID = 1L;

	
	/* OBTEM O CONTEXTO DA APLICAÇÃO */
	private static WebApplicationContext getWac() {
		return WebApplicationContextUtils.getWebApplicationContext(getCurrentWebApplicationContext().getServletContext());
	}
	
	/* PERMITE RECUPERAR O CONTEXTO DE ALGUMA PARTE DO SPRING EM QUALQUER LUGAR DA APLICAÇÃO */
	public static Object getBean(String idNomeBean) {
		return getWac().getBean(idNomeBean);
	}
		
	/* RETORNA O CONTEXTO DE UM ARQUIVO APARTIR DE UMA URL  */
	public static String getContexto(String url) {
		return getWac().getServletContext().getRealPath(url);
	}
	
	/* RETORNA O CONTEXTO APARTIR DE UMA URL DE REQUISICAO */
	public static String getDominio(HttpServletRequest httpServletRequest) {
		return httpServletRequest.getRequestURL().toString().replaceAll(httpServletRequest.getServletPath().toString(), "") + "/confirmacao/solicitacao";
	}
	
}
