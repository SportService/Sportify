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
    public Boolean isRandom;
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

    public Equipe(String nom, String niveau, Categorie IDCateg, Boolean isRandom, int rank) {
        Nom = nom;
        Niveau = niveau;
        this.IDCateg = IDCateg;
        this.isRandom = isRandom;
        this.rank = rank;
    }

    public Equipe(){

    }

    public Equipe(String nomEquipe, String membre1, String membre2, String membre3, String membre4, String membre5, String membre6, String membre7, String membre8, String membre9, String membre10, String membre11) {

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
        this.IDCateg = IDCateg;
      //  establishForeignKeyConstraint();
    }
 /*   private void establishForeignKeyConstraint() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sportify", "root", null);
            String sql = "ALTER TABLE Equipe ADD CONSTRAINT fk_IDCateg FOREIGN KEY (IDCateg) REFERENCES Categorie(id_categorie)";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
*/
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
        return "Equipe{" +
                "ID=" + IDEquipe +
                ", Nom='" + Nom + '\'' +

                ", Niveau='" + Niveau + '\'' +
                ", IDCateg=" + IDCateg +
                ", isRandom=" + isRandom +
                ", id_createur=" + id_createur +
                ", rank='" + rank + '\'' +
                '}';
    }
}