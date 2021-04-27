package com.ideas2it.employeemanagement.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ideas2it.employeemanagement.dao.EmployeeDao;
import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.Employee;
//import com.ideas2it.projectmanagement.model.Project;
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
    public boolean addEmployee(Employee employee) {
        boolean checkIsAdded = true;
        Session session = null;

        try {
            SessionFactory sessionFactory = dataBaseConnection.getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(employee);
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