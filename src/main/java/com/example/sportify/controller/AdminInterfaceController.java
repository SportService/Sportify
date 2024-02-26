package com.example.sportify.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;




public class AdminInterfaceController {



    @FXML
    private Label competitionButton;

    @FXML
    private AnchorPane interfacechanger;

    @FXML
    private AnchorPane navbar;

    @FXML
    private Label profilebutton;

    @FXML
    private Label reservationButton;

    @FXML
    private AnchorPane sidebar;

    @FXML
    void competitionButton(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/sportify/AdminMatchCompetition.fxml"));
        AnchorPane competitioninterface = fxmlLoader.load();
        interfacechanger.getChildren().add(competitioninterface) ;
    }

    @FXML
    void profilebutton(MouseEvent event) {

    }
}
