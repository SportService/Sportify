package entities;

import java.sql.Date;

public class Match {
    int id ;
    String nom,type,description;
    Date date,heure;
    int id_equipe1,id_equipe2;

    public Match(int id, String nom, String type, String description, Date date, Date heure, int id_equipe1, int id_equipe2) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.description = description;
        this.date = date;
        this.heure = heure;
        this.id_equipe1 = id_equipe1;
        this.id_equipe2 = id_equipe2;
    }
    public Match( String nom, String type, String description, Date date, Date heure, int id_equipe1, int id_equipe2) {

        this.nom = nom;
        this.type = type;
        this.description = description;
        this.date = date;
        this.heure = heure;
        this.id_equipe1 = id_equipe1;
        this.id_equipe2 = id_equipe2;
    }
    public Match(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getHeure() {
        return heure;
    }

    public void setHeure(Date heure) {
        this.heure = heure;
    }

    public int getId_equipe1() {
        return id_equipe1;
    }

    public void setId_equipe1(int id_equipe1) {
        this.id_equipe1 = id_equipe1;
    }

    public int getId_equipe2() {
        return id_equipe2;
    }

    public void setId_equipe2(int id_equipe2) {
        this.id_equipe2 = id_equipe2;
    }

    @java.lang.Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", heure=" + heure +
                ", id_equipe1=" + id_equipe1 +
                ", id_equipe2=" + id_equipe2 +
                '}';
    }
}
