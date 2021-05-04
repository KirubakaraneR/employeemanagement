package com.ideas2it.projectmanagement.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.projectmanagement.dao.ProjectDao;
import com.ideas2it.projectmanagement.model.Project;
import com.ideas2it.sessionfactory.DataBaseConnection;

/**
 * We perform create, read, update operations to the table in data base.
 *
 * @version 1.0 04-05-2021
 * @author Kirubakarane R
 */
public class ProjectDaoImpl implements ProjectDao {
    private DataBaseConnection dataBaseConnection = DataBaseConnection.getInstance();


    /**
     * {@inheritdoc}
     */
    @Override
    public boolean addOrUpdateProject(Project project) {
        Session session = null;
        boolean checkIsAdded = true;
       
        try {
            session = dataBaseConnection.getSessionFactory().openSession();
            session.beginTransaction();
            session.saveOrUpdate(project);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            checkIsAdded = false;
            session.getTransaction().rollback();
        } finally {

            if(session != null) {
                session.close();
            }
        }
        return checkIsAdded;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<Project> getAllProject() {
        Session session = null;
        List<Project> project = null;

        try {
            session = dataBaseConnection.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Project project WHERE is_deleted = false");
            project = 	query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
      
            if(session != null) {
                session.close();
            }
        }
        return project;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public Project getProject(int id) {
        Session session = null;
        Project project = null;

        try {
            session = dataBaseConnection.getSessionFactory().openSession();
            session.beginTransaction();
            project = (Project)session.get(Project.class, id);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
      
            if(session != null) {
                session.close();
            }
        }
        return project;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public int getIdCount(int id) {
        Session session = null;
        int count = 0;

        try {
            session = dataBaseConnection.getSessionFactory().openSession();
            Query query = session.createQuery("SELECT COUNT(id) FROM Project project WHERE"
                    + " is_deleted = false AND id = :id");
            query.setParameter("id", id);
            count = ((Long)query.uniqueResult()).intValue();
        } catch (HibernateException e) {
            count = 0;
        } finally {

            if(session != null) {
                session.close();
            }
        }
        return count;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public int getDeletedIdCount(int id) {
        Session session = null;
        int count = 0;

        try {
            session = dataBaseConnection.getSessionFactory().openSession();
            Query query = session.createQuery("SELECT COUNT(id) FROM Project project WHERE"
                    + " is_deleted = true AND id = :id");
            query.setParameter("id", id);
            count = ((Long)query.uniqueResult()).intValue();
        } catch (HibernateException e) {
            count = 0;
        } finally {

            if(session != null) {
                session.close();
            }
        }
        return count;
    }
}