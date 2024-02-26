package com.example.sportify.controller;

import com.example.sportify.HelloApplication;
import entities.Competition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.CompetitionService;
import javafx.scene.input.MouseEvent;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;

public class Addcompetiton {

    @FXML
    private DatePicker Datee;

    @FXML
    private TextField DescText;

    @FXML
    private TextField TypeText;

    @FXML
    private Button annuler_btn;

    @FXML
    private TextField nomText;

    @FXML
    private Button save_btn;

    private CompetitionController competitionController;

    private boolean update ;

    private int competitonId ;
    CompetitionService competitionService = new CompetitionService(); // Create an instance of CompetitionService
    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) annuler_btn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void save(MouseEvent event) {
   /*     String name = nomText.getText();
        String desc = DescText.getText();
        String type = TypeText.getText();
        String birth = String.valueOf(Date.getValue());

*/
        if (nomText.getText().isEmpty() || DescText.getText().isEmpty() || TypeText.getText().isEmpty() ) {
                Alert alertt = new Alert(Alert.AlertType.ERROR);
                alertt.setHeaderText(null);
                alertt.setContentText("Please Fill All DATA");
                alertt.showAndWait(); }

        try {
            String birth = String.valueOf(Datee.getValue());
            System.out.println(birth);
            Competition compet = new Competition(competitonId,nomText.getText(), DescText.getText(), TypeText.getText(), Time.valueOf("12:12:00"), Date.valueOf(String.valueOf(Datee.getValue())));
            if (update == false) {
                competitionService.ajouter(compet);


                Stage stage = (Stage) save_btn.getScene().getWindow();
                stage.close();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("competition ajoute");
                alert.showAndWait();
                clean();

        }else { competitionService.modifier(compet);
                Stage stage = (Stage) save_btn.getScene().getWindow();
                stage.close();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Match Competition est modifiée");
                alert.showAndWait();
                clean();
            }

            competitionController.loadAll();


        } catch (SQLException e) {
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

    @FXML
    private void clean() {
        DescText.setText(null);
        Datee.setValue(null);
        TypeText.setText(null);
        nomText.setText(null);

    }


    void setTextField(int id ,  String nom , String desc , String Type , Date date) {
        competitonId=id ;
        nomText.setText(nom);
        DescText.setText(desc);
        TypeText.setText(Type);
        Datee.setValue(date.toLocalDate());
    }

    public void setCompetitionController(CompetitionController competitionController) {
        this.competitionController=competitionController ;
    }

    public void setUpdate( boolean bo) {this.update=bo ; }
}
