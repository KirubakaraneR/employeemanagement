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
 * pass the values to employee dao to perform CRUD operation in DB.
 *
 * @version 1.0 04-05-2021
 * @author Kirubakarane R
 */  
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDao employeeDao = new EmployeeDaoImpl();

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean createEmployee(String id, String name, LocalDate dateOfJoin,
            LocalDate dateOfBirth, int age, double salary, 
            String mobileNumber, String mailId) {
        Employee employee = new Employee(id, name, Date.valueOf(dateOfJoin), 
                Date.valueOf(dateOfBirth), age, salary, mobileNumber, mailId);
        return employeeDao.addOrUpdateEmployee(employee);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void addEmployeeAddress(String id, String doorNumber, 
            String street, String nagar, String city, String district,
            String state, String country, int pinCode, 
            String addressType) {
        Employee employee = employeeDao.getIndividualEmployee(id);
        List<Address> addressList = employee.getAddresses();
        Address address = new Address(doorNumber, street, nagar, 
                city, district, state, country, pinCode, addressType);
        addressList.add(address);
        employee.setAddresses(addressList);
        employeeDao.addOrUpdateEmployee(employee);
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
    public boolean checkValidYear(LocalDate date, String temp) {
        return ("doj".equals(temp)) ? (2010 < date.getYear()) ? true : false
                                    : ((LocalDate.now().getYear() - 60) < date.getYear()) ? true : false; 
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
        Employee employee = employeeDao.getIndividualEmployee(id);
        List<Address> employeeAddress = employee.getAddresses();
        List<String> employeeDetails = new ArrayList<String>();
        employeeDetails.add(employee.toString());
 
        for (Address addressValues : employeeAddress) {

            if (!addressValues.getIsDeleted()) {
                employeeDetails.add(addressValues.toString());
            }
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
        Employee employee = employeeDao.getIndividualEmployee(id);
        List<Address> employeeAddress = employee.getAddresses();
        List<String> employeeDetails = new ArrayList<String>();
 
        for (Address addressValues : employeeAddress) {
            employeeDetails.add(addressValues.toString());
        }
        return employeeDetails;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean updateEmployee(String id, String name, LocalDate dateOfJoin, 
            LocalDate dateOfBirth, int age, double salary, 
            String mobileNumber, String mailId) {
        Employee employee = employeeDao.getIndividualEmployee(id);
        employee.setId(id);
        String newName = (null == name) ? employee.getName()
                                        : name;
        Date newDateOfBirth = (null == dateOfBirth) ? employee.getDateOfBirth()
                                                    : Date.valueOf(dateOfBirth);
        Date newDateOfJoin = (null == dateOfJoin) ? employee.getDateOfJoin()
                                                  : Date.valueOf(dateOfJoin);
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
        return employeeDao.addOrUpdateEmployee(employee);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean updateAddress(String employeeId, String doorNumber, 
            String street, String nagar, String city, String district,
            String state, String country, int pinCode, 
            String addressType, int addressId) {
        Employee employee = employeeDao.getIndividualEmployee(employeeId);
        List<Address> addressList = employee.getAddresses();

        for (Address address : addressList) {

            if (addressId == address.getId()) {
                address.setId(addressId);
                address.setDoorNumber(doorNumber);
                address.setStreet(street);
                address.setNagar(nagar);
                address.setCity(city);
                address.setDistrict(district);
                address.setState(state);
                address.setCountry(country);
                address.setPinCode(pinCode);
                address.setAddressType(addressType);
            }
        }
        return employeeDao.addOrUpdateEmployee(employee);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean deleteEmployee(String id) {
        Employee employee = employeeDao.getIndividualEmployee(id);
        employee.setIsDeleted(true);
        List<Address> employeeAddress = employee.getAddresses();

        for (Address address : employeeAddress) {
            address.setIsDeleted(true);
        }
        return employeeDao.addOrUpdateEmployee(employee);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean deleteAddress(String id) {
        Employee employee = employeeDao.getIndividualEmployee(id);
        List<Address> employeeAddress = employee.getAddresses();

        for (Address address : employeeAddress) {
            address.setIsDeleted(true);
        }
        return employeeDao.addOrUpdateEmployee(employee);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean deleteIndividualAddress(String employeeId, int addressId) {
        Employee employee = employeeDao.getIndividualEmployee(employeeId);
        List<Address> employeeAddress = employee.getAddresses();

        for (Address address : employeeAddress) {
    
            if (addressId == address.getId()) {
                address.setIsDeleted(true);
            }
        }
        return employeeDao.addOrUpdateEmployee(employee);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void assignProjectToEmployee(String id, List<Integer> projectIdList) {
        ProjectService projectService = new ProjectServiceImpl();
        List<Project> project = projectService.getProject(projectIdList);
        Employee employee = employeeDao.getIndividualEmployee(id);
        List<Project> assignedProject = employee.getProjects();
        assignedProject.addAll(project);
        employee.setId(id);
        employee.setProjects(assignedProject);
        employeeDao.addOrUpdateEmployee(employee);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void unassignProjectFromEmployee(String id, List<Integer> projectIdList)  {
        ProjectService projectService = new ProjectServiceImpl();
        List<Project> project = projectService.getProject(projectIdList);
        System.out.println(project);
        Employee employee = employeeDao.getIndividualEmployee(id);
        List<Project> assignedProject = employee.getProjects();

        for (int index1 = 0; index1 < project.size(); index1++) {
      
            for (int index2 = 0; index2 < assignedProject.size(); index2++) {

                if ((project.get(index1)).getId() == (assignedProject.get(index2)).getId()) { 
                    assignedProject.remove(index2);
                }
            }
        }
        employee.setId(id);
        employee.setProjects(assignedProject);
        employeeDao.addOrUpdateEmployee(employee);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<List<Integer>> getAvailableProjectId(String id, List<Integer> projectIdList) {
        Employee employee = employeeDao.getIndividualEmployee(id);
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
        Employee employee = employeeDao.getIndividualEmployee(id);
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
        int count = employeeDao.getIdCount(id);
        return (0 == count) ? false : true;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean checkAddressIdExistOrNot(int addressId) {
        int count = employeeDao.getAddressCount(addressId);
        return (0 == count) ? false : true;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean checkYearExistOrNot(String year) {
        int count = employeeDao.getYearCount(Integer.parseInt(year));
        return (0 == count) ? false : true;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean restoreEmployee(String id) {
        Employee employee = employeeDao.getIndividualEmployee(id);
        employee.setIsDeleted(false);
        List<Address> addressList = employee.getAddresses();
 
        for (Address address : addressList) {
            address.setIsDeleted(false);
        }
        return employeeDao.addOrUpdateEmployee(employee);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean checkIdIsDeleted(String id) {
        int count = employeeDao.getDeletedIdCount(id);
        return (0 == count) ? false : true;
    }
}