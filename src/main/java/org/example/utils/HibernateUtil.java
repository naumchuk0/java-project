package org.example.utils;

import lombok.Getter;
import org.example.models.Category;
import org.example.models.Person;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    @Getter
    private static SessionFactory sessionFactory;

    static {
        try {
            Configuration conf = new Configuration().configure();
            conf.addAnnotatedClass(Category.class);
            conf.addAnnotatedClass(Person.class);
            sessionFactory = conf.buildSessionFactory();
        }
        catch (Throwable ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
