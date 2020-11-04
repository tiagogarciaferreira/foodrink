package session;

import java.io.Serializable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil implements Serializable {

	/* EVITA INCOMPATIBILIDADE */
	private static final long serialVersionUID = 1L;

	/* STATIC PORQUE SERA APENAS UMA PARA TODO O SISTEMA */
	private static SessionFactory sessionFactory = buildSessionFactory();
	/* LE O ARQUIVO DE CONFIGURAÇÃO DO HIBERNATE HIBERNATE.CFG.XML */
	private static SessionFactory buildSessionFactory() {
		try {
			if (sessionFactory == null) {
				Configuration configuration = new Configuration();
				configuration.configure("/hibernate.cfg.xml");
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			}
			return sessionFactory;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError("Erro ao criar a SESSION FACTORY!");
		}
	}

	/* RETORNA A SESSION FACTORY CRIADA */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/* RETORNA A SESSION FACTORY DA SESSION */
	public static Session getCurrentSession() {
		return getSessionFactory().getCurrentSession();
	}

	/* ABRE UMA NOVA SESSION FACTORY */
	public static Session openSession() {
		if (sessionFactory == null) {
			buildSessionFactory();
		}
		return sessionFactory.openSession();
	}
	
}
