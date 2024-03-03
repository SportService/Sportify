package services;

import entities.Utilisateurss;
import utils.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicUtilisateurss implements IServiceX<Utilisateurss> {
    private Connection con;

    public ServicUtilisateurss() {
        con = DB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Utilisateurss utilisateurss) throws SQLException {
        String req = "INSERT INTO utilisateur (nom, prenom, mot_de_passe, email, adresse, image, niveau_competence, role, date_de_naissance) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, utilisateurss.getNom());
        pre.setString(2, utilisateurss.getPrenom());
        pre.setString(3, utilisateurss.getMot_de_passe());
        pre.setString(4, utilisateurss.getEmail());
        pre.setString(5, utilisateurss.getAdresse());
        pre.setString(6, utilisateurss.getImage());
        pre.setString(7, utilisateurss.getNiveau_competence());
        pre.setString(8, utilisateurss.getRole());
        pre.setString(9, utilisateurss.getDate_de_naissance());

        pre.executeUpdate();
    }

    @Override
    public void modifier(Utilisateurss utilisateurss) throws SQLException {

    }

    @Override
    public void modifiert(int ID_User, Utilisateurss utilisateurss) throws SQLException {
        String req = "UPDATE utilisateur SET nom=?, prenom=?, mot_de_passe=?, email=?, adresse=?, image=?, niveau_competence=?, role=?, date_de_naissance=? WHERE ID_User=?";
        try (PreparedStatement pre = con.prepareStatement(req)) {
            pre.setString(1, utilisateurss.getNom());
            pre.setString(2, utilisateurss.getPrenom());
            pre.setString(3, utilisateurss.getMot_de_passe());
            pre.setString(4, utilisateurss.getEmail());
            pre.setString(5, utilisateurss.getAdresse());
            pre.setString(6, utilisateurss.getImage());
            pre.setString(7, utilisateurss.getNiveau_competence());
            pre.setString(8, utilisateurss.getRole());
            pre.setString(9, utilisateurss.getDate_de_naissance());
            pre.setInt(10, ID_User);

            pre.executeUpdate();
        }
    }


    @Override
    public void supprimer(int ID_User) throws SQLException {
        String req = "DELETE FROM utilisateur WHERE ID_User=?";
        try (PreparedStatement pre = con.prepareStatement(req)) {
            pre.setInt(1, ID_User);
            pre.executeUpdate();
        }
    }

    @Override
    public List<Utilisateurss> afficher() throws SQLException {
        List<Utilisateurss> utilisateursses = new ArrayList<>();

        String req = "SELECT * FROM utilisateur";
        PreparedStatement pre = con.prepareStatement(req);
        ResultSet res = pre.executeQuery();
        while (res.next()) {
            Utilisateurss utilisateurss = new Utilisateurss();
            utilisateurss.setId(res.getInt("ID_User"));
            utilisateurss.setNom(res.getString("nom"));
            utilisateurss.setPrenom(res.getString("prenom"));
            utilisateurss.setMot_de_passe(res.getString("mot_de_passe"));
            utilisateurss.setEmail(res.getString("email"));
            utilisateurss.setAdresse(res.getString("adresse"));
            utilisateurss.setImage(res.getString("image"));
            utilisateurss.setNiveau_competence(res.getString("niveau_competence"));
            utilisateurss.setRole(res.getString("role"));
            utilisateurss.setDate_de_naissance(res.getString("date_de_naissance"));
            utilisateursses.add(utilisateurss);
        }

        return utilisateursses;
    }

    public Utilisateurss authentifier(String email, String password) throws SQLException {
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

    private Utilisateurss mapUtilisateur(ResultSet result) throws SQLException {
        return new Utilisateurss(
                result.getInt("ID_User"),
                result.getString("nom"),
                result.getString("prenom"),
                result.getString("mot_de_passe"),
                result.getString("email"),
                result.getString("adresse"),
                result.getString("image"),
                result.getString("niveau_competence"),
                result.getString("role"),
                result.getString("date_de_naissance")
        );
    }
}
