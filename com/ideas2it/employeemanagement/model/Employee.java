package com.ideas2it.employeemanagement.model;

import java.sql.Date;
import java.util.List;

import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.projectmanagement.model.Project;

/**
 * POJO class for employee
 * It contains employee details such as id, name, mobile number, email,
 * salary, address, date of birth and date of join  
 *
 * @version 1.0 12 March 2021
 * @author Kirubakarane R
 */
public class Employee {
    private String id;
    private String name;
    private Date dateOfJoin;
    private Date dateOfBirth;
    private int age;
    private double salary;
    private String mobileNumber;
    private String mailId;
    private boolean isDeleted;
    private List<Address> addresses;
    private List<Project> projects;

    /**
     * Here we get the employee details 
     *
     * @param dateOfJoin - Employee date of join
     * @param id - Employee id
     * @param name - Employee name
     * @param dateOfBirth - employee date of birth
     * @param age - employee age
     * @param mobileNumber - employee mobile number
     * @param mailId - employee mail id
     * @param salary - employee salary
     * @param address - employee address
     */
    public Employee(String id, String name, Date dateOfJoin, 
            Date dateOfBirth, int age, double salary, 
            String mobileNumber, String mailId, List<Address> addresses) {
        this.dateOfJoin = dateOfJoin;        
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.salary = salary;
        this.mobileNumber = mobileNumber;
        this.mailId = mailId;
        this.addresses = addresses;
    }
 
    /**
     * Here we get the employee details except address
     *
     * @param dateOfJoin - Employee date of join
     * @param id - Employee id
     * @param name - Employee name
     * @param dateOfBirth - employee date of birth
     * @param age - employee age
     * @param mobileNumber - employee mobile number
     * @param mailId - employee mail id
     * @param salary - employee salary
     * @param address - employee address
     */
    public Employee(String id, String name, Date dateOfJoin, 
            Date dateOfBirth, int age, double salary,
            String mobileNumber, String mailId) {
        this.dateOfJoin = dateOfJoin;        
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.salary = salary;
        this.mobileNumber = mobileNumber;
        this.mailId = mailId;
    }

    public Employee() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String email) {
        this.mailId = mailId;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfJoin() {
        return dateOfJoin;
    }

    public void setDateOfJoin(Date dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
    }

    public int getAge() {
        return age;
    }
  
    public void setAge(int age) {
        this.age = age;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public String toString() {
        return "ID           : " + this.id + "\nName         : " + this.name
                + "\nMobile Number: " + this.mobileNumber + "\nEmail ID     : "
                + this.mailId + "\nSalary       : " + this.salary
                + "\nDate Of Birth: " + this.dateOfBirth
                + "\nDate Of Join : " + this.dateOfJoin + "\n";
    }
}