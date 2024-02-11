package test;


import entities.*;

import services.ServiceCustom;
import services.ServiceMatch;

import java.sql.Date;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
    utils.DB conn1 = utils.DB.getInstance();
    Match m1=new Match("louay","ranked","onligne",new Date(2024,10,10),new Date(2024,12,12),1,2);
    ServiceMatch  s=new ServiceMatch();
        Custom c1= new Custom(1,1);
        ServiceCustom s1=new ServiceCustom();
        try {
            System.out.println(s.afficher());
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        try {
             s1.supprimer(c1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }}
