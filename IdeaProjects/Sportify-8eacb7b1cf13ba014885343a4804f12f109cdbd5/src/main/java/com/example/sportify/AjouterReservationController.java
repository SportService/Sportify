package com.example.sportify;

import entities.Reservation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import services.ServiceReservation;
import services.ServiceTerrain;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class AjouterReservationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    @FXML
    private Button button7;

    @FXML
    private Button button8;
    @FXML
    private Button button9;


    private int idTerrain;
    private ServiceReservation serviceReservation  = new ServiceReservation();


    public void setID_Terrain(int idTerrain) {
        this.idTerrain = idTerrain;
    }


    public AjouterReservationController() {
        // Vous pouvez initialiser des choses ici si nécessaire
    }

    @FXML
    public void initialize() {
        // Configurez ici les actions des boutons, si ce n'est pas déjà fait dans le FXML
        button1.setOnAction(this::handleReservationAction);
        button2.setOnAction(this::handleReservationAction);
        button3.setOnAction(this::handleReservationAction);
        button4.setOnAction(this::handleReservationAction);
        button5.setOnAction(this::handleReservationAction);
        button6.setOnAction(this::handleReservationAction);
        button7.setOnAction(this::handleReservationAction);
        button8.setOnAction(this::handleReservationAction);
        button9.setOnAction(this::handleReservationAction);
        // Répétez pour chaque bouton
    }

    private void handleReservationAction(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        // Remplacer "ID_utilisateur" par l'identifiant réel de l'utilisateur connecté
        int ID_utilisateur = 1; // Cet ID doit venir de votre système de gestion d'utilisateurs

        // Initialize DateTimeFormatter before the conditions
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime dateTime;

        if (clickedButton.equals(button1)) {
            dateTime = LocalDateTime.parse("02/03/2024 10:00:00", formatter);
        } else if (clickedButton.equals(button2)) {
            dateTime = LocalDateTime.parse("05/03/2024 11:00:00", formatter);
        } else if (clickedButton.equals(button3)) {
            dateTime = LocalDateTime.parse("07/03/2024 14:00:00", formatter);
        } else if (clickedButton.equals(button4)) {
            dateTime = LocalDateTime.parse("03/03/2024 17:00:00", formatter);
        } else if (clickedButton.equals(button5)) {
            dateTime = LocalDateTime.parse("05/03/2024 18:00:00", formatter);
        } else if (clickedButton.equals(button6)) {
            dateTime = LocalDateTime.parse("07/03/2024 19:00:00", formatter);
        } else if (clickedButton.equals(button7)) {
            dateTime = LocalDateTime.parse("03/03/2024 21:00:00", formatter);
        } else if (clickedButton.equals(button8)) {
            dateTime = LocalDateTime.parse("05/03/2024 22:00:00", formatter);
        } else if (clickedButton.equals(button9)) {
            dateTime = LocalDateTime.parse("07/03/2024 14:00:00", formatter);
        } else {
            // For other buttons, use the current date and time
            dateTime = LocalDateTime.now();
        }


        // La durée par défaut est d'une heure, mais cela peut être ajusté si nécessaire
        String duree = "1 heure"; // Par exemple, si vous avez besoin de la durée spécifique du bouton, utilisez clickedButton.getText();

        // Créez l'objet Reservation
        Reservation reservation = new Reservation(0, ID_utilisateur, idTerrain, dateTime, duree);

        // Utilisez le service pour ajouter la réservation
        serviceReservation.ajouter(reservation);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "La réservation a été effectuée avec succès.", ButtonType.OK);
        alert.setTitle("Réservation effectuée");
        alert.setHeaderText(null); // Vous pouvez mettre à null ou définir un texte d'en-tête
        alert.showAndWait();
    }


}