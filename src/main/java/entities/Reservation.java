package entities;

import java.sql.Time;
import java.util.Date;

public class Reservation {
    int ID_reservation;
    int ID_utilisateur;
    int ID_Terrain;
    Date Date_Heure;
    String Duree;

    public Reservation(){

    }

    public Reservation(int ID_reservation, int ID_utilisateur, int ID_Terrain, Date date_Heure, String duree) {
        this.ID_reservation = ID_reservation;
        this.ID_utilisateur = ID_utilisateur;
        this.ID_Terrain = ID_Terrain;
        Date_Heure = date_Heure;
        Duree = duree;
    }

    public Reservation(int ID_utilisateur, int ID_Terrain, Date date_Heure, String duree) {
        this.ID_utilisateur = ID_utilisateur;
        this.ID_Terrain = ID_Terrain;
        Date_Heure = date_Heure;
        Duree = duree;
    }




    public int getID_reservation() {
        return ID_reservation;
    }

    public void setID_reservation(int ID_reservation) {
        this.ID_reservation = ID_reservation;
    }

    public int getID_utilisateur() {
        return ID_utilisateur;
    }

    public void setID_utilisateur(int ID_utilisateur) {
        this.ID_utilisateur = ID_utilisateur;
    }

    public int getID_Terrain() {
        return ID_Terrain;
    }

    public void setID_Terrain(int ID_Terrain) {
        this.ID_Terrain = ID_Terrain;
    }

    public Date getDate_Heure() {
        return Date_Heure;
    }

    public void setDate_Heure(Date date_Heure) {
        Date_Heure = date_Heure;
    }

    public String getDuree() {
        return Duree;
    }

    public void setDuree(String duree) {
        Duree = duree;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "ID_reservation=" + ID_reservation +
                ", ID_utilisateur=" + ID_utilisateur +
                ", ID_Terrain=" + ID_Terrain +
                ", Date_Heure=" + Date_Heure +
                ", Duree='" + Duree + '\'' +
                '}';
    }
}
