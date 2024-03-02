package com.example.sportify.controller;

import entities.Competition;
import entities.Equipe;
import entities.Score;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.ServiceScore;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PropScoreInterfaceController implements Initializable {
    @FXML
    private Button annuler_btn;

    @FXML
    private Text equipe1;

    @FXML
    private Text equipe2;


    @FXML
    private TextField playere1;

    @FXML
    private TextField playere2;

    @FXML
    private Button save_btn;

    private Competition competsave ;

    @FXML
    private TextField scoreequipe1;

    @FXML
    private TextField scoreequipe2;

    private Equipe winner ;
    private Equipe loser ;
    private ServiceScore scoreService= new ServiceScore() ;

    private Score score ;


    @FXML
    void close(MouseEvent event) {
            Stage stage = (Stage) annuler_btn.getScene().getWindow();
            stage.close();
        }


    void setScore(Competition compet) {
        equipe1.setText(compet.getEquipe1().getNom());
        equipe2.setText(compet.getEquipe2().getNom());

        try {
            score = scoreService.getScoreByCompet(compet);
            if (score != null) {
                scoreequipe1.setText(String.valueOf(score.getEquipe1Score()));
                scoreequipe2.setText(String.valueOf(score.getEquipe2Score()));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        competsave=compet ;
    }

    @FXML
    void save(MouseEvent event) throws SQLException {


        if (scoreequipe1.getText().equals("-") || scoreequipe2.getText().equals("-")) {
            Alert alertt = new Alert(Alert.AlertType.ERROR);
            alertt.setHeaderText(null);
            alertt.setContentText("Please Fill All DATA");
            alertt.showAndWait();
        } else if (equipe1.getText().isEmpty() || scoreequipe2.getText().isEmpty() ) {
            Alert alertt = new Alert(Alert.AlertType.ERROR);
            alertt.setHeaderText(null);
            alertt.setContentText("Les équipes ne sont pas entrées dans le match");
            alertt.showAndWait(); }
        else {
            try {

                int scoreE1 = Integer.parseInt(scoreequipe1.getText());
                int scoreE2 = Integer.parseInt(scoreequipe2.getText());


        if (scoreE1 > scoreE2) {
            winner = competsave.getEquipe1();
            loser = competsave.getEquipe2();
        } else if (scoreE1 < scoreE2) {
            winner = competsave.getEquipe2();
            loser = competsave.getEquipe1();
        } else {
            winner = null;
            loser = null;
        }
            if (score == null) {
                score = new Score(competsave, winner, loser, scoreE1, scoreE2, "", "");
                scoreService.ajouter(score);
            } else {
                Score scoree = new Score(score.getIdScore(),competsave, winner, loser, scoreE1, scoreE2, "", "");
                // Update the existing score in the database
                scoreService.modifier(scoree);
            }

            Stage stage = (Stage) save_btn.getScene().getWindow();
            stage.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Score Enregistrer ");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scoreequipe1.setText("-");
        scoreequipe2.setText("-");
    }

}
