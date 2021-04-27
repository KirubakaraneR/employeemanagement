package com.ideas2it.projectmanagement.controller;

import java.sql.Date;
import java.util.List;

import com.ideas2it.projectmanagement.service.impl.ProjectServiceImpl;
import com.ideas2it.projectmanagement.service.ProjectService;

/**
 * We get user values from the project view and pass to projec
 * service to perform create, read, and update operations
 */
public class ProjectController {
    private ProjectService projectService = new ProjectServiceImpl();

    /**
     * We create project details by getting value project view
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
            Date endDate, String status) {
        return projectService.createProject(name, startDate, endDate,
                status);
    }
}