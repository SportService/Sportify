package controllers;

import entities.Custom;
import entities.Equipe;
import entities.Match;
import entities.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.ServiceCustom;
import services.ServiceMatch;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AjouterMatchController implements Initializable  {

    ServiceMatch s= new ServiceMatch();
    ServiceCustom serviceCustom=new ServiceCustom(s);
    @FXML
    private Spinner<Integer> hoursSpinner;

    @FXML
    private Spinner<Integer> minutesSpinner;

    @FXML
    private Spinner<Integer> secondsSpinner;
    @FXML
    private TextField nomTextField;

    @FXML
    private TextField typeTextField;
    @FXML
    private DatePicker datePicker;

    //@FXML
   // private DatePicker dateTextField;
   // @FXML
    //private TextField heureTextField;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private TextField equipe1TextField;
    @FXML
    private TextField equipe2TextField;

    @FXML
    private TextField utilisateurTextField;

    @FXML
    private TextField dureeEstimeeTextField;
    @FXML
    private TextField dateCreationTextField;

    @FXML
    private TextField commentairesClientTextField;


    @FXML
    private TableView<Match> ListMatch;

    @FXML
    private TableColumn<Match, Integer> ID_Matc;

    @FXML
    private TableColumn<Match, String> Nom;

    @FXML
    private TableColumn<Match, String> Type;

    @FXML
    private TableColumn<Match, Date> Date;
    @FXML
    private TableColumn<Match, Time> Heure;
    @FXML
    private TableColumn<Match, String> Description;
    @FXML
    private TableColumn<Match, Integer> Equipe1;
    @FXML
    private TableColumn<Match, Integer> Equipe2;
    @FXML
    private TableColumn<Match, Void> ACTION;

    ObservableList<Match> observableList = FXCollections.observableArrayList();
    LocalDate currentDate = LocalDate.now();
    LocalTime heureActuelle = LocalTime.now();






    public AjouterMatchController() throws SQLException {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        datePicker.setValue(currentDate);
       // hoursSpinner.valueProperty().addListener((observable, oldValue, newValue) -> updateValues());
       // minutesSpinner.valueProperty().addListener((observable, oldValue, newValue) -> updateValues());
        //secondsSpinner.valueProperty().addListener((observable, oldValue, newValue) -> updateValues());

        try {
            observableList.addAll(s.afficher());
            ID_Matc.setCellValueFactory(new PropertyValueFactory<>("ID_Matc"));
            Nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
            Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
            Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
            Heure.setCellValueFactory(new PropertyValueFactory<>("Heure"));
            Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
            Equipe1.setCellValueFactory(new PropertyValueFactory<>("Equipe1"));
            Equipe2.setCellValueFactory(new PropertyValueFactory<>("Equipe2"));
            ACTION.setCellFactory(param -> new ButtonTableCell<>());


            ListMatch.setItems(observableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /*@FXML
    void AjouterCustom(ActionEvent event) throws SQLException {
        String idUtilisateurString = tf_user.getText(); // Récupérer l'identifiant de l'utilisateur sous forme de String
        int idUtilisateur = Integer.parseInt(idUtilisateurString); // Convertir le String en int

        // Utiliser l'identifiant pour récupérer l'utilisateur correspondant à partir de la base de données
        Utilisateur utilisateur = serviceCustom.getUtilisateurById(idUtilisateur); // Assurez-vous d'avoir une méthode pour récupérer un utilisateur par son identifiant dans votre service utilisateur

        int dureeEstimee = Integer.parseInt(tf_dureeEstimee.getText());
        Date dateCreation = java.sql.Date.valueOf(tf_dateCreation.getText());
        String commentairesClient = tf_commentairesClient.getText();
        String nom = tf_Nom.getText();
        String type = tf_Type.getText();
        String description = tf_Description.getText();
        Date date = java.sql.Date.valueOf(tf_Date.getText());
        Time heure = Time.valueOf(tf_Heure.getText());
        String idEquipe1String = tf_Equipe1.getText(); // Récupérer l'identifiant de l'utilisateur sous forme de String
        int idEquipe1 = Integer.parseInt(idEquipe1String); // Convertir le String en int

        // Utiliser l'identifiant pour récupérer l'utilisateur correspondant à partir de la base de données
        Equipe equipe1 = s.getEquipeById(idEquipe1); // Assurez-vous d'avoir une méthode pour récupérer un utilisateur par son identifiant dans votre service utilisateur

        String idEquipe2String = tf_Equipe2.getText(); // Récupérer l'identifiant de l'utilisateur sous forme de String
        int idEquipe2 = Integer.parseInt(idEquipe2String); // Convertir le String en int

        // Utiliser l'identifiant pour récupérer l'utilisateur correspondant à partir de la base de données
        Equipe equipe2 = s.getEquipeById(idEquipe1); // Assurez-vous d'avoir une méthode pour récupérer un utilisateur par son identifiant dans votre service utilisateur

        try {
            serviceCustom.ajouter(new Custom(utilisateur,dureeEstimee,dateCreation,commentairesClient,nom,type,description,date,heure,equipe1,equipe2));
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("personne ajoute");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }*/
    @FXML
    private Button creerCustomButton;

    @FXML
    private void creerCustomButtonClicked(ActionEvent event) {

        LocalDate selectedDate = datePicker.getValue();
        // Créez une nouvelle fenêtre modale
        Stage customStage = new Stage();
        customStage.initModality(Modality.APPLICATION_MODAL);

        // Créez une disposition pour le formulaire
        //AnchorPane formLayout = new AnchorPane();

        // Ajoutez des champs de saisie au formulaire

        TextField utilisateurTextField = new TextField();
        utilisateurTextField.setPromptText("Utilisateur");

        TextField dureeEstimeeTextField = new TextField();
        dureeEstimeeTextField.setPromptText("Durée estimée");

        //TextField dateCreationTextField = new TextField();
        //dateCreationTextField.setPromptText("Date de création");

        TextField commentairesClientTextField = new TextField();
        commentairesClientTextField.setPromptText("Commentaires du client");

        TextField nomTextField = new TextField();
        nomTextField.setPromptText("Nom");

        TextField typeTextField = new TextField();
        typeTextField.setPromptText("Type");

        TextField descriptionTextField = new TextField();
        descriptionTextField.setPromptText("Description");

        DatePicker dateTextField = new DatePicker();
        dateTextField.setValue(LocalDate.now());

        //TextField heureTextField = new TextField();
        //heureTextField.setPromptText("Heure");
        //int hours = hoursSpinner.getValue();
        //int minutes = minutesSpinner.getValue();
        //int seconds = secondsSpinner.getValue();

        TextField equipe1TextField = new TextField();
        equipe1TextField.setPromptText("ID Equipe 1");

        TextField equipe2TextField = new TextField();
        equipe2TextField.setPromptText("ID Equipe 2");


        // Ajoutez un bouton pour valider le formulaire
        Button validerButton = new Button("Valider");

        validerButton.setOnAction(e -> {
            hoursSpinner.increment(0);
            minutesSpinner.increment(0);
            secondsSpinner.increment(0);

            String idUtilisateurString = utilisateurTextField.getText(); // Récupérer l'identifiant de l'utilisateur sous forme de String
            int idUtilisateur = Integer.parseInt(idUtilisateurString); // Convertir le String en int

            // Utiliser l'identifiant pour récupérer l'utilisateur correspondant à partir de la base de données
            Utilisateur utilisateur = null; // Assurez-vous d'avoir une méthode pour récupérer un utilisateur par son identifiant dans votre service utilisateur
            try {
                utilisateur = serviceCustom.getUtilisateurById(idUtilisateur);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            int dureeEstimee = Integer.parseInt(dureeEstimeeTextField.getText());
            Date dateCreation = java.sql.Date.valueOf(currentDate);
            String commentairesClient = commentairesClientTextField.getText();
            String nom = nomTextField.getText();
            String type = typeTextField.getText();
            String description = descriptionTextField.getText();
            java.sql.Date date = java.sql.Date.valueOf(datePicker.getValue());
            int hours = hoursSpinner.getValue();
            int minutes = minutesSpinner.getValue();
            int seconds = secondsSpinner.getValue();
            Time time = Time.valueOf(String.format("%02d:%02d:%02d", hours, minutes, seconds));
            String idEquipe1String = equipe1TextField.getText(); // Récupérer l'identifiant de l'utilisateur sous forme de String
            int idEquipe1 = Integer.parseInt(idEquipe1String); // Convertir le String en int

            // Utiliser l'identifiant pour récupérer l'utilisateur correspondant à partir de la base de données
            Equipe equipe1 = null; // Assurez-vous d'avoir une méthode pour récupérer un utilisateur par son identifiant dans votre service utilisateur
            try {
                equipe1 = s.getEquipeById(idEquipe1);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            String idEquipe2String = equipe2TextField.getText(); // Récupérer l'identifiant de l'utilisateur sous forme de String
            int idEquipe2 = Integer.parseInt(idEquipe2String); // Convertir le String en int

            // Utiliser l'identifiant pour récupérer l'utilisateur correspondant à partir de la base de données
            Equipe equipe2 = null; // Assurez-vous d'avoir une méthode pour récupérer un utilisateur par son identifiant dans votre service utilisateur
            try {
                equipe2 = s.getEquipeById(idEquipe1);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }



            try {
                // Appel de la méthode d'enregistrement dans le service de données
                // Remplacez "service" par l'instance de votre service de données
                serviceCustom.ajouter(new Custom(utilisateur, dureeEstimee, dateCreation, commentairesClient, nom, type, description, date, time, equipe1, equipe2));

                // Affichage d'un message de succès
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setContentText("Données enregistrées avec succès !");
                alert.showAndWait();

                // Fermeture de la fenêtre modale
                customStage.close();
            } catch (SQLException ex) {
                // Gestion des exceptions
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Veuillez saisir des valeurs valides pour les champs numériques.");
                alert.showAndWait();
            }



            // Fermez la fenêtre modale après avoir traité les données du formulaire
            customStage.close();
        });
        VBox formLayout = new VBox(10); // 10 est l'espace vertical entre les éléments (en pixels)
        formLayout.getStyleClass().add("framelayout");
// Ajoutez des champs de saisie au formulaire
        formLayout.getChildren().addAll(
                utilisateurTextField,
                dureeEstimeeTextField,
                //dateCreationTextField,
                commentairesClientTextField,
                nomTextField,
                typeTextField,
                descriptionTextField,
                dateTextField,
                hoursSpinner,
                minutesSpinner,
                secondsSpinner,
               // heureTextField,
                equipe1TextField,
                equipe2TextField,
                validerButton
        );

        // Créez une scène pour la nouvelle fenêtre avec la disposition du formulaire
        Scene customScene = new Scene(formLayout, 600, 600);

        // Configurez la scène et affichez la fenêtre modale
        customStage.setScene(customScene);
        customStage.show();
    }
    /*private void updateValues() {
        int hours = hoursSpinner.getValue();
        int minutes = minutesSpinner.getValue();
        int seconds = secondsSpinner.getValue();
        Time time = Time.valueOf(String.format("%02d:%02d:%02d", hours, minutes, seconds));

        // Utilisez les valeurs mises à jour au besoin
    }*/
}

