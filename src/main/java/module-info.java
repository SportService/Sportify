/*module com.example.sportify {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.sportify to javafx.fxml;
    exports com.example.sportify;
    exports controllers;
    opens controllers to javafx.fxml;
}*/
module com.example.sportify {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens controllers to javafx.fxml;

    opens entities to javafx.base;
    // Open the test package to both javafx.fxml and javafx.graphics
    opens test to javafx.fxml, javafx.graphics;
}

