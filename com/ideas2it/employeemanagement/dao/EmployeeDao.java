package com.ideas2it.employeemanagement.dao;

import java.util.List;

import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.Employee;

/**
 * We create abstract method for all methods in employee 
 * dao implementation.
 */
public interface EmployeeDao {

    /**
     * We add employee details of new joined employees to the table
     * in data base
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
     * We assign list of projects to an employee.
     *
     * @param employee - Employee pojo
     */
    public void assignProjectToEmployee(Employee employee);

    /**
     * We get the count value of the joining date year.
     * 
     * @param year - Year belongs to joining date
     *
     * @return count of the year
     */
    public int getYearCount(String year);
}