package services;

import entities.Competition;
import entities.Equipe;
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
        String req = "insert into competition (Nom,Description,Type,Date,Heure)"+
                "values ('"+competition.getNom()+"','"+competition.getDescription()+"','"+competition.getType()+"','"+competition.getDate()+"','"+competition.getHeure()+"')" ;
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
    public void supprimer(Competition competition) throws SQLException {
        String req="DELETE FROM competition WHERE ID_Competition=?" ;
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1,competition.getID_competiton()) ;
        pre.executeUpdate() ;
    }

    @Override
    public List<Competition> afficher() throws SQLException {
       List<Competition> pers = new ArrayList<>();

        String req = "select * from Competition";
        PreparedStatement pre = con.prepareStatement(req);
        ResultSet res= pre.executeQuery();
        while (res.next()){
            Competition c = new Competition();
            c.setID_competiton(res.getInt(1));
            c.setNom(res.getString("nom"));
            c.setDescription(res.getString(3));
            c.setType(res.getString("Type"));
            c.setDate(res.getDate("Date"));
            c.setHeure(res.getTime("Heure"));
            pers.add(c);
        }
        return pers;
    }

}
