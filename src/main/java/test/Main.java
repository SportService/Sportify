package test;

import entities.Competition;
import services.CompetitionService;
import utils.DB;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main{
    public static void main(String[] args) {


        DB conn1 = DB.getInstance();


        Competition c1 = new Competition("rankedsolo1", "match dans la date fleni ", "Solo", Time.valueOf("12:12:00"), Date.valueOf("2024-02-11"));
        Competition c2 = new Competition("rankedteam1", "match dans la date fleni ", "Team", Time.valueOf("17:00:00"), Date.valueOf("2024-02-26"));
        Competition c3 = new Competition("rankedsolo2", "match dans la date fleni ", "Solo", Time.valueOf("12:30:00"), Date.valueOf("2024-03-12"));
        Competition c4 = new Competition(3, "rankedsolo23", "match dans la dazeate fleni ", "Solo", Time.valueOf("12:30:00"), Date.valueOf("2024-04-12"));

        CompetitionService cS = new CompetitionService();

       /* try {
            cS.ajouter(c1);
            cS.ajouter(c2);
            cS.ajouter(c3);

        } catch ( SQLException e) {
            System.out.println(e.getMessage());
        } */
        /*
        try {
            System.out.println(cS.afficher());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } */
        try {
            cS.modifier(c4);
        } catch (SQLException s) {
            System.out.println(s.getMessage());
        }

    }

}



