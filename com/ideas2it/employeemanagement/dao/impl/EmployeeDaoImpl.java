package com.ideas2it.employeemanagement.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ideas2it.employeemanagement.dao.EmployeeDao;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.projectmanagement.model.Project;
import com.ideas2it.sessionfactory.DataBaseConnection;

/**
 * We perform create, read, update operations to the table in data base.
 *
 * @version 1.0 04-05-2021
 * @author Kirubakarane R
 */
public class EmployeeDaoImpl implements EmployeeDao {
    private DataBaseConnection dataBaseConnection = DataBaseConnection.getInstance();
    
    /**
     * {@inheritdoc}
     */
    @Override    
    public boolean addOrUpdateEmployee(Employee employee) {
        Session session = null;
        boolean checkIsAdded = true;

        try {
            session = dataBaseConnection.getSessionFactory().openSession();
            session.beginTransaction();
            session.saveOrUpdate(employee);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            checkIsAdded = false;
            session.getTransaction().rollback();
        } finally {
            closeSession(session);
        }
        return checkIsAdded;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<Employee> getAllEmployee() {
        Session session = null;
        List<Employee> employee = null;

        try {
            session = dataBaseConnection.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Employee employee WHERE is_deleted = false");
            employee = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            closeSession(session);
        }
        return employee;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public Employee getIndividualEmployee(String id) {
        Session session = null;
        Employee employee = null;

        try {
            session = dataBaseConnection.getSessionFactory().openSession();
            session.beginTransaction();
            employee = (Employee) session.get(Employee.class, id);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            closeSession(session);
        }
        return employee;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<Employee> getAllEmployeeOfParticularYear(String year) {
        Session session = null;
        List<Employee> employee = null;

        try {
            session = dataBaseConnection.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Employee employee "
                + "WHERE SUBSTRING(YEAR(date_of_join),3) = :year "
                + "AND is_deleted = false");
            query.setParameter("year", year);
            employee = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            closeSession(session);
        }
        return employee;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public int getYearCount(int year) {
        Session session = null;
        int count = 0;

        try {
            session = dataBaseConnection.getSessionFactory().openSession();
            Query query = session.createQuery("SELECT COUNT(date_of_join) FROM Employee employee WHERE"
                    + " YEAR(date_of_join) = :year AND is_deleted = false");
            query.setParameter("year", year);
            count = ((Long)query.uniqueResult()).intValue();
        } catch (HibernateException e) {
            count = 0;
        } finally {
            closeSession(session);
        }
        return count;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public int getIdCount(String id) {
        Session session = null;
        int count = 0;

        try {
            session = dataBaseConnection.getSessionFactory().openSession();
            Query query = session.createQuery("SELECT COUNT(id) FROM Employee employee WHERE"
                    + " is_deleted = false AND id = :id");
            query.setParameter("id", id);
            count = ((Long)query.uniqueResult()).intValue();
        } catch (HibernateException e) {
            count = 0;
        } finally {
            closeSession(session);
        }
        return count;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public int getAddressCount(int id) {
        Session session = null;
        int count = 0;

        try {
            session = dataBaseConnection.getSessionFactory().openSession();
            Query query = session.createQuery("SELECT COUNT(id) FROM Address address WHERE"
                    + " is_deleted = false AND id = :id");
            query.setParameter("id", id);
            count = ((Long)query.uniqueResult()).intValue();
        } catch (HibernateException e) {
            count = 0;
        } finally {
            closeSession(session);
        }
        return count;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public int getDeletedIdCount(String id) {
        Session session = null;
        int count = 0;

        try {
            session = dataBaseConnection.getSessionFactory().openSession();
            Query query = session.createQuery("SELECT COUNT(id) FROM Employee employee WHERE"
                    + " is_deleted = true AND id = :id");
            query.setParameter("id", id);
            count = ((Long)query.uniqueResult()).intValue();
        } catch (HibernateException e) {
            count = 0;
        } finally {
            closeSession(session);
        }
        return count;
    }

    /**
     * We close the session object.
     */
    private void closeSession(Session session) {
        if(session != null) {
            session.close();
        }
    }
}