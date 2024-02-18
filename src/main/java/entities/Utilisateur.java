package entities;

public class Utilisateur {
    int id;
    String nom, prenom, mot_de_passe, email, image, adresse, niveau_competence, role, date_de_naissance;

    public Utilisateur(int id, String nom, String prenom, String image,String mot_de_passe, String adresse, String date_de_naissance, String role,String email,  String niveau_competence) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.image = image;
        this.mot_de_passe = mot_de_passe;
        this.adresse = adresse;
        this.date_de_naissance = date_de_naissance;

        this.role = role;
        this.email = email;

        this.niveau_competence = niveau_competence;

    }

    public Utilisateur( String nom, String prenom, String image,String mot_de_passe, String adresse, String date_de_naissance, String role,String email,  String niveau_competence) {
        this.nom = nom;
        this.prenom = prenom;
        this.image = image;
        this.mot_de_passe = mot_de_passe;
        this.adresse = adresse;
        this.date_de_naissance = date_de_naissance;

        this.role = role;
        this.email = email;

        this.niveau_competence = niveau_competence;

    }
    public Utilisateur() {
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", image='" + image + '\'' +
                ", mot_de_passe='" + mot_de_passe + '\'' +
                ", adresse='" + adresse + '\'' +
                ", date_de_naissance='" + date_de_naissance + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", niveau_competence='" + niveau_competence + '\'' +
                '}';
    }

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