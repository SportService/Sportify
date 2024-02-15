package services;

import entities.Terrain;
import utils.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceTerrain implements IService<Terrain> {

    private Connection con = DB.getInstance().getConnection();

    public ServiceTerrain() {
    }


    @Override
    public void ajouter(Terrain terrain) {
        try {
            // Mise à jour de la requête pour exclure ID_Proprietaire
            String query = "INSERT INTO Terrain (ID_Terrain, Nom, Type_surface, Localisation, Prix) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(query);

            // Mise à jour des paramètres pour correspondre aux attributs de l'objet Terrain, sans ID_Proprietaire
            stm.setDouble(1, terrain.getID_Terrain());
            stm.setString(2, terrain.getNom());
            stm.setString(3, terrain.getType_surface());
            stm.setString(4, terrain.getLocalisation());
            stm.setDouble(5, terrain.getPrix());

            stm.executeUpdate();
            System.out.println("Terrain ajouté avec succès");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Terrain terrain) throws SQLException {

    }
    public void modifertr(Terrain c) {
        try {
            String query = "UPDATE terrain SET ID_Proprietaire=?, Nom=?, Type_surface=?, Localisation=?, Prix=? WHERE ID_Terrain=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, c.getID_Proprietaire());
            pstmt.setString(2, c.getNom());
            pstmt.setString(3, c.getType_surface());
            pstmt.setString(4, c.getLocalisation());
            pstmt.setDouble(5, c.getPrix());
            pstmt.setInt(6, c.getID_Terrain());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Le terrain est modifié !");
            } else {
                System.out.println("Aucun terrain n'a été modifié.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Override
    public void supprimer(int id) {

            try {
                String querry = "DELETE FROM `terrain` WHERE ID_Terrain="+id;
                Statement stm =con.createStatement();
                stm.executeUpdate(querry);
                System.out.println("Terrain supprimée !");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }

    @Override
    public List<Terrain> afficher() {
        List<Terrain> terrain = new ArrayList();

        try {
            Statement stm =con.createStatement();
            String querry ="SELECT * FROM `terrain`";

            ResultSet rs= stm.executeQuery(querry);

            while(rs.next()){
                Terrain a = new Terrain();
                a.setID_Terrain(rs.getInt(1));
                a.setNom(rs.getString(2));
                a.setType_surface(rs.getString(3));
                a.setLocalisation(rs.getString(4));

                a.setPrix(rs.getDouble(5));

                a.setID_Proprietaire(rs.getInt(6));



                terrain.add(a);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return terrain;

    }
    }



