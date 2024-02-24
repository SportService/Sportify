package services;
import entities.Utilisateur;
import utils.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ServiceUtilisateur implements IService<Utilisateur>{

    private Connection con;

    public ServiceUtilisateur() {
        con = DB.getInstance().getConnection();
    }


    // Method to retrieve Utilisateur object by ID
    public Utilisateur retrieveUtilisateurById(int id) throws SQLException {
        Utilisateur utilisateur = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Establish a connection to your database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sportify", "root", null);

            // Prepare the SQL statement to retrieve Utilisateur by ID
            String query = "SELECT * FROM Utilisateur WHERE ID_User = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            // Execute the query
            resultSet = statement.executeQuery();

            // If a record is found, create a Utilisateur object
            if (resultSet.next()) {
                utilisateur = new Utilisateur(
                        resultSet.getInt("ID_User"),
                        resultSet.getString("Nom"),
                        resultSet.getString("Prenom"),
                        resultSet.getString("Email"),
                        resultSet.getString("Mot_de_passe"),
                        resultSet.getString("Image"),
                        resultSet.getString("Niveau_competence"),
                        resultSet.getString("Role"),
                        resultSet.getString("Adresse"),
                        resultSet.getString("Sexe")
                );
            }
        } finally {
            // Close resources
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return utilisateur;
    }

    @Override
    public void ajouter(Utilisateur utilisateur) throws SQLException {

    }

    @Override
    public void modifier(Utilisateur utilisateur) throws SQLException {

    }

    @Override
    public void supprimer(Utilisateur utilisateur) throws SQLException {

    }

    @Override
    public List<Utilisateur> afficher() throws SQLException {
        return null;
    }
}
