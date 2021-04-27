package com.ideas2it.employeemanagement.dao.impl;

import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employeemanagement.dao.EmployeeDao;
import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.projectmanagement.model.Project;
import com.ideas2it.sessionfactory.DataBaseConnection;

/**
 * We perform create, read, update operations to the table in data base
 */
public class EmployeeDaoImpl implements EmployeeDao {
    
    /**
     * {@inheritdoc}
     */
    @Override    
    public boolean addEmployee(Employee employee) {
        String query = "INSERT INTO employee(id, name, date_of_join,"
                + "date_of_birth, age, mobile_number, mail_id, salary) "
                + "VALUES (?,?,?,?,?,?,?,?)";
        boolean checkIsAdded = true;
        Connection connection = null;

        try {
            connection = DataBaseConnection.getDataBaseUrl();
            PreparedStatement preparedStatement 
                    = connection.prepareStatement(query);
            preparedStatement.setString(1, employee.getId());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setDate(3, Date.valueOf(employee
                    .getDateOfJoin()));
            preparedStatement.setDate(4, Date.valueOf(employee
                    .getDateOfBirth()));
            preparedStatement.setInt(5, employee.getAge());
            preparedStatement.setString(6, employee.getMobileNumber());
            preparedStatement.setString(7, employee.getMailId());
            preparedStatement.setDouble(8, employee.getSalary());
            preparedStatement.executeUpdate();
            addAddress(employee);
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
    public boolean addAddress(Employee employee) {
        String query = "INSERT INTO address (door_number, street,"
                + "nagar, city, district, state, country, pincode,"
                + "address_type, employee_id) VALUES (?,?,?,?,?,?,?,?,?,?)";
        boolean checkIsAdded = true;
        Connection connection = null;
        List<Address> addressValues = employee.getAddress();
        try {
            connection = DataBaseConnection.getDataBaseUrl();
            PreparedStatement preparedStatement 
                    = connection.prepareStatement(query);

            for (Address address : addressValues) {
                preparedStatement.setString(1, address.getDoorNumber());
                preparedStatement.setString(2, address.getStreet());
                preparedStatement.setString(3, address.getNagar());
                preparedStatement.setString(4, address.getCity());
                preparedStatement.setString(5, address.getDistrict());
                preparedStatement.setString(6, address.getState());
                preparedStatement.setString(7, address.getCountry());
                preparedStatement.setInt(8, address.getPinCode());
                preparedStatement.setString(9, address.getAddressType());
                preparedStatement.setString(10, address.getEmployeeId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            addressValues.clear();
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
    public List<Employee> getAllEmployee() {
        String query = "SELECT id, name, date_of_join, date_of_birth,"
                + "age, mobile_number, mail_id, salary FROM employee "
                + "WHERE is_deleted = 0";
        Connection connection = null;
        List<Employee> employeeDetails = new ArrayList<Employee>();

        try {
            connection = DataBaseConnection.getDataBaseUrl();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Employee employee = new Employee(resultSet.getString("id"), 
                        resultSet.getString("name"), 
                        resultSet.getDate("date_of_join").toLocalDate(),
                        resultSet.getDate("date_of_birth").toLocalDate(), 
                        resultSet.getInt("age"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("mobile_number"),
                        resultSet.getString("mail_id"));
                employeeDetails.add(employee);
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
        return employeeDetails;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public Employee getIndividualEmployee(String id) {
        String query = "SELECT employee.id, employee.name, employee.date_of_join,"
                + " employee.date_of_birth, employee.age, employee.salary, employee.mobile_number,"
                + " employee.mail_id, address.employee_id, address.door_number,"
                + " address.street, address.nagar, address.city, address.district,"
                + " address.state, address.country, address.pincode, address.address_type,"
                + " address.id, employee.is_deleted AS e_delete, address.is_deleted AS a_delete"
                + " FROM employee LEFT JOIN address ON employee.id = address.employee_id"
                + " WHERE employee.id = ?";
        Connection connection = null;
        Employee employee = null;
        List<Address> employeeAddress = new ArrayList<Address>();

        try {
            connection = DataBaseConnection.getDataBaseUrl();
            PreparedStatement preparedStatement 
                    = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
           
            if (!resultSet.getBoolean("e_delete")) {
                employee = new Employee(resultSet.getString("id"), 
                        resultSet.getString("name"), 
                        resultSet.getDate("date_of_join").toLocalDate(),
                        resultSet.getDate("date_of_birth").toLocalDate(), 
                        resultSet.getInt("age"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("mobile_number"),
                        resultSet.getString("mail_id"));
            }

            do {
                
                if (!resultSet.getBoolean("a_delete")) {
                    Address address = new Address(
                            resultSet.getString("employee_id"), 
                            resultSet.getString("door_number"),
                            resultSet.getString("street"), 
                            resultSet.getString("nagar"),
                            resultSet.getString("city"),
                            resultSet.getString("district"),
                            resultSet.getString("state"), 
                            resultSet.getString("country"),
                            resultSet.getInt("pincode"), 
                            resultSet.getString("address_type"));
                    employeeAddress.add(address);
                }
            } while (resultSet.next());
            employee.setAddress(employeeAddress);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return employee;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<Employee> getAllEmployeeOfParticularYear(String year) {
        String query = "SELECT id, name, date_of_join, date_of_birth,"
                + "age, mobile_number, mail_id, salary FROM employee "
                + "WHERE SUBSTRING(YEAR(employee.date_of_join),3) = ? "
                + "AND is_deleted = 0";
        Connection connection = null;
        List<Employee> employeeDetails = new ArrayList<Employee>();

        try {
            connection = DataBaseConnection.getDataBaseUrl();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, year);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee(resultSet.getString("id"), 
                    resultSet.getString("name"), 
                    resultSet.getDate("date_of_join").toLocalDate(),
                    resultSet.getDate("date_of_birth").toLocalDate(), 
                    resultSet.getInt("age"),
                    resultSet.getDouble("salary"),
                    resultSet.getString("mobile_number"),
                    resultSet.getString("mail_id"));
                employeeDetails.add(employee);
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
        return employeeDetails;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean updateEmployee(Employee employee) {
        String query = "UPDATE employee SET date_of_join = ?,"
                + "name = ?, date_of_birth = ?, age = ?," 
                + "mobile_number = ?, mail_id = ?, salary = ?"
                + "WHERE id = ?";
        boolean checkIsUpdated = true;
        Connection connection = null;
  
        try {
            connection = DataBaseConnection.getDataBaseUrl();
            PreparedStatement preparedStatement
                    = connection.prepareStatement(query);
            preparedStatement.setDate(1, Date.valueOf(employee.getDateOfJoin()));
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setDate(3, Date.valueOf(employee.getDateOfBirth()));
            preparedStatement.setInt(4, employee.getAge());
            preparedStatement.setString(5, employee.getMobileNumber());
            preparedStatement.setString(6, employee.getMailId());
            preparedStatement.setDouble(7, employee.getSalary());
            preparedStatement.setString(8, employee.getId());
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
    public Address getAddress(String employeeId, int addressId) {
        String query = "SELECT employee_id, door_number, street, nagar,"
                + " city, district, state, country, pincode, address_type"
                + " FROM address WHERE employee_id = ? AND id = ?";
        Connection connection = null;
        Address address = null;

        try {
            connection = DataBaseConnection.getDataBaseUrl();
            PreparedStatement preparedStatement
                    = connection.prepareStatement(query);
            preparedStatement.setString(1, employeeId);
            preparedStatement.setInt(2, addressId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            address = new Address(resultSet.getString("employee_id"), 
                        resultSet.getString("door_number"),
                        resultSet.getString("street"), 
                        resultSet.getString("nagar"),
                        resultSet.getString("city"), 
                        resultSet.getString("district"),
                        resultSet.getString("state"), 
                        resultSet.getString("country"),
                        resultSet.getInt("pincode"), 
                        resultSet.getString("address_type"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return address;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean updateAddress(Address address) {
        String query = "UPDATE address SET door_number = ?, street = ?,"
                + "nagar = ?, city = ?, district = ?, state = ?,"
                + "country = ?, pincode = ?, address_type = ? WHERE employee_id = ?"
                + " AND id = ?";
        boolean checkIsUpdated = true;
        Connection connection = null;

        try {
            connection = DataBaseConnection.getDataBaseUrl();
            PreparedStatement preparedStatement
                    = connection.prepareStatement(query);
            preparedStatement.setString(1, address.getDoorNumber());
            preparedStatement.setString(2, address.getStreet());
            preparedStatement.setString(3, address.getNagar());
            preparedStatement.setString(4, address.getCity());
            preparedStatement.setString(5, address.getDistrict());
            preparedStatement.setString(6, address.getState());
            preparedStatement.setString(7, address.getCountry());
            preparedStatement.setInt(8, address.getPinCode());
            preparedStatement.setString(9, address.getAddressType());
            preparedStatement.setString(10, address.getEmployeeId());
            preparedStatement.setInt(11, address.getAddressId());
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
    public boolean deleteEmployee(String id) {
        String query = "UPDATE employee, address SET employee.is_deleted = 1,"
                + "address.is_deleted = 1 WHERE employee.id = ? "
                + "AND address.employee_id = ?";
        boolean checkIsDeleted = true;
        Connection connection = null;
  
        try {
            connection = DataBaseConnection.getDataBaseUrl();
            PreparedStatement preparedStatement 
                    = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, id);
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
    public boolean deleteAddress(String id) {
        String query = "UPDATE address SET is_deleted = 1 "
                + "WHERE employee_id = ?";
        boolean checkIsDeleted = true;
        Connection connection = null;
  
        try {
            connection = DataBaseConnection.getDataBaseUrl();
            PreparedStatement preparedStatement 
                    = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
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
    public boolean deleteIndividualAddress(int id) {
        String query = "UPDATE address SET is_deleted = 1 "
                + "WHERE id = ?";
        boolean checkIsDeleted = true;
        Connection connection = null;
  
        try {
            connection = DataBaseConnection.getDataBaseUrl();
            PreparedStatement preparedStatement 
                    = connection.prepareStatement(query);
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
    public Employee getEmployeeProject(String id) {
        String query = "SELECT employee.id as employeeId, employee.name as employeeName, employee.date_of_join,"
                + "employee.date_of_birth, employee.age, employee.mobile_number,"
                + "employee.mail_id, employee.salary, employee.is_deleted AS e_delete,"
                + "project.is_deleted AS p_delete, project.name as projectName, project.start_date,"
                + "project.end_date, project.id as projectId, project.status "
                + "FROM employee LEFT JOIN employee_project ON employee.id = "
                + "employee_project.employee_id LEFT JOIN project ON employee_project.project_id ="
                + "project.id WHERE employee.id = ?";
        Connection connection = null;
        Employee employee = null;
        
        try {
            connection = DataBaseConnection.getDataBaseUrl();
            PreparedStatement preparedStatement 
                    = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Project> projectValues = new ArrayList<Project>();
            resultSet.next();
           
            if (!resultSet.getBoolean("e_delete")) {
                employee = new Employee(resultSet.getString("employeeId"), 
                        resultSet.getString("employeeName"), 
                        resultSet.getDate("date_of_join").toLocalDate(),
                        resultSet.getDate("date_of_birth").toLocalDate(), 
                        resultSet.getInt("age"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("mobile_number"),
                        resultSet.getString("mail_id"));
            }

            do {

                if ((!resultSet.getBoolean("p_delete")) && (0 != resultSet.getInt("projectId"))) {
                    Project project = new Project(resultSet.getInt("projectId"),
                            resultSet.getString("projectName"),
                            resultSet.getDate("start_date").toLocalDate(),
                            resultSet.getDate("end_date").toLocalDate(),
                            resultSet.getString("status"));
                    projectValues.add(project);
                }
            } while (resultSet.next());
            employee.setProjects(projectValues);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return employee;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void assignProjectToEmployee(Employee employee) {
        String query = "INSERT INTO employee_project (employee_id, project_id) VALUES (?,?)";
        Connection connection = null;
        List<Project> projectValues = employee.getProjects();
        
        try {
            connection = DataBaseConnection.getDataBaseUrl();
            PreparedStatement preparedStatement 
                    = connection.prepareStatement(query);
            
            for (Project project : projectValues) {
                preparedStatement.setString(1, employee.getId());
                preparedStatement.setInt(2, project.getId());
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
    public void unassignProjectFromEmployee(Employee employee) {
        String query = "DELETE FROM employee_project WHERE employee_id = ? AND"
                + " project_id = ?";
        Connection connection = null;
        List<Project> projectValues = employee.getProjects();
        
        try {
            connection = DataBaseConnection.getDataBaseUrl();
            PreparedStatement preparedStatement 
                    = connection.prepareStatement(query);
            
            for (Project project : projectValues) {
                preparedStatement.setString(1, employee.getId());
                preparedStatement.setInt(2, project.getId());
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
    public int getEmployeeIdCount(String id) {
        String query = "SELECT COUNT(id) AS count FROM employee WHERE id = ?";
        Connection connection = null;
        int count = 0;

        try {
            connection = DataBaseConnection.getDataBaseUrl();
            PreparedStatement preparedStatement 
                    = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
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
 
    /**
     * {@inheritdoc}
     */
    @Override
    public int getAddressIdCount(int id) {
        String query = "SELECT COUNT(id) AS count FROM address WHERE id = ?";
        Connection connection = null;
        int count = 0;

        try {
            connection = DataBaseConnection.getDataBaseUrl();
            PreparedStatement preparedStatement 
                    = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
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

    /**
     * {@inheritdoc}
     */
    @Override
    public int getYearCount(String year) {
        String query = "SELECT COUNT(date_of_join) AS count FROM employee WHERE"
                + " YEAR(date_of_join) = ?";
        Connection connection = null;
        int count = 0;

        try {
            connection = DataBaseConnection.getDataBaseUrl();
            PreparedStatement preparedStatement 
                    = connection.prepareStatement(query);
            preparedStatement.setString(1, year);
            ResultSet resultSet = preparedStatement.executeQuery();
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