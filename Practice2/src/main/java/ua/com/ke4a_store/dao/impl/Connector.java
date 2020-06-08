package ua.com.ke4a_store.dao.impl;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    private static final String URL = "jdbc:mysql://localhost:3306/storagedb";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "admin";

    public Connection getConnection() {
        try {
            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }


}
