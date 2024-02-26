package com.example.sportify.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class rankedsolointerfacecontroller implements Initializable {
    @FXML
    private HBox hbox;

    @FXML
    private VBox vboxall;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boolean color1=true ;
        boolean color2= false ;
       for (int i=0 ; i<10 ; i++) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/sportify/soloitem.fxml"));
            try {
                HBox hboxitems = fxmlLoader.load();
                soloitem items= fxmlLoader.getController() ;
                items.SetText("12","WASSIM","121","82173");

                if (color1) {
                    hboxitems.setStyle("-fx-background-color: #3A2F05");
                } else if (color2) {
                    hboxitems.setStyle("-fx-background-color: #292E37");
                } else {
                    hboxitems.setStyle("-fx-background-color: #271304");
                }

                color1=!color1 ;
                color2=!color2;
                vboxall.getChildren().add(hboxitems) ;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }


    }
}
