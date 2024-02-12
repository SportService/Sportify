package entities;

import java.util.Date;

public class Match {
      int ID_Match ;
      String Description;
      static String Nom;
      String Type ;

      Date Heure ;
      Date Date ;

      public Match() {
      }

      public Match(int ID_Match, String description, String nom, String type, java.util.Date heure, java.util.Date date) {
            this.ID_Match = ID_Match;
            Description = description;
            Nom = nom;
            Type = type;
            Heure = heure;
            Date = date;
      }

      public int getID_Match() {
            return ID_Match;
      }

      public void setID_Match(int ID_Match) {
            this.ID_Match = ID_Match;
      }

      public String getDescription() {
            return Description;
      }

      public void setDescription(String description) {
            Description = description;
      }

      public static String getNom() {
            return Nom;
      }

      public void setNom(String nom) {
            Nom = nom;
      }

      public String getType() {
            return Type;
      }

      public void setType(String type) {
            Type = type;
      }

      public java.util.Date getHeure() {
            return Heure;
      }

      public void setHeure(java.util.Date heure) {
            Heure = heure;
      }

      public java.util.Date getDate() {
            return Date;
      }

      public void setDate(java.util.Date date) {
            Date = date;
      }

      @Override
      public String toString() {
            return "Match{" +
                    "ID_Match=" + ID_Match +
                    ", Description='" + Description + '\'' +
                    ", Nom='" + Nom + '\'' +
                    ", Type='" + Type + '\'' +
                    ", Heure=" + Heure +
                    ", Date=" + Date +
                    '}';
      }
}
