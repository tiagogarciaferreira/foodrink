package repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import interfac.InterfaceGeneric;
import session.HibernateUtil;
import util.Servico;

public class RepositoryGeneric<T> implements InterfaceGeneric<T>, Serializable {

	/* EVITA INCOMPATIBILIDADE */
	private static final long serialVersionUID = 1L;

	/* PEGANDO OU CRIANDO UMA SESSAO */
	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	/* SALVA UM REGISTRO NO BANCO */
	@Override
	public void save(T objeto) throws Exception {
		sessionFactory.getCurrentSession().save(objeto);
		executeFlushSession();
	}

	/* SALVA UM REGISTRO NO BANCO */
	@Override
	public void persist(T objeto) throws Exception {
		sessionFactory.getCurrentSession().persist(objeto);
		executeFlushSession();
	}

	/* SALVA OU ATUALIZA UM REGISTRO */
	@Override
	public void saveOrUpdate(T objeto) throws Exception {
		sessionFactory.getCurrentSession().saveOrUpdate(objeto);
		executeFlushSession();
	}

	/* ATUALIZA UM REGISTRO */
	@Override
	public void update(T objeto) throws Exception {
		sessionFactory.getCurrentSession().update(objeto);
		executeFlushSession();
	}

	/* DELETA UM REGISTRO */
	@Override
	public void delete(T objeto) throws Exception {
		sessionFactory.getCurrentSession().delete(objeto);
		executeFlushSession();
	}
	
	/* DELETA UM REGISTRO COM SQL */
	@Override
	public void deletar(String sql) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(sql);
		query.executeUpdate();
		executeFlushSession();
	}

	/* SALVA UM REGISTRO NO BANCO E RETORNA */
	@Override
	public T merge(T objeto) throws Exception {
		objeto = (T) sessionFactory.getCurrentSession().merge(objeto);
		executeFlushSession();
		return objeto;
	}

	/* RETORNA UMA OBJETO */
	@Override
	public T getObjetoId(Class<T> entidade, Integer id) {
		return (T) sessionFactory.getCurrentSession().get(entidade.getName(), id);
	}

	/* RETORNA UM OBJETO APARTIR DE SQL OU HQL COM TODOS OU ALGUNS CAMPOS */
	@Override
	public T getObjetoSQL_HQL(Class<T> entidade, HashMap<String, String> filtro) throws Exception{
		Session session = sessionFactory.getCurrentSession();
		Query query = (filtro.get("tipo_consulta").equals("HQL")) ? session.createQuery(filtro.get("consulta")) : session.createSQLQuery(filtro.get("consulta"));
		filtro.remove("tipo_consulta");
		filtro.remove("consulta"); 
		T objeto = (T) entidade.newInstance();
		query = novaQuery(query, filtro);
		query.setResultTransformer(Transformers.aliasToBean(entidade));
		objeto = (query.uniqueResult() != null) ? (T) query.uniqueResult() : objeto;
		return objeto;
	}

	/* RETORNA UM OBJETO COMPLETO COM HQL */
	@Override
	public T getObjetoHQL(HashMap<String, String> filtro) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(filtro.get("consulta"));
		filtro.remove("consulta"); 
		query = novaQuery(query, filtro);
		T objeto = (T) query.uniqueResult();
		return objeto;
	}
	
	/* RETORNA UMA LISTA DE OBJETOS COM HQL UTILIZANDO CONSTRUTOR */
	@Override
	public List<T> getListaHQLConstrutor(HashMap<String, String> filtro) throws Exception { 
		  List<T> lista = new ArrayList<T>();
		  Session session = sessionFactory.getCurrentSession(); 
		  Query query = session.createQuery(filtro.get("consulta"));
		  filtro.remove("consulta");
		  query = novaQuery(query, filtro);
		  lista = (List<T>) query.list();
		  return lista;  
	  } 
	
	/* RETORNA UM OBJETO COM HQL UTILIZANDO CONSTRUTOR */
	@Override
	public T getObjetoHQLConstrutor(HashMap<String, String> filtro) throws Exception { 
		  Session session = sessionFactory.getCurrentSession(); 
		  Query query = session.createQuery(filtro.get("consulta"));
		  filtro.remove("consulta");
		  query = novaQuery(query, filtro);
		  T objeto = (T) query.uniqueResult();
		  return objeto;  
	} 

	/* RETORNA UMA LISTA DE OBJETOS COM TODOS OU ALGUNS CAMPOS COM SQL OU HQL*/
	@Override
	public List<T> getListaSQL_HQL(Class<T> entidade, HashMap<String, String> filtro) throws Exception { 
		  List<T> lista = new ArrayList<T>();
		  Session session = sessionFactory.getCurrentSession(); 
		  Query query = (filtro.get("tipo_consulta").equals("HQL")) ? session.createQuery(filtro.get("consulta")) : session.createSQLQuery(filtro.get("consulta"));
		  filtro.remove("tipo_consulta");
		  filtro.remove("consulta");
		  query = novaQuery(query, filtro);
		  lista = (List<T>) query.setResultTransformer(Transformers.aliasToBean(entidade)).list();
		  return lista;  
	  } 

	/* RETORNA UMA LISTA DE TODOS OS OBJETOS COM TODOS OS CAMPOS COM HQL*/
	@Override
	public List<T> getListaTodosHQL(HashMap<String, String> filtro) throws Exception {
		List<T> lista = new ArrayList<T>();
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(filtro.get("consulta"));
		filtro.remove("consulta");
		query = novaQuery(query, filtro);
		lista = (List<T>) query.list();
		return lista;
	}

	/* RETORNA UM INTEGER */
	@Override
	public Integer getInteger(HashMap<String, String> filtro) throws Exception{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(filtro.get("consulta"));
		filtro.remove("consulta");
		query = novaQuery(query, filtro);
		return Integer.parseInt(String.valueOf(query.uniqueResult()));
	}
	
	/* MONTA UMA QUERY PARA CONSULTA */
	private Query novaQuery(Query query, HashMap<String, String> filtro) {
		if(filtro != null && !filtro.isEmpty()) {
			for (Entry<String, String> parametro : filtro.entrySet()) {
				if (!Servico.isInteger(parametro.getValue())) {
					query.setParameter(parametro.getKey(), parametro.getValue());
				} else {
					query.setParameter(parametro.getKey(), Integer.parseInt(parametro.getValue()));
				}
			}
		}
		return query;
	}

	/* EXECUTAR O SQL OU HQL */
	private void executeFlushSession() {
		sessionFactory.getCurrentSession().flush();
	}

	/* LIMPAR SESSÃO DO HIBERNATE  */
	@Override
	public void clearSession() throws Exception {
		sessionFactory.getCurrentSession().clear();
	}
}
