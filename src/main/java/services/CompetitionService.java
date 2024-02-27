package services;

import entities.Competition;
import entities.Equipe;
import entities.Terrain;
import utils.DB;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompetitionService implements IService<Competition> {

    private Connection con ;

    public CompetitionService() {
        this.con = DB.getInstance().getConnection();
    }


    @Override
    public void ajouter(Competition competition) throws SQLException {
        String req = "INSERT INTO competition (Nom, Description, Type, Heure,Date,terrain_id) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(req);
        pstmt.setString(1, competition.getNom());
        pstmt.setString(2, competition.getDescription());
        pstmt.setString(3, competition.getType());
        pstmt.setTime(4, competition.getHeure());
        pstmt.setDate(5, competition.getDate());
        pstmt.setInt(6, competition.getTerrain().getID_Terrain());





        pstmt.executeUpdate();
        pstmt.close();
    }

    @Override
    public void modifier(Competition competition) throws SQLException {
        String req = "update competition set nom=? , Description=? , Type=? , Heure=? , Date=? , terrain_id=?  where ID_Competition=?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1,competition.getNom());
        pre.setString(2,competition.getDescription());
        pre.setString(3,competition.getType());
        pre.setTime(4,competition.getHeure());
        pre.setDate(5,competition.getDate());
        pre.setInt(6, competition.getTerrain().getID_Terrain());
        pre.setInt(7,competition.getID_competiton());


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
        List<Competition> competitions = new ArrayList<>();

        String req = "SELECT c.*, t.* FROM Competition c LEFT JOIN Terrain t ON c.Terrain_id = t.ID_Terrain";
        try (PreparedStatement pre = con.prepareStatement(req);
             ResultSet res = pre.executeQuery()) {
            while (res.next()) {
                Competition c = new Competition();
                c.setID_competiton(res.getInt(1));
                c.setNom(res.getString("nom"));
                c.setDescription(res.getString("Description"));
                c.setType(res.getString("Type"));
                c.setHeure(res.getTime("Heure"));
                try {
                    Date date = res.getDate("Date");
                    if (date != null && !date.toLocalDate().equals(LocalDate.of(0000, 1, 1))) {
                        c.setDate(date);
                    } else {
                        // Set a default date or handle the zero date case
                        c.setDate(null); // Or set to a default date
                    }
                } catch (SQLException e) {
                    // Handle SQLException
                    e.printStackTrace();
                }

                if (res.getObject("Terrain_id") != null) {
                    Terrain terrain = new Terrain();
                    terrain.setID_Terrain(res.getInt("ID_Terrain"));
                    terrain.setNomTerrain(res.getString("NomTerrain"));
                    terrain.setType_surface(res.getString("Type_surface"));
                    terrain.setLocalisation(res.getString("Localisation"));
                    terrain.setPrix(res.getDouble("Prix"));
                    terrain.setID_Proprietaire(res.getInt("ID_Propriétaire"));
                    c.setTerrain(terrain);
                } else {
                    Terrain defaultTerrain = new Terrain();
                    // Set default values or leave them as null
                    c.setTerrain(defaultTerrain); // Set the terrain to null if it's not associated
                }
                competitions.add(c);
            }
        }
        return competitions;
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
