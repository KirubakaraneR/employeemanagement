package com.ideas2it.projectmanagement.service;

import java.sql.Date;
import java.util.List;

import com.ideas2it.projectmanagement.model.Project;

/**
 * We create abstract method for all methods in project 
 * service implementation
 */
public interface ProjectService {

    /**
     * We create project details.
     * Details such as name, start date, end date
     * 
     * @param projectName - Project name
     * @param startDate - Project start date
     * @param endDate - Project end date
     * @param status - Project status
     *
     * @return true if datas are successfully added to table
     *         else false
     */
    public boolean createProject(String name, Date startDate,
            Date endDate, String status);
}