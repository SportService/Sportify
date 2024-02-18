package services;

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
        String req = "INSERT INTO utilisateur (nom, prenom, image,mot_de_passe,adresse,date_de_naissance,  role,email,   niveau_competence ) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, utilisateur.getNom());
        pre.setString(2, utilisateur.getPrenom());
        pre.setString(3, utilisateur.getImage());
        pre.setString(4, utilisateur.getMot_de_passe());
        pre.setString(5, utilisateur.getAdresse());
        pre.setString(6, utilisateur.getDate_de_naissance());
        pre.setString(7, utilisateur.getRole());
        pre.setString(8, utilisateur.getEmail());
        pre.setString(9, utilisateur.getNiveau_competence());

        pre.executeUpdate();
    }

    @Override
    public void modifier(int id, Utilisateur utilisateur) throws SQLException {
        String req = "UPDATE utilisateur SET nom=?, prenom=?, image=?, mot_de_passe=?, adresse=?, date_de_naissance=?, role=?, email=?, niveau_competence=? WHERE id=?";
        try (PreparedStatement pre = con.prepareStatement(req)) {
            pre.setString(1, utilisateur.getNom());
            pre.setString(2, utilisateur.getPrenom());
            pre.setString(3, utilisateur.getImage());
            pre.setString(4, utilisateur.getMot_de_passe());
            pre.setString(5, utilisateur.getAdresse());
            pre.setString(6, utilisateur.getDate_de_naissance());
            pre.setString(7, utilisateur.getRole());
            pre.setString(8, utilisateur.getEmail());
            pre.setString(9, utilisateur.getNiveau_competence());
            pre.setInt(10, id);

            pre.executeUpdate();
        }
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

        String req = "SELECT * FROM utilisateur";
        PreparedStatement pre = con.prepareStatement(req);
        ResultSet res = pre.executeQuery();
        while (res.next()) {
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setId(res.getInt("id"));
            utilisateur.setNom(res.getString("nom"));
            utilisateur.setPrenom(res.getString("prenom"));
            utilisateur.setImage(res.getString("image"));
            utilisateur.setMot_de_passe(res.getString("mot_de_passe"));
            utilisateur.setAdresse(res.getString("adresse"));
            utilisateur.setDate_de_naissance(res.getString("date_de_naissance"));
            utilisateur.setRole(res.getString("role"));
            utilisateur.setEmail(res.getString("email"));
            utilisateur.setNiveau_competence(res.getString("niveau_competence"));
            utilisateurs.add(utilisateur);
        }

        return utilisateurs;
    }

    public Utilisateur authentifier(String email, String password) throws SQLException {
        String req = "SELECT * FROM utilisateur WHERE email = ? AND mot_de_passe = ?";

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
        }
    }

    private Utilisateur mapUtilisateur(ResultSet result) throws SQLException {
        return new Utilisateur(
                result.getInt("id"),
                result.getString("nom"),
                result.getString("prenom"),
                result.getString("image"),
                result.getString("mot_de_passe"),
                result.getString("adresse"),
                result.getString("date_de_naissance"),
                result.getString("role"),
                result.getString("email"),
                result.getString("niveau_competence")
        );
    }
}
