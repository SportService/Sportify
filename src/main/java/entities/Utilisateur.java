package entities;

public class Utilisateur {
    int ID_User;
    String nom, prenom, mot_de_passe, email, image, adresse, niveau_competence, role, date_de_naissance;

    public Utilisateur(int ID_User, String nom, String prenom, String mot_de_passe, String image, String email, String adresse, String niveau_competence, String role, String date_de_naissance) {
        this.ID_User = ID_User;
        this.nom = nom;
        this.prenom = prenom;
        this.mot_de_passe = mot_de_passe;
        this.email = email;
        this.adresse = adresse;
        this.image = image;
        this.niveau_competence = niveau_competence;
        this.role = role;
        this.date_de_naissance = date_de_naissance;
    }

    public Utilisateur(String nom, String prenom, String mot_de_passe, String image, String email, String adresse, String niveau_competence, String role, String date_de_naissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.mot_de_passe = mot_de_passe;
        this.email = email;
        this.adresse = adresse;
        this.image = image;
        this.niveau_competence = niveau_competence;
        this.role = role;
        this.date_de_naissance = date_de_naissance;
    }

    public Utilisateur() {
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + ID_User +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", mot_de_passe='" + mot_de_passe + '\'' +
                ", email='" + email + '\'' +
                ", adresse='" + adresse + '\'' +
                ", image='" + image + '\'' +
                ", niveau_competence='" + niveau_competence + '\'' +
                ", role='" + role + '\'' +
                ", date_de_naissance='" + date_de_naissance + '\'' +
                '}';
    }

    public int getId() {
        return ID_User;
    }

    public void setId(int ID_User) {
        this.ID_User = ID_User;
    }



    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public String getNiveau_competence() {
        return niveau_competence;
    }

    public void setNiveau_competence(String niveau_competence) {
        this.niveau_competence = niveau_competence;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDate_de_naissance() {
        return date_de_naissance;
    }

    public void setDate_de_naissance(String date_de_naissance) {
        this.date_de_naissance = date_de_naissance;
    }

}