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
}