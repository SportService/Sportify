package com.example.sportify.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.IOException;

public class ClassementController {

    @FXML
    private AnchorPane interfacechange;

    @FXML
    void EquipeButton(MouseEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/sportify/rankedteam.fxml"));
        AnchorPane classementinterface = fxmlLoader.load();
        //scene.setFill(Color.TRANSPARENT); // to make rounded corners
        //stage.initStyle(StageStyle.TRANSPARENT); // to make rounded corners
        interfacechange.getChildren().add(classementinterface) ;

    }

    @FXML
    void SoloButton(MouseEvent event) throws IOException {
        System.out.println("----------PROFIL CLASSEMENT");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/sportify/rankedsoloclassement.fxml"));
        AnchorPane classementinterface = fxmlLoader.load();
        //scene.setFill(Color.TRANSPARENT); // to make rounded corners
        //stage.initStyle(StageStyle.TRANSPARENT); // to make rounded corners
        interfacechange.getChildren().add(classementinterface) ;
    }

}

