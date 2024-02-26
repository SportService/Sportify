package com.example.sportify.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.sql.Date;
import java.sql.Time;

public class Itemmatch {

    @FXML
    private Label date;

    @FXML
    private Button joinbutton;

    @FXML
    private Label nomcompetition;

    @FXML
    private Label terrain;

    @FXML
    private Label time;

    void SetText(String nomm , Date datee, Time heure, String terrainnom) {
        nomcompetition.setText(nomm);
        date.setText(String.valueOf(datee));
        time.setText(String.valueOf(heure));
        terrain.setText(terrainnom);
    }

    @FXML
    void joindreButton(MouseEvent event) {


    }
}
