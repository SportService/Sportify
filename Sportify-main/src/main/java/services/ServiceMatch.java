package services;

import entities.Match;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceMatch implements services.IService<Match> {
    private Connection con;

    public ServiceMatch(){
        con = utils.DB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Match match) throws SQLException {
        String req = "INSERT INTO `match` (nom, type, description, date, heure, id_equipe1, id_equipe2) " +
                "VALUES ('" + match.getNom() + "', '" + match.getType() + "', '" + match.getDescription() + "', '" +
                match.getDate() + "', '" + match.getHeure() + "', '" + match.getId_equipe1() + "', " + match.getId_equipe2() + ")";
        Statement ste = con.createStatement();
        ste.executeUpdate(req);
    }

    @Override
    public void modifier(Match match) throws SQLException {
        String req = "update `match` set nom=? , type=? , description=?,date=?,heure=?,id_equipe1=?,id_equipe2=? where id=?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1,match.getNom());
        pre.setString(2,match.getType());
        pre.setString(3,match.getDescription());
        pre.setDate(4,match.getDate());
        pre.setDate(5,match.getHeure());
        pre.setInt(6,match.getId_equipe1());
        pre.setInt(7,match.getId_equipe2());
        pre.setInt(8,match.getId());

        pre.executeUpdate();
    }

    @Override
    public void supprimer(Match match) throws SQLException {
        String req = "DELETE FROM `match` WHERE id = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, match.getId());
        pre.executeUpdate();
    }

    @Override
    public List<Match> afficher() throws SQLException {
        List<Match> pers = new ArrayList<>();

        String req = "select * from `match` ";
        PreparedStatement pre = con.prepareStatement(req);
        ResultSet res= pre.executeQuery();
        while (res.next()){
            Match p = new Match();
            p.setId(res.getInt(1));
            p.setNom(res.getString("nom"));
            p.setType(res.getString("type"));
            p.setDescription(res.getString("description"));
            p.setDate(res.getDate("date"));
            p.setHeure(res.getDate("heure"));
            p.setId_equipe1(res.getInt(7));
            p.setId_equipe2(res.getInt(8));
            pers.add(p);
        }


        return pers;
    }
}
