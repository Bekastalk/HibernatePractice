package bekks.config;

import bekks.entity.Books;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class HibernateConfig {
    public static SessionFactory createSessionFactory(){
        Properties properties=new Properties();
        properties.setProperty(Environment.DRIVER, "org.postgresql.Driver");
        properties.setProperty(Environment.URL, "jdbc:postgresql://localhost:5432/postgres");
        properties.setProperty(Environment.USER,"postgres");
        properties.setProperty(Environment.PASS,"12345");

        properties.setProperty(Environment.HBM2DDL_AUTO,"update");       //validate бул DML //update ddl
        properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty(Environment.SHOW_SQL, "true");

        Configuration configuration=new Configuration();
        configuration.setProperties(properties);
        configuration.addAnnotatedClass(Books.class);

        return  configuration.buildSessionFactory();

    }
    public static EntityManagerFactory createEntityManagerFactory(){
        return createSessionFactory().unwrap(EntityManagerFactory.class);
    }
}
