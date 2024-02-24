package controllers;

import entities.Categorie;
import entities.Equipe;
import javafx.beans.Observable;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ServiceCategorie;
import services.ServiceEquipe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class DashboardEquipeController {

    ServiceEquipe serviceEquipe=new ServiceEquipe();

    @FXML
    private TableColumn<Equipe, String> colNom;

    @FXML
    private TableColumn<Equipe, Categorie> colIDCateg;

    @FXML
    private TableColumn<Equipe, String> colNiveau;
    @FXML
    private TableColumn<Equipe, Boolean> colRandom;

    @FXML
    private TableColumn<Equipe, Integer> colRank;

    @FXML
    private MenuButton menuSports;

    @FXML
    private TableView<Equipe> tvEquipe;
    @FXML
    private Button ajouterButton;
    @FXML
    private TextField idCateg;

    @FXML
    private MenuButton menuButton;

    @FXML
    private Button modifierButton;

    @FXML
    private TextField niveau;

    @FXML
    private TextField nom;

    @FXML
    private TextField random;

    @FXML
    private TextField rank;

    @FXML
    private Button supprimerButton;


    public ServiceCategorie serviceCategorie = new ServiceCategorie();


    @FXML
    void initialize(){
        try {
            ObservableList<Equipe> observableList = FXCollections.observableList(serviceEquipe.afficher());
            tvEquipe.setItems(observableList);
            colNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
            colIDCateg.setCellValueFactory(new PropertyValueFactory<>("IDCateg"));
            colNiveau.setCellValueFactory(new PropertyValueFactory<>("Niveau"));
            colRandom.setCellValueFactory(cellData -> {
                Equipe equipe = cellData.getValue();
                return new SimpleBooleanProperty(equipe.isRandom);
            });

            colRank.setCellValueFactory(new PropertyValueFactory<>("rank"));
        }catch (SQLException e){
            System.out.println(e.getMessage());

        }
    }
    @FXML
    void ajouter(ActionEvent event) {
        try {
            // Retrieve values from input fields
            String nomEquipe = nom.getText();
            String niveauEquipe = niveau.getText();
            boolean isRandom = Boolean.parseBoolean(random.getText());
            int rankEquipe = Integer.parseInt(rank.getText());
            int categorieId = Integer.parseInt(idCateg.getText()); // Assuming you get the category ID from a text field

            // Retrieve the Categorie object corresponding to the category ID
            Categorie categorie = serviceCategorie.getCategorieById(categorieId); // Assuming you have a method to retrieve Categorie by ID

            // Create a new Equipe object
            Equipe newEquipe = new Equipe(nomEquipe, niveauEquipe,categorie, isRandom, rankEquipe);

            // Call the service method to add the Equipe to the database
            serviceEquipe.ajouter(newEquipe);

            // Refresh the TableView to reflect the changes
            refreshTableView();

            // Clear input fields after adding
            clearFields();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    void modifier(ActionEvent event) {
        try {
            // Get the selected Equipe from the TableView
            Equipe selectedEquipe = tvEquipe.getSelectionModel().getSelectedItem();

            if (selectedEquipe != null) {
                // Retrieve updated values from input fields
                String nomEquipe = nom.getText();
                String niveauEquipe = niveau.getText();
                boolean isRandom = Boolean.parseBoolean(random.getText());
                int rankEquipe = Integer.parseInt(rank.getText());
                int categorieId = Integer.parseInt(idCateg.getText()); // Assuming you get the category ID from a text field

                // Retrieve the Categorie object corresponding to the category ID
                Categorie categorie = serviceCategorie.getCategorieById(categorieId); // Assuming you have a method to retrieve Categorie by ID

                // Update the selected Equipe object
                selectedEquipe.setNom(nomEquipe);
                selectedEquipe.setIDCateg(categorie);
                selectedEquipe.setNiveau(niveauEquipe);
                selectedEquipe.setRandom(isRandom);
                selectedEquipe.setRank(rankEquipe);

                // Call the service method to update the Equipe in the database
                serviceEquipe.modifier(selectedEquipe);

                // Refresh the TableView to reflect the changes
                refreshTableView();

                // Clear input fields after modifying
                clearFields();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void supprimer(ActionEvent event) {
        try {
            // Get the selected Equipe from the TableView
            Equipe selectedEquipe = tvEquipe.getSelectionModel().getSelectedItem();

            if (selectedEquipe != null) {
                // Call the service method to delete the selected Equipe from the database
                serviceEquipe.supprimer(selectedEquipe);

                // Refresh the TableView to reflect the changes
                refreshTableView();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Helper method to refresh the TableView with updated data
    private void refreshTableView() throws SQLException {
        ObservableList<Equipe> observableList = FXCollections.observableList(serviceEquipe.afficher());
        tvEquipe.setItems(observableList);
    }

    // Helper method to clear input fields
    private void clearFields() {
        nom.clear();
        idCateg.clear();
        niveau.clear();
        random.clear();
        rank.clear();
    }
}





