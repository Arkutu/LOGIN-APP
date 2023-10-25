package com.myproject.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection() {
        String databasePath = "/home/arkutu/Downloads/UsersAccounts.db"; // Replace with your database file path
        String url = "jdbc:sqlite:" + databasePath;

        try {
            Class.forName("org.sqlite.JDBC");
            databaseLink = DriverManager.getConnection(url);
            System.out.println("Connection to the database is successful."); // Add this line
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Connection to the database failed."); // Add this line
        }

        return databaseLink;
    }

}
