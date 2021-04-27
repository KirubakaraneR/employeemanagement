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
    public boolean addEmployee(Employee employee); 

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
     * We get employee list belong to particular year.
     *
     * @param year - Year belong to joining date.
     *
     * @return employee list
     */
    public List<Employee> getAllEmployeeOfParticularYear(String year);

    /**
     * We update all values of an employee.
     *
     * @param employee - Employee pojo
     *
     * @return true if datas are successfully updated to table
     *         else false
     */
    public boolean updateEmployee(Employee employee);

    /**
     * We get an individual address of an employee.
     *
     * @param employeeId - Id of an employee
     * @param addressId - Id of an address
     */
    public Address getAddress(String employeeId, int addressId);

    /**
     * We add address for an employee.
     *
     * @param employee - Employee pojo
     *
     * @return true if datas are successfully added to table
     *         else false
     */
    public boolean addAddress(Employee employee);

    /**
     * We update address of an employee.
     *
     * @param address - Address pojo
     *
     * @return true if datas are successfully added to table
     *         else false
     */
    public boolean updateAddress(Address address);

    /**
     * We delete entire record of an employee.
     *
     * @param id - String id
     *
     * @return true if datas are successfully deleted from table
     *         else false
     */
    public boolean deleteEmployee(String id);

    /**
     * We delete all address of an employee.
     *
     * @param id - Employee id
     *
     * @return true if datas are successfully deleted from table
     *         else false
     */
    public boolean deleteAddress(String id);

    /**
     * We delete an individual address of an employee.
     *
     * @param id - Address id
     *
     * @return true if datas are successfully deleted from table
     *         else false
     */
    public boolean deleteIndividualAddress(int id);

    /**
     * We get project and employee values where employee has
     * multiple assigned projects.
     *
     * @param id - String id
     *
     * @return Employee pojo
     */
    public Employee getEmployeeProject(String id);

    /**
     * We assign list of projects to an employee.
     *
     * @param employee - Employee pojo
     */
    public void assignProjectToEmployee(Employee employee);

    /**
     * We unassign project from the employee.
     *
     * @param employee - Employee pojo
     */
    public void unassignProjectFromEmployee(Employee employee);

    /**
     * We get the count value of the employee id.
     *
     * @param id - Employee id
     *
     * @return count of employee id
     */
    public int getEmployeeIdCount(String id);
 
    /**
     * We get the count value of the address id.
     *
     * @param id - Address id
     *
     * @return count of address id
     */
    public int getAddressIdCount(int id);

    /**
     * We get the count value of the joining date year.
     * 
     * @param year - Year belongs to joining date
     *
     * @return count of the year
     */
    public int getYearCount(String year);
}