package com.ideas2it.employeemanagement.service.impl;

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
        Employee employee = new Employee(id, name, dateOfJoin, dateOfBirth, 
                age, salary, mobileNumber, mailId, employeeAddress);
        return employeeDao.addEmployee(employee);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void createEmployeeAddress(String id, String doorNumber, 
            String street, String nagar, String city, String district,
            String state, String country, int pinCode, 
            String addressType) {
        employeeAddress.add(new Address(id, doorNumber, street, nagar, 
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

    /**
     * {@inheritdoc}
     */
    @Override
    public List<String> getAllEmployeeForDisplay() {
        List<Employee> employeeList = employeeDao.getAllEmployee();
        List<String> employeeDetails = new ArrayList<String>();

        for (Employee employeeValues : employeeList) {
            employeeDetails.add(employeeValues.toString());
        }
        return employeeDetails;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<String> getIndividualEmployeeForDisplay(String id) {
        Employee employee 
                = employeeDao.getIndividualEmployee(id);
        employeeAddress = employee.getAddress();
        List<String> employeeDetails = new ArrayList<String>();
        employeeDetails.add(employee.toString());
 
        for (Address addressValues : employeeAddress) {
            employeeDetails.add(addressValues.toString());
        }
        return employeeDetails;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<String> getEmployeeOfParticularYearForDisplay(String year) {
        List<Employee> employeeList = employeeDao.getAllEmployeeOfParticularYear(year);
        List<String> employeeDetails = new ArrayList<String>();

        for (Employee employeeValues : employeeList) {
            employeeDetails.add(employeeValues.toString());
        }
        return employeeDetails;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<String> getIndividualAddressForDisplay(String id) {
        Employee employee 
                = employeeDao.getIndividualEmployee(id);
        employeeAddress = employee.getAddress();
        List<String> employeeDetails = new ArrayList<String>();
 
        for (Address addressValues : employeeAddress) {
            employeeDetails.add(addressValues.toString());
        }
        employeeAddress.clear();
        return employeeDetails;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean updateEmployee(String id, String name, LocalDate dateOfJoin, 
            LocalDate dateOfBirth, int age, double salary, 
            String mobileNumber, String mailId) {
        Employee employee 
                = employeeDao.getIndividualEmployee(id);
        employee.setId(id);
        String newName = (null == name) ? employee.getName()
                                          : name;
        LocalDate newDateOfBirth = (null == dateOfBirth) ? employee.getDateOfBirth()
                                                      : dateOfBirth;
        LocalDate newDateOfJoin = (null == dateOfJoin) ? employee.getDateOfJoin()
                                                      : dateOfJoin;
        int newAge = (0 == age) ? employee.getAge()
                                      : age;
        double newSalary = (0.0 == salary) ? employee.getSalary()
                                            : salary;
        String newMobileNumber = (null == mobileNumber) ? employee.getMobileNumber()
                                                          : mobileNumber;
        String newMailId = (null == mailId) ? employee.getMailId()
                                              : mailId;
        employee.setName(newName);
        employee.setDateOfBirth(newDateOfBirth);
        employee.setDateOfJoin(newDateOfJoin);
        employee.setAge(newAge);
        employee.setSalary(newSalary);
        employee.setMobileNumber(newMobileNumber);
        employee.setMailId(newMailId);
        return employeeDao.updateEmployee(employee);
    }
  
    /**
     * {@inheritdoc}
     */
    @Override
    public boolean addAddress(String id, String doorNumber, 
            String street, String nagar, String city, String district,
            String state, String country, int pinCode, 
            String addressType) {
        Employee employee 
                = employeeDao.getIndividualEmployee(id);
        employeeAddress.add(new Address(id, doorNumber, street, nagar, 
                city, district, state, country, pinCode, addressType));
        employee.setAddress(employeeAddress);
        return employeeDao.addAddress(employee);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean updateAddress(String employeeId, String doorNumber, 
            String street, String nagar, String city, String district,
            String state, String country, int pinCode, 
            String addressType, int addressId) {
        Address address = employeeDao.getAddress(employeeId, addressId);
        address.setEmployeeId(employeeId);
        address.setAddressId(addressId);
        address.setDoorNumber(doorNumber);
        address.setStreet(street);
        address.setNagar(nagar);
        address.setCity(city);
        address.setDistrict(district);
        address.setState(state);
        address.setCountry(country);
        address.setPinCode(pinCode);
        address.setAddressType(addressType);
        return employeeDao.updateAddress(address);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean deleteEmployee(String id) {
        return employeeDao.deleteEmployee(id);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean deleteAddress(String id) {
        return employeeDao.deleteAddress(id);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean deleteIndividualAddress(int id) {
        return employeeDao.deleteIndividualAddress(id);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void assignProjectToEmployee(String id, List<Integer> projectIdList) {
        ProjectService projectService = new ProjectServiceImpl();
        List<Project> project = projectService.getProject(projectIdList);
        Employee employee = employeeDao.getEmployeeProject(id);
        employee.setId(id);
        employee.setProjects(project);
        employeeDao.assignProjectToEmployee(employee);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void unassignProjectFromEmployee(String id, List<Integer> projectIdList)  {
        ProjectService projectService = new ProjectServiceImpl();
        List<Project> project = projectService.getProject(projectIdList);
        Employee employee = employeeDao.getEmployeeProject(id);
        employee.setId(id);
        employee.setProjects(project);
        employeeDao.unassignProjectFromEmployee(employee);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<List<Integer>> getAvailableProjectId(String id, List<Integer> projectIdList) {
        Employee employee = employeeDao.getEmployeeProject(id);
        List<Project> projectValues = employee.getProjects();
        List<Integer> projectId = new ArrayList<Integer>();
        List<Integer> projectIdForAssign = new ArrayList<Integer>();
        List<Integer> projectIdForUnassign = new ArrayList<Integer>();
        List<List<Integer>> project = new ArrayList<List<Integer>>();
        boolean isChecked;

        if (null == projectValues) {
            Collections.copy(projectIdForAssign, projectIdList);
        } else {
  
            for (Project projectList : projectValues) {
                projectId.add(projectList.getId());
            }

            for (Integer unassignedId : projectIdList) {
                isChecked = false;

                for (Integer assignedId : projectId) {

                    if (unassignedId == assignedId) {
                        isChecked = true;
                        projectIdForUnassign.add(unassignedId);
                    }
                }
                
                if (false == isChecked) {
                    projectIdForAssign.add(unassignedId);
                }
            }
        }
        project.add(projectIdForAssign);
        project.add(projectIdForUnassign);
        return project;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<Employee> getEmployee(List<String> employeeIdList) {
        List<Employee> employeeList = employeeDao.getAllEmployee();
        List<Employee> employeeDetails = new ArrayList<Employee>();

        for (Employee employee : employeeList) {
        
            if (employeeIdList.contains(employee.getId())) {
                employeeDetails.add(employee);
            }    
        }
        return employeeDetails;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<String> getAssignedProject(String id) {
        Employee employee = employeeDao.getEmployeeProject(id);
        List<Project> projectList = employee.getProjects();
        List<String> projectDetails = new ArrayList<String>();

        for (Project project : projectList) {
            projectDetails.add(project.toString());
        }
        return projectDetails;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean checkIsEmpty(List<Integer> projectIdList) {
        return projectIdList.isEmpty();
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean checkIdExistOrNot(String id) {
        int count = employeeDao.getEmployeeIdCount(id);
        return (0 == count) ? false : true;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean checkAddressIdExistOrNot(int id) {
        int count = employeeDao.getAddressIdCount(id);
        return (0 == count) ? false : true;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean checkYearExistOrNot(String year) {
        int count = employeeDao.getYearCount(year);
        return (0 == count) ? false : true;
    }
}