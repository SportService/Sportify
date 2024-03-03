package entities;

public class EquipeMembre {
    private Equipe equipe ;
    private Utilisateurss membre ;

    public EquipeMembre(Equipe equipe, Utilisateurss membre) {
        this.equipe = equipe;
        this.membre = membre;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public Utilisateurss getMembre() {
        return membre;
    }

    public void setMembre(Utilisateurss membre) {
        this.membre = membre;
    }


}
