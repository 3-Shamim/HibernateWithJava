package onetomanyrelation;

import org.hibernate.*;
import org.hibernate.cfg.AnnotationConfiguration;

public interface SessionFactory_Provider {
    SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
}
