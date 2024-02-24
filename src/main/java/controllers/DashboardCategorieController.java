package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import entities.Categorie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ServiceCategorie;

public class DashboardCategorieController implements Initializable {



    @FXML
    private TableColumn<Categorie, String> colNom;
    @FXML
    private TableColumn<Categorie, String> colDesc;

    @FXML
    private TableColumn<Categorie, String> colImage;

    @FXML
    private TextField imageField;

    @FXML
    private MenuButton menuSports;

    @FXML
    private Button modifierButton;

    @FXML
    private TextField nomField;
    @FXML
    private TextField description;

    @FXML
    private Button supprimerButton;

    @FXML
    private TableView<Categorie> tv2;
    @FXML
    private Button btnChooseImage;
    @FXML
    private Button ajouterButton;
    @FXML
    private HBox hb;
    @FXML
    private Button returnButton;
    @FXML
    private VBox vbox1;

    @FXML
    private VBox vbox2;

    ServiceCategorie serviceCategorie=new ServiceCategorie();

    @FXML
    private void chooseImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");
        Stage stage = (Stage) btnChooseImage.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            // Get the selected file path
            String imageUrl = selectedFile.toURI().toString();
            // Set the text of the tfImage TextField with the selected file path
            imageField.setText(imageUrl);
        }
    }

   /* @FXML
    void initialize() {
        try {
            ObservableList<Categorie> observableList = FXCollections.observableList(serviceCategorie.afficher());
             tv2.setItems(observableList);
             colNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
            colDesc.setCellValueFactory(new PropertyValueFactory<>("Description"));
            colImage.setCellValueFactory(new PropertyValueFactory<>("Image"));
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }*/
    @FXML
    void ajouter(ActionEvent event) {
        try {
            ServiceCategorie serviceCategorie = new ServiceCategorie();
            System.out.println("*********");
            serviceCategorie.ajouter(new Categorie(nomField.getText(), description.getText(), imageField.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Categorie ajoutée");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    void modifier(ActionEvent event) {

        Categorie selectedCategorie = tv2.getSelectionModel().getSelectedItem();
        if (selectedCategorie != null) {

            selectedCategorie.setNom(nomField.getText());
            selectedCategorie.setDescription(description.getText());
            selectedCategorie.setImage(imageField.getText());
            try {

                serviceCategorie.modifier(selectedCategorie);

                ObservableList<Categorie> observableList = FXCollections.observableList(serviceCategorie.afficher());
                tv2.setItems(observableList);

                clearFields();
            } catch (SQLException e) {
                e.printStackTrace();

            }
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a categorie to modify.");
            alert.showAndWait();
        }
    }

    @FXML
    void supprimer(ActionEvent event) {

        Categorie selectedCategorie = tv2.getSelectionModel().getSelectedItem();
        if (selectedCategorie != null) {
            try {

                serviceCategorie.supprimer(selectedCategorie);

                ObservableList<Categorie> observableList = FXCollections.observableList(serviceCategorie.afficher());
                tv2.setItems(observableList);

                clearFields();
            } catch (SQLException e) {
                e.printStackTrace();

            }
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a categorie to delete.");
            alert.showAndWait();
        }
    }



    private void clearFields() {
        nomField.clear();
        description.clear();
        imageField.clear();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObservableList<Categorie> observableList = FXCollections.observableList(serviceCategorie.afficher());
            tv2.setItems(observableList);
            colNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
            colDesc.setCellValueFactory(new PropertyValueFactory<>("Description"));
            colImage.setCellValueFactory(new PropertyValueFactory<>("Image"));
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }

        for (MenuItem item : menuSports.getItems()) {
            item.setOnAction(this::handleMenuItemAction); // Assigning the event handler
        }
    }
    // Event handler for MenuItem action
    private void handleMenuItemAction(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        menuSports.setText(menuItem.getText()); // Update the text of the MenuButton
    }
    @FXML
    void retourner(ActionEvent event) {
        try {
            // Load the FXML file for DashboardEquipe
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DashboardEquipe.fxml"));
            Parent root = loader.load();

            // Get the controller for DashboardEquipe
            DashboardEquipeController controller = loader.getController();

            // Show the scene
            Stage stage = (Stage) returnButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
