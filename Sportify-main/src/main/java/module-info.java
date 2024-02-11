module com.example.sportify {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens controllers to javafx.fxml;
    opens entities;
    exports controllers;
    exports test;

}