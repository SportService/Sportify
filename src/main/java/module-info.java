module com.example.sportify {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.sportify to javafx.fxml;
    exports com.example.sportify;
}