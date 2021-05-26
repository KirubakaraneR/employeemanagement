package com.ideas2it.project.employeemanagement.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;

import com.ideas2it.project.employeemanagement.dao.EmployeeDao;
import com.ideas2it.project.employeemanagement.model.Employee;
import com.ideas2it.project.exceptions.ProjectException;
import com.ideas2it.project.logger.Loggers;
import com.ideas2it.sessionfactory.DataBaseConnection;

/**
 * We perform create, read, update operations to the table in data base.
 *
 * @version 1.0 04-05-2021
 * @author Kirubakarane R
 */
public class EmployeeDaoImpl implements EmployeeDao {
    private DataBaseConnection dataBaseConnection = DataBaseConnection.getInstance();
    private Loggers log = new Loggers(EmployeeDaoImpl.class);
    
    /**
     * {@inheritdoc}
     * @throws ProjectException 
     */
    @Override    
    public boolean addOrUpdateEmployee(Employee employee) throws ProjectException {
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
            log.logError("Issue while adding or updating values.", e);
            throw new ProjectException("Issue while adding or updating values.");
        } finally {
            closeSession(session);
        }
        return checkIsAdded;
    }

    /**
     * {@inheritdoc}
     * @throws ProjectException 
     */
    @Override
    public List<Employee> getAllEmployee() throws ProjectException {
        Session session = null;
        List<Employee> employee = null;

        try {
            session = dataBaseConnection.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Employee employee WHERE is_deleted "
            		+ "= false ORDER BY date_of_join desc");
            employee = query.list();
        } catch (HibernateException e) {
        	log.logError("Issue while fetching all employee datas.", e);
            throw new ProjectException("Issue while fetching all employee datas.");
        } finally {
            closeSession(session);
        }
        return employee;
    }

    /**
     * {@inheritdoc}
     * @throws ProjectException 
     */
    @Override
    public Employee getIndividualEmployee(String id) throws ProjectException {
        Session session = null;
        Employee employee = null;

        try {
            session = dataBaseConnection.getSessionFactory().openSession();
            session.beginTransaction();
            employee = (Employee) session.get(Employee.class, id);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            log.logError("Issue while fetching individual employee datas.", e);
            throw new ProjectException("Issue while fetching individual employee datas.");
        } finally {
            closeSession(session);
        }
        return employee;
    }

    /**
     * {@inheritdoc}
     * @throws ProjectException 
     */
    @Override
    public List<Employee> getAllEmployeeOfParticularYear(String year) throws ProjectException {
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
        	log.logError("Issue while fetching employee datas.", e);
        	throw new ProjectException("Issue while fetching employee datas.");
        } finally {
            closeSession(session);
        }
        return employee;
    }

    /**
     * {@inheritdoc}
     * @throws ProjectException 
     */
    @Override
    public int getYearCount(int year) throws ProjectException {
        Session session = null;
        int count = 0;

        try {
            session = dataBaseConnection.getSessionFactory().openSession();
            Query query = session.createQuery("SELECT COUNT(date_of_join) FROM Employee "
            		+ "employee WHERE YEAR(date_of_join) = :year AND is_deleted = false");
            query.setParameter("year", year);
            count = ((Long)query.uniqueResult()).intValue();
        } catch (HibernateException e) {
            count = 0;
            log.logError("Issue in getting year count.", e);
            throw new ProjectException("Issue in getting year count.");
        } finally {
            closeSession(session);
        }
        return count;
    }

    /**
     * {@inheritdoc}
     * @throws ProjectException 
     */
    @Override
    public int getIdCount(String id) throws ProjectException {
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
            log.logError("Issue in getting count value.", e);
            throw new ProjectException("Issue in getting count value.");
        } finally {
            closeSession(session);
        }
        return count;
    }

    /**
     * {@inheritdoc}
     * @throws ProjectException 
     */
    @Override
    public int getAddressCount(int id) throws ProjectException {
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
            log.logError("Issue in getting address count.", e);
            throw new ProjectException("Issue in getting address count.");
        } finally {
            closeSession(session);
        }
        return count;
    }

    /**
     * {@inheritdoc}
     * @throws ProjectException 
     */
    @Override
    public int getDeletedIdCount(String id) throws ProjectException {
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
            log.logError("Issue in getting deleted id count.", e);
            throw new ProjectException("Issue in getting deleted id count.");
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