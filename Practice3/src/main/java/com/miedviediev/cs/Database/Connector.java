package com.miedviediev.cs.Database;

import java.sql.Connection;
import java.sql.SQLException;

public class Connector {
    private final static  String URL = DBProperties.getProperty("db_url");
    private final static String USERNAME = DBProperties.getProperty("username");
    private final static String PASSWORD = DBProperties.getProperty("password");

    private static Connector connector;

    public static Connector getInstance() {
        if(connector == null)
            initConnector();
        return connector;
    }

    public static void initConnector() {
        if(connector == null)
            connector = new Connector();
    }

    private ConnectionPool pool;

    private Connector() {
        try {
            //Class.forName("org.postgresql.Driver");
            //pool = ConnectionPool.create(URL, USERNAME, PASSWORD);
            Class.forName("org.sqlite.JDBC");
            pool = ConnectionPool.create("jdbc:sqlite:cs_practice.db", null, null);
        } catch (ClassNotFoundException e) {
            System.err.println("Database driver loading problem!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database pool creating problem!");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return pool.getConnection();
    }

    public void releaseConnection(Connection connection) {
        try {
            pool.releaseConnection(connection);
        } catch (SQLException e) {
            System.err.println("Problem with releasing connection");
            e.printStackTrace();
        }
    }
}
