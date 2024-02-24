package controllers;

import entities.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.Node;
import javafx.stage.Window;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;


public class AfficherEquipeController implements Initializable {

    @FXML
    private Button btnCreate;

    @FXML
    private Button btnAfficher;

    @FXML
    private MenuButton menuSports;

    @FXML
    private Text tfListe;

    @FXML
    private TextField tfMembre10;

    @FXML
    private TextField tfMembre11;

    @FXML
    private TextField tfMembre3;

    @FXML
    private TextField tfMembre4;

    @FXML
    private TextField tfMembre5;

    @FXML
    private TextField tfMembre6;

    @FXML
    private TextField tfMembre7;

    @FXML
    private TextField tfMembre8;

    @FXML
    private TextField tfMembre9;

    @FXML
    private Text tfNom;

    @FXML
    private TextField tfNomEquipe;

    @FXML
    private TextField tfmembre1;

    @FXML
    private TextField tfmembre2;
    @FXML
    private Text membersText;

    @FXML
    private VBox vbox1;

    public void initData(String teamName, String members) {
        tfNomEquipe.setText("Team Name: " + teamName);
        membersText.setText("Members: " + members);
    }


    @FXML
    void create(ActionEvent event) {
        String nomEquipe = tfNomEquipe.getText();
        String membre1 = tfmembre1.getText();
        String membre2 = tfmembre2.getText();
        String membre3= tfMembre3.getText();
        String membre4 = tfMembre4.getText();
        String membre5 = tfMembre5.getText();
        String membre6= tfMembre6.getText();
        String membre7 = tfMembre7.getText();
        String membre8 = tfMembre8.getText();
        String membre9 = tfMembre9.getText();
        String membre10 = tfMembre10.getText();
        String membre11 = tfMembre11.getText();
        if (nomEquipe.isEmpty() || membre1.isEmpty() || membre2.isEmpty()) {
            showAlert("Veuillez remplir le nom de l'équipe et au moins deux membres.");
            return;
        }


        Team equipe = new Team(nomEquipe, Arrays.asList(membre1, membre2,membre3,membre4));
        System.out.println("Equipe ajoutée: " + equipe.toString());
        displayEquipe(equipe, ((Node) event.getSource()).getScene().getWindow());

    }
    @FXML
    void afficher(ActionEvent event) throws IOException {
        String nomEquipe = tfNomEquipe.getText();
        String membre1 = tfmembre1.getText();
        String membre2 = tfmembre2.getText();

        if (nomEquipe.isEmpty() || membre1.isEmpty() || membre2.isEmpty()) {
            showAlert("Veuillez remplir le nom de l'équipe et au moins deux membres.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DisplayEquipe.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }


    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void displayEquipe(Team equipe, Window window) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DisplayEquipe.fxml"));
            Parent root = loader.load();

            DisplayEquipeController controller = loader.getController();
            controller.setData(equipe.getNomEquipe(), equipe.getMembres().toArray(new String[0]));

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initOwner(window);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
