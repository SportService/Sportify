package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;


public class DisplayEquipeController {

    @FXML
    private ListView<String> membersListView;
    @FXML
    private Button bntCreate;

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
    private Text tfNomEquipe;

    @FXML
    private TextField tfmembre1;

    @FXML
    private TextField tfmembre2;

    @FXML
    private VBox vbox1;


    @FXML
    private Text membersText;
    @FXML
    private Button bntRetour;

    public void initData(String teamName, String[] members) {
        tfNomEquipe.setText("Team Name: " + teamName);
        ObservableList<String> observableList = FXCollections.observableArrayList(members);
        membersListView.setItems(observableList);
    }
    // Method to set the data directly
    public void setData(String teamName, String[] members) {
        tfNomEquipe.setText("Team Name: " + teamName);
        ObservableList<String> observableList = FXCollections.observableArrayList(members);
        membersListView.setItems(observableList);
    }
    @FXML
    void Retourner(ActionEvent event) {
        // Load Equipes.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Equipes.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}







