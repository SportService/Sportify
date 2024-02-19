package entities;

import java.util.Date;

public class Match {
      int ID_Match ;
      String Description;
      static String Nom;
      String Type ;

      Date Heure ;
      Date Date ;

      private Equipe equipe1 ;

      private Equipe equipe2 ;

      public Match() {
      }

      public Match(int ID_Match, String description, String nom, String type, java.util.Date heure, java.util.Date date , Equipe equipe1 , Equipe equipe2) {
            this.ID_Match = ID_Match;
            Description = description;
            Nom = nom;
            Type = type;
            Heure = heure;
            Date = date;
            equipe1=equipe1 ;
            equipe2=equipe2 ;
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

      public Equipe getEquipe1() {
            return equipe1;
      }

      public void setEquipe1(Equipe equipe1) {
            this.equipe1 = equipe1;
      }

      public Equipe getEquipe2() {
            return equipe2;
      }

      public void setEquipe2(Equipe equipe2) {
            this.equipe2 = equipe2;
      }

      @Override
      public String toString() {
            return "Match{" +
                    "ID_Match=" + ID_Match +
                    ", Description='" + Description + '\'' +
                    ", Type='" + Type + '\'' +
                    ", Heure=" + Heure +
                    ", Date=" + Date +
                    ", equipe1=" + equipe1 +
                    ", equipe2=" + equipe2 +
                    '}';
      }
}
