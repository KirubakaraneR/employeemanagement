package com.ideas2it.projectmanagement.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//import com.ideas2it.employeemanagement.model.Employee;
//import com.ideas2it.employeemanagement.service.EmployeeService;
//import com.ideas2it.employeemanagement.service.impl.EmployeeServiceImpl;
import com.ideas2it.projectmanagement.dao.ProjectDao;
import com.ideas2it.projectmanagement.dao.impl.ProjectDaoImpl;
import com.ideas2it.projectmanagement.model.Project;
import com.ideas2it.projectmanagement.service.ProjectService;

/**
 * We perform create and update operation to pojo and also
 * pass the values to project dao to perform CRUD operation in DB
 */
public class ProjectServiceImpl implements ProjectService {
    private ProjectDao projectDao = new ProjectDaoImpl();
    
    /**
     * {@inheritdoc}
     */
    @Override
    public boolean createProject(String name, Date startDate,
            Date endDate, String status) {
        return projectDao.createProject(new Project(name, startDate, endDate,
                status));
    }
}