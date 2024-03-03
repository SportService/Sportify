package services;

import entities.Utilisateurs;
import utils.DB;

import java.sql.*;
import java.util.List;

public class ServiceUtilisateurs implements IServiceE<Utilisateurs> {

    private Connection con;

    public ServiceUtilisateurs() {
        con = DB.getInstance().getConnection();
    }

    public Utilisateurs retrieveUtilisateurByEmail(String email) throws SQLException {
        Utilisateurs utilisateurs = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fianl", "root", null);

            String query = "SELECT * FROM Utilisateurs WHERE Email = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                utilisateurs = new Utilisateurs(
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

        return utilisateurs;
    }
    public Utilisateurs getUserByEmail(String email) throws SQLException {
        Utilisateurs utilisateurs = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fianl", "root", null);

            String query = "SELECT * FROM Utilisateurs WHERE Email = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                utilisateurs = new Utilisateurs(
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

        return utilisateurs;
    }


    @Override
    public void ajouter(Utilisateurs utilisateurs) throws SQLException {

    }

    @Override
    public void modifier(Utilisateurs utilisateurs) throws SQLException {

    }

    @Override
    public void supprimer(Utilisateurs utilisateurs) throws SQLException {

    }

    @Override
    public List<Utilisateurs> afficher() throws SQLException {
        return null;
    }
}
