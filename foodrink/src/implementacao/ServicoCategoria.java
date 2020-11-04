package implementacao;

import org.hibernate.SessionFactory;

import model.Categoria;
import repository.RepositoryGeneric;
import session.HibernateUtil;

public class ServicoCategoria implements Runnable {

	private Integer categoriaID;
	private Integer token;
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	public ServicoCategoria(Integer IDCategoria, Integer token) {
		this.categoriaID = IDCategoria;
		this.token = token;
	}
	
	public void somaQuantidade() {
		try {
			Categoria categoria = new RepositoryGeneric<Categoria>().getObjetoId(Categoria.class, this.categoriaID);
			categoria.setQuantidadeProdutos(categoria.getQuantidadeProdutos() + 1);
			new RepositoryGeneric<Categoria>().update(categoria);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void subtraiQuantidade() {
		try {
			Categoria categoria = new RepositoryGeneric<Categoria>().getObjetoId(Categoria.class, this.categoriaID);
			categoria.setQuantidadeProdutos(categoria.getQuantidadeProdutos() - 1);
			new RepositoryGeneric<Categoria>().update(categoria);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		switch (this.token) {
		case 1:
			sessionFactory.getCurrentSession().beginTransaction();
			somaQuantidade();
			sessionFactory.getCurrentSession().getTransaction().commit();
			sessionFactory.getCurrentSession().close();
			break;
		case -1:
			sessionFactory.getCurrentSession().beginTransaction();
			subtraiQuantidade();
			sessionFactory.getCurrentSession().getTransaction().commit();
			sessionFactory.getCurrentSession().close();
			break;
		}
	}
}
