package com.myproject.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection() {
        String databaseName = "UserAccounts.db"; // Replace with your SQLite database file name
        String url = "jdbc:sqlite:" + databaseName;

        try {
            Class.forName("org.sqlite.JDBC");
            databaseLink = DriverManager.getConnection(url);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return databaseLink;
    }
}