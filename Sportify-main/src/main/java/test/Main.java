package test;


import entities.*;

import services.ServiceMatch;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;


public class Main {

    public static void main(String[] args)  {
        System.out.println("111111");
    utils.DB conn1 = utils.DB.getInstance();
        System.out.println(conn1);
    ServiceMatch serviceMatch =new ServiceMatch();
        LocalTime heureActuelle = LocalTime.now();
        System.out.println(heureActuelle);
        Time time = Time.valueOf(heureActuelle);
        Utilisateur utilisateur=new Utilisateur(1,"ghnima","louay1","ghnima","123456","louay@gmail.com","moulares","haut","admin","17/08/2001");
        Categorie categorie=new Categorie(1,"cat1","enjoy","cccc");

        Equipe e1=new Equipe(1,"louay","bas",categorie,true,utilisateur,10);
        Equipe e2=new Equipe(2,"louay2","bas",categorie,true,utilisateur,10);
        //Match m3=new Match("louay","custom","vvvvvvvv",new Date(2024,10,10),time ,e1,e1);

        /*try {
            serviceMatch.ajouter(m3);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/


        //  Utilisateur utilisateur=new Utilisateur(1,"ghnima","louay1","ghnima","123456","louay@gmail.com","moulares","haut","admin","17/08/2001");
   // Categorie categorie=new Categorie(1,"cat1","enjoy","cccc");
    //Equipe e1=new Equipe(1,"louay","bas",categorie,true,utilisateur,10);
      //  Equipe e2=new Equipe(2,"louay2","bas",categorie,true,utilisateur,10);
    //Match m1=new Match("louay","custom","vvvvvvvv",new Date(2024,10,10),time ,e1,e1);
       // Match m2=new Match(3,"bvbvv","custom","vvvvvvvv",new Date(2024,10,10),time ,e1,e1);
    ServiceMatch  s=new ServiceMatch();
        //Custom c1= new Custom(7,utilisateur,1,new Date(5555,02,10),"commentaire","louay","custom","vvvvvvvv",new Date(2024,10,10),time ,e1,e1);
       // ServiceCustom s1=new ServiceCustom(new ServiceMatch());
       /*try {
            s1.modifier(c1);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }*/
       /* try {
            s.supprimer(m2);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }*/
        /*try {
            s.ajouter(m1);
            System.out.println("22222");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        try {
            System.out.println(s.afficher());
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        /*try {
            s1.ajouter(c1);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        /*try {
            System.out.println(s1.afficher());
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        /*try {
             s1.supprimer(c1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            s.supprimer(m1);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }*/

    }}
