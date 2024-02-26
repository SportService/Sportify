package services;

import entities.Competition;
import entities.Equipe;
import entities.Terrain;
import utils.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompetitionService implements IService<Competition> {

    private Connection con ;

    public CompetitionService() {
        this.con = DB.getInstance().getConnection();
    }


    @Override
    public void ajouter(Competition competition) throws SQLException {
        String req = "insert into competition (Nom,Description,Type,Date,Heure,Terrain_id)"+
                "values ('"+competition.getNom()+"','"+competition.getDescription()+"','"+competition.getType()+"','"+competition.getDate()+"','"+competition.getHeure()+"','"+competition.getTerrain().getID_Terrain()+"')" ;
        Statement ste = con.createStatement();
        ste.executeUpdate(req);
    }

    @Override
    public void modifier(Competition competition) throws SQLException {
        String req = "update competition set nom=? , Description=? , Type=? , Heure=? , Date=? where ID_Competition=?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1,competition.getNom());
        pre.setString(2,competition.getDescription());
        pre.setString(3,competition.getType());
        pre.setTime(4,competition.getHeure());
        pre.setDate(5,competition.getDate());
        pre.setInt(6,competition.getID_competiton());


        pre.executeUpdate();
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req="DELETE FROM competition WHERE ID_Competition=?" ;
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1,id) ;
        pre.executeUpdate() ;
    }

    @Override
    public void modifiert(int id, Competition competition) throws SQLException {

    }


    @Override
    public Competition authentifier(String email, String password) throws SQLException {
        return null;
    }

    @Override
    public List<Competition> afficher() throws SQLException {
       List<Competition> pers = new ArrayList<>();

        String req = "SELECT c.*, t.* FROM Competition c INNER JOIN Terrain t ON c.Terrain_id = t.ID_Terrain";
        PreparedStatement pre = con.prepareStatement(req);
        ResultSet res= pre.executeQuery();
        while (res.next()){
            Competition c = new Competition();
            c.setID_competiton(res.getInt(1));
            c.setNom(res.getString("nom"));
            c.setDescription(res.getString(6));
            c.setType(res.getString("Type"));
            c.setDate(res.getDate("Date"));
            c.setHeure(res.getTime("Heure"));

           Terrain terrain = new Terrain();
            terrain.setID_Terrain(res.getInt("ID_Terrain"));
            terrain.setNomTerrain(res.getString("NomTerrain")); // Issue might be here
            terrain.setType_surface(res.getString("Type_surface"));
            terrain.setLocalisation(res.getString("Localisation"));
            terrain.setPrix(res.getDouble("Prix"));
            terrain.setID_Proprietaire(res.getInt("ID_Propriétaire"));

            c.setTerrain(terrain);
            pers.add(c);
        }
        return pers;
    }

    public Competition getById(int id) throws SQLException {
        Competition competition = null;
        String query = "SELECT * FROM Competition WHERE ID_Competition = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    competition = new Competition();
                    competition.setID_competiton(resultSet.getInt("ID_Competition"));
                    competition.setNom(resultSet.getString("nom"));
                    // Populate other attributes as needed
                }
            }
        }
        return competition;
    }

}
