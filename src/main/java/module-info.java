module com.example.sportify {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires javafx.web;
    requires de.jensd.fx.glyphs.commons; // Add this line if you haven't already
    requires de.jensd.fx.glyphs.materialdesignicons;
    requires de.jensd.fx.glyphs.fontawesome;
    requires MaterialFX;

    opens entities to javafx.base;

    opens com.example.sportify to javafx.fxml;
    opens com.example.sportify.controller to javafx.fxml; // Add this line to export the controller package

    exports com.example.sportify;

    // Add any additional module dependencies or exports as needed
}
