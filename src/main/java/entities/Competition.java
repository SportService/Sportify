package entities;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.Date;

public class Competition  {
    int ID_competiton ;

    // cosntructor with super
/*
    public Competition(int ID_Match, String description, String nom, String type, java.util.Date heure, java.util.Date date, int ID_competiton) {
        super(ID_Match, description, nom, type, heure, date);
        this.ID_competiton = ID_competiton;
    }
*/
     String Nom;
    String Description ;

    String Type ;

    Time Heure ;
    Date Date ;

    public Competition() {
    }

    public Competition(int ID_competiton, String nom, String description, String type, Time heure, Date date) {
        this.ID_competiton = ID_competiton;
        Nom = nom;
        Description = description;
        Type = type;
        Heure = heure;
        Date = date;
    }

    public Competition(String nom, String description, String type, Time heure, Date date) {
        Nom = nom;
        Description = description;
        Type = type;
        Heure = heure;
        Date = date;
    }

    public int getID_competiton() {
        return ID_competiton;
    }

    public void setID_competiton(int ID_competiton) {
        this.ID_competiton = ID_competiton;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Time getHeure() {
        return Heure;
    }

    public void setHeure(Time heure) {
        Heure = heure;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date date) {
        Date = date;
    }

    @Override
    public String toString() {
        return "Competition{" +
                "ID_competiton=" + ID_competiton +
                ", Nom='" + Nom + '\'' +
                ", Description='" + Description + '\'' +
                ", Type='" + Type + '\'' +
                ", Heure=" + Heure +
                ", Date=" + Date +
                '}';
    }
}
