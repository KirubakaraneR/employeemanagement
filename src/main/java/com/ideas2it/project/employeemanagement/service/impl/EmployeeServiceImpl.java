package com.ideas2it.project.employeemanagement.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.regex.Pattern;

import com.ideas2it.project.employeemanagement.dao.EmployeeDao;
import com.ideas2it.project.employeemanagement.dao.impl.EmployeeDaoImpl;
import com.ideas2it.project.employeemanagement.model.Address;
import com.ideas2it.project.employeemanagement.model.Employee;
import com.ideas2it.project.employeemanagement.service.EmployeeService;
import com.ideas2it.project.exceptions.UserDefinedException;
import com.ideas2it.project.logger.Loggers;
import com.ideas2it.project.projectmanagement.model.Project;
import com.ideas2it.project.projectmanagement.service.ProjectService;
import com.ideas2it.project.projectmanagement.service.impl.ProjectServiceImpl;

/**
 * We perform create and update operation to pojo and also
 * pass the values to employee dao to perform CRUD operation in DB.
 *
 * @version 1.0 04-05-2021
 * @author Kirubakarane R
 */  
public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeDao employeeDao = new EmployeeDaoImpl();
	private Loggers log = new Loggers(EmployeeServiceImpl.class);

	/**
	 * {@inheritdoc}
	 * @throws UserDefinedException 
	 */
	@Override
	public boolean createEmployee(String id, String name, LocalDate dateOfJoin,
			LocalDate dateOfBirth, int age, double salary, 
			String mobileNumber, String mailId, String password) throws UserDefinedException {
		Employee employee = new Employee(id, name, Date.valueOf(dateOfJoin), 
				Date.valueOf(dateOfBirth), age, salary, mobileNumber, mailId, 
				password);
		boolean isChecked = employeeDao.addOrUpdateEmployee(employee);

		if (true == isChecked) {
			log.logDebug("A new employee is added");
			log.logDebug(employee.toString());
		} else {
			log.logInfo("There is an issue in creating new employee.");
		}
		return isChecked;
	}

	/**
	 * {@inheritdoc}
	 * @throws UserDefinedException 
	 */
	@Override
	public void addEmployeeAddress(String id, String doorNumber, 
			String street, String nagar, String city, String district,
			String state, String country, int pinCode, 
			String addressType) throws UserDefinedException {
		Employee employee = employeeDao.getIndividualEmployee(id);
		List<Address> addressList = employee.getAddresses();
		Address address = new Address(doorNumber, street, nagar, 
				city, district, state, country, pinCode, addressType);
		boolean isChecked;
		try {
			addressList.add(address);
			employee.setAddresses(addressList);
			isChecked = employeeDao.addOrUpdateEmployee(employee);
			if (true == isChecked) {
				log.logDebug("Address is added for the employee " + id);
				log.logDebug(address.toString());
			} else {
				log.logInfo("THere is an issue in adding address to the employee " + id);
			}
		} catch (InputMismatchException e) {
			log.logError("Input values doesn't match", e);
		}
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
				: ((LocalDate.now().getYear() - 60) < date.getYear()) ? true 
						: false; 
	}

	/**
	 * {@inheritdoc}
	 * @throws UserDefinedException 
	 */
	@Override
	public List<Employee> getAllEmployeeForDisplay() throws UserDefinedException {
		List<Employee> employeeList = employeeDao.getAllEmployee();
		return employeeList;
	}

	/**
	 * {@inheritdoc}
	 * @throws UserDefinedException 
	 */
	@Override
	public Employee getIndividualEmployeeForDisplay(String id) throws UserDefinedException {
		Employee employee = employeeDao.getIndividualEmployee(id);
		return employee;
	}

	/**
	 * {@inheritdoc}
	 * @throws UserDefinedException 
	 */
	@Override
	public List<String> getEmployeeOfParticularYearForDisplay(String year) throws UserDefinedException {
		List<Employee> employeeList = employeeDao.getAllEmployeeOfParticularYear(year);
		List<String> employeeDetails = new ArrayList<String>();

		for (Employee employeeValues : employeeList) {
			employeeDetails.add(employeeValues.toString());
		}
		return employeeDetails;
	}

	/**
	 * {@inheritdoc}
	 * @throws UserDefinedException 
	 */
	@Override
	public List<Address> getIndividualAddressForDisplay(int addressId, String employeeId) throws UserDefinedException {
		Employee employee = employeeDao.getIndividualEmployee(employeeId);
		List<Address> employeeAddress = employee.getAddresses();
		List<Address> addressList = new ArrayList<Address>();

		for (Address addressValues : employeeAddress) {

			if (addressId == addressValues.getId()) {
				addressList.add(addressValues);
			}
		}
		return addressList;
	}

	/**
	 * {@inheritdoc}
	 * @throws UserDefinedException 
	 */
	@Override
	public boolean updateEmployee(String id, String name, LocalDate dateOfJoin, 
			LocalDate dateOfBirth, int age, double salary, 
			String mobileNumber, String mailId, String password) 
					throws UserDefinedException {
		boolean isChecked = false;
		
		try {
			Employee employee = employeeDao.getIndividualEmployee(id);
			employee.setId(id);
			employee.setName(name);
			employee.setDateOfBirth(Date.valueOf(dateOfBirth));
			employee.setDateOfJoin(Date.valueOf(dateOfJoin));
			employee.setAge(getAge(dateOfBirth));
			employee.setSalary(salary);
			employee.setMobileNumber(mobileNumber);
			employee.setMailId(mailId);
			employee.setPassword(password);
			isChecked = employeeDao.addOrUpdateEmployee(employee);
			
			if (true == isChecked) {
				log.logDebug("The details have been updated to the employee " + id);
				log.logDebug(employee.toString());
			} else {
				log.logInfo("There is an issue in adding employee.");
			}
		} catch (InputMismatchException e) {
			log.logError("Input values doesnt match", e);
		}
		return isChecked;
	}

	/**
	 * {@inheritdoc}
	 * @throws UserDefinedException 
	 */
	@Override
	public boolean updateAddress(String employeeId, String doorNumber, 
			String street, String nagar, String city, String district,
			String state, String country, int pinCode, 
			String addressType, int addressId) throws UserDefinedException {
		Employee employee = employeeDao.getIndividualEmployee(employeeId);
		List<Address> addressList = employee.getAddresses();
		boolean isChecked = false;
       
		try {
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
			isChecked = employeeDao.addOrUpdateEmployee(employee);
			
			if (true == isChecked) {
				log.logDebug("The address details have been updated to the employee " + employeeId);
				log.logDebug(employee.getAddresses().toString());
			} else {
				log.logInfo("There is an issue in adding address for employee " + employeeId);
			}
		} catch (InputMismatchException e) {
			log.logError("Input values doesnt match", e);
		}
		return isChecked;
	}

	/**
	 * {@inheritdoc}
	 * @throws UserDefinedException 
	 */
	@Override
	public boolean deleteEmployee(String id) throws UserDefinedException {
		Employee employee = employeeDao.getIndividualEmployee(id);
		employee.setIsDeleted(true);
		List<Address> employeeAddress = employee.getAddresses();
		List<Project> project = employee.getProjects();

		for (Address address : employeeAddress) {
			address.setIsDeleted(true);
		}
		project.clear();
		employee.setProjects(project);
		boolean isChecked = employeeDao.addOrUpdateEmployee(employee);

		if (true == isChecked) {
			log.logDebug("Employee " + id + " is deleted successfully.");
		} else {
			log.logInfo("Issue in deleting employee");
		}

		return isChecked;
	}

	/**
	 * {@inheritdoc}
	 * @throws UserDefinedException 
	 */
	@Override
	public boolean deleteAddress(String id) throws UserDefinedException {
		Employee employee = employeeDao.getIndividualEmployee(id);
		List<Address> employeeAddress = employee.getAddresses();

		for (Address address : employeeAddress) {
			address.setIsDeleted(true);
		}
		boolean isChecked = employeeDao.addOrUpdateEmployee(employee);

		if (true == isChecked) {
			log.logDebug("All Address of Employee " + id + " deleted successfully.");
		} else {
			log.logInfo("Issue in deleting address.");
		}
		return isChecked;
	}

	/**
	 * {@inheritdoc}
	 * @throws UserDefinedException 
	 */
	@Override
	public boolean deleteIndividualAddress(String employeeId, int addressId) throws UserDefinedException {
		Employee employee = employeeDao.getIndividualEmployee(employeeId);
		List<Address> employeeAddress = employee.getAddresses();

		for (Address address : employeeAddress) {

			if (addressId == address.getId()) {
				address.setIsDeleted(true);
			}
		}
		boolean isChecked = employeeDao.addOrUpdateEmployee(employee);

		if (true == isChecked) {
			log.logDebug("Individual address " + addressId + " deleted successfully.");
		} else {
			log.logInfo("There is an issue in deleting address.");
		}
		return isChecked;
	}

	/**
	 * {@inheritdoc}
	 * @throws UserDefinedException 
	 */
	@Override
	public void assignProjectToEmployee(String id, List<Integer> projectIdList) throws UserDefinedException {
		ProjectService projectService = new ProjectServiceImpl();
		List<Project> project = projectService.getProject(projectIdList);
		Employee employee = employeeDao.getIndividualEmployee(id);
		List<Project> assignedProject = employee.getProjects();
		assignedProject.addAll(project);
		try {
			employee.setId(id);
			employee.setProjects(assignedProject);
		} catch (InputMismatchException e) {
			log.logDebug("The datas that we set into bean class doesn't match.");
			log.logError(e);
		}
		employeeDao.addOrUpdateEmployee(employee);
	}

	/**
	 * {@inheritdoc}
	 * @throws UserDefinedException 
	 */
	@Override
	public void unassignProjectFromEmployee(String id, List<Integer> projectIdList) throws UserDefinedException  {
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
		try {
			employee.setId(id);
			employee.setProjects(assignedProject);
		} catch (InputMismatchException e) {
			log.logDebug("The datas that we set into bean class doesn't match.");
			log.logError(e);
		}
		employeeDao.addOrUpdateEmployee(employee);
	}

	/**
	 * {@inheritdoc}
	 * @throws UserDefinedException 
	 */
	@Override
	public List<List<Integer>> getAvailableProjectId(String id, List<Integer> projectIdList) throws UserDefinedException {
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
	 * @throws UserDefinedException 
	 */
	@Override
	public List<Employee> getEmployee(List<String> employeeIdList) throws UserDefinedException {
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
	 * @throws UserDefinedException 
	 */
	@Override
	public List<String> getAssignedProject(String id) throws UserDefinedException {
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
	 * @throws UserDefinedException 
	 */
	@Override
	public boolean checkIdExistOrNot(String id) throws UserDefinedException {
		int count = employeeDao.getIdCount(id);
		return (0 == count) ? false : true;
	}

	/**
	 * {@inheritdoc}
	 * @throws UserDefinedException 
	 */
	@Override
	public boolean checkAddressIdExistOrNot(int addressId) throws UserDefinedException {
		int count = employeeDao.getAddressCount(addressId);
		return (0 == count) ? false : true;
	}

	/**
	 * {@inheritdoc}
	 * @throws UserDefinedException 
	 */
	@Override
	public boolean checkYearExistOrNot(String year) throws UserDefinedException {
		int count = employeeDao.getYearCount(Integer.parseInt(year));
		return (0 == count) ? false : true;
	}

	/**
	 * {@inheritdoc}
	 * @throws UserDefinedException 
	 */
	@Override
	public boolean restoreEmployee(String id) throws UserDefinedException {
		Employee employee = employeeDao.getIndividualEmployee(id);
		employee.setIsDeleted(false);
		List<Address> addressList = employee.getAddresses();

		for (Address address : addressList) {
			address.setIsDeleted(false);
		}
		boolean isChecked = employeeDao.addOrUpdateEmployee(employee);
		
		if (true == isChecked) {
			log.logDebug("Employee " + id + " is restored successfully.");
		} else {
			log.logDebug("Issue in restoring employee " + id);
			log.logInfo("This issue can occur if the id "+ id + " is not present in the DB.");
		}
		return isChecked;
	}

	/**
	 * {@inheritdoc}
	 * @throws UserDefinedException 
	 */
	@Override
	public boolean checkIdIsDeleted(String id) throws UserDefinedException {
		int count = employeeDao.getDeletedIdCount(id);
		return (0 == count) ? false : true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getAge(LocalDate dateOfBirth) {
		return Period.between(dateOfBirth, LocalDate.now()).getYears();
	}

	/**
	 * {@inheritDoc}
	 * @throws UserDefinedException 
	 */
	@Override
	public List<Employee> getTodayEvents() throws UserDefinedException {
		List<Employee> employeeList = employeeDao.getAllEmployee();
		List<Employee> employeeValues = new ArrayList<Employee>();
		int currentMonth = LocalDate.now().getMonthValue();
		int currentDate = LocalDate.now().getDayOfMonth();

		for (Employee employee : employeeList) {
			LocalDate dateOfBirth = employee.getDateOfBirth().toLocalDate();
			int birthMonth = dateOfBirth.getMonthValue();
			int birthDate = dateOfBirth.getDayOfMonth();

			if ((currentMonth == birthMonth) && (currentDate == birthDate)) {
				String id = employee.getId();
				int age = getAge(dateOfBirth);
				Employee employeeData = employeeDao.getIndividualEmployee(id);
				employeeData.setAge(age);
				employeeDao.addOrUpdateEmployee(employeeData);
				employee.setAge(age);
				employeeValues.add(employee);
			}
		}
		return employeeValues;
	}
}