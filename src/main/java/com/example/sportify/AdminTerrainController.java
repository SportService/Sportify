package com.example.sportify;

import entities.Terrain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.ServiceTerrain;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminTerrainController{
    private final ServiceTerrain ST = new ServiceTerrain();

    @FXML
    private TableView<Terrain> affichetr;
    private Terrain currentTerrain;

    @FXML
    private TableColumn<Terrain, Integer> idtr;

    @FXML
    private TableColumn<Terrain, String> localtr;

    @FXML
    private TableColumn<Terrain, String> nomtr;
    @FXML
    private TextField nomm;
    @FXML
    private TableColumn<Terrain, String> prixtr;

    @FXML
    private TableColumn<Terrain, String> typetr;

    @FXML
    private TableColumn<Terrain, String> idproptr;

    @FXML
    private TextField typet;
    @FXML
    private TextField prixt;

    @FXML
    private TextField nomt;
    @FXML
    private TextField loclt;
    @FXML
    private TextField idt;


    @FXML
    private Button suptr;
    @FXML
    private Button modtr;

    @FXML
    private void ajt(ActionEvent event) throws IOException {
        refreshTable();
        ServiceTerrain sm = new ServiceTerrain() ;
        Terrain c = new Terrain() ;


        StringBuilder errors=new StringBuilder();

        if(nomt.getText().trim().isEmpty()&&prixt.getText().trim().isEmpty()){
            errors.append("svp entrer un nom et le prix de terrain\n");
        }

        if(errors.length()>0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        } else {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Activite is added successfully!");
            alert.show();


            c.setNom(nomt.getText());
            c.setType_surface(typet.getText());
            c.setLocalisation(loclt.getText());
            c.setPrix(Double.parseDouble(prixt.getText()));
            //c.setActivite_id(Integer.parseInt(aid.getText()));



            sm.ajouter(c);

            // Réinitialisation des champs
            nomt.setText("");
            typet.setText("");
            loclt.setText("");
            prixt.setText("");
        }
    }


    @FXML
    void modtr(ActionEvent event) throws IOException {
        Terrain selectedTerrain = affichetr.getSelectionModel().getSelectedItem();
        if (selectedTerrain != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierTr.fxml"));
            Parent root = loader.load();

            ModifierTrController modifierTrController = loader.getController();
            modifierTrController.setTerrainData(selectedTerrain);
            modifierTrController.setPreviousController(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

            // Rafraîchir la table après la modification
            refreshTable();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune sélection");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un terrain à modifier.");
            alert.showAndWait();
        }
    }
    public void refreshTable() {
        try {
            List<Terrain> Ps = new ArrayList<>(ST.afficher());
            ObservableList<Terrain> observableList = FXCollections.observableList(Ps);
            affichetr.setItems(observableList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void initialize() {

        try {
            refreshTable();
            List<Terrain> Ps = new ArrayList<>(ST.afficher());
            ObservableList<Terrain> observableList = FXCollections.observableList(Ps);
            affichetr.setItems(observableList);
            // Set the on mouse clicked event handler
            affichetr.setOnMouseClicked(event -> {
                Terrain selectedItem = affichetr.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    nomm.setText(selectedItem.getNom());
                    // You can now use selectedItem to perform other actions as needed
                }
            });
        } catch (SQLException e) {
        }
        nomtr.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        typetr.setCellValueFactory(new PropertyValueFactory<>("Type_surface"));
        localtr.setCellValueFactory(new PropertyValueFactory<>("Localisation"));
        prixtr.setCellValueFactory(new PropertyValueFactory<>("Prix"));
    }

    public void suptr(ActionEvent event) {
        Terrain selectedTerrain = affichetr.getSelectionModel().getSelectedItem();
        if (selectedTerrain != null) {
            ServiceTerrain st = new ServiceTerrain();
            try {
                // Call the method to delete the terrain, assuming there is a method like deleteTerrain
                st.supprimer(selectedTerrain.getID_Terrain()); // replace getID_Terrain() with your actual method name for getting ID

                // Remove the selected item from the ListView
                affichetr.getItems().remove(selectedTerrain);

                // Clear the selection
                affichetr.getSelectionModel().clearSelection();

                // Clear the TextField if needed
                nomm.clear();

                // Show confirmation alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Terrain has been successfully deleted.");
                alert.showAndWait();
            } catch (SQLException e) {
                e.printStackTrace();
                // Show error alert
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Failed to delete terrain");
                alert.setContentText("An error occurred: " + e.getMessage());
                alert.showAndWait();
            }
        } else {
            // Show warning alert
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a terrain to delete.");
            alert.showAndWait();
        }
    }
}