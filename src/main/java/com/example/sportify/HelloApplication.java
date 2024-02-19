package com.example.sportify;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AdminMatchCompetition.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
       Image icon = new Image("https://i.ibb.co/dgpN1Hj/logo-SPORTIFY.png") ;
        stage.getIcons().add(icon) ;
        //stage.setResizable(false);
        stage.setTitle("login!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}