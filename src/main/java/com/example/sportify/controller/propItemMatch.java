package com.example.sportify.controller;

import com.example.sportify.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

public class propItemMatch {
    @FXML
    private Button Scorebutton;

    @FXML
    private Label date;

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
    void Score(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/sportify/propScoreInterface.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Image icon = new Image("https://i.ibb.co/dgpN1Hj/logo-SPORTIFY.png");
        Stage stage = new Stage();
        stage.getIcons().add(icon);
        scene.setFill(Color.TRANSPARENT); // to make rounded corners
        stage.initStyle(StageStyle.TRANSPARENT); // to make rounded corners
        stage.setResizable(false);
        stage.setTitle("SCORE");
        stage.setScene(scene);
        stage.show();
    }
}
