package controllers;

import entities.Arbitre;
import entities.Equipe;
import entities.Match;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import services.ServiceArbitre;
import services.ServiceMatch;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AjouterMatchController implements Initializable {
    @FXML
    private ListView<Match> matchListView;

    @FXML
    private TextField idMatchField;

    @FXML
    private TextField nomField;

    @FXML
    private ComboBox<String> typeComboBox;


    @FXML
    private TextField descriptionField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField heureField;

    @FXML
    private TextField minField;

    @FXML
    private ComboBox<Equipe> equipe1ComboBox;

    @FXML
    private ComboBox<Equipe> equipe2ComboBox;

    @FXML
    private ComboBox<Arbitre> arbitreComboBox;

    @FXML
    private Button addButton;

    @FXML
    private Button supprimerButton;




    private ServiceMatch serviceMatch;
    private ServiceArbitre serviceArbitre;
    private Match newMatch;

    public Match getNewlyAddedMatch() {
        return newMatch;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        serviceMatch = new ServiceMatch();
        serviceArbitre = new ServiceArbitre();



        loadEquipes();
        loadArbitres();

    }

    private void loadEquipes() {
        try {
            ObservableList<Equipe> equipes = FXCollections.observableArrayList(serviceMatch.getAllEquipes());
            equipe1ComboBox.setItems(equipes);
            equipe2ComboBox.setItems(equipes);
        } catch (SQLException e) {
            showAlert("Erreur lors du chargement des équipes : " + e.getMessage());
        }
    }
    public void showMatch(ObservableList<Match> matchList) {
        matchListView.setItems(matchList);
    }


    private void loadArbitres() {
        try {
            ObservableList<Arbitre> arbitres = FXCollections.observableArrayList(serviceArbitre.getAllArbitres());
           arbitreComboBox.setItems(arbitres);

        } catch (SQLException e) {
            showAlert("Erreur lors du chargement des arbitres : " + e.getMessage());
        }
    }

    @FXML
    private void ajouterMatch() {
        // Récupérer les informations du match depuis les champs de texte et les éléments ComboBox
        String nom = nomField.getText();
        if (nom.isEmpty() || nom.length() < 3 || nom.length() > 25) {
            showAlert("Le nom doit contenir entre 3 et 25 caractères !");
            return;
        }

        String type = typeComboBox.getValue();
        if (type == null || type.isEmpty()) {
            showAlert("Veuillez sélectionner un type !");
            return;
        }

        String description = descriptionField.getText();
        if (description.isEmpty() || description.length() < 5 || description.length() > 50) {
            showAlert("La description doit contenir entre 5 et 50 caractères !");
            return;
        }

        LocalDate localDate = datePicker.getValue();
        if (localDate == null) {
            showAlert("Veuillez sélectionner une date !");
            return;
        }

        if (localDate.isBefore(LocalDate.now())) {
            showAlert("Veuillez sélectionner une date future !");
            return;
        }

        if (localDate.isAfter(LocalDate.now().plusDays(15))) {
            showAlert("La date sélectionnée ne peut pas être plus de 15 jours dans le futur !");
            return;
        }

        int heures;
        try {
            heures = Integer.parseInt(heureField.getText());
        } catch (NumberFormatException e) {
            showAlert("Veuillez entrer une valeur numérique pour les heures !");
            return;
        }

        if (heures < 0 || heures > 23) {
            showAlert("Veuillez entrer une valeur entre 0 et 23 pour les heures !");
            return;
        }

        int minutes;
        try {
            minutes = Integer.parseInt(minField.getText());
        } catch (NumberFormatException e) {
            showAlert("Veuillez entrer une valeur numérique pour les minutes !");
            return;
        }

        if (minutes < 0 || minutes > 59) {
            showAlert("Veuillez entrer une valeur entre 0 et 59 pour les minutes !");
            return;
        }

        String heureString = heureField.getText() + ":" + minField.getText() + ":00";
        Time heure = Time.valueOf(heureString);

        Equipe equipe1 = equipe1ComboBox.getValue();
        if (equipe1 == null) {
            showAlert("Veuillez sélectionner une équipe 1 !");
            return;
        }

        Equipe equipe2 = equipe2ComboBox.getValue();
        if (equipe2 == null) {
            showAlert("Veuillez sélectionner une équipe 2 !");
            return;
        }

        if (equipe1.equals(equipe2)) {
            showAlert("Les équipes sélectionnées ne peuvent pas être les mêmes !");
            return;
        }

        Arbitre arbitre = arbitreComboBox.getValue();
        if (arbitre == null) {
            showAlert("Veuillez sélectionner un arbitre !");
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation");
        confirmation.setHeaderText("Voulez-vous vraiment ajouter ce match ?");
        confirmation.setContentText("Nom: " + nom + "\n"
                + "Type: " + type + "\n"
                + "Description: " + description + "\n"
                + "Date: " + localDate + "\n"
                + "Heure: " + heure + "\n"
                + "Équipe 1: " + equipe1.getNom() + "\n"
                + "Équipe 2: " + equipe2.getNom() + "\n"
                + "Arbitre: " + arbitre.getNom() + " " + arbitre.getPrenom());

        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {

            try {
                serviceMatch.ajouter(new Match(nom, type, description, Date.valueOf(localDate), heure, equipe1, equipe2, arbitre));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


            Stage stage = (Stage) nomField.getScene().getWindow();
            stage.close();
        } else {
            System.out.println("L'utilisateur a annulé l'ajout du match.");

            nomField.clear();
            typeComboBox.setValue(null);
            descriptionField.clear();
            datePicker.setValue(null);
            heureField.clear();
            minField.clear();
            equipe1ComboBox.setValue(null);
            equipe2ComboBox.setValue(null);
            arbitreComboBox.setValue(null);
        }}

        private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    private void modifierMatch() {

        String nom = nomField.getText();
        String type = typeComboBox.getValue();
        String description = descriptionField.getText();
        LocalDate localDate = datePicker.getValue();
        Date date = Date.valueOf(localDate);
        int heures = Integer.parseInt(heureField.getText());
        int minutes = Integer.parseInt(minField.getText());

        if (heures < 0 || heures > 23 || minutes < 0 || minutes > 59) {
            showAlert("Veuillez entrer des valeurs valides pour les heures et les minutes.");
            return;
        }
        String heureString  = heureField.getText()+":"+minField.getText()+":00";
        Time heure = Time.valueOf(heureString);
        Equipe equipe1 = equipe1ComboBox.getValue();
        Equipe equipe2 = equipe2ComboBox.getValue();
        Arbitre arbitre= arbitreComboBox.getValue();
        if (heures < 0 || heures > 23 || minutes < 0 || minutes > 59) {
            showAlert("Veuillez entrer des valeurs valides pour les heures et les minutes.");
            return;
        }


        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation");
        confirmation.setHeaderText("Voulez-vous vraiment ajouter ce match ?");
        confirmation.setContentText("Nom: " + nom + "\n"
                + "Type: " + type + "\n"
                + "Description: " + description + "\n"
                + "Date: " + date + "\n"
                + "Heure: " + heure + "\n"
                + "Équipe 1: " + equipe1 + "\n"
                + "Équipe 2: " + equipe2 + "\n"
                + "Arbitre: " + arbitre);


        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {

            try {
                serviceMatch.modifier(new Match(nom, type, description, date, heure, equipe1, equipe2, arbitre));

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }



        } else {
            System.out.println("L'utilisateur a annulé l'ajout du match.");

            // Réinitialiser les champs du formulaire
            nomField.setText("");
            typeComboBox.setValue(null);
            descriptionField.setText("");
            datePicker.setValue(null);
            heureField.setText("");
            equipe1ComboBox.setValue(null);
            equipe2ComboBox.setValue(null);
            arbitreComboBox.setValue(null);
        }

    }

}
