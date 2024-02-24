module com.example.sportify {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires javafx.web;
    requires javafx.media; // Add this line if you haven't already

    opens com.example.sportify to javafx.fxml;
    opens com.example.sportify.controller to javafx.fxml; // Add this line to export the controller package

    exports com.example.sportify;

    // Add any additional module dependencies or exports as needed
    opens entities;
    exports com.example.sportify.controller;

}
