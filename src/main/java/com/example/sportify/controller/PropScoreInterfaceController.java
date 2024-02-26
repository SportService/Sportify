package com.example.sportify.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PropScoreInterfaceController {
    @FXML
    private TextField TypeText;

    @FXML
    private Button annuler_btn;

    @FXML
    private TextField loser;

    @FXML
    private TextField reclam;

    @FXML
    private Button save_btn;

    @FXML
    private Text score;

    @FXML
    private TextField winner;

    @FXML
    void close(MouseEvent event) {
            Stage stage = (Stage) annuler_btn.getScene().getWindow();
            stage.close();
        }



    @FXML
    void save(MouseEvent event) {
        Stage stage = (Stage) save_btn.getScene().getWindow();
        stage.close();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("Score ");
        alert.showAndWait();

    }
}
