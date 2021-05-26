package com.ideas2it.project.employeemanagement.dao;

import java.util.List;

import com.ideas2it.project.employeemanagement.model.Employee;
import com.ideas2it.project.exceptions.ProjectException;

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
     * @throws ProjectException 
     */    
    public boolean addOrUpdateEmployee(Employee employee) throws ProjectException; 

    /**
     * We get list of all employees.
     *
     * @return List of all employees
     * @throws ProjectException 
     */
    public List<Employee> getAllEmployee() throws ProjectException;

    /**
     * We get an details of an individual employee.
     * 
     * @param id - Employee id
     * 
     * @return employee pojo
     * @throws ProjectException 
     */
    public Employee getIndividualEmployee(String id) throws ProjectException;

    /**
     * We get employee details of an particular year.
     *
     * @param year - Joining date of specific year
     *
     * @return List of employees who belong to specific year
     * @throws ProjectException 
     */
    public List<Employee> getAllEmployeeOfParticularYear(String year) throws ProjectException;

    /**
     * We get the count value of the non deleted employees.
     * 
     * @param id - Employee id
     *
     * @return count of the non deleted employee
     * @throws ProjectException 
     */
    public int getIdCount(String id) throws ProjectException;

    /**
     * We get the count value of the non deleted address.
     * 
     * @param id - Address id
     *
     * @return count of the non deleted address
     * @throws ProjectException 
     */
    public int getAddressCount(int id) throws ProjectException;

    /**
     * We get the count value of the joining date year.
     * 
     * @param year - Year belongs to joining date
     *
     * @return count of the year
     * @throws ProjectException 
     */
    public int getYearCount(int year) throws ProjectException;

    /**
     * We check whether the given id is deleted.
     * 
     * @param id - Employee id
     *
     * @return true if id is deleted
     *         else false
     * @throws ProjectException 
     */
    public int getDeletedIdCount(String id) throws ProjectException;
}