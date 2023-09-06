package lk.ijse.D24_Hostel_Management_System.config;

import lk.ijse.D24_Hostel_Management_System.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class SessionFactoryConfiguration {

    private static SessionFactoryConfiguration sessionFactoryConfiguration;
    private final SessionFactory sessionFactory;

    private SessionFactoryConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Student.class);

        /** Configuration using property file */
        Properties property = new Properties();
        try {
            property.load(ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.properties"));
        } catch (IOException e) {
            System.out.println("Property file not found!");
        }
        configuration.mergeProperties(property).buildSessionFactory();

        sessionFactory = configuration.buildSessionFactory();

    }

    public static SessionFactoryConfiguration getInstance() {
        return (sessionFactoryConfiguration == null) ?
                sessionFactoryConfiguration = new SessionFactoryConfiguration() :
                sessionFactoryConfiguration;
    }

    public Session getSession() {
        Session session = sessionFactory.openSession();
        return session;
    }
}
