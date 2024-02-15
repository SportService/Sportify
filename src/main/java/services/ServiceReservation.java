package services;

import entities.Reservation;
import entities.Terrain;
import utils.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceReservation implements IService<Reservation> {
    private Connection con = DB.getInstance().getConnection();

    public ServiceReservation() {
    }


    @Override
    public void ajouter(Reservation reservation) {
        try {
            // Assuming ID_reservation is auto-incremented in the database
            String query = "INSERT INTO Reservation (ID_reservation, ID_utilisateur, ID_Terrain, Date_Heure, Duree) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(query);

            // Set the parameters based on the Reservation object's attributes
            stm.setInt(1, reservation.getID_reservation());
            stm.setInt(2, reservation.getID_utilisateur());
            stm.setInt(3, reservation.getID_Terrain());
            stm.setDate(4, new java.sql.Date(reservation.getDate_Heure().getTime()));
            stm.setString(5, reservation.getDuree());

            stm.executeUpdate();
            System.out.println("Reservation ajoutée avec succès");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Reservation reservation) throws SQLException {

    }




    public void modifierReservation(Reservation reservation) {
        try {
            // Assurez-vous que la requête SQL correspond bien à la structure de votre base de données
            String query = "UPDATE Reservation SET ID_utilisateur=?, ID_Terrain=?, Date_Heure=?, Duree=? WHERE ID_reservation=?";
            PreparedStatement pstmt = con.prepareStatement(query);

            // Configuration des paramètres basée sur les attributs de l'objet Reservation
            pstmt.setInt(1, reservation.getID_utilisateur());
            pstmt.setInt(2, reservation.getID_Terrain());
            // Utilisation de setTimestamp pour la gestion de Date_Heure
            pstmt.setTimestamp(3, new java.sql.Timestamp(reservation.getDate_Heure().getTime()));
            pstmt.setString(4, reservation.getDuree());
            pstmt.setInt(5, reservation.getID_reservation());

            // Exécution de la requête
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("La réservation a été correctement modifiée.");
            } else {
                System.out.println("Aucune modification n'a été effectuée. Vérifiez l'ID de la réservation.");
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la modification de la réservation : " + ex.getMessage());
        }
    }


    @Override
    public void supprimer(int id) {
        try {
            String query = "DELETE FROM Reservation WHERE ID_reservation=?";
            PreparedStatement pstmt = con.prepareStatement(query);

            // Set the id parameter on the PreparedStatement
            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Réservation supprimée !");
            } else {
                System.out.println("Aucune réservation n'a été supprimée.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Reservation> afficher() {
        List<Reservation> reservations = new ArrayList<>();

        try {
            Statement stm = con.createStatement();
            String query = "SELECT * FROM Reservation";

            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                int idReservation = rs.getInt("ID_reservation");
                int ID_utilisateur = rs.getInt("ID_utilisateur");
                int idTerrain = rs.getInt("ID_Terrain");
                Date dateHeure = rs.getDate("Date_Heure");
                String duree = rs.getString("Duree");

                Reservation reservation = new Reservation(idReservation, ID_utilisateur, idTerrain, dateHeure, duree);
                reservations.add(reservation);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return reservations;
    }


}
