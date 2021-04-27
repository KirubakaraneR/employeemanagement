package com.ideas2it.sessionfactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    private static Connection connection;

    private DataBaseConnection() {
    }

    public static Connection getDataBaseUrl() {

        try {
            
            if ((null == connection) || (connection.isClosed())) {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/employeemanagement", 
                        "root", "admin");
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return connection;
    }
}