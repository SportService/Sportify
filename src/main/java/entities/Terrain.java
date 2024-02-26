package entities;

public class Terrain {


        int ID_Terrain,ID_Proprietaire;
        String NomTerrain, Type_surface, Localisation;
        Double Prix;


        public Terrain(int ID_Proprietaire, String NomTerrain, String type_surface, String localisation, Double prix) {
            this.ID_Proprietaire = ID_Proprietaire;
            this.NomTerrain = NomTerrain;
            Type_surface = type_surface;
            Localisation = localisation;
            Prix = prix;
        }

        public Terrain(int ID_Terrain, int ID_Proprietaire, String NomTerrain, String type_surface, String localisation, Double prix) {
            this.ID_Terrain = ID_Terrain;
            this.NomTerrain= NomTerrain;
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

    public String getNomTerrain() {
        return NomTerrain;
    }

    public void setNomTerrain(String nomTerrain) {
        NomTerrain = nomTerrain;
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
                ", ID_Proprietaire=" + ID_Proprietaire +
                ", NomTerrain='" + NomTerrain + '\'' +
                ", Type_surface='" + Type_surface + '\'' +
                ", Localisation='" + Localisation + '\'' +
                ", Prix=" + Prix +
                '}';
    }
}

