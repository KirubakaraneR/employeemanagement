package com.ideas2it.project.employeemanagement.dao;

import java.util.List;

import com.ideas2it.project.employeemanagement.model.Employee;

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
     */    
    public boolean addOrUpdateEmployee(Employee employee); 

    /**
     * We get list of all employees.
     *
     * @return List of all employees
     */
    public List<Employee> getAllEmployee();

    /**
     * We get an details of an individual employee.
     * 
     * @param id - Employee id
     * 
     * @return employee pojo
     */
    public Employee getIndividualEmployee(String id);

    /**
     * We get employee details of an particular year.
     *
     * @param year - Joining date of specific year
     *
     * @return List of employees who belong to specific year
     */
    public List<Employee> getAllEmployeeOfParticularYear(String year);

    /**
     * We get the count value of the non deleted employees.
     * 
     * @param id - Employee id
     *
     * @return count of the non deleted employee
     */
    public int getIdCount(String id);

    /**
     * We get the count value of the non deleted address.
     * 
     * @param id - Address id
     *
     * @return count of the non deleted address
     */
    public int getAddressCount(int id);

    /**
     * We get the count value of the joining date year.
     * 
     * @param year - Year belongs to joining date
     *
     * @return count of the year
     */
    public int getYearCount(int year);

    /**
     * We check whether the given id is deleted.
     * 
     * @param id - Employee id
     *
     * @return true if id is deleted
     *         else false
     */
    public int getDeletedIdCount(String id);
}