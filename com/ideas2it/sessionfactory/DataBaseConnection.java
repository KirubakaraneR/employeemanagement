package com.ideas2it.sessionfactory;

import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

public class DataBaseConnection {
    private static DataBaseConnection dataBaseConnection = null;
    private static SessionFactory sessionFactory = null;

    private DataBaseConnection() {
    }

    public static DataBaseConnection getInstance() {
        if (null == dataBaseConnection) {
            dataBaseConnection = new DataBaseConnection();
        }
        return dataBaseConnection;
    }

    public static SessionFactory getSessionFactory() {

        try {
            
            if (null == sessionFactory) {
                sessionFactory = new Configuration().configure("/resource/hibernate/properties/hibernate.cfg.xml").buildSessionFactory();
            }
        } catch (HibernateException e) {
            e.printStackTrace(); 
        }
        return sessionFactory;
    }
}