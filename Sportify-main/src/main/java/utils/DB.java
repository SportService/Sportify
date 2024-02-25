package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    public final String URL = "jdbc:mysql://localhost:3306/sportify";
    public final String USERNAME = "root";
    public final String PWD = "root";


    public static DB instance;

    private Connection connection;


    public DB() {

        try {
            System.out.println("connexion en cours");
            connection = DriverManager.getConnection(URL,USERNAME,PWD);
            System.out.println("Connected");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static DB getInstance(){
        if(instance==null){
            instance = new DB();
        }
        return instance;

    }

    public Connection getConnection() {
        return connection;
    }
}
