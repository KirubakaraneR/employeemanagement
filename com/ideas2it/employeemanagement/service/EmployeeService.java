package com.ideas2it.employeemanagement.service;

import java.time.LocalDate;
import java.util.List;

import com.ideas2it.employeemanagement.model.Employee;

/**
 * We create abstract method for all methods in employee 
 * service implementation
 */
public interface EmployeeService {

    /**
     * We create employee details for newly joined employees by passing
     * values to employee service.
     * Details such as id, name, age, mobile number, mail id, 
     * date of birth and date of join.
     *
     * @param dateOfJoin - Employee date of join
     * @param id - Employee id
     * @param name - Employee name
     * @param dateOfBirth - Employee date of birth
     * @param age - Employee age
     * @param salary - Employee salary
     * @param mobileNumber - Employee mobile number
     * @param mailId - Employee Mail Id
     *
     * @return true if datas are successfully added to table
     *         else false
     */
    public boolean createEmployee(String id, String name, LocalDate dateOfJoin,
            LocalDate dateOfBirth, int age, double salary, 
            String mobileNumber, String mailId);

    /**
     * We create address details for newly joined employee by passing
     * values to employee service.
     *
     * @param id - Employee id
     * @param doorNumber - Resident door number
     * @param street - Resident street name
     * @param nagar - Residential nagar name
     * @param city - Residential city name
     * @param district - District name
     * @param state - State name
     * @param country - Country name
     * @param pinCode - Residential pincode
     * @param addressType - Permanent or temporary
     */
    public void createEmployeeAddress(String doorNumber, 
            String street, String nagar, String city, String district,
            String state, String country, int pinCode, 
            String addressType);

    /**
     * We check whether the employee id given by the user is valid or not
     * by passing employee id to employee service.
     *
     * @param id - Employee id
     *
     * @return true if the condition satisfies
     *         else false
     */
    public boolean checkValidEmployeeId(String id);

    /**
     * We check whether the employee mobile number given by the user
     * is valid or not by passing employee mobile number 
     * to employee service
     * 
     * @param mobileNumber - Employee mobile number
     *
     * @return true if the condition satisfies
     *         else false
     */
    public boolean checkValidEmployeeMobileNumber(String mobileNumber);

    /**
     * We check whether the employee mail id given by the user
     * is valid or not by passing employee mail id to employee service
     *
     * @param mailId - Employee mail id
     *
     * @return true if the condition satisfies
     *         else false
     */
    public boolean checkValidEmployeeMailId(String mailId);
}