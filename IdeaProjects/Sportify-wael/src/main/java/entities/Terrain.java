package entities;

public class Terrain {
    int ID_Terrain,ID_Proprietaire;
    String Nom, Type_surface, Localisation;
    Double Prix;


    public Terrain(int ID_Proprietaire, String nom, String type_surface, String localisation, Double prix) {
        this.ID_Proprietaire = ID_Proprietaire;
        Nom = nom;
        Type_surface = type_surface;
        Localisation = localisation;
        Prix = prix;
    }

    public Terrain(int ID_Terrain, int ID_Proprietaire, String nom, String type_surface, String localisation, Double prix) {
        this.ID_Terrain = ID_Terrain;
        Nom = nom;
        Type_surface = type_surface;
        Localisation = localisation;
        Prix = prix;
        this.ID_Proprietaire = ID_Proprietaire;

    }



    public int getID_Terrain() {
        return ID_Terrain;
    }

    public void setID_Terrain(int ID_Terrain) {
        this.ID_Terrain = ID_Terrain;
    }

    public int getID_Proprietaire() {
        return ID_Proprietaire;
    }

    public void setID_Proprietaire(int ID_Proprietaire) {
        this.ID_Proprietaire = ID_Proprietaire;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getType_surface() {
        return Type_surface;
    }

    public void setType_surface(String type_surface) {
        Type_surface = type_surface;
    }

    public String getLocalisation() {
        return Localisation;
    }

    public void setLocalisation(String localisation) {
        Localisation = localisation;
    }

    public Double getPrix() {
        return Prix;
    }

    public void setPrix(Double prix) {
        Prix = prix;
    }

    public Terrain(){

    }

    @Override
    public String toString() {
        return "Terrain{" +
                "ID_Terrain=" + ID_Terrain +
                ", Nom='" + Nom + '\'' +
                ", Type_surface='" + Type_surface + '\'' +
                ", Localisation='" + Localisation + '\'' +
                ", Prix=" + Prix +
                '}';
    }
}
