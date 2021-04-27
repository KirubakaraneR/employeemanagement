package com.ideas2it.employeemanagement.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import com.ideas2it.employeemanagement.dao.EmployeeDao;
import com.ideas2it.employeemanagement.dao.impl.EmployeeDaoImpl;
import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.projectmanagement.model.Project;
import com.ideas2it.projectmanagement.service.ProjectService;
import com.ideas2it.projectmanagement.service.impl.ProjectServiceImpl;

/**
 * We perform create and update operation to pojo and also
 * pass the values to employee dao to perform CRUD operation in DB
 */  
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDao employeeDao = new EmployeeDaoImpl();
    private List<Address> employeeAddress = new ArrayList<Address>();

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean createEmployee(String id, String name, LocalDate dateOfJoin,
            LocalDate dateOfBirth, int age, double salary, 
            String mobileNumber, String mailId) {
        Employee employee = new Employee(id, name, Date.valueOf(dateOfJoin), Date.valueOf(dateOfBirth), 
                age, salary, mobileNumber, mailId, employeeAddress);
        return employeeDao.addEmployee(employee);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void createEmployeeAddress(String doorNumber, 
            String street, String nagar, String city, String district,
            String state, String country, int pinCode, 
            String addressType) {
        employeeAddress.add(new Address(doorNumber, street, nagar, 
                city, district, state, country, pinCode, addressType));
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean checkValidEmployeeId(String id) {
        return Pattern.matches("[I][0-9]{5}", id);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean checkValidEmployeeMobileNumber(String mobileNumber) {
        return Pattern.matches("[6789][0-9]{9}",mobileNumber);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean checkValidEmployeeMailId(String mailId) {
        String regexPattern = "^[A-Za-z0-9][A-Za-z0-9.-_]+@[A-Za-z0-9.]{2,}$";
        return Pattern.matches(regexPattern, mailId);
    }
}