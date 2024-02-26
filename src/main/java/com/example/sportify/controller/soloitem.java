package com.example.sportify.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class soloitem {
    @FXML
    private Label nbrematch;

    @FXML
    private Label nom;

    @FXML
    private Label points;

    @FXML
    private Label rank;

    void SetText(String nbre ,String nomm , String pts, String ranking) {
        nbrematch.setText(nbre);
        nom.setText(nomm);
        points.setText(pts);
        rank.setText(ranking);
    }


}
