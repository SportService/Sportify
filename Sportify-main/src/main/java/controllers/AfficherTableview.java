package controllers;

import entities.Arbitre;
import entities.Equipe;
import entities.Match;
import entities.Utilisateur;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.ServiceMatch;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AfficherTableview implements Initializable {


    ServiceMatch s= new ServiceMatch();




    @FXML
    private TableView<Match> ListMatch;

    @FXML
    private TableColumn<Match, Integer> ID_Matc;

    @FXML
    private TableColumn<Match, String> Nom;

    @FXML
    private TableColumn<Match, String> Type;

    @FXML
    private TableColumn<Match, java.sql.Date> Date;
    @FXML
    private TableColumn<Match, Time> Heure;
    @FXML
    private TableColumn<Match, String> Description;
    @FXML
    private TableColumn<Match, Equipe> Equipe1;
    @FXML
    private TableColumn<Match, Equipe> Equipe2;
    @FXML
    private TableColumn<Match, Arbitre> Arbitre;
    @FXML
    private TableColumn<Match, Void> ACTION;

    ObservableList<Match> observableList = FXCollections.observableArrayList();
    LocalDate currentDate = LocalDate.now();
    LocalTime heureActuelle = LocalTime.now();








    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        try {
            observableList.addAll(s.afficher());
           // ID_Matc.setCellValueFactory(new PropertyValueFactory<>("ID_Matc"));
            Nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
            Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
            Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
            Heure.setCellValueFactory(new PropertyValueFactory<>("Heure"));
            Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
            Equipe1.setCellValueFactory(new PropertyValueFactory<>("Equipe1"));
            Equipe2.setCellValueFactory(new PropertyValueFactory<>("Equipe2"));
            Arbitre.setCellValueFactory(new PropertyValueFactory<>("Arbitre"));





            ACTION.setCellFactory(param -> new ButtonTableCell<>());


            ListMatch.setItems(observableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}

