package filter;

import java.io.IOException;
import java.io.Serializable;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.filter.DelegatingFilterProxy;
import contexto.Contexto;
import session.HibernateUtil;

@WebFilter(filterName = "filtroConexao")
public class FilterOpenSessionInView extends DelegatingFilterProxy implements Serializable {

	private static final long serialVersionUID = 1L;

	private static SessionFactory sessionFactory;

	/* QUANDO A APLICAÇÃO É INICIADA DEIXA DISPONIVEL A CONEXAO DO HIBERNATE */
	@Override
	protected void initFilterBean() throws ServletException {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	/* FILTRO DA APLICAÇÃO */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		BasicDataSource springDataSource = (BasicDataSource) Contexto.getBean("springDataSource");
		DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
		PlatformTransactionManager platformTransactionManager = new DataSourceTransactionManager(springDataSource);
		TransactionStatus transactionStatus = platformTransactionManager.getTransaction(defaultTransactionDefinition);

		try {
			request.setCharacterEncoding("ISO-8859-1");
			sessionFactory.getCurrentSession().beginTransaction();
			filterChain.doFilter(request, response);
			platformTransactionManager.commit(transactionStatus);

			/* SE A TRANSAÇÃO ATIVA */
			if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
				/* EXECUTA O SQL */
				sessionFactory.getCurrentSession().flush();
				/* COMMIT A OPERAÇÃO */
				sessionFactory.getCurrentSession().getTransaction().commit();
			}
			/* A SESSAO AINDA ESTA ABERTA */
			if (sessionFactory.getCurrentSession().isOpen()) {
				/* FECHA A SESSAO */
				sessionFactory.getCurrentSession().close();
			}
			response.setCharacterEncoding("ISO-8859-1");
			response.setContentType("text/html; charset=ISO-8859-1");

		} catch (Exception e) {
			/* EXECUTA UM ROLLBACK NO SPRING_DATA_SOURCE */
			platformTransactionManager.rollback(transactionStatus);
			e.printStackTrace();
			/* SE A TRANSAÇÃO ATIVA */
			if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
				/* EXECUTA UM ROLLBACK */
				sessionFactory.getCurrentSession().getTransaction().rollback();
			}
			/* SE SESSAO ABERTA */
			if (sessionFactory.getCurrentSession().isOpen()) {
				/* FECHA A SESSAO */
				sessionFactory.getCurrentSession().close();
			}

		}
		/* INDEPENDENTE DE DAR CERTO OU NÃO O BLOCO DE CODIGO É EXECUTADO IGUAL */
		finally {
			/* SE SESSAO ABERTA */
			if (sessionFactory.getCurrentSession().isOpen()) {
				/* SE A TRANSAÇÃO ATIVA */
				if (sessionFactory.getCurrentSession().beginTransaction().isActive()) {
					/* EXECUTA O SQL */
					sessionFactory.getCurrentSession().flush();
					/* LIMPAR A SESSAO */
					sessionFactory.getCurrentSession().clear();
				}
				/* SE SESSAO ABERTA */
				if (sessionFactory.getCurrentSession().isOpen()) {
					/* FECHA A SESSAO */
					sessionFactory.getCurrentSession().close();
				}
			}
		}
	}

}
