package com.ideas2it.project.employeemanagement.service;

import java.time.LocalDate;
import java.util.List;

import com.ideas2it.project.employeemanagement.model.Address;
import com.ideas2it.project.employeemanagement.model.Employee;
import com.ideas2it.project.exceptions.UserDefinedException;

/**
 * We create abstract method for all methods in employee 
 * service implementation.
 *
 * @version 1.0 04-05-2021
 * @author Kirubakarane R
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
     * @throws UserDefinedException 
     */
    public boolean createEmployee(String id, String name, LocalDate dateOfJoin,
            LocalDate dateOfBirth, int age, double salary, 
            String mobileNumber, String mailId, String password) throws UserDefinedException;

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
     * @throws UserDefinedException 
     */
    public void addEmployeeAddress(String id, String doorNumber, 
            String street, String nagar, String city, String district,
            String state, String country, int pinCode, 
            String addressType) throws UserDefinedException;

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
     * We check whether the year is valid or not.
     *
     * @param dateOfJoin - Joining date of the employee
     * @param temp - Temp variable
     *
     * @return true if the date is in valid format
     *         else return false
     */
    public boolean checkValidYear(LocalDate date, String temp);

    /**
     * We display the whole employee data by returning values to 
     * employee view
     *
     * @return Employee List
     * @throws UserDefinedException 
     */
    public List<Employee> getAllEmployeeForDisplay() throws UserDefinedException;

    /**
     * We display the individual employee data by returning values to 
     * employee view.
     *
     * @param id - Employee id
     *
     * @return Employee List
     * @throws UserDefinedException 
     */
    public Employee getIndividualEmployeeForDisplay(String id) throws UserDefinedException;

    /**
     * We display the List employee data belong to particular year
     * by returning values to employee view.
     *
     * @param year - Date of join year
     *
     * @return Employee List
     * @throws UserDefinedException 
     */
    public List<String> getEmployeeOfParticularYearForDisplay(String year) throws UserDefinedException;

    /**
     * We display the address of individual employee.
     *
     * @param id - Employee id
     *
     * @return Address list
     * @throws UserDefinedException 
     */
    public List<Address> getIndividualAddressForDisplay(int addressId, String employeeId) throws UserDefinedException;

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
     * @throws UserDefinedException 
     */
    public boolean updateEmployee(String id, String name, LocalDate dateOfJoin, 
            LocalDate dateOfBirth, int age, double salary, 
            String mobileNumber, String mailId, String password) throws UserDefinedException;

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
     * @throws UserDefinedException 
     */
    public boolean updateAddress(String employeeId, String doorNumber, 
            String street, String nagar, String city, String district,
            String state, String country, int pinCode, 
            String addressType, int addressId) throws UserDefinedException;

    /**
     * We delete all the records of an employee by passing employee id
     * to employee service
     *
     * @param id - Employee id
     *
     * @return true if datas are successfully deleted from table
     *         else false
     * @throws UserDefinedException 
     */
    public boolean deleteEmployee(String id) throws UserDefinedException;

    /**
     * We delete all the records of an address by passing employee id
     * to employee service
     *
     * @param id - Employee id
     *
     * @return true if datas are successfully deleted from table
     *         else false
     * @throws UserDefinedException 
     */
    public boolean deleteAddress(String id) throws UserDefinedException;

    /**
     * We delete an individual address by passing address id
     * to employee service
     *
     * @param addressId - Address id
     * @param employeeId - Employee id
     *
     * @return true if datas are successfully deleted from table
     *         else false
     * @throws UserDefinedException 
     */
    public boolean deleteIndividualAddress(String employeeId, int addressId) throws UserDefinedException;

    /**
     * We assign projects to the employees.
     *
     * @param id - Employee id
     * @param projectIdList - List of project id
     * @throws UserDefinedException 
     */
    public void assignProjectToEmployee(String id, List<Integer> projectIdList) throws UserDefinedException;

    /**
     * We unassign projects to the employees.
     *
     * @param id - Employee id
     * @param projectIdList - List of project id
     * @throws UserDefinedException 
     */
    public void unassignProjectFromEmployee(String id, List<Integer> projectIdList) throws UserDefinedException;

    /**
     * We get the list of employees id for assign and unassign.
     * 
     * @param id - Employee id
     * @param projectIdList - List of project id
     * 
     * @return List of list project id values
     * @throws UserDefinedException 
     */
    public List<List<Integer>> getAvailableProjectId(String id, List<Integer> projectIdList) 
    		throws UserDefinedException;

    /**
     * We get the employee details which matchs with the id list we are
     * passing. This method is called from projectServiceImpl.
     *
     * @param employeeIdList - List of employee id
     *
     * @return List of employee details
     * @throws UserDefinedException 
     */
    public List<Employee> getEmployee(List<String> employeeIdList) throws UserDefinedException;

    /**
     * We display list of employees who are assigned to project.
     *
     * @param id - Employee id
     *
     * @return List of Employees
     * @throws UserDefinedException 
     */
    public List<String> getAssignedProject(String id) throws UserDefinedException;

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
     * @throws UserDefinedException 
     */
    public boolean checkIdExistOrNot(String id) throws UserDefinedException;

    /**
     * We check whether the address id exist in the table or not.
     *
     * @param addressId - Address id
     *
     * @return true if id exists
     *         else false
     * @throws UserDefinedException 
     */
    public boolean checkAddressIdExistOrNot(int addressId) throws UserDefinedException;

    /**
     * We check whether the year exist in the table or not.
     *
     * @param year - Year belongs to joining date.
     *
     * @return true if id exists
     *         else false
     * @throws UserDefinedException 
     */
    public boolean checkYearExistOrNot(String year) throws UserDefinedException;

    /**
     * We restore the deleted employee from the table.
     * 
     * @param id - Employee id
     *
     * @return true if values are restored successfully
     *         else false
     * @throws UserDefinedException 
     */
    public boolean restoreEmployee(String id) throws UserDefinedException;

    /**
     * We check whether the given id is deleted.
     * 
     * @param id - Employee id
     *
     * @return true if id is deleted
     *         else false
     * @throws UserDefinedException 
     */
    public boolean checkIdIsDeleted(String id) throws UserDefinedException;
    
    /**
     * Here we get the age of person by calculating with date of birth
     * 
     * @param dateOfBirth
     * 
     * @return age
     */
    public int getAge(LocalDate dateOfBirth);
    
    /**
     * We get list of employees who celebrate birthday on current day.
     * 
     * @return employee list
     * @throws UserDefinedException 
     */
    public List<Employee> getTodayEvents() throws UserDefinedException;
}