package com.ideas2it.sessionfactory;

import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

/**
 * We create a session factory for connected data base.
 *
 * @version 1.0 04-05-2021
 * @author Kirubakarane R
 */
public class DataBaseConnection {
    private static DataBaseConnection dataBaseConnection = null;
    private static SessionFactory sessionFactory = null;

    private DataBaseConnection() {
    }

    /**
     * getInstance used to create DataBaseConnection object.
     *
     * @return dataBaseConnection
     */
    public static DataBaseConnection getInstance() {
        if (null == dataBaseConnection) {
            dataBaseConnection = new DataBaseConnection();
        }
        return dataBaseConnection;
    }

    /**
     * SessionFactory created between java and database.
     *
     * @return session factory
     */
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