package entities;

import java.sql.Date;
import java.sql.Time;

public class Custom extends Match{
    int ID_Custom;
    Utilisateur ID_User;
    String commentairesClient;
    Date dateCreation;
    int dureeEstimee;

    public String getCommentairesClient() {
        return commentairesClient;
    }

    public void setCommentairesClient(String commentairesClient) {
        this.commentairesClient = commentairesClient;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public int getDureeEstimee() {
        return dureeEstimee;
    }

    public void setDureeEstimee(int dureeEstimee) {
        this.dureeEstimee = dureeEstimee;
    }

    public Custom(int ID_Custom, Utilisateur ID_User, int dureeEstimee, Date dateCreation, String commentairesClient , String nom, String type, String description, Date date, Time heure, Equipe equipe1, Equipe equipe2) {
        super(nom,  type,  description, date, heure, equipe1, equipe2);
        this.ID_Custom = ID_Custom;
        this.ID_User = ID_User;
        this.dureeEstimee=dureeEstimee;
        this.dateCreation=dateCreation;
        this.commentairesClient=commentairesClient;
    }
    public Custom(Utilisateur ID_User,int dureeEstimee, Date dateCreation,String commentairesClient,String nom, String type, String description, Date date, Time heure, Equipe equipe1, Equipe equipe2) {
        super(nom,  type,  description, date, heure, equipe1, equipe2);
        this.ID_User = ID_User;
        this.dureeEstimee=dureeEstimee;
        this.dateCreation=dateCreation;
        this.commentairesClient=commentairesClient;
    }

    public int getIdcustom() {
        return ID_Custom;
    }

    public void setIdcustom(int ID_Custom) {
        this.ID_Custom = ID_Custom;
    }

    public Utilisateur getId_user() {
        return ID_User;
    }

    public void setId_user(Utilisateur ID_User) {
        this.ID_User = ID_User;
    }



    public Custom() {
    }

    @Override
    public String toString() {
        return "Custom{" +
                "ID_User=" + ID_User.getNom()+" "+ID_User.getPrenom() +
                ", commentairesClient='" + commentairesClient + '\'' +
                ", dateCreation=" + dateCreation +
                ", dureeEstimee=" + dureeEstimee +
                ", nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", heure=" + heure +
                ", equipe1=" + equipe1.getNom() +
                ", equipe2=" + equipe2.getNom() +
                '}';
    }
}
