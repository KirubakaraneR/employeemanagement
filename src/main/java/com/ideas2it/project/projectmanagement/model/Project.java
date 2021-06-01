package com.ideas2it.project.projectmanagement.model;

import java.sql.Date;
import java.util.List;

import com.ideas2it.project.employeemanagement.model.Employee;

/**
 * POJO class for project
 * It contains project details such as project id, project name,
 * start date, end date, status 
 *
 * @version 1.0 04-05-2021
 * @author Kirubakarane R
 */
public class Project {
    private int id;
    private String name;
    private Date startDate;
    private Date endDate;
    private String status;
    private boolean isDeleted;
    private List<Employee> employees;

    /**
     * Here we get the project details.
     * 
     * @param name - Project name
     * @param startDate - Project start date
     * @param endDate - Project end date
     * @param status - Project status
     */
    public Project(String name,
            Date startDate, Date endDate,
            String status) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Project() {
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {  
        this.status = status;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {  
        this.employees = employees;
    }

	@Override
	public String toString() {
		return id + "," + name + "," + startDate + "," + endDate
				+ "," + status + "," + isDeleted + "," + employees;
	}
    
    
}