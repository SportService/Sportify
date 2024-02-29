package services;

import entities.Equipe;
import entities.Terrain;
import utils.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceEquipe implements IService<Equipe> {

    private Connection con ;

    public ServiceEquipe() {
        this.con = DB.getInstance().getConnection();
    }


    @Override
    public void ajouter(Equipe equipe) throws SQLException {


    }

    @Override
    public void modifier(Equipe equipe) throws SQLException {

    }

    @Override
    public void supprimer(int t) throws SQLException {

    }

    @Override
    public void modifiert(int id, Equipe equipe) throws SQLException {

    }

    @Override
    public Equipe authentifier(String email, String password) throws SQLException {
        return null;
    }

    @Override
    public List<Equipe> afficher() throws SQLException {
        return null;
    }


    public List<Equipe> afficherNom() throws SQLException {
            List<Equipe> equipes = new ArrayList<>() ;
            String query = "SELECT IDEquipe , Nom FROM Equipe ";
            try (PreparedStatement statement = con.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Equipe equipe = new Equipe();
                        equipe.setID(resultSet.getInt("IDEquipe"));
                        equipe.setNom(resultSet.getString("Nom"));

                        equipes.add(equipe) ;
                    }
                }
            }
            return equipes;

    }
    public Equipe getById(int id) throws SQLException {
        Equipe equipe = null;
        String query = "SELECT * FROM Equipe WHERE IDEquipe = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    equipe = new Equipe();
                    equipe.setID(resultSet.getInt("IDEquipe"));
                    equipe.setNom(resultSet.getString("Nom"));
                }
            }
        }
        return equipe;
    }


}