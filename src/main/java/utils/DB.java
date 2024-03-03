package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private final String URL = "jdbc:mysql://localhost:3306/sportify";
    private final String USERNAME = "root";
    private final String PASSWORD = "PHW#84#jeor";
    //private final String PASSWORD = "";
    private static DB instance;
    private Connection connection;

    private DB() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static DB getInstance() {
        if (instance == null) {
            instance = new DB();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}