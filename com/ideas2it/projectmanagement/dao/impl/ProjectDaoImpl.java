package com.ideas2it.projectmanagement.dao.impl;

import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.projectmanagement.dao.ProjectDao;
import com.ideas2it.projectmanagement.model.Project;
import com.ideas2it.sessionfactory.DataBaseConnection;

/**
 * We perform create, read, update operations to the table in data base
 */
public class ProjectDaoImpl implements ProjectDao {

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean createProject(Project project) {
        String query = "INSERT INTO project (name, start_date,"
                + "end_date, status) VALUES (?,?,?,?)";
        boolean checkIsAdded = true;
        Connection connection = null;
       
        try {
            connection = DataBaseConnection.getDataBaseUrl();
            PreparedStatement preparedStatement 
                    = connection.prepareStatement(query);
            preparedStatement.setString(1, project.getName());
            preparedStatement.setDate(2, Date.valueOf(project
                    .getStartDate()));
            preparedStatement.setDate(3, Date.valueOf(project
                    .getEndDate()));
            preparedStatement.setString(4, project.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            checkIsAdded = false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return checkIsAdded;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void assignEmployeeToProject(Project project) {
        String query = "INSERT INTO employee_project (project_id, employee_id) VALUES (?,?)";
        Connection connection = null;
        List<Employee> employeeValues = project.getEmployees();
        
        try {
            connection = DataBaseConnection.getDataBaseUrl();
            PreparedStatement preparedStatement 
                    = connection.prepareStatement(query);
            
            for (Employee employee : employeeValues) {
                preparedStatement.setInt(1, project.getId());
                preparedStatement.setString(2, employee.getId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void unassignEmployeeFromProject(Project project) {
        String query = "DELETE FROM employee_project WHERE project_id = ? AND"
                + " employee_id = ?";
        Connection connection = null;
        List<Employee> employeeValues = project.getEmployees();
        
        try {
            connection = DataBaseConnection.getDataBaseUrl();
            PreparedStatement preparedStatement 
                    = connection.prepareStatement(query);
            
            for (Employee employee : employeeValues) {
                preparedStatement.setInt(1, project.getId());
                preparedStatement.setString(2, employee.getId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<Project> getAllProject() {
        String query = "SELECT id, name, start_date, end_date, status FROM project";
        Connection connection = null;
        Project project = null;
        List<Project> projectValues = new ArrayList<Project>();
        try {
            connection = DataBaseConnection.getDataBaseUrl();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                project = new Project(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDate("start_date").toLocalDate(),
                        resultSet.getDate("end_date").toLocalDate(),
                        resultSet.getString("status"));
                projectValues.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
      
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return projectValues;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public Project getProject(int id) {
        String query = "SELECT id, name, start_date, end_date, status FROM project WHERE id = ?";
        Connection connection = null;
        Project project = null;

        try {
            connection = DataBaseConnection.getDataBaseUrl();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                project = new Project(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDate("start_date").toLocalDate(),
                        resultSet.getDate("end_date").toLocalDate(),
                        resultSet.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
      
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return project;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean updateProjectDetails(Project project) {
        String query = "UPDATE project SET name = ?, start_date = ?,"
                + "end_date = ?, status = ? WHERE id = ?";
        boolean checkIsUpdated = true;
        Connection connection = null;
      
        try {
            connection = DataBaseConnection.getDataBaseUrl();
            PreparedStatement preparedStatement 
                    = connection.prepareStatement(query);
            preparedStatement.setString(1, project.getName());
            preparedStatement.setDate(2, Date.valueOf(project.getStartDate()));
            preparedStatement.setDate(3, Date.valueOf(project.getEndDate()));
            preparedStatement.setString(4, project.getStatus());
            preparedStatement.setInt(5, project.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            checkIsUpdated = false;
        } finally {

            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return checkIsUpdated;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean updateProjectStartDate(Project project) {
        String query = "UPDATE project SET start_date = ? CASE WHEN update = 'no' END WHERE id = ?";
        boolean checkIsUpdated = true;
        Connection connection = null;

        try {
            connection = DataBaseConnection.getDataBaseUrl();
            PreparedStatement preparedStatement 
                    = connection.prepareStatement(query);
            preparedStatement.setDate(1, Date.valueOf(project.getStartDate()));
            preparedStatement.setInt(2, project.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            checkIsUpdated = false;
        } finally {

            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return checkIsUpdated;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean deleteProject(int id) {
        String query = "DELETE FROM project WHERE id = ?";
        boolean checkIsDeleted = true;
        Connection connection = null;

        try {
            connection = DataBaseConnection.getDataBaseUrl();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            checkIsDeleted = false;
        } finally {

            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return checkIsDeleted;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public Project getProjectEmployee(int id) {
        String query = "SELECT project.name as projectName, project.start_date,"
                + "project.end_date, project.id as projectId, project.status,"
                + "employee.id as employeeId, employee.name as employeeName, employee.date_of_join,"
                + "employee.date_of_birth, employee.age, employee.mobile_number,"
                + "employee.mail_id, employee.salary, employee.is_deleted AS e_delete,"
                + "project.is_deleted AS p_delete FROM project LEFT JOIN "
                + "employee_project ON project.id = employee_project.project_id "
                + "LEFT JOIN employee ON employee_project.employee_id = "
                + "employee.id WHERE project.id = ?";
        Connection connection = null;
        Project project = null;
        
        try {
            connection = DataBaseConnection.getDataBaseUrl();
            PreparedStatement preparedStatement 
                    = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Employee> employeeValues = new ArrayList<Employee>();
            resultSet.next();
            
            if (!resultSet.getBoolean("p_delete")) {
                project = new Project(resultSet.getInt("projectId"),
                        resultSet.getString("projectName"),
                        resultSet.getDate("start_date").toLocalDate(),
                        resultSet.getDate("end_date").toLocalDate(),
                        resultSet.getString("status"));
            }
            
            do {

                if ((!resultSet.getBoolean("e_delete")) && (null != resultSet.getString("employeeId"))) {
                    Employee employee = new Employee(
                            resultSet.getString("employeeId"),
                            resultSet.getString("employeeName"),
                            resultSet.getDate("date_of_join").toLocalDate(),
                            resultSet.getDate("date_of_birth").toLocalDate(),
                            resultSet.getInt("age"),
                            resultSet.getDouble("salary"),
                            resultSet.getString("mobile_number"),
                            resultSet.getString("mail_id"));
                    employeeValues.add(employee);
                }
            } while (resultSet.next());
            project.setEmployees(employeeValues);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return project;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public int checkIdExistOrNot(int id) {
        String query = "SELECT COUNT(id) AS count FROM project WHERE id = " + id;
        Connection connection = null;
        int count = 0;

        try {
            connection = DataBaseConnection.getDataBaseUrl();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            count = resultSet.getInt("count");
        } catch (SQLException e) {
            count = 0;
        } finally {

            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }
}