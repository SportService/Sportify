package controllers;

import entities.Categorie;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.ServiceCategorie;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;


public class CategorieClientController implements Initializable {

    @FXML
    private ListView<Categorie> categoriesListView;
    private Connection con;

    private ServiceCategorie serviceCategorie;
    @FXML
    private MenuButton menuButton;

    @FXML
    private Button CreateEquipe;


    // In your ServiceCategorie class, add a method to retrieve image paths from the database
    public List<String> getImagePaths() throws SQLException {
        List<String> imagePaths = new ArrayList<>();
        String query = "SELECT Image FROM categorie";
        try (PreparedStatement statement = con.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String imagePath = resultSet.getString("Image");
                imagePaths.add(imagePath);
            }
        }
        return imagePaths;
    }

    // In your CategorieClientController class, use the retrieved image paths to create Image objects
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        serviceCategorie = new ServiceCategorie();

        try {
            // Get the list of categories from the service
            List<Categorie> categories = serviceCategorie.afficher();
            categoriesListView.setItems(FXCollections.observableList(categories));

            // Customize how items are displayed in the ListView
            categoriesListView.setCellFactory(new Callback<>() {
                @Override
                public ListCell<Categorie> call(ListView<Categorie> param) {
                    return new ListCell<>() {
                        @Override
                        protected void updateItem(Categorie item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty || item == null) {
                                setText(null);
                                setGraphic(null);
                            } else {
                                setText(item.getNom() + " - " + item.getDescription());
                                // Load the image from the file path
                                String imagePath = item.getImage();
                                try {
                                    if (imagePath != null && !imagePath.isEmpty()) {
                                         imagePath = "/img/807f757c-tennis-gf8a2b0441_1920-768x513.jpg";
                                        Image image = new Image(getClass().getResource(imagePath).toExternalForm());

                                      //  Image image = new Image(imagePath);
                                        ImageView imageView = new ImageView(image);
                                        imageView.setFitWidth(50); // Set the width of the image view
                                        imageView.setFitHeight(50); // Set the height of the image view
                                        setGraphic(imageView); // Set the image view as the graphic of the cell
                                    } else {
                                        // Handle empty or null imagePath, for example, display a default image
                                        Image defaultImage = new Image("src/main/resources/img/180358.jpg");
                                        ImageView imageView = new ImageView(defaultImage);
                                        imageView.setFitWidth(50); // Set the width of the default image view
                                        imageView.setFitHeight(50); // Set the height of the default image view
                                        setGraphic(imageView); // Set the default image view as the graphic of the cell
                                    }
                                } catch (IllegalArgumentException e) {
                                    System.err.println("Error loading image: " + e.getMessage());
                                    // Handle the exception appropriately
                                }
                            }
                        }
                    };
                }
            });
            // Add event handlers to MenuItems
            for (MenuItem item : menuButton.getItems()) {
                item.setOnAction(this::handleMenuItemAction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }
    // Event handler for MenuItem action
    private void handleMenuItemAction(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        menuButton.setText(menuItem.getText()); // Update the text of the MenuButton
    }
    @FXML
    void Create(ActionEvent event) {
        // Load CreationEquipe.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/CreationEquipe.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
