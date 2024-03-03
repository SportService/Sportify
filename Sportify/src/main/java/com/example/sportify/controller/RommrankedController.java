package com.example.sportify.controller;

import entities.Utilisateurss;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RommrankedController implements Initializable {

    @FXML
    private VBox PlayerCardd;

    @FXML
    private VBox cardvide;

    @FXML
    private VBox cardvidedroit;

    @FXML
    private Button searchmatch;

    @FXML
    private AnchorPane interfaceranked;

    private Utilisateurss loggedInUser;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
   @FXML
    void searchButton(MouseEvent event) throws IOException {
       System.out.println("BONOJORJ3426666--------------------------");
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/sportify/Listecompetition.fxml"));
        AnchorPane listeinterface = fxmlLoader.load();
        ListecompetitonController PlayerListeCompetition = fxmlLoader.getController() ;
        PlayerListeCompetition.initData(loggedInUser) ;
        // Pass the interfacechanger to the RommrankedController

        interfaceranked.getChildren().add(listeinterface) ;

    }

    void initData(Utilisateurss user) {
        this.loggedInUser=user ;
        loadPlayerCards() ;
    }

    private void loadPlayerCards() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/sportify/Playercard.fxml"));
            VBox vbox = fxmlLoader.load();
            Playercard cardcontrol = fxmlLoader.getController();
            cardcontrol.setCard(loggedInUser.getNom());

            // Optionally, you can get the controller instance if needed
            PlayerCardd.getChildren().add(vbox);

            //second card vide
            FXMLLoader fxmlLoadergauche = new FXMLLoader(getClass().getResource("/com/example/sportify/Playercard.fxml"));
            VBox vboxvide = fxmlLoadergauche.load();
            Playercard cardcontrolgauche = fxmlLoadergauche.getController();
            cardcontrolgauche.setCard("Inconnue");

            cardvide.getChildren().add(vboxvide);
            cardvide.setOpacity(0.4);

            FXMLLoader fxmlLoaderdroit = new FXMLLoader(getClass().getResource("/com/example/sportify/Playercard.fxml"));
            VBox vboxvidedroit = fxmlLoaderdroit.load();
            Playercard cardcontroldroit = fxmlLoaderdroit.getController();
            cardcontroldroit.setCard("Inconnue");

            cardvidedroit.getChildren().add(vboxvidedroit);
            cardvidedroit.setOpacity(0.4);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }
    }




}
