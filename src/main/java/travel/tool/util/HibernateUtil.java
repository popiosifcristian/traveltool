package travel.tool.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

/**
 * @author ipop
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        Properties properties = new Properties();
        Configuration configuration = new Configuration();
        try {
            properties.load(
                    Thread.currentThread().getContextClassLoader().getResourceAsStream("hibernate.properties"));
            configuration.setProperties(properties);

            return configuration.buildSessionFactory();
        } catch (IOException e) {
            System.err.println("Initial SessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void closeSessionFactory() {
        getSessionFactory().close();
    }
}
