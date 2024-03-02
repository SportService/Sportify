package entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entities.Utilisateur;

public class Equipe {
    int IDEquipe;
    String Nom;

    String Niveau;

    Categorie IDCateg;
    Boolean isRandom;

    public Equipe(int IDEquipe, String nom) {
        this.IDEquipe = IDEquipe;
        Nom = nom;
    }

    Utilisateur id_createur;
    int rank;

    public Equipe(int IDEquipe, String nom,  String niveau, Categorie IDCateg, Boolean isRandom, Utilisateur id_createur, int rank) {
        this.IDEquipe = IDEquipe;
        Nom = nom;

        Niveau = niveau;
        this.IDCateg = IDCateg;
        this.isRandom = isRandom;
        this.id_createur = id_createur;
        this.rank = rank;
    }

    public Equipe(String nom, String niveau, Categorie IDCateg, Boolean isRandom, Utilisateur id_createur, int rank) {
        Nom = nom;
        Niveau = niveau;
        this.IDCateg = IDCateg;
        this.isRandom = isRandom;
        this.id_createur = id_createur;
        this.rank = rank;
    }
    public Equipe(){

    }

    public Equipe(int id, String nom, Utilisateur idCreateur) {
        this.IDEquipe = id;
        this.Nom = nom;
        this.id_createur=idCreateur;
    }

    public int getID() {
        return IDEquipe;
    }

    public void setID(int ID) {
        this.IDEquipe = ID;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }


    public String getNiveau() {
        return Niveau;
    }

    public void setNiveau(String niveau) {
        Niveau = niveau;
    }

    public Categorie getIDCateg() {
        return IDCateg;
    }

    public void setIDCateg(Categorie IDCateg) {
        this.IDCateg = IDCateg;}
        public Boolean getRandom() {
            return isRandom;
        }

        public void setRandom(Boolean random) {
            isRandom = random;
        }

        public Utilisateur getId_createur() {
            return id_createur;
        }

        public void setId_createur(Utilisateur id_createur) {
            this.id_createur = id_createur;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        @Override
        public String toString() {
            return

                     Nom ;
        }
    public String toString1() {
        return " Nom: " + getNom() + " Niveau: " + getNiveau()+ " Rank: " + getRank();

    }
    }