package net.spring.coding.sesstion;

import net.spring.coding.model.Employee;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


/**
 * This class manages hibernate session as config to db , and get session for query
*/
public class HibernateSession {

    private static final Logger log = Logger.getLogger(HibernateSession.class);
    public static SessionFactory sessionFactory = null;

    /** When this class is working loadSessionFactory() method is working too. */
    public HibernateSession() {
        loadSessionFactory();
    }

    public static void loadSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate-config/hibernate.cfg.xml"); // load file xml
        configuration.addAnnotatedClass(Employee.class); // load class */
        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(registry);
    }

    public static Session getSession() throws HibernateException {
        Session session = null;
            try {
                session = sessionFactory.openSession(); // getting session object from session factory
            }
            catch (Throwable mess) {
                log.log(Level.DEBUG, "Failed on open section : " + mess);
            }
            if (session == null) {
                log.log(Level.WARN, "session is null " );
            }
            return session;
    }

    public static void downSession() throws HibernateException {
        sessionFactory.close();
    }
}
