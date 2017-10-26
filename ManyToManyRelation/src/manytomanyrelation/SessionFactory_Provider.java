package manytomanyrelation;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public interface SessionFactory_Provider {
    SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
}
