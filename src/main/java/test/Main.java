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


    }

}



