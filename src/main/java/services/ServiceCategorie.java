package services;

import entities.Categorie;
import entities.Equipe;
import utils.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceCategorie implements IService<Categorie> {
    private Connection con;

    public ServiceCategorie() {
        con = DB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Categorie categorie) throws SQLException {
        String req = "INSERT INTO categorie (nom, description, image) VALUES (?, ?, ?)";
        try (PreparedStatement pre = con.prepareStatement(req)) {
            pre.setString(1, categorie.getNom());
            pre.setString(2, categorie.getDescription());
            pre.setString(3, categorie.getImage());
            pre.executeUpdate();
        }
    }

    @Override
    public void modifier(Categorie categorie) throws SQLException {
        String req = "UPDATE categorie SET nom=?, description=?, image=? WHERE IDCateg=?";
        try (PreparedStatement pre = con.prepareStatement(req)) {
            pre.setString(1, categorie.getNom());
            pre.setString(2, categorie.getDescription());
            pre.setString(3, categorie.getImage());
            pre.setInt(4, categorie.getID_Categ());
            pre.executeUpdate();
        }
    }

    /*@Override
    public void supprimer(Categorie categorie) throws SQLException {
        String req = "DELETE FROM categorie WHERE IDCateg = ?";
        try (PreparedStatement pre = con.prepareStatement(req)) {
            pre.setInt(1, categorie.getID_Categ());
            pre.executeUpdate();
        }
    }*/
    @Override
    public void supprimer(Categorie categorie) throws SQLException {
        // Check if any equipe records reference the category
        String checkEquipeQuery = "SELECT COUNT(*) FROM equipe WHERE IDCateg = ?";
        try (PreparedStatement checkEquipeStatement = con.prepareStatement(checkEquipeQuery)) {
            checkEquipeStatement.setInt(1, categorie.getID_Categ());
            try (ResultSet resultSet = checkEquipeStatement.executeQuery()) {
                if (resultSet.next()) {
                    int equipeCount = resultSet.getInt(1);
                    if (equipeCount > 0) {
                        // If equipe records exist, throw an exception to indicate that deletion is not allowed
                        throw new SQLException("Cannot delete the category as it is referenced by equipe records.");
                    }
                }
            }
        }

        // If no equipe records reference the category, proceed with deletion
        String deleteCategoryQuery = "DELETE FROM categorie WHERE IDCateg = ?";
        try (PreparedStatement deleteCategoryStatement = con.prepareStatement(deleteCategoryQuery)) {
            deleteCategoryStatement.setInt(1, categorie.getID_Categ());
            deleteCategoryStatement.executeUpdate();
            System.out.println("Category deleted successfully.");
        }
    }



    @Override
    public List<Categorie> afficher() throws SQLException {
        List<Categorie> categories = new ArrayList<>();
        String req = "SELECT * FROM categorie";
        try (PreparedStatement pre = con.prepareStatement(req);
             ResultSet res = pre.executeQuery()) {
            while (res.next()) {
                Categorie c = new Categorie();
                c.setIDCateg(res.getInt("IDCateg"));
                c.setNom(res.getString("nom"));
                c.setDescription(res.getString("description"));
                c.setImage(res.getString("image"));
                categories.add(c);
            }
        }
        return categories;
    }
}
