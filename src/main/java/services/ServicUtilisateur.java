package services;

import entities.Role;
import entities.Utilisateur;
import utils.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicUtilisateur implements IService<Utilisateur> {
    private Connection con;

    public ServicUtilisateur() {
        con = DB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Utilisateur utilisateur) throws SQLException {
        String query = "INSERT INTO utilisateur (nom, prenom, mot_de_passe, email, image, adresse, niveau_competence, date_de_naissance, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, utilisateur.getNom());
            pst.setString(2, utilisateur.getPrenom());
            pst.setString(3, utilisateur.getMot_de_passe()); // Consider hashing the password
            pst.setString(4, utilisateur.getEmail());
            pst.setString(5, utilisateur.getImage());
            pst.setString(6, utilisateur.getAdresse());
            pst.setString(7, utilisateur.getNiveau_competence());
            pst.setDate(8, new java.sql.Date(utilisateur.getDate_de_naissance().getTime()));
            pst.setString(9, utilisateur.getRole().toString());

            pst.executeUpdate();

    }

    @Override
    public void modifier(int id, Utilisateur utilisateur) throws SQLException {
        String query = "UPDATE utilisateur SET nom=?, prenom=?, mot_de_passe=?, email=?, image=?, adresse=?, niveau_competence=?, date_de_naissance=?, role=? WHERE id=?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, utilisateur.getNom());
        pst.setString(2, utilisateur.getPrenom());
        pst.setString(3, utilisateur.getMot_de_passe());
        pst.setString(4, utilisateur.getEmail());
        pst.setString(5, utilisateur.getImage());
        pst.setString(6, utilisateur.getAdresse());
        pst.setString(7, utilisateur.getNiveau_competence());
        pst.setDate(8, new java.sql.Date(utilisateur.getDate_de_naissance().getTime()));
        pst.setString(9, utilisateur.getRole().toString());
        pst.setInt(10, id);

        pst.executeUpdate();

    }


    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM utilisateur WHERE id=?";
        try (PreparedStatement pre = con.prepareStatement(req)) {
            pre.setInt(1, id);
            pre.executeUpdate();
        }
    }

    @Override
    public List<Utilisateur> afficher() throws SQLException {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String query = "SELECT * FROM utilisateur";
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setId(rs.getInt("id"));
                utilisateur.setNom(rs.getString("nom"));
                utilisateur.setPrenom(rs.getString("prenom"));
                utilisateur.setMot_de_passe(rs.getString("mot_de_passe")); // Consider security implications
                utilisateur.setEmail(rs.getString("email"));
                utilisateur.setImage(rs.getString("image"));
                utilisateur.setAdresse(rs.getString("adresse"));
                utilisateur.setNiveau_competence(rs.getString("niveau_competence"));
                utilisateur.setDate_de_naissance(rs.getDate("date_de_naissance"));
                utilisateur.setRole(Role.valueOf(rs.getString("role")));

                utilisateurs.add(utilisateur);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'affichage des utilisateurs: " + e.getMessage());
        }
        return utilisateurs;
    }
    public Utilisateur getById(int id)throws SQLException{
        return afficher().stream().filter(u->u.getId()==id).findFirst().orElse(null);
    }
    public Utilisateur login(String email, String password) throws SQLException {
        List<Utilisateur> utilisateurs = afficher();

        return utilisateurs.stream()
                .filter(u -> u.getEmail().equals(email) && u.getMot_de_passe().equals(password))
                .findFirst()
                .orElse(null);
    }


    public Utilisateur authentifier(String email, String password) throws SQLException {
        /*String req = "SELECT * FROM utilisateur WHERE email = ? AND mot_de_passe = ?";

        try (PreparedStatement pre = con.prepareStatement(req)) {
            pre.setString(1, email);
            pre.setString(2, password);

            try (ResultSet result = pre.executeQuery()) {
                if (result.next()) {
                    return mapUtilisateur(result);
                } else {
                    return null;
                }
            }
        }*/
        return null;
    }


}
