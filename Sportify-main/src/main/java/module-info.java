module sportify {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.web;
    requires twilio;
    requires org.json;
    requires jdk.jsobject;
    requires java.mail;
    requires org.apache.pdfbox;
    requires java.desktop;


    requires org.apache.poi.ooxml;
   // requires javase.java6;
    requires com.google.zxing;
    requires com.google.zxing.javase;
    requires com.fasterxml.jackson.databind;
    // requires core.java6;
    //requires poi;


    opens controllers to javafx.fxml;
    opens entities;
    exports controllers;
    exports test;


}