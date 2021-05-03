package com.ideas2it.employeemanagement.view;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.Scanner;

import com.ideas2it.employeemanagement.controller.EmployeeController;

/**
 * We get the input values from the user and perform display operation.
 */
public class EmployeeView {
    private Scanner scanner = new Scanner(System.in);
    private EmployeeController employeeController 
            = new EmployeeController();

    /**
     * We get input from user and select an option which we need to 
     * perform. 
     * Options such as 1.create, 2.display, 3.update, 4.delete,
     * 5.assign project, 6.unassign project and 7.exit.
     */
    public void selectCrudOptions() {
        String printStatementForCrudOptions = "\nSelect an option you need to perform"
                + "\n1.Create\n2.Display\n3.Update\n4.Delete"
                + "\n5.Assign Project\n6.Unassign Project"
                + "\n7.Display assigned project\n8.Exit";                 
        int userOption;

        do {
            System.out.println(printStatementForCrudOptions);
            System.out.print("\nENTER AN OPTION: ");
            userOption = scanner.nextInt();

            switch (userOption) { 
                case 1:  createEmployee();
                         break;
                case 2:  chooseDisplayOption();
                         break;
                case 3:  chooseUpdateOption();
                         break;
                case 4:  chooseDeleteOption();
                         break;
                case 5:  assignProjectToEmployee();
                         break;
                case 6:  unassignProjectFromEmployee(); 
                         break;
                case 7:  displayAssignedProject();
                         break;
                case 8:  System.out.println("\nYou are back to home page");
                         break;
                default: System.out.println("\nWARNING: Invalid Entry");
                         continue;
            }
        } while (8 != userOption);
    }

    /**
     * We create employee details for all employees.
     * Employee details such as id, name, age, mobile number, mail id, 
     * date of birth and date of join.
     */
    public void createEmployee() {
        LocalDate dateOfJoin = getEmployeeDateOfJoin();
        String id = getValidEmployeeId(dateOfJoin.getYear());
        String name = getEmployeeName();
        LocalDate dateOfBirth = getEmployeeDateOfBirth();
        int age = calculateEmployeeAge(dateOfBirth);
        double salary = getEmployeeSalary();
        String mobileNumber = getValidEmployeeMobileNumber();
        String mailId = getValidEmployeeMailId();
 
        if (employeeController.createEmployee(id, name, dateOfJoin,
                dateOfBirth, age, salary, mobileNumber, mailId)) {
            System.out.println("Datas added successfully.");
        } else {
            System.out.println("There is an issue in adding datas.");
        }
        createEmployeeAddress(id);
    }

    /**
     * We get the joining date of an employee.
     *
     * @return Employee date of join
     */
    public LocalDate getEmployeeDateOfJoin() {
        System.out.println("\n[NOTE: Use this format YYYY-MM-DD]");
        System.out.print("\nDATE OF JOIN: ");
        return LocalDate.parse(scanner.next());
    }
 
    /**
     * We get the id for an employee.
     *
     * @param year - Year that belongs to joining date
     *
     * @return Employee id
     */
    public String getEmployeeId(int year) {
        System.out.println("\n[NOTE: Enter only 3 digit values]");
        System.out.print("\nEMPLOYEE ID: ");
        return "I".concat(String.valueOf(year).substring(2))
                .concat(scanner.next());
    }

    /**
     * We check whether an employee id is in valid format or not.
     * If the entered employee id is in valid format it returns the id
     * or else it will ask you to re-enter the employee id.
     *
     * @param year - Year that belongs to joining date
     *
     * @return Employee id
     */
    public String getValidEmployeeId(int year) {
        String id = getEmployeeId(year);

        while (!employeeController.checkValidEmployeeId(id)) {
            System.out.println("\nWARNING: The employee id is not in valid "
                    + "format.\nPlease give id in valid format");
            id = getEmployeeId(year);
        }
        return id;
    }
   
    /**
     * We get only the year from an employee date of join.
     *
     * @return Year that belongs to joining date
     */
    public String getYearOfJoiningDate() {
        return String.valueOf(getEmployeeDateOfJoin().getYear());
    }

    /**
     * We get the name of an employee.
     *
     * @return Employee name
     */
    public String getEmployeeName() {
        System.out.print("\nEMPLOYEE NAME: ");
        return scanner.skip("[\r\n]+").nextLine();
    }    

    /**
     * We get the date of birth of an employee.
     *
     * @return Employee date of birth
     */
    public LocalDate getEmployeeDateOfBirth() {
        System.out.println("\n[NOTE: Use this format YYYY-MM-DD]");
        System.out.print("\nDATE OF BIRTH: ");
        return LocalDate.parse(scanner.next());
    }

    /** 
     * We calculate the age of an employee.
     *
     * @param dateOfBirth - date of birth of an employee
     *
     * @return Age of an employee
     */
    public int calculateEmployeeAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    /**
     * We get the valid mobile number of an employee.
     * It returns the mobile number if the given number is in valid form
     * or else it will ask you to re-enter the mobile number.
     *
     * @return Employee mobile number
     */
    public String getValidEmployeeMobileNumber() {
        String mobileNumber = getEmployeeMobileNumber();
     
        while (!employeeController.checkValidEmployeeMobileNumber(mobileNumber)) {
            System.out.println("\nWARNING: The mobile number is"
                    + " not in valid format.\nPlease give the number"
                    + "in valid format");
            mobileNumber = getEmployeeMobileNumber();
        }
        return mobileNumber;
    }

    /**
     * We get the mobile number of the employee.
     *
     * @return Employee mobile number
     */
    public String getEmployeeMobileNumber() {
        System.out.print("\nMOBILE NUMBER: ");
        return scanner.next();
    }

    /**
     * We get the valid mail id of an employee.
     * It return the mail id if the given id is in valid format or else
     * it will ask you to re-enter the mail id.
     *
     * @return Employee mail id
     */
    public String getValidEmployeeMailId() {
        String mailId = getEmployeeMailId();

        while (!employeeController.checkValidEmployeeMailId(mailId)) {
            System.out.println("\nWARNING: The mail id is not"
                    + " in valid format");
            mailId = getEmployeeMailId();
        }
        return mailId;
    }

    /**
     * We get the mail id of an employee.
     *
     * @return Employee mail id
     */
    public String getEmployeeMailId() {
        System.out.print("\nMAIL ID: ");
        return scanner.next();
    }

    /**
     * We get the salary of an employee.
     *
     * @return Employee salary
     */
    public double getEmployeeSalary() {
        System.out.print("\nSALARY: ");
        return scanner.nextDouble();
    }

    /**
     * We get the address details of an employee to create address.
     *
     * @param id - Employee id
     */
    public void createEmployeeAddress(String id) {
        int userOption;    
        
        do { 
            System.out.println("Which type of address you need to add?"
                    + "\n1.Permanent\n2.Temporary");
            String addressType = (1 == scanner.nextInt()) ? "permanent"
                                                          : "temporary";
            String doorNumber = getDoorNumber();
            String street = getStreet();
            String nagar = getNagar();
            String city = getCity();
            String district = getDistrict();
            String state = getState();
            String country = getCountry();
            int pinCode = getPinCode(); 
            employeeController.addEmployeeAddress(id, doorNumber, 
                    street, nagar, city, district, state, country, 
                    pinCode, addressType);
            System.out.println("\nPress 1 to continue address"
                    + "\n      0 to exit");
            userOption = scanner.nextInt();
        } while (1 == userOption);   
    }

    /**
     * We get the home door number for address details.
     *
     * @return Door number
     */
    public String getDoorNumber() {
        System.out.print("\nDOOR NUMBER: ");
        return scanner.next();
    }

    /**
     * We ge the street name for address details.
     *
     * @return Street
     */
    public String getStreet() {
        System.out.print("\nSTREET NAME: ");
        return scanner.skip("[\r\n]+").nextLine();
    }
  
    /**
     * We get the nagar or area name for address details.
     *
     * @return Nagar
     */
    public String getNagar() {
        System.out.print("\nNAGAR/AREA NAME: ");
        return scanner.nextLine();
    }

    /**
     * We get the city name for address details.
     *
     * @return City
     */
    public String getCity() {
        System.out.print("\nCITY NAME: ");
        return scanner.nextLine();
    }
 
    /** 
     * We get the district name for address details.
     *
     * @return District
     */
    public String getDistrict() {
        System.out.print("\nDISTRICT NAME: ");
        return scanner.nextLine();
    }

    /**
     * We get the state name for address details.
     *
     * @return State 
     */
    public String getState() {
        System.out.print("\nSTATE NAME: ");
        return scanner.nextLine();
    }

    /**
     * We get country name for address details.
     *
     * @return Country
     */
    public String getCountry() {
        System.out.print("\nCOUNTRY NAME: ");
        return scanner.nextLine();
    }
 
    /**
     * We get pincode for address details.
     *
     * @return Pincode
     */
    public int getPinCode() {
        System.out.print("\nPINCODE: ");
        return scanner.nextInt();
    }

    /**
     * We select an option for display.
     * The available options are 1.all employee, 2.individual employee,
     * 3.employee belong to specific year, 4.individual address, 5.exit.
     */
    public void chooseDisplayOption() {
        String printStatementForDisplayOptions = "\nSelect an option you "
                + "need to perform\n1.All employee\n2.Individual employee"
                + "\n3.Employee belong to particular year"
                + "\n4.Individual employee address\n5.Exit";
        int userOption;
        
        do {
            System.out.println(printStatementForDisplayOptions);
            userOption = scanner.nextInt();
            
            switch (userOption) {
                case 1:  displayAllEmployee();
                         break;
                case 2:  displayIndividualEmployee();
                         break;
                case 3:  displayEmployeeOfParticularYear();
                         break;
                case 4:  displayAddressOfIndividualEmployee();
                         break;
                case 5:  break;
                default: System.out.println("\nWARNING: Invalid Entry");
                         continue;
            }
        } while (5 != userOption);
    }

    /**
     * We display all the datas of employees.
     */
    public void displayAllEmployee() {
        List<String> allDetails = employeeController
                .getAllEmployeeForDisplay();
  
        for (String values : allDetails) {
            System.out.println(values);
        }
    }

    /**
     * We display all datas of an individual employee.
     */
    public void displayIndividualEmployee() {
        System.out.print("\nEnter the employee id which you wish to"
                + " display: ");
        String id = scanner.next();

        if (employeeController.checkIdExistOrNot(id)) {
            List<String> allDetails = employeeController
                    .getIndividualEmployeeForDisplay(id);
  
            for (String values : allDetails) {
                System.out.println(values);
            }
        } else {
            System.out.println("\nThe given employee id is not available.");
        }
    }

    /**
     * We display all employee datas who belong to a particular year.
     */
    public void displayEmployeeOfParticularYear() {
        System.out.print("\nEnter the year to display employee data: ");
        String year = scanner.next();
        
        if (employeeController.checkYearExistOrNot(year)) {
            List<String> allDetails = employeeController
                    .getEmployeeOfParticularYearForDisplay(year.substring(2));
  
            for (String values : allDetails) {
                System.out.println(values);
            }
        } else {
            System.out.println("\nThe given year is not available.");
        }
    }

    /**
     * We display the address details of individual employee.
     */
    public void displayAddressOfIndividualEmployee() {
        System.out.print("\nEnter the employee id to display the address: ");
        String id = scanner.next();

        if(employeeController.checkIdExistOrNot(id)) {
            List<String> allDetails = employeeController
                    .getIndividualAddressForDisplay(id);
  
            for (String values : allDetails) {
                System.out.println(values);
            }
        } else {
            System.out.println("\nThe given employee id is not available.");
        }
    }

    /**
     * We select an option for update.
     * The available options are 1.update employee and 2.update address
     */
    public void chooseUpdateOption() {
        String printStatementForUpdateOption = "\nSelect an option you need to perform"
                + "\n1.Employee details\n2.Employee address\n3.Exit";
        int userOption;
  
        do {
            System.out.println(printStatementForUpdateOption);
            userOption = scanner.nextInt();
            switch (userOption) {
                case 1:  updateEmployee();
                         break;
                case 2:  chooseUpdateAddressOptions();
                         break;
                case 3:  break;
                default: System.out.println("\nWARNING: Invalid Entry");
                         continue;
            }
        } while (3 != userOption);
    }

    /**
     * We update details of an employee and there are options available to
     * update employee details.
     * The options available are 1.name, 2.date of birth, 3.date of join,
     * 4.mobile number, 5.mail id, 6.salary, 7.exit
     */
    public void updateEmployee() {
        String printStatementForUpdateEmployee = "\nSelect an option "
                + "for update employee details\n1.Name"
                + "\n2.Date of birth\n3.Date of join"
                + "\n4.Mobile number\n5.Mail id\n6.Salary"
                + "\n7.Exit"; 
        int userOption, age = 0;
        String id, name = null, mobileNumber = null, mailId = null;
        LocalDate dateOfBirth = null, dateOfJoin = null;
        Double salary = 0.0;
        System.out.print("\nEnter the employee id which you need"
                    + " to update:");
        id = scanner.next();

        if (employeeController.checkIdExistOrNot(id)) {

            do {
                System.out.println(printStatementForUpdateEmployee);
                userOption = scanner.nextInt();
            
                switch (userOption) {
                    case 1:  name = getEmployeeName();
                             break;
                    case 2:  dateOfBirth = getEmployeeDateOfBirth();
                             age = calculateEmployeeAge(dateOfBirth);
                             break;
                    case 3:  dateOfJoin = getEmployeeDateOfJoin();
                             break;
                    case 4:  mobileNumber = getValidEmployeeMobileNumber();
                             break;
                    case 5:  mailId = getValidEmployeeMailId();
                             break;
                    case 6:  salary = getEmployeeSalary();
                             break;
                    case 7:  break;
                    default: System.out.println("\nWARNING: Invalid Entry");
                             continue;
                }
            } while (7 != userOption);
        
            if (employeeController.updateEmployee(id, name, dateOfJoin,
                    dateOfBirth, age, salary, mobileNumber, mailId)) {
                System.out.println("Datas are updated successfully");
            } else {
                System.out.println("There is an issue in updating values.");
            }
        } else {
            System.out.println("\nThe given employee id is not available.");
        }
    }

    /**
     * We have options to update address.
     * Options such as 1.add, 2.update, 3.exit.
     * Here add option is to add new address to an employee.
     */
    public void chooseUpdateAddressOptions() {
        String printStatementForUpdateOption = "\nDo you wanna add new address or update "
                + "the existing address?\n1.Add\n2.Update\n3.Exit";
        int userOption;

        do {
            System.out.println(printStatementForUpdateOption);
            userOption = scanner.nextInt();

            switch (userOption) {
                case 1:  addAddress();
                         break;
                case 2:  updateAddress();
                         break;
                case 3:  break;
                default: System.out.println("\nWARNING: Invalid Entry");
                         continue;
            }
        } while (3 != userOption);
    }

    /**
     * We add new address to the employee.
     */
    public void addAddress() {
        int userOption;
        System.out.print("\nEnter the employee id where you need to add address: ");
        String id = scanner.next();
     
        if (employeeController.checkIdExistOrNot(id)) {

            do {
                System.out.println("Which type of address you need to add?"
                        + "\n1.Permanent\n2.Temporary");
                String addressType = (1 == scanner.nextInt()) ? "permanent"
                                                              : "temporary";
                String doorNumber = getDoorNumber();
                String street = getStreet();
                String nagar = getNagar();
                String city = getCity();
                String district = getDistrict();
                String state = getState();
                String country = getCountry();
                int pinCode = getPinCode();

                employeeController.addEmployeeAddress(id, doorNumber, 
                        street, nagar, city, district, state, country, 
                        pinCode, addressType);
                System.out.println("\nPress 1 to continue add address"
                        + "\n      0 to exit");
                userOption = scanner.nextInt();
            } while (1 == userOption);
        } else {
            System.out.println("\nThe given employee id is not available.");
        }
    }

    /**
     * We update any one of the address of an employee.
     * We ask the user whether to update temporary or permanent address.
     */
    public void updateAddress() {
        int userOption;
        System.out.print("\nEnter the employee id: ");
        String employeeId = scanner.next();

        if (employeeController.checkIdExistOrNot(employeeId)) {

            do {
                System.out.println("Choose 1 to update permanent address"
                        + "\n       2 to update temporary address");
                int addressOption = scanner.nextInt();
                String addressType = (1 == addressOption) ? "permanent" 
                                                          : "temporary";
                System.out.print("\nEnter the address id: ");
                int addressId = scanner.nextInt();
                String doorNumber = getDoorNumber();
                String street = getStreet();
                String nagar = getNagar();
                String city = getCity();
                String district = getDistrict();
                String state = getState();
                String country = getCountry();
                int pinCode = getPinCode();

                if (employeeController.updateAddress(employeeId, doorNumber, street, 
                        nagar, city, district, state, country, pinCode,
                        addressType, addressId)) {
                    System.out.println("Datas are added successfully to the table");
                } else {
                    System.out.println("There is an issue in adding datas.");
                }
                System.out.println("\nPress 1 to continue update"
                        + "\n      0 to exit");
                userOption = scanner.nextInt();
            } while (1 == userOption);
        } else {
            System.out.println("\nThe given employee id is not available.");
        }
    }

    /**
     * We choose an option for delete operations.
     * Options such as 1.employee, 2.address, 3.exit.
     */
    public void chooseDeleteOption() {
        String printStatementForDeleteOption = "\nChoose an option for delete process"
                + "\n1.Employee\n2.Address\n3.Exit";
        int userOption;
        
        do {
            System.out.println(printStatementForDeleteOption);
            userOption = scanner.nextInt();

            switch (userOption) {
                case 1:  deleteEmployee();
                         break;
                case 2:  deleteAddress();
                         break;
                case 3:  break;
                default: System.out.println("WARNING: Invalid Entry");
                         continue;
            }
        } while (3 != userOption);
    }

    /**
     * We delete all the records of an specific employee.
     */
    public void deleteEmployee() {
        int userOption;
        System.out.print("Enter the employee id to delete:");
        String id = scanner.next();

        if (employeeController.checkIdExistOrNot(id)) {

            do {
             
                if (employeeController.deleteEmployee(id)) {
                    System.out.println("Datas are deleted successfully.");
                } else {
                    System.out.println("There is an issue in deleting data.");
                }
                System.out.println("\nPress 1 to continue delete"
                        + "\n      0 to exit");
                userOption = scanner.nextInt();
            } while (1 == userOption);
        } else {
            System.out.println("\nThe given employee id is not available.");
        }
    }

    /**
     * We choose an option for delete address.
     */
    public void deleteAddress() {
        String printStatementForDeleteOption = "\nChoose an option for delete address"
                + "\n1.All address of individual employee"
                + "\n2.Individual employee address\n3.Exit";
        int userOption;

        do {
            System.out.println(printStatementForDeleteOption);
            userOption = scanner.nextInt();

            switch (userOption) {
                case 1:  deleteAllAddressOfIndividualEmployee();
                         break;
                case 2:  deleteIndividualAddress();
                         break;
                case 3:  break;
                default: System.out.println("WARNING: Invalid Entry");
                         continue;
            }
        } while (3 != userOption);
    }

    /**
     * We delete all address details of an individual employee.
     */
    public void deleteAllAddressOfIndividualEmployee() {
        System.out.print("\nEnter the employee id: ");
        String employeeId = scanner.next(); 

        if (employeeController.checkIdExistOrNot(employeeId)) {

            if (employeeController.deleteAddress(employeeId)) {
                System.out.println("Datas are deleted successfully.");
            } else {
                System.out.println("There is an issue in deleting data.");
            }
        } else {
            System.out.println("\nThe given employee id is not available.");
        }
    }

    /**
     * We delete an individual address of an employee.
     */
    public void deleteIndividualAddress() {
        System.out.print("\nEnter the employee id: ");
        String employeeId = scanner.next();
        System.out.print("\nEnter the address id: ");
        int addressId = scanner.nextInt();

        if (employeeController.checkAddressIdExistOrNot(employeeId, addressId)) {

            if (employeeController.deleteIndividualAddress(employeeId, addressId)) {
                System.out.println("Datas are deleted successfully.");
            } else {
                System.out.println("There is an issue in deleting data.");
            }
        } else {
            System.out.println("\nThe given address id is not available.");
        }
    }

    /**
     * We assign projects to the employees.
     */
    public void assignProjectToEmployee() {
        int userOption;
  
        do {
            System.out.print("\nEnter the employee id: ");
            String id = scanner.next(); 

            if (employeeController.checkIdExistOrNot(id)) {
                List<List<Integer>> projectIdList = getProjectIdList(id);

                if (employeeController.checkIsEmpty(projectIdList.get(0))) {
                    System.out.println("\nProject values are already assigned to employee.");
                } else {
                    employeeController.assignProjectToEmployee(id, projectIdList.get(0));
                }
            } else {
                System.out.println("\nThe given address id is not available.");
            }
            System.out.println("\nPress 1 to continue delete"
                    + "\n      0 to exit");
            userOption = scanner.nextInt();
        } while (1 == userOption);
    }

    /**
     * We unassign projects to the employees.
     */
    public void unassignProjectFromEmployee() {
        int userOption;
  
        do {
            System.out.print("\nEnter the employee id: ");
            String id = scanner.next(); 

            if (employeeController.checkIdExistOrNot(id)) {
                List<List<Integer>> projectIdList = getProjectIdList(id);

                if (employeeController.checkIsEmpty(projectIdList.get(1))) {
                    System.out.println("There is no employee or project available to unassign");
                } else {
                    employeeController.unassignProjectFromEmployee(id, projectIdList.get(1));
                }
            } else {
                System.out.println("\nThe given address id is not available.");
            }
            System.out.println("\nPress 1 to continue delete"
                    + "\n      0 to exit");
            userOption = scanner.nextInt();
        } while (1 == userOption);
    }

    /**
     * We get the list of employees id for assign and unassign.
     */
    public List<List<Integer>> getProjectIdList(String id) {
        System.out.print("\nEnter the number of project: ");
        int userOption = scanner.nextInt();
        int index = 0;
        List<Integer> projectIdList = new ArrayList<Integer>();

        while (index < userOption) {
            System.out.print("\nEnter the project id: ");
            projectIdList.add(scanner.nextInt()); 
            index++;
        }
   
        return employeeController.getAvailableProjectId(id, projectIdList);
    }

    /**
     * We display list of employees who are assigned to project.
     */
    public void displayAssignedProject() {
        System.out.print("Enter the employee id: ");
        String id = scanner.next();
        
        if (employeeController.checkIdExistOrNot(id)) {
            List<String> projectValues = employeeController.getAssignedProject(id);
 
            for (String project : projectValues) {
                System.out.println(project);
            }
        } else {
            System.out.println("\nThe given address id is not available.");
        }
    }
}