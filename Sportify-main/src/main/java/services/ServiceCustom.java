package services;


import entities.Custom;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceCustom implements services.IService<Custom> {
    private Connection con;

    public ServiceCustom(){
        con = utils.DB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Custom custom) throws SQLException {
        String req = "INSERT INTO `custom` (id_user) " +
                "VALUES (" +  custom.getId_user() + ")";
        Statement ste = con.createStatement();
        ste.executeUpdate(req);
    }

    @Override
    public void modifier(Custom custom) throws SQLException {
        String req = "update `custom` set id_user=? where idcustom=?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1,custom.getId_user());
        pre.setInt(2,custom.getIdcustom());

        pre.executeUpdate();
    }


    @Override
    public void supprimer(Custom custom) throws SQLException {
        String req = "DELETE FROM `custom` WHERE idcustom = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, custom.getIdcustom());
        pre.executeUpdate();
    }


    @Override
    public List<Custom> afficher() throws SQLException {
        List<Custom> pers = new ArrayList<>();

        String req = "select * from `custom` ";
        PreparedStatement pre = con.prepareStatement(req);
        ResultSet res= pre.executeQuery();
        while (res.next()){
            Custom p = new Custom();
            p.setIdcustom(res.getInt(1));
            p.setId_user(res.getInt(2));
            pers.add(p);
        }


        return pers;
    }
}
