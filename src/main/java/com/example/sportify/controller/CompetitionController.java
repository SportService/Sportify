package com.example.sportify.controller;

import com.example.sportify.HelloApplication;


import entities.Competition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;





import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import services.CompetitionService;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CompetitionController {

    @FXML
    private TableColumn<Competition, Date> col_date;

    @FXML
    private TableColumn<Competition, String> col_desc;

    @FXML
    private TableColumn<Competition, Time> col_heure;

    @FXML
    private TableColumn<Competition, String> col_nom;

    @FXML
    private TableColumn<Competition, String> col_type;

    @FXML
    private TableColumn<Competition, String> col_edit ;

    @FXML
    private TableView<Competition> tablecompet;

    Competition competi = null ;
     CompetitionService competitionService= new CompetitionService();

    @FXML
    public void initialize() {
      loadAll() ;
    }

    @FXML
    public void loadAll() {


        try {
            ObservableList<Competition> observableList = FXCollections.observableList(competitionService.afficher());
            System.out.println(observableList);
            tablecompet.setItems(observableList);

            System.out.println(tablecompet);
            col_nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
            col_desc.setCellValueFactory(new PropertyValueFactory<>("Description"));
            col_type.setCellValueFactory(new PropertyValueFactory<>("Type"));
            col_heure.setCellValueFactory(new PropertyValueFactory<>("Heure"));
            col_date.setCellValueFactory(new PropertyValueFactory<>("Date"));


            Callback<TableColumn<Competition, String>, TableCell<Competition, String>> cellFactory =
                    (TableColumn<Competition, String> param) -> new TableCell<>() {
                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                            } else {
                                Button deleteButton = new Button("Delete");
                                deleteButton.setStyle(
                                        "-fx-cursor: hand ;"
                                                + "-fx-background-color: #ff1744;"
                                                + "-fx-text-fill: white;"
                                                + "-fx-font-size: 14px;"
                                );
                                deleteButton.setOnAction(event -> {
                                    Competition competition = getTableView().getItems().get(getIndex());
                                    try {
                                        competitionService.supprimer(competition.getID_competiton());
                                        loadAll() ;
                                         // Assuming you have a refreshTable() method
                                    } catch (SQLException ex) {
                                        Logger.getLogger(CompetitionController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                });

                                setGraphic(new HBox(deleteButton));
                            }
                        }
                    };

            col_edit.setCellFactory(cellFactory);
        } catch (SQLException e) {
            Logger.getLogger(CompetitionController.class.getName()).log(Level.SEVERE, null, e);
        }
    }









                         /*   editIcon.setOnMouseClicked((MouseEvent event) -> {

                                student = studentsTable.getSelectionModel().getSelectedItem();
                                FXMLLoader loader = new FXMLLoader ();
                                loader.setLocation(getClass().getResource("/tableView/addStudent.fxml"));
                                try {
                                    loader.load();
                                } catch (IOException ex) {
                                    Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                AddStudentController addStudentController = loader.getController();
                                addStudentController.setUpdate(true);
                                addStudentController.setTextField(student.getId(), student.getName(),
                                        student.getBirth().toLocalDate(),student.getAdress(), student.getEmail());
                                Parent parent = loader.getRoot();
                                Stage stage = new Stage();
                                stage.setScene(new Scene(parent));
                                stage.initStyle(StageStyle.UTILITY);
                                stage.show();




                            });

                            HBox managebtn = new HBox(editIcon, deleteIcon);
                            managebtn.setStyle("-fx-alignment:center");
                            HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                            HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                            setGraphic(managebtn);

                            setText(null);

                        }
                    }

                };

                return cell;
            };
            editCol.setCellFactory(cellFoctory);
            studentsTable.setItems(StudentList);


        }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


                          */

    @FXML
    public void AddButton(MouseEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addcompetiton.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Image icon = new Image("https://i.ibb.co/dgpN1Hj/logo-SPORTIFY.png") ;
        Stage stage = new Stage();
        stage.getIcons().add(icon) ;
        stage.setResizable(false);
        stage.setTitle("Ajouter competition match");
        stage.setScene(scene);
        stage.show();
    }


        /*  try {

            List<Competition> competitions = competitionService.afficher();
            ObservableList<Competition> dataList = FXCollections.observableArrayList(competitions);

            // Set cell factory to customize the appearance of each item
            myListView.setCellFactory(new Callback<ListView<Competition>, ListCell<Competition>>() {
                @Override
                public ListCell<Competition> call(ListView<Competition> param) {
                    return new ListCell<Competition>() {
                        @Override
                        protected void updateItem(Competition item, boolean empty) {
                            super.updateItem(item, empty);
                            if (item != null) {
                                // Create a grid pane for table-like appearance
                                GridPane gridPane = new GridPane();
                                gridPane.setHgap(10); // Horizontal gap between cells

                                // Add labels and values to the grid pane
                                gridPane.addColumn(0,  new Text(item.getNom()+"                            "));
                                gridPane.addColumn(1,  new Text(item.getDescription()+"                            "));
                                gridPane.addColumn(2, new Text(item.getType()+"                            "));
                                gridPane.addColumn(3,  new Text(item.getHeure().toString()+"                            "));
                                gridPane.addColumn(4,  new Text(item.getDate().toString()+"                            "));

                                // Set the grid pane as the cell content
                                setGraphic(gridPane);
                            } else {
                                // Clear the cell content if item is null or empty
                                setGraphic(null);
                            }
                        }
                    };
                }
            });

            myListView.setItems(dataList);*/

             }