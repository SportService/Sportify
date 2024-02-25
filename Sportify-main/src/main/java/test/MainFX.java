package test;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFX extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Crée et affiche la première fenêtre
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/AfficherMatch.fxml"));
        Parent root= fxmlLoader.load();
        Scene scene= new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/Scene.css").toExternalForm());
        Stage stage1 = new Stage();
        stage1.setScene(scene);
        stage1.setTitle("Gérer Match");
        stage1.show();

// Crée et affiche la deuxième fenêtre
        FXMLLoader fxmlLoader1= new FXMLLoader(getClass().getResource("/AfficherArbitre.fxml"));
        Parent root1= fxmlLoader1.load();
        Scene scene1= new Scene(root1);
        scene1.getStylesheets().add(getClass().getResource("/Scene.css").toExternalForm());
        Stage stage2 = new Stage();
        stage2.setScene(scene1);
        stage2.setTitle("Gérer Arbitres");
        stage2.show();

// Crée et affiche la troisième fenêtre
        FXMLLoader fxmlLoader2= new FXMLLoader(getClass().getResource("/afficherMatchtableview.fxml"));
        Parent root2= fxmlLoader2.load();
        Scene scene2= new Scene(root2);
        scene2.getStylesheets().add(getClass().getResource("/Scene.css").toExternalForm());
        Stage stage3 = new Stage();
        stage3.setScene(scene2);
        stage3.setTitle("Gérer Match");
        stage3.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
