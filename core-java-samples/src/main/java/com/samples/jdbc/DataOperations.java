package com.samples.jdbc;

import java.sql.*;

public class DataOperations {

    public static void insertData() {

        Connection connection = null;
        Statement statement = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();

            String sql = "INSERT INTO REGISTRATION VALUES (100, 'Ali', 'TURAN', 18)";
            statement.executeUpdate(sql);

            sql = "INSERT INTO REGISTRATION VALUES (101, 'Veli', 'ASLAN', 25)";
            statement.executeUpdate(sql);

        } catch (SQLException se) {
            //Handle errors for JDBC
        } catch (Exception e) {
            //Handle errors for Class.forName
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
            }
        }
    }

    public static void updateData() {

        Connection connection = null;
        Statement statement = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();

            String sql = "UPDATE REGISTRATION SET age = 30 WHERE id in (100, 101)";
            statement.executeUpdate(sql);

        } catch (SQLException se) {
            //Handle errors for JDBC
        } catch (Exception e) {
            //Handle errors for Class.forName
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
            }
        }
    }

    public static void deleteData() {

        Connection connection = null;
        Statement statement = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();

            String sql = "DELETE FROM REGISTRATION WHERE id = 101";
            statement.executeUpdate(sql);

        } catch (SQLException se) {
            //Handle errors for JDBC
        } catch (Exception e) {
            //Handle errors for Class.forName
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
            }
        }
    }

    public static void selectAllData() {

        Connection connection = null;
        Statement statement = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();

            String sql = "SELECT id, firstname, lastname, age FROM REGISTRATION";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                int age = rs.getInt("age");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");

                System.out.println("ID : " + id + " - Age : " + age + " - Firstname : " + firstname + " - Lastname:" + lastname);
            }
            rs.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
        } catch (Exception e) {
            //Handle errors for Class.forName
        } finally {
            //finally block used to close resources
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException se) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
            }
        }
        System.out.println("---------- ---------- ----------");
    }

    private static Connection getConnection() throws Exception {
        //Register JDBC driver
        Class.forName(Constants.JDBC_DRIVER);

        //Open DB connection
        return DriverManager.getConnection(Constants.DB_URL_WITH_DB, Constants.USER, Constants.PASS);

    }

}
