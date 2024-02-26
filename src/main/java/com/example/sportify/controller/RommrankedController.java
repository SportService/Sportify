package com.example.sportify.controller;

import com.example.sportify.HelloApplication;
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




   @FXML
    void searchButton(MouseEvent event) throws IOException {
       System.out.println("BONOJORJ3426666--------------------------");
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/sportify/Listecompetition.fxml"));
        AnchorPane listeinterface = fxmlLoader.load();
        // Pass the interfacechanger to the RommrankedController

        interfaceranked.getChildren().add(listeinterface) ;

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/sportify/Playercard.fxml"));
            VBox vbox = fxmlLoader.load();
            // Optionally, you can get the controller instance if needed
            PlayerCardd.getChildren().add(vbox);

            //second card vide
            FXMLLoader fxmlLoaderr = new FXMLLoader(getClass().getResource("/com/example/sportify/Playercard.fxml"));
            VBox vboxvide = fxmlLoaderr.load();
            Playercard cardcontrol = fxmlLoaderr.getController();
            cardcontrol.setCard("Inconnue");

            cardvide.getChildren().add(vboxvide) ;
            cardvide.setOpacity(0.4);

            FXMLLoader fxmlLoaderdroit = new FXMLLoader(getClass().getResource("/com/example/sportify/Playercard.fxml"));
            VBox vboxvidedroit = fxmlLoaderdroit.load();
            Playercard cardcontroldroit = fxmlLoaderdroit.getController();
            cardcontroldroit.setCard("Inconnue");

            cardvidedroit.getChildren().add(vboxvidedroit) ;
            cardvidedroit.setOpacity(0.4);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }


    }
}
