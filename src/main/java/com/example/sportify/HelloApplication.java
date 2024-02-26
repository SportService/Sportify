package com.example.sportify;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AdminInterface.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        //scene.setFill(Color.TRANSPARENT); // to make rounded corners
       Image icon = new Image("https://i.ibb.co/dgpN1Hj/logo-SPORTIFY.png") ;
       //stage.initStyle(StageStyle.TRANSPARENT); // to make rounded corners
        stage.getIcons().add(icon) ;
        //stage.setResizable(false);
        stage.setTitle("SPORTIFY");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}