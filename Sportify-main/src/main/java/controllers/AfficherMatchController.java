package controllers;

import entities.Arbitre;
import entities.Equipe;
import entities.Match;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.ServiceArbitre;
import services.ServiceMatch;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AfficherMatchController implements Initializable {
    private ServiceMatch s= new ServiceMatch();
    private Connection connection;
    @FXML
    private Button addButton;
    @FXML
    private Button refrech;
    @FXML
    private ListView<Match> matchListView;
    private ObservableList<Match> matchList = FXCollections.observableArrayList();
    private AjouterMatchController ajouterMatchController;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        matchListView.setCellFactory(new Callback<ListView<Match>, ListCell<Match>>() {
            @Override
            public ListCell<Match> call(ListView<Match> param) {
                return new MatchListCell();
            }
        });


        refreshListView();

    }
    @FXML
    private void handleRefreshButtonAction(ActionEvent event) {
        refreshListView();
    }

    // Méthode pour rafraîchir la ListView
    private void refreshListView() {
        Platform.runLater(() -> {
            matchListView.setItems(getMatchesFromDatabase());
            matchListView.refresh();
        });
    }

    private HBox createMatchNode(Match match) {
        Label nomLabel = new Label(String.format("%-20s", match.getNom()));
        Label typeLabel = new Label(String.format("%-15s", match.getType()));
        Label dateLabel = new Label(String.format("%-15s", match.getDate()));
        Label heureLabel = new Label(String.format("%-15s", match.getHeure()));
        Label descriptionLabel = new Label(String.format("%-30s", match.getDescription()));
        Label equipe1Label = new Label(String.format("%-20s (%s - Rang: %d)", match.getEquipe1().getNom(), match.getEquipe1().getNiveau(), match.getEquipe1().getRank()));
        Label equipe2Label = new Label(String.format("%-20s (%s - Rang: %d)", match.getEquipe2().getNom(), match.getEquipe2().getNiveau(), match.getEquipe2().getRank()));
        Label arbitreLabel = new Label(String.format("%-20s", match.getArbitre()));

        HBox hbox = new HBox(nomLabel, typeLabel, dateLabel, heureLabel, descriptionLabel, equipe1Label, equipe2Label, arbitreLabel);
        hbox.setSpacing(10);
        return hbox;

    }

    private ObservableList<Match> getMatchesFromDatabase() {
        ObservableList<Match> matchList = FXCollections.observableArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sportify", "root", "root");
            String query = "SELECT * FROM matc";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                Match match = new Match();
                match.setID_Matc(rs.getInt("ID_Matc"));
                match.setNom(rs.getString("nom"));
                match.setType(rs.getString("type"));
                LocalDate localDate = LocalDate.parse(rs.getString("Date"));
                Date sqlDate = Date.valueOf(localDate);
                match.setDate(sqlDate);
                match.setHeure(Time.valueOf(rs.getString("Heure")));
                match.setDescription(rs.getString("Description"));
                match.setEquipe1(getTeamName(rs.getInt("Equipe1")));
                match.setEquipe2(getTeamName(rs.getInt("Equipe2")));
                match.setArbitre(getRefereeName(rs.getInt("arbitre")));
                matchList.add(match);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return matchList;
    }
    private Equipe getTeamName(int teamId) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sportify", "root", "root");
        String query = "SELECT Nom,Niveau,`rank` FROM equipe WHERE IDEquipe = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, teamId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                    Equipe equipe = new Equipe();
                    equipe.setNom(resultSet.getString("Nom"));
                    equipe.setNiveau(resultSet.getString("Niveau"));
                    equipe.setRank(resultSet.getInt("Rank"));
                    return equipe;

            }
        }
        return null;
    }

    private Arbitre getRefereeName(int refereeId) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sportify", "root", "root");
        String query = "SELECT Nom,Prenom FROM arbitre WHERE id_arbitre = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, refereeId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Arbitre arbitre =new Arbitre();
                arbitre.setNom(resultSet.getString("Nom"));
                arbitre.setPrenom(resultSet.getString("Prenom"));


                return arbitre;
            }
        }
        return null;
    }

    public void handleAddButtonAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterMatch.fxml"));
            Parent root = loader.load();

            // Récupérer le contrôleur de la fenêtre AjouterMatch.fxml
            AjouterMatchController controller = (AjouterMatchController) loader.getController();

            // Afficher la fenêtre pour ajouter un nouveau match
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

            Match newMatch = controller.getNewlyAddedMatch();
            stage.close();


            if (newMatch != null) {
                matchList.add(newMatch);
                System.out.println("Nouveau match ajouté : " + newMatch);
                matchListView.setItems(getMatchesFromDatabase()); // Mettre à jour la ListView avec la nouvelle liste de matches
                matchListView.refresh(); // Rafraîchir la ListView pour afficher le nouveau match ajouté
                System.out.println("ListView rafraîchie");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void handleModifierButtonAction(ActionEvent actionEvent) {
        Match selectedMatch = matchListView.getSelectionModel().getSelectedItem();
        if (selectedMatch != null) {
            try {
                // Charger le fichier FXML de la fenêtre de modification
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierMatch.fxml"));
                Parent root = loader.load();

                // Récupérer le contrôleur de la fenêtre de modification
                ModifierMatch modifierMatchController = loader.getController();

                // Passer le match sélectionné au contrôleur de la fenêtre de modification
                modifierMatchController.initData(selectedMatch);

                // Créer une nouvelle fenêtre modale pour la fenêtre de modification
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Vous devez choisir de nouveau les équipes et l'arbitre.");

                alert.showAndWait();

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root));
                stage.showAndWait();

                System.out.println("Modification du match : " + selectedMatch);
                stage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

            System.out.println("Aucun match sélectionné.");
        }}
    public void handleSupprimerButtonAction(ActionEvent actionEvent) {
        Match selectedMatch = matchListView.getSelectionModel().getSelectedItem();
        if (selectedMatch != null) {
            try {
                s.supprimer(selectedMatch);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText("Supprimer le match sélectionné ?");
            alert.setContentText("Êtes-vous sûr de vouloir supprimer ce match ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

                matchList.remove(selectedMatch);
                System.out.println("Match supprimé : " + selectedMatch);
            }
        } else {
            System.out.println("Aucun match sélectionné.");
        }
    }
}



