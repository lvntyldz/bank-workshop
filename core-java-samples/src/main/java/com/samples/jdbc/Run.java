package com.samples.jdbc;

public class Run {

    public static void main(String[] args) {

        DBOperations.createDatabase();
        DBOperations.createTables();

        DataOperations.insertData();
        DataOperations.selectAllData();

        DataOperations.updateData();
        DataOperations.selectAllData();

        DataOperations.deleteData();
        DataOperations.selectAllData();
    }
}
