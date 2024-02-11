package entities;

public class Custom {
    int idcustom,id_user;

    public int getIdcustom() {
        return idcustom;
    }

    public void setIdcustom(int idcustom) {
        this.idcustom = idcustom;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Custom(int idcustom, int id_user) {
        this.idcustom = idcustom;
        this.id_user = id_user;
    }
    public Custom( int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Custom{" +
                "idcustom=" + idcustom +
                ", id_user=" + id_user +
                '}';
    }

    public Custom() {
    }

}
