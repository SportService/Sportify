package com.example.sportify.controller;

import entities.Utilisateurss;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PlayerInterface  {


    @FXML
    private AnchorPane interfacechanger;

    @FXML
    private AnchorPane navbar;

    @FXML
    private Label profilebutton;

    @FXML
    private Button rechercherbutton;

    @FXML
    private AnchorPane sidebar;

    private Utilisateurss loggedInUser;

    @FXML
    private Label UserloggedNom;

    void initData(Utilisateurss user) {
        this.loggedInUser=user ;
        UserloggedNom.setText(loggedInUser.getNom());
    }

    Utilisateurss getLoggedInUser() {
        return this.loggedInUser ;
    }
    @FXML
    void profilebutton(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/sportify/classement.fxml"));
        AnchorPane classementinterface = fxmlLoader.load();
        interfacechanger.getChildren().add(classementinterface) ;
    }


    @FXML
    void rechercheButton(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/sportify/roomranked.fxml"));
        AnchorPane classementinterface = fxmlLoader.load();
        RommrankedController rommranked = fxmlLoader.getController() ;
        rommranked.initData(getLoggedInUser());
        interfacechanger.getChildren().add(classementinterface) ;
    }
}
