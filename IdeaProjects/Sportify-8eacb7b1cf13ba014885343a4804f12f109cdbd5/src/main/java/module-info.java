module com.example.sportify {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens entities to javafx.base;
    opens com.example.sportify to javafx.fxml;
    exports com.example.sportify;
}