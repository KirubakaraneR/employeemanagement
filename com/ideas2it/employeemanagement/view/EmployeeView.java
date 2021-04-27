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
                /*case 2:  chooseDisplayOption();
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
                         break;*/
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
        createEmployeeAddress(id);

        if (employeeController.createEmployee(id, name, dateOfJoin,
                dateOfBirth, age, salary, mobileNumber, mailId)) {
            System.out.println("Datas added successfully.");
        } else {
            System.out.println("There is an issue in adding datas.");
        }
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
            employeeController.createEmployeeAddress(doorNumber, 
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
}