package com.samples.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBOperations {

    public static void createDatabase() {
        Connection connection = null;
        Statement statement = null;

        try {
            //Register JDBC driver
            Class.forName(Constants.JDBC_DRIVER);

            //Open a connection
            connection = DriverManager.getConnection(Constants.DB_URL, Constants.USER, Constants.PASS);
            statement = connection.createStatement();

            String sql = "CREATE DATABASE IF NOT EXISTS STUDENTS ";
            statement.executeUpdate(sql);

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {

            //finally block used to close resources
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException se2) {
            }

            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }

        }

    }

    public static void createTables() {
        Connection connection = null;
        Statement statement = null;
        try {
            //Register JDBC driver
            Class.forName(Constants.JDBC_DRIVER);

            connection = DriverManager.getConnection(Constants.DB_URL, Constants.USER, Constants.PASS);

            statement = connection.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS STUDENTS.REGISTRATION " +
                    "(id INTEGER not NULL, " +
                    " firstname VARCHAR(255), " +
                    " lastname VARCHAR(255), " +
                    " age INTEGER, " +
                    " PRIMARY KEY ( id ))";

            statement.executeUpdate(sql);

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (statement != null)
                    connection.close();
            } catch (SQLException se) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }
}
