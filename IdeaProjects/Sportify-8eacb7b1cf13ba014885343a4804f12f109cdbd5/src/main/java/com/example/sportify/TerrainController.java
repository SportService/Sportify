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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import services.ServiceTerrain;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private AnchorPane principal;
    @FXML
    private Button Import_btn;
    private Image image;

    @FXML
    private ImageView image_id;

    private String imagePath;



    @FXML
    private void ajt(ActionEvent event) {
        ServiceTerrain sm = new ServiceTerrain() ;

        Terrain c = new Terrain();
        c.setNom(nomt.getText());
        c.setType_surface(typet.getText());
        c.setLocalisation(loclt.getText());
        c.setPrix(Double.parseDouble(prixt.getText()));
        c.setImage_ter(imagePath);

        try {






            sm.ajouter(c, imagePath);

            // Show a dialog box to inform the user that the addition was successful
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Validation");
            alert.setContentText("Event ajouté avec succès");
            alert.showAndWait();

            // Reload the data in the TableView
            initialize();
        }  catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("SQL Exception");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
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


    public void Import(ActionEvent  Terrain) {

        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File", "*png", "*jpg"));

        File file = openFile.showOpenDialog(principal.getScene().getWindow());

        if (file != null) {

            imagePath = file.getAbsolutePath(); // Change this line
            image = new Image(file.toURI().toString(), 200, 200, false, true);

            image_id.setImage(image);
        }
    }


}


