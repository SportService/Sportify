package services;

import entities.Equipe;
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
        String req = "INSERT INTO `matc` (nom, type, description, date, heure, equipe1, equipe2) VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement pstmt = con.prepareStatement(req);
        pstmt.setString(1, match.getNom());
        pstmt.setString(2, match.getType());
        pstmt.setString(3, match.getDescription());
        pstmt.setDate(4, match.getDate());
        pstmt.setTime(5, match.getHeure());
        pstmt.setInt(6, match.getEquipe1().getID());
        pstmt.setInt(7, match.getEquipe2().getID());

        pstmt.executeUpdate();
    }

    @Override
    public void modifier(Match match) throws SQLException {
        String req = "UPDATE `matc` " +
                "SET nom=?, type=?, description=?, date=?, heure=?, equipe1=?, equipe2=? " +
                "WHERE ID_Matc=?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, match.getNom());
        pre.setString(2, match.getType());
        pre.setString(3, match.getDescription());
        pre.setDate(4, match.getDate());
        pre.setTime(5, match.getHeure());
        pre.setInt(6, match.getEquipe1().getID()); // Utilisez l'identifiant d'équipe pour equipe1
        pre.setInt(7, match.getEquipe2().getID()); // Utilisez l'identifiant d'équipe pour equipe2
        pre.setInt(8, match.getID_Matc());

        pre.executeUpdate();
    }

    @Override
    public void supprimer(Match match) throws SQLException {
        String req = "DELETE FROM `matc` WHERE ID_Matc = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, match.getID_Matc());
        pre.executeUpdate();
    }
    public Equipe getEquipeById(int equipeId) throws SQLException {
        Equipe equipe = null;
        String req = "SELECT * FROM equipe WHERE IDEquipe = ?";
        PreparedStatement pstmt = con.prepareStatement(req);
        pstmt.setInt(1, equipeId);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            equipe = new Equipe();
            equipe.setID(rs.getInt("IDEquipe"));
            equipe.setNom(rs.getString("nom"));
            // Définissez d'autres attributs de votre objet Equipe si nécessaire
        }

        return equipe;
    }


    @Override
    public List<Match> afficher() throws SQLException {
        List<Match> pers = new ArrayList<>();

        String req = "SELECT * FROM `matc`";
        PreparedStatement pre = con.prepareStatement(req);
        ResultSet res = pre.executeQuery();

        while (res.next()) {
            Match p = new Match();
            p.setID_Matc(res.getInt(1));
            p.setNom(res.getString("nom"));
            p.setType(res.getString("type"));
            p.setDescription(res.getString("description"));
            p.setDate(res.getDate("date"));
            p.setHeure(res.getTime("heure"));
            Equipe equipe1 = getEquipeById(res.getInt("equipe1"));
            Equipe equipe2 = getEquipeById(res.getInt("equipe2"));

            p.setEquipe1(equipe1);
            p.setEquipe2(equipe2);

            pers.add(p);

        }


        return pers;
    }
}
