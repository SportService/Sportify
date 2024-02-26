package com.example.sportify.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import de.jensd.fx.glyphs.GlyphsStack ;
import javafx.scene.layout.VBox;


import java.io.IOException;

public class proprietaireInterfaceController {

    @FXML
    private AnchorPane interfacechanger;

    @FXML
    private AnchorPane navbar;

    @FXML
    private AnchorPane sidebar;

    @FXML
    private Label profilebutton;


    @FXML
    private Label competitionButton;



    public void profilebutton(MouseEvent mouseEvent) throws IOException {
        System.out.println("----------PROFIL CLASSEMENT");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/sportify/classement.fxml"));
        AnchorPane classementinterface = fxmlLoader.load();
        interfacechanger.getChildren().add(classementinterface) ;
    }

    @FXML
    void competition(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/sportify/propListeCompetition.fxml"));
        AnchorPane Competition = fxmlLoader.load();
        interfacechanger.getChildren().add(Competition) ;

    }
}
