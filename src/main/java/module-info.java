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
    requires org.json;
    requires java.desktop;
    requires com.google.zxing;
    requires javafx.swing;
    requires com.google.zxing.javase;
    requires org.apache.pdfbox;


    opens controllers to javafx.fxml;

    opens entities to javafx.base;
    // Open the test package to both javafx.fxml and javafx.graphics
    opens test to javafx.fxml, javafx.graphics;
}

