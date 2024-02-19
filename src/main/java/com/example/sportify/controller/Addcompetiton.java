package com.example.sportify.controller;

import entities.Competition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import services.CompetitionService;
import javafx.scene.input.MouseEvent;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

public class Addcompetiton {

    @FXML
    private DatePicker Datee;

    @FXML
    private TextField DescText;

    @FXML
    private TextField TypeText;

    @FXML
    private Button annuler_btn;

    @FXML
    private TextField nomText;

    @FXML
    private Button save_btn;

    CompetitionService competitionService = new CompetitionService(); // Create an instance of CompetitionService


    @FXML
    void save(MouseEvent event) {
   /*     String name = nomText.getText();
        String desc = DescText.getText();
        String type = TypeText.getText();
        String birth = String.valueOf(Date.getValue());


        if (name.isEmpty() || birth.isEmpty() || desc.isEmpty() || type.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
        }*/

        try {
            String birth = String.valueOf(Datee.getValue());
            System.out.println(birth);
            Competition compet = new Competition(nomText.getText(),DescText.getText(),TypeText.getText(), Time.valueOf("12:12:00"), Date.valueOf(String.valueOf(Datee.getValue())) ) ;
            competitionService.ajouter(compet) ;
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("competition ajoute");
            alert.showAndWait();
            clean() ;
            CompetitionController Compett = new CompetitionController() ;
                    Compett.loadAll() ;
        } catch (SQLException e) {
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void clean() {
        DescText.setText(null);
        Datee.setValue(null);
        TypeText.setText(null);
        nomText.setText(null);

    }

}
