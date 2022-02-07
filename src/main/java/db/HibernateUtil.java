package db;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.Log;
import dao.Message;
import dao.User;

public class HibernateUtil {
	/*private static EntityManagerFactory factory;
	
	public static Session getSession() {
		if(factory == null) {
			factory = Persistence.createEntityManagerFactory("springmvc");
		}
		
		EntityManager entityManager = factory.createEntityManager();
		Session session = entityManager.unwrap(Session.class);
		
		SessionFactory factory = session.getSessionFactory();
		
		return session;
	}
	
	public static Session getCurrentSession() {
		Map<String, String> settings = new HashMap<>();
		settings.put("connection.driver_class", "com.mysql.jdbc.Driver");
		settings.put("dialect", "org.hibernate.dialect.MySQL8Dialect");
		settings.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/springmvc");
		settings.put("hibernate.connection.username", "root");
		settings.put("hibernate.connection.password", "1234");
		settings.put("hibernate.current_session_context_class", "thread");
		settings.put("hibernate.show_sql", "true");
		settings.put("hibernate.format_sql", "true");
		
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
				applySettings(settings).build();
		
		MetadataSources metadataSources = new MetadataSources(serviceRegistry);
		metadataSources.addAnnotatedClass(User.class);
		metadataSources.addAnnotatedClass(Log.class);
		metadataSources.addAnnotatedClass(Message.class);
		
		Metadata metadata = metadataSources.buildMetadata();
		SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
		Session session = sessionFactory.getCurrentSession();
		return session;
	}*/
	
	public static Session beginTransaction() {
		Session hibernateSession = getHibernateSession();
		hibernateSession.beginTransaction();
		return hibernateSession;
	}

	public static void commitTransaction(Session s) {
		s.getTransaction().commit();
	}

	public static void rollbackTransaction(Session s) {
		s.getTransaction().rollback();
	}

	public static void closeSession(Session s) {
		s.close();
	}
	
	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;
	
	public static Session getHibernateSession() {
		if(sessionFactory == null) {
			try {
				StandardServiceRegistryBuilder registryBuilder =
						new StandardServiceRegistryBuilder();
				
				Map<String, String> settings = new HashMap<>();
				settings.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
				settings.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/springmvc");
				settings.put("hibernate.connection.username", "root");
				settings.put("hibernate.connection.password", "1234");
				settings.put("hibernate.current_session_context_class", "thread");
				settings.put("hibernate.show_sql", "true");
				settings.put("hibernate.format_sql", "true");
				
				registryBuilder.applySettings(settings);
				
				registry = registryBuilder.build();
				
				MetadataSources sources = new MetadataSources(registry);
				sources.addAnnotatedClass(User.class);
				sources.addAnnotatedClass(Log.class);
				sources.addAnnotatedClass(Message.class);
				
				Metadata metadata = sources.getMetadataBuilder().build();
				
				sessionFactory = metadata.getSessionFactoryBuilder().build();
				return sessionFactory.openSession();
			}catch (Exception ex) {
				System.out.println("Session Factory creation failed.");
				if(registry == null) {
					StandardServiceRegistryBuilder.destroy(registry);
				}
			}
		}
		return sessionFactory.getCurrentSession();
	}
	
	public static void closeHibernateSession() {
		if(sessionFactory != null) {
			sessionFactory.close();
			sessionFactory = null;
		}
	}
	
	
    /*public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/springmvc?useSSL=false");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "1234");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }*/
}
