package com.ideas2it.employeemanagement.controller;

import java.time.LocalDate;
import java.util.List;

import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.employeemanagement.service.impl.EmployeeServiceImpl;

/**
 * We get user values from the employee view and pass to employee 
 * service to perform create, read, and update operations
 */
public class EmployeeController {
    private EmployeeService employeeService = new EmployeeServiceImpl();

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
            String mobileNumber, String mailId) {
        return employeeService.createEmployee(id, name, dateOfJoin,
                dateOfBirth, age, salary, mobileNumber, mailId);
    }

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
    public void addEmployeeAddress(String id, String doorNumber, 
            String street, String nagar, String city, String district,
            String state, String country, int pinCode, 
            String addressType) {
        employeeService.addEmployeeAddress(id, doorNumber, street,  
                    nagar, city, district, state, country, pinCode, 
                    addressType);
    }

    /**
     * We check whether the employee id given by the user is valid or not
     * by passing employee id to employee service.
     *
     * @param id - Employee id
     *
     * @return true if the condition satisfies
     *         else false
     */
    public boolean checkValidEmployeeId(String id) {
        return employeeService.checkValidEmployeeId(id);
    }

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
    public boolean checkValidEmployeeMobileNumber(String mobileNumber) {
        return employeeService.checkValidEmployeeMobileNumber(mobileNumber);
    }

    /**
     * We check whether the employee mail id given by the user
     * is valid or not by passing employee mail id to employee service
     *
     * @param mailId - Employee mail id
     *
     * @return true if the condition satisfies
     *         else false
     */
    public boolean checkValidEmployeeMailId(String mailId) {
        return employeeService.checkValidEmployeeMailId(mailId);
    }

    /**
     * We display the whole employee data by returning values to 
     * employee view
     *
     * @return Employee List
     */
    public List<String> getAllEmployeeForDisplay() {
        return employeeService.getAllEmployeeForDisplay();
    }

    /**
     * We display the individual employee data by returning values to 
     * employee view.
     *
     * @param id - Employee id
     *
     * @return Employee List
     */
    public List<String> getIndividualEmployeeForDisplay(String id) {
        return employeeService.getIndividualEmployeeForDisplay(id);
    }

    /**
     * We display the List employee data belong to particular year
     * by returning values to employee view.
     *
     * @param year - Date of join year
     *
     * @return Employee List
     */
    public List<String> getEmployeeOfParticularYearForDisplay(String year) {
        return employeeService.getEmployeeOfParticularYearForDisplay(year);
    }

    /**
     * We display the address of individual employee.
     *
     * @param id - Employee id
     *
     * @return Address list
     */
    public List<String> getIndividualAddressForDisplay(String id) {
        return employeeService.getIndividualAddressForDisplay(id);
    }

    /**
     * We update all the details of the employee by getting from the user.
     * 
     * @param id - Employee id
     * @param dateOfJoin - Employee joining date
     * @param name - Employee Name
     * @param dateOfbirth - Employee birth date
     * @param age - Employee age
     * @param salary - Employee salary
     * @param mobileNumber - Employee mobile number
     * @param mailId - Employee mail id
     *
     * @return true if datas are successfully updated to table
     *         else false
     */
    public boolean updateEmployee(String id, String name, LocalDate dateOfJoin, 
            LocalDate dateOfBirth, int age, double salary, 
            String mobileNumber, String mailId) {
        return employeeService.updateEmployee(id, name, dateOfJoin,
                dateOfBirth, age, salary, mobileNumber, mailId);
    }

    /**
     * We update the address for an individual employee.
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
     * @param addressId - Address id
     *
     * @return true if datas are successfully added to table
     *         else false
     */
    public boolean updateAddress(String employeeId, String doorNumber, 
            String street, String nagar, String city, String district,
            String state, String country, int pinCode, 
            String addressType, int addressId) {
        return employeeService.updateAddress(employeeId, doorNumber, street,  
                nagar, city, district, state, country, pinCode, 
                addressType, addressId);
    }

    /**
     * We delete all the records of an employee by passing employee id
     * to employee service
     *
     * @param id - Employee id
     *
     * @return true if datas are successfully deleted from table
     *         else false
     */
    public boolean deleteEmployee(String id) {
        return employeeService.deleteEmployee(id);
    }

    /**
     * We delete all the records of an address by passing employee id
     * to employee service.
     *
     * @param id - Employee id
     *
     * @return true if datas are successfully deleted from table
     *         else false
     */
    public boolean deleteAddress(String id) {
        return employeeService.deleteAddress(id);
    }

    /**
     * We delete an individual address by passing address id
     * to employee service
     *
     * @param addressId - Address id
     * @param employeeId - Employee id
     *
     * @return true if datas are successfully deleted from table
     *         else false
     */
    public boolean deleteIndividualAddress(String employeeId, int addressId) {
        return employeeService.deleteIndividualAddress(employeeId, addressId);
    }

    /**
     * We assign projects to the employees.
     *
     * @param id - Employee id
     * @param projectIdList - List of project id
     */
    public void assignProjectToEmployee(String id, List<Integer> projectIdList) {
        employeeService.assignProjectToEmployee(id, projectIdList);
    }

    /**
     * We unassign projects to the employees.
     *
     * @param id - Employee id
     * @param projectIdList - List of project id
     */
    public void unassignProjectFromEmployee(String id, List<Integer> projectIdList) {
        employeeService.unassignProjectFromEmployee(id, projectIdList);
    }

    /**
     * We get the list of employees id for assign and unassign.
     * 
     * @param id - Employee id
     * @param projectIdList - List of project id
     * 
     * @return List of list project id values
     */
    public List<List<Integer>> getAvailableProjectId(String id, List<Integer> projectIdList) {
        return employeeService.getAvailableProjectId(id, projectIdList);
    }

    /**
     * We display list of employees who are assigned to project.
     *
     * @param id - Project id
     *
     * @return List of Employee
     */
    public List<String> getAssignedProject(String id) {
        return employeeService.getAssignedProject(id);
    }
   
    /**
     * We check whether the list is empty or not.
     *
     * @param projectIdList - List of project id
     *
     * @return true if empty
     *         else false
     */
    public boolean checkIsEmpty(List<Integer> projectIdList) {
        return employeeService.checkIsEmpty(projectIdList);
    }

    /**
     * We check whether the employee id exist in the table or not.
     *
     * @param id - Employee id
     *
     * @return true if id exists
     *         else false
     */
    public boolean checkIdExistOrNot(String id) {
        return employeeService.checkIdExistOrNot(id);
    }

    /**
     * We check whether the address id exist in the table or not.
     *
     * @param addressId - Address id
     * @param employeeId - Employee id
     *
     * @return true if id exists
     *         else false
     */
    public boolean checkAddressIdExistOrNot(String employeeId, int addressId) {
        return employeeService.checkAddressIdExistOrNot(employeeId, addressId);
    }

    /**
     * We check whether the year exist in the table or not.
     *
     * @param year - Year belongs to joining date.
     *
     * @return true if id exists
     *         else false
     */
    public boolean checkYearExistOrNot(String year) {
        return employeeService.checkYearExistOrNot(year);
    }
}