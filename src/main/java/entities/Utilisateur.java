package entities;

import java.util.Date;

public class Utilisateur {
    int ID_User;
    String Nom,Prenom,Email,Mot_de_passe,Image,Niveau_competence,Role,Adresse,Sexe;
    Date Date_de_naissance;

    public  Utilisateur(){

    }

    public Utilisateur(int ID_User, String nom, String prenom, String email, String mot_de_passe, String image, String niveau_competence, String role, String adresse, String sexe) {
        this.ID_User = ID_User;
        Nom = nom;
        Prenom = prenom;
        Email = email;
        Mot_de_passe = mot_de_passe;
        Image = image;
        Niveau_competence = niveau_competence;
        Role = role;
        Adresse = adresse;
        Sexe = sexe;
     //   Date_de_naissance = date_de_naissance;
    }

    public Utilisateur(String nom, String prenom, String email, String mot_de_passe, String image, String niveau_competence, String role, String adresse, String sexe) {
        Nom = nom;
        Prenom = prenom;
        Email = email;
        Mot_de_passe = mot_de_passe;
        Image = image;
        Niveau_competence = niveau_competence;
        Role = role;
        Adresse = adresse;
        Sexe = sexe;
    }

    /*
        public Utilisateur(String nom, String prenom, String email, String mot_de_passe, String image, String niveau_competence, String role, String adresse, String sexe, Date date_de_naissance) {
            Nom = nom;
            Prenom = prenom;
            Email = email;
            Mot_de_passe = mot_de_passe;
            Image = image;
            Niveau_competence = niveau_competence;
            Role = role;
            Adresse = adresse;
            Sexe = sexe;
            Date_de_naissance = date_de_naissance;
        }
    */
    public int getID_User() {
        return ID_User;
    }

    public void setID_User(int ID_User) {
        this.ID_User = ID_User;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMot_de_passe() {
        return Mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        Mot_de_passe = mot_de_passe;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getNiveau_competence() {
        return Niveau_competence;
    }

    public void setNiveau_competence(String niveau_competence) {
        Niveau_competence = niveau_competence;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public String getSexe() {
        return Sexe;
    }

    public void setSexe(String sexe) {
        Sexe = sexe;
    }

    /*public Date getDate_de_naissance() {
        return Date_de_naissance;
    }

    public void setDate_de_naissance(Date date_de_naissance) {
        Date_de_naissance = date_de_naissance;
    }*/

    @Override
    public String toString() {
        return "Utilisateur{" +
                "ID_User=" + ID_User +
                ", Nom='" + Nom + '\'' +
                ", Prenom='" + Prenom + '\'' +
                ", Email='" + Email + '\'' +
                ", Mot_de_passe='" + Mot_de_passe + '\'' +
                ", Image='" + Image + '\'' +
                ", Niveau_competence='" + Niveau_competence + '\'' +
                ", Role='" + Role + '\'' +
                ", Adresse='" + Adresse + '\'' +
                ", Sexe='" + Sexe + '\'' +
              //  ", Date_de_naissance=" + Date_de_naissance +
                '}';
    }
}
