package com.example.sportify.controller;

import entities.Competition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import services.CompetitionService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ListecompetitonController implements Initializable {

    @FXML
    private VBox vboxall;

    CompetitionService competitonService = new CompetitionService() ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      boolean color1=true ;
        try {
            List<Competition> competitions= competitonService.afficher() ;

        for (Competition compet:competitions) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/sportify/itemmatch.fxml"));
            HBox hboxitems = fxmlLoader.load();

            Itemmatch items = fxmlLoader.getController();
            items.SetText(compet.getNom(),compet.getDate(),compet.getHeure(),compet.getTerrain().getNomTerrain());
            //color start
            if (color1) {
                hboxitems.setStyle("-fx-background-color: #3A2F05");
            } else {
                hboxitems.setStyle("-fx-background-color: #271304");
            }

            color1 = !color1;
            //color end
            vboxall.getChildren().add(hboxitems);
        }        } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}

