package com.ideas2it.project.employeemanagement.dao;

import java.util.List;

import com.ideas2it.project.employeemanagement.model.Employee;
import com.ideas2it.project.exceptions.UserDefinedException;

/**
 * We create abstract method for all methods in employee 
 * dao implementation.
 *
 * @version 1.0 04-05-2021
 * @author Kirubakarane R
 */
public interface EmployeeDao {

    /**
     * We perform add and update operation to table.
     *
     * @param employee - Employee pojo
     *
     * @return true if datas are successfully added to table
     *         else false
     * @throws UserDefinedException 
     */    
    public boolean addOrUpdateEmployee(Employee employee) throws UserDefinedException; 

    /**
     * We get list of all employees.
     *
     * @return List of all employees
     * @throws UserDefinedException 
     */
    public List<Employee> getAllEmployee() throws UserDefinedException;

    /**
     * We get an details of an individual employee.
     * 
     * @param id - Employee id
     * 
     * @return employee pojo
     * @throws UserDefinedException 
     */
    public Employee getIndividualEmployee(String id) throws UserDefinedException;

    /**
     * We get employee details of an particular year.
     *
     * @param year - Joining date of specific year
     *
     * @return List of employees who belong to specific year
     * @throws UserDefinedException 
     */
    public List<Employee> getAllEmployeeOfParticularYear(String year) throws UserDefinedException;

    /**
     * We get the count value of the non deleted employees.
     * 
     * @param id - Employee id
     *
     * @return count of the non deleted employee
     * @throws UserDefinedException 
     */
    public int getIdCount(String id) throws UserDefinedException;

    /**
     * We get the count value of the non deleted address.
     * 
     * @param id - Address id
     *
     * @return count of the non deleted address
     * @throws UserDefinedException 
     */
    public int getAddressCount(int id) throws UserDefinedException;

    /**
     * We get the count value of the joining date year.
     * 
     * @param year - Year belongs to joining date
     *
     * @return count of the year
     * @throws UserDefinedException 
     */
    public int getYearCount(int year) throws UserDefinedException;

    /**
     * We check whether the given id is deleted.
     * 
     * @param id - Employee id
     *
     * @return true if id is deleted
     *         else false
     * @throws UserDefinedException 
     */
    public int getDeletedIdCount(String id) throws UserDefinedException;
}