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
 * We perform create, read, update operations to the table in data base
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

            if(session != null) {
                session.close();
            }
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

            if(session != null) {
                session.close();
            }
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
                + "WHERE SUBSTRING(YEAR(employee.date_of_join),3) = :year "
                + "AND is_deleted = false");
            query.setParameter("year", year);
            employee = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {

            if(session != null) {
                session.close();
            }
        }
        return employee;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void assignProjectToEmployee(Employee employee) {
        Session session = null;

        try {
            session = dataBaseConnection.getSessionFactory().openSession();
            session.beginTransaction();
            session.merge(employee);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {

            if(session != null) {
                session.close();
            }
        }
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public int getYearCount(String year) {
        Session session = null;
        int count = 0;

        try {
            session = dataBaseConnection.getSessionFactory().openSession();
            Query query = session.createQuery("SELECT COUNT(date_of_join) FROM Employee employee WHERE"
                    + " YEAR(date_of_join) = :year");
            query.setParameter("year", year);
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