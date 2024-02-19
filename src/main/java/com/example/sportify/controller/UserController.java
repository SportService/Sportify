package com.example.sportify.controller;

import entities.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;
import services.ServicUtilisateur;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class UserController {
    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField prenom2Field;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField adresseField;

    @FXML
    private TextField imageField;

    @FXML
    private TextField niveauCompetenceField;

    @FXML
    private TextField roleField;

    @FXML
    private TextField dateNaissanceField;

    @FXML
    private TextField loginEmailField;

    @FXML
    private PasswordField loginPasswordField;
    @FXML
    private Label errorLabel;
    @FXML
    private Button connexionButton;
    @FXML
    private TableView<Utilisateur> utilisateurTableView;
    @FXML
    private TextField idTextField;
    @FXML
    private TextField nomTextField;
    @FXML
    private TextField prenomTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField dateNaissanceTextField;
    @FXML
    private TextField niveauCompetenceTextField;
    @FXML
    private TextField roleTextField;
    @FXML
    private TextField sexeTextField;
    @FXML
    private TextField adresseTextField;
    @FXML
    private TableColumn<Utilisateur, String> nomColumn;

    @FXML
    private TableColumn<Utilisateur, String> prenomColumn;

    @FXML
    private TableColumn<Utilisateur, String> emailColumn;

    @FXML
    private TableColumn<Utilisateur, String> dateNaissanceColumn;

    @FXML
    private TableColumn<Utilisateur, String> niveauCompetenceColumn;

    @FXML
    private TableColumn<Utilisateur, String> roleColumn;

    @FXML
    private TableColumn<Utilisateur, String> sexeColumn;

    @FXML
    private TableColumn<Utilisateur, String> adresseColumn;

    @FXML
    private TableColumn<Utilisateur, String> idColumn;

    private ObservableList<Utilisateur> utilisateurList = FXCollections.observableArrayList();

    private Utilisateur loggedInUser;

    @FXML
    public void initialize() {
        if (loggedInUser != null) {
            nomField.setText(loggedInUser.getNom());
            prenomField.setText(loggedInUser.getPrenom());
            prenom2Field.setText(loggedInUser.getPrenom());
            passwordField.setText(loggedInUser.getMot_de_passe());
            emailField.setText(loggedInUser.getEmail());
            adresseField.setText(loggedInUser.getAdresse());
            niveauCompetenceField.setText(loggedInUser.getNiveau_competence());
            roleField.setText(loggedInUser.getRole());
            dateNaissanceField.setText(loggedInUser.getDate_de_naissance());
        }
    }



    private final ServicUtilisateur userService;

    public UserController() {
        this.userService = new ServicUtilisateur();
    }

    @FXML
    public void updateUserById(ActionEvent actionEvent) {
        if (loggedInUser != null) {
            try {
                userService.modifiert(loggedInUser.getId(), loggedInUser);
                System.out.println("Profile updated successfully!");

                initData(loggedInUser);
            } catch (SQLException e) {
                System.out.println("Error updating user: " + e.getMessage());
            }
        }
    }
    



    @FXML
    public void deleteUserById(ActionEvent actionEvent) {
        if (loggedInUser != null) {
            try {
                userService.supprimer(loggedInUser.getId());
                System.out.println("User deleted successfully!");

                // Optionally, perform additional actions after deletion

                // Clear the fields or navigate to another page
                // nomField.clear();
                // prenomField.clear();
                // ... (clear other fields)

            } catch (SQLException e) {
                System.out.println("Error deleting user: " + e.getMessage());
            }
        }
    }

    @FXML
    public void addUser(ActionEvent actionEvent) {
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String motDePasse = passwordField.getText();
        String email = emailField.getText();
        String adresse = adresseField.getText();
        String image = imageField.getText();
        String niveauCompetence = niveauCompetenceField.getText();
        String role = roleField.getText();
        String dateDeNaissance = dateNaissanceField.getText();

        Utilisateur newUser = new Utilisateur(nom, prenom, motDePasse, email,
                adresse, image, niveauCompetence, role, dateDeNaissance);

        try {
            userService.ajouter(newUser);
            System.out.println("User added successfully!");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sportify/login.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            stage.show();

            ((Stage) nomField.getScene().getWindow()).close();
        } catch (SQLException | IOException e) {
            System.out.println("Error adding user: " + e.getMessage());
        }
    }
    @FXML
    public void login(ActionEvent actionEvent) {
        String email = loginEmailField.getText();
        String password = loginPasswordField.getText();

        try {
            Utilisateur user = userService.authentifier(email, password);

            if (user != null) {
                System.out.println("Login successful!");

                if (user.getEmail() != null && user.getEmail().equals("admin") && user.getMot_de_passe().equals("admin")) {
                    // Redirect to admin page
                    redirectToAdminPage();
                } else {
                    // Set the logged-in user
                    loggedInUser = user;

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sportify/profile.fxml"));
                    Parent root = loader.load();

                    UserController profileController = loader.getController();

                    profileController.initData(user);

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Profile");
                    stage.show();

                    ((Stage) loginEmailField.getScene().getWindow()).close();
                }
            } else {
                errorLabel.setText("Invalid email or password. Please try again.");
            }
        } catch (SQLException | IOException e) {
            System.out.println("Error during login: " + e.getMessage());
        }
    }

    private void redirectToAdminPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sportify/admin.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Admin Page");
        stage.show();

        ((Stage) loginEmailField.getScene().getWindow()).close();
    }



    public void initData(Utilisateur user) {
        loggedInUser = user;
        System.out.println("Setting nomField: " + user.getNom());
        System.out.println("Setting prenomField: " + user.getPrenom());
        System.out.println("Setting passwordField: " + user.getMot_de_passe());

        // Set other fields
        nomField.setText(user.getNom());
        prenomField.setText(user.getPrenom());

        passwordField.setText(user.getMot_de_passe());
        emailField.setText(user.getEmail());
        adresseField.setText(user.getAdresse());
        niveauCompetenceField.setText(user.getNiveau_competence());
        roleField.setText(user.getRole());
        dateNaissanceField.setText(user.getDate_de_naissance());

    }



    @FXML
    public void goToLoginPage(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sportify/login.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            stage.show();

            // Optionally, close the current window (register window)
            ((Stage) connexionButton.getScene().getWindow()).close();
        } catch (IOException e) {
            System.out.println("Error loading login page: " + e.getMessage());
        }
    }
    @FXML
    public void goToREGISTER(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sportify/register.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("register");
            stage.show();

            // Optionally, close the current window (register window)
            ((Stage) connexionButton.getScene().getWindow()).close();
        } catch (IOException e) {
            System.out.println("Error loading login page: " + e.getMessage());
        }
    }


    private void updateTextFields(Utilisateur selectedUser) {
    }

    @FXML
    public void displayAllUsers(ActionEvent actionEvent) {
        try {
            List<Utilisateur> users = userService.afficher();

            if (users.isEmpty()) {
                System.out.println("No users found in the database.");
            } else {
                System.out.println("Found " + users.size() + " users in the database."); // Add this line
                utilisateurList.clear();
                utilisateurList.addAll(users);
                utilisateurTableView.setItems(utilisateurList);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching users: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Other initialization code...

        // Add a selection listener to the TableView
        utilisateurTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Update the text fields with the selected user's data
                updateTextFields(newValue);
            }
        });

        // Call displayAllUsers to populate the TableView initially
        displayAllUsers(new ActionEvent());

        // Call initializee to set up cell value factories
        initializee();
    }


    private void initializee() {
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        dateNaissanceColumn.setCellValueFactory(new PropertyValueFactory<>("date_de_naissance"));
        niveauCompetenceColumn.setCellValueFactory(new PropertyValueFactory<>("niveau_competence"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        sexeColumn.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    }
}

