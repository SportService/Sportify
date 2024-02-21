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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ServiceTerrain;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TerrainController{
    private final ServiceTerrain ST = new ServiceTerrain();

<<<<<<< HEAD

=======
    @FXML
    private TableView<Terrain> affichetr;

    @FXML
    private TableColumn<Terrain, Integer> idtr;

    @FXML
    private TableColumn<Terrain, String> localtr;

    @FXML
    private TableColumn<Terrain, String> nomtr;

    @FXML
    private TableColumn<Terrain, String> prixtr;

    @FXML
    private TableColumn<Terrain, String> typetr;

    @FXML
    private TableColumn<Terrain, String> idproptr;
>>>>>>> ad3df5a6931f001522d89c62a060c387855daa9a

    @FXML
    private TextField typet;
    @FXML
    private TextField prixt;

    @FXML
    private TextField nomt;
    @FXML
    private TextField loclt;
<<<<<<< HEAD

    @FXML
    private Button affter;
=======
    @FXML
    private TextField idt;

>>>>>>> ad3df5a6931f001522d89c62a060c387855daa9a

    @FXML
    private Button suptr;
    @FXML
    private Button modtr;

<<<<<<< HEAD



=======
/* try
    @FXML
    public void afficher() {
        {
            ServiceTerrain ST = new ServiceTerrain();
            List<Terrain> Ps = ST.afficher(); // Assuming ST.afficher() throws SQLException
            ObservableList<Terrain> observableList = FXCollections.observableList(Ps);
            affichetr.setItems(observableList);

            // Set the cell factories outside of the try-catch block
            nomtr.setCellValueFactory(new PropertyValueFactory<>("nom"));
            typetr.setCellValueFactory(new PropertyValueFactory<>("typeSurface"));
            localtr.setCellValueFactory(new PropertyValueFactory<>("localisation"));
            prixtr.setCellValueFactory(new PropertyValueFactory<>("prix"));
        } catch (SQLException e) {
            e.printStackTrace(); // Or handle the exception as you see fit

    }}*/
>>>>>>> ad3df5a6931f001522d89c62a060c387855daa9a

    @FXML
    private void ajt(ActionEvent event) throws IOException {
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
<<<<<<< HEAD
    void initialize() {

    }

    @FXML
    void affter(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageTerrain.fxml"));
        Parent root = loader.load();
        AffichageTerrainController a = loader.getController();
        nomt.getScene().setRoot(root);
    }



=======
    void modtr(ActionEvent event) {

        Terrain c = new Terrain();
        StringBuilder errors = new StringBuilder();

        if (idt.getText().trim().isEmpty()) {
            errors.append("Please enter an ID.\n");
        } else {
            try {
                c.setID_Terrain(Integer.parseInt(idt.getText().trim()));
            } catch (NumberFormatException e) {
                errors.append("ID must be a positive integer.\n");
            }
        }

        if (nomt.getText().trim().isEmpty()) {
            errors.append("Please enter a name.\n");
        } else {
            c.setNom(nomt.getText().trim());
        }

        if (typet.getText().trim().isEmpty()) {
            errors.append("Please enter a type.\n");
        } else {
            c.setType_surface(typet.getText().trim());
        }

        if (loclt.getText().trim().isEmpty()) {
            errors.append("Please enter a location.\n");
        } else {
            c.setLocalisation(loclt.getText().trim());
        }

        if (prixt.getText().trim().isEmpty()) {
            errors.append("Please enter a price.\n");
        } else {
            try {
                c.setPrix(Double.parseDouble(prixt.getText().trim()));
            } catch (NumberFormatException e) {
                errors.append("Please enter a valid price.\n");
            }
        }

        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Validation Error");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        } else {
            try {
                ServiceTerrain sm = new ServiceTerrain();
                sm.modifertr(c);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("The modification was successful.");
                alert.showAndWait();

                // Clear input fields
                idt.setText("");
                nomt.setText("");
                typet.setText("");
                loclt.setText("");
                prixt.setText("");

                // Refresh the table view here if necessary
                // refreshTable();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Modification Error");
                alert.setContentText("An error occurred while modifying the record: " + e.getMessage());
                alert.showAndWait();
            }
        }
    }




    @FXML
    void suptr(ActionEvent event) throws IOException, SQLException {

        ServiceTerrain sm = new ServiceTerrain() ;
        StringBuilder errors=new StringBuilder();
        if(idt.getText().trim().isEmpty()){
            errors.append("Please enter an id\n");
        }
        if(errors.length()>0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }
        sm.supprimer(Integer.parseInt(idt.getText()));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("Terrain is deleted successfully!");
        alert.show();
    }
    @FXML
    void initialize() {
        try {
            List<Terrain> Ps = new ArrayList<>(ST.afficher());
            ObservableList<Terrain> observableList = FXCollections.observableList(Ps);
            affichetr.setItems(observableList);
        } catch (SQLException e) {
        }
            nomtr.setCellValueFactory(new PropertyValueFactory<>("Nom"));
            typetr.setCellValueFactory(new PropertyValueFactory<>("Type_surface"));
            localtr.setCellValueFactory(new PropertyValueFactory<>("Localisation"));
            prixtr.setCellValueFactory(new PropertyValueFactory<>("Prix"));
    }
>>>>>>> ad3df5a6931f001522d89c62a060c387855daa9a

}


