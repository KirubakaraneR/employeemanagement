package com.ideas2it.projectmanagement.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

//import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.projectmanagement.dao.ProjectDao;
import com.ideas2it.projectmanagement.model.Project;
import com.ideas2it.sessionfactory.DataBaseConnection;

/**
 * We perform create, read, update operations to the table in data base
 */
public class ProjectDaoImpl implements ProjectDao {
    private DataBaseConnection dataBaseConnection = DataBaseConnection.getInstance();

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean createProject(Project project) {
        Session session = null;
        boolean checkIsAdded = true;
       
        try {
            SessionFactory sessionFactory = dataBaseConnection.getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(project);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            checkIsAdded = false;
        } finally {

            if(session != null) {
                session.close();
            }
        }
        return checkIsAdded;
    }
}