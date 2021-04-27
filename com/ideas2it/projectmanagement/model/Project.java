package com.ideas2it.projectmanagement.model;

import java.time.LocalDate;
import java.util.List;

import com.ideas2it.employeemanagement.model.Employee;

/**
 * POJO class for project
 * It contains project details such as project id, project name,
 * start date, end date, status 
 *
 * @version 1.0 09 March 2021
 * @author Kirubakarane R
 */
public class Project {
    private int id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private List<Employee> employees;

    /**
     * Here we get the project details
     * 
     * @param id - Project id
     * @param name - Project name
     * @param startDate - Project start date
     * @param endDate - Project end date
     * @param status - Project status
     * @param employee - List of Employee details
     */
    public Project(int id, String name,
            LocalDate startDate, LocalDate endDate,
            String status, List<Employee> employees) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.employees = employees;
    }

    /**
     * Here we get the project details
     * 
     * @param id - Project id
     * @param name - Project name
     * @param startDate - Project start date
     * @param endDate - Project end date
     * @param status - Project status
     */
    public Project(int id, String name,
            LocalDate startDate, LocalDate endDate,
            String status) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }
 
    public Project(String name, LocalDate startDate,
            LocalDate endDate, String status) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public int getId() { 
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {  
        this.status = status;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {  
        this.employees = employees;
    }

    public String toString() {
        return "PROJECT ID:         " + this.id + "\nPROJECT NAME:       "
                + this.name + "\nPROJECT START DATE: " + this.startDate
                + "\nPROJECT END DATE:   " + this.endDate + "\n";
    }
}