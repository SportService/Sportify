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

    public Utilisateur retrieveUtilisateurByEmail(String email) throws SQLException {
        Utilisateur utilisateur = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sportify", "root", null);

            String query = "SELECT * FROM Utilisateur WHERE Email = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
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
    public Utilisateur getUserByEmail(String email) throws SQLException {
        Utilisateur utilisateur = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sportify", "root", null);

            String query = "SELECT * FROM Utilisateur WHERE Email = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
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
