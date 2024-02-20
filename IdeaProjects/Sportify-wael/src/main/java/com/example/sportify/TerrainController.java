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



    @FXML
    private TextField typet;
    @FXML
    private TextField prixt;

    @FXML
    private TextField nomt;
    @FXML
    private TextField loclt;

    @FXML
    private Button affter;

    @FXML
    private Button suptr;
    @FXML
    private Button modtr;





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
    void initialize() {

    }

    @FXML
    void affter(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageTerrain.fxml"));
        Parent root = loader.load();
        AffichageTerrainController a = loader.getController();
        nomt.getScene().setRoot(root);
    }




}


