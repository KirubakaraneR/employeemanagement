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
    public void createEmployeeAddress(String id, String doorNumber, 
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

    /**
     * We display the whole employee data by returning values to 
     * employee view
     *
     * @return Employee List
     */
    public List<String> getAllEmployeeForDisplay();

    /**
     * We display the individual employee data by returning values to 
     * employee view.
     *
     * @param id - Employee id
     *
     * @return Employee List
     */
    public List<String> getIndividualEmployeeForDisplay(String id);

    /**
     * We display the List employee data belong to particular year
     * by returning values to employee view.
     *
     * @param year - Date of join year
     *
     * @return Employee List
     */
    public List<String> getEmployeeOfParticularYearForDisplay(String year);

    /**
     * We display the address of individual employee.
     *
     * @param id - Employee id
     *
     * @return Address list
     */
    public List<String> getIndividualAddressForDisplay(String id);

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
     * @return true if datas are successfully added to table
     *         else false
     */
    public boolean updateEmployee(String id, String name, LocalDate dateOfJoin, 
            LocalDate dateOfBirth, int age, double salary, 
            String mobileNumber, String mailId);
  
    /**
     * We add new address for the employee.
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
     *
     * @return true if datas are successfully added to table
     *         else false
     */
    public boolean addAddress(String id, String doorNumber, 
            String street, String nagar, String city, String district,
            String state, String country, int pinCode, 
            String addressType);

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
            String addressType, int addressId);

    /**
     * We delete all the records of an employee by passing employee id
     * to employee service
     *
     * @param id - Employee id
     *
     * @return true if datas are successfully deleted from table
     *         else false
     */
    public boolean deleteEmployee(String id);

    /**
     * We delete all the records of an address by passing employee id
     * to employee service
     *
     * @param id - Employee id
     *
     * @return true if datas are successfully deleted from table
     *         else false
     */
    public boolean deleteAddress(String id);

    /**
     * We delete an individual address by passing address id
     * to employee service
     *
     * @param id - Address id
     *
     * @return true if datas are successfully deleted from table
     *         else false
     */
    public boolean deleteIndividualAddress(int id);

    /**
     * We assign projects to the employees.
     *
     * @param id - Employee id
     * @param projectIdList - List of project id
     */
    public void assignProjectToEmployee(String id, List<Integer> projectIdList);

    /**
     * We unassign projects to the employees.
     *
     * @param id - Employee id
     * @param projectIdList - List of project id
     */
    public void unassignProjectFromEmployee(String id, List<Integer> projectIdList);

    /**
     * We get the list of employees id for assign and unassign.
     * 
     * @param id - Employee id
     * @param projectIdList - List of project id
     * 
     * @return List of list project id values
     */
    public List<List<Integer>> getAvailableProjectId(String id, List<Integer> projectIdList);

    /**
     * We get the employee details which matchs with the id list we are
     * passing. This method is called from projectServiceImpl.
     *
     * @param employeeIdList - List of employee id
     *
     * @return List of employee details
     */
    public List<Employee> getEmployee(List<String> employeeIdList);

    /**
     * We display list of employees who are assigned to project.
     *
     * @param id - Employee id
     *
     * @return List of Employees
     */
    public List<String> getAssignedProject(String id);

    /**
     * We check whether the list is empty or not.
     *
     * @param projectIdList - List of project id
     *
     * @return true if empty
     *         else false
     */
    public boolean checkIsEmpty(List<Integer> projectIdList);

    /**
     * We check whether the employee id exist in the table or not.
     *
     * @param id - Employee id
     *
     * @return true if id exists
     *         else false
     */
    public boolean checkIdExistOrNot(String id);

    /**
     * We check whether the address id exist in the table or not.
     *
     * @param id - Address id
     *
     * @return true if id exists
     *         else false
     */
    public boolean checkAddressIdExistOrNot(int id);

    /**
     * We check whether the year exist in the table or not.
     *
     * @param year - Year belongs to joining date.
     *
     * @return true if id exists
     *         else false
     */
    public boolean checkYearExistOrNot(String year);
}