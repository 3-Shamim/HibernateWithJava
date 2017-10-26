/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newhibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author SHAMIM
 */
public interface sessionFactoryProvider {
    public static final SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
}
