package services;


import entities.Custom;
import entities.Equipe;
import entities.Match;
import entities.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceCustom implements services.IService<Custom> {
    private ServiceMatch serviceMatch;

    private Connection con;

    public ServiceCustom(ServiceMatch serviceMatch){
        con = utils.DB.getInstance().getConnection();
        this.serviceMatch = serviceMatch;
    }

    @Override
    public void ajouter(Custom custom) throws SQLException {
        // Étape 1 : Ajouter un nouvel enregistrement dans la table custom
        String reqCustom = "INSERT INTO custom (ID_Custom, ID_User,dureeEstimee ,dateCreation ,commentairesClient ) VALUES (?,?,?,?, ?)";
        PreparedStatement preCustom = con.prepareStatement(reqCustom, Statement.RETURN_GENERATED_KEYS);
        preCustom.setInt(1, custom.getIdcustom());
        preCustom.setInt(2, custom.getId_user().getId());
        preCustom.setInt(3, custom.getDureeEstimee());
        preCustom.setDate(4, custom.getDateCreation());
        preCustom.setString(5, custom.getCommentairesClient());

        preCustom.executeUpdate();

        // Étape 2 : Récupérer l'id généré pour l'enregistrement custom
        ResultSet generatedKeysCustom = preCustom.getGeneratedKeys();
        int idCustom;
        if (generatedKeysCustom.next()) {
            idCustom = generatedKeysCustom.getInt(1);
        } else {
            throw new SQLException("Échec de la création de l'enregistrement custom, aucun ID généré.");
        }

        // Étape 3 : Ajouter un nouvel enregistrement dans la table match
        String reqMatch = "INSERT INTO `matc` (ID_Matc,  nom, type, description, date, heure, equipe1, equipe2) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preMatch = con.prepareStatement(reqMatch);
        preMatch.setInt(1, custom.getIdcustom()); // Utilisez idcustom comme idmatch dans la table match
       // preMatch.setInt(2, idCustom); // Utilisez l'id généré de custom comme référence dans la table match
        preMatch.setString(2, custom.getNom());
        preMatch.setString(3, custom.getType());
        preMatch.setString(4, custom.getDescription());
        preMatch.setDate(5, custom.getDate());
        preMatch.setTime(6, custom.getHeure());
        preMatch.setInt(7, custom.getEquipe1().getID());
        preMatch.setInt(8, custom.getEquipe2().getID());
        preMatch.executeUpdate();
    }
    @Override
    public void modifier(Custom custom) throws SQLException {
        // Étape 1 : Mettre à jour l'enregistrement dans la table custom
        String reqCustom = "UPDATE custom SET ID_User = ?, dureeEstimee = ?, dateCreation = ?, commentairesClient = ? WHERE ID_Custom = ?";
        PreparedStatement preCustom = con.prepareStatement(reqCustom);
        preCustom.setInt(1, custom.getId_user().getId());
        preCustom.setInt(2, custom.getDureeEstimee());
        preCustom.setDate(3, custom.getDateCreation());
        preCustom.setString(4, custom.getCommentairesClient());
        preCustom.setInt(5, custom.getIdcustom());
        preCustom.executeUpdate();

        // Étape 2 : Mettre à jour l'enregistrement dans la table match
        String reqMatch = "UPDATE matc SET nom = ?, type = ?, description = ?, date = ?, heure = ?, equipe1 = ?, equipe2 = ? WHERE ID_Matc = ?";
        PreparedStatement preMatch = con.prepareStatement(reqMatch);
        preMatch.setString(1, custom.getNom());
        preMatch.setString(2, custom.getType());
        preMatch.setString(3, custom.getDescription());
        preMatch.setDate(4, custom.getDate());
        preMatch.setTime(5, custom.getHeure());
        preMatch.setInt(6, custom.getEquipe1().getID());
        preMatch.setInt(7, custom.getEquipe2().getID());
        preMatch.setInt(8, custom.getIdcustom());
        preMatch.executeUpdate();
    }


    @Override
    public void supprimer(Custom custom) throws SQLException {
        // Étape 1 : Supprimer l'enregistrement de la table match
        String reqDeleteMatch = "DELETE FROM matc WHERE ID_Matc = ?";
        PreparedStatement pre = con.prepareStatement(reqDeleteMatch);
        pre.setInt(1, custom.getIdcustom());
        pre.executeUpdate();

        // Étape 2 : Supprimer l'enregistrement de la table custom
        String reqDeleteCustom = "DELETE FROM custom WHERE ID_Custom = ?";
        PreparedStatement preDeleteCustom = con.prepareStatement(reqDeleteCustom);
        preDeleteCustom.setInt(1, custom.getIdcustom());
        preDeleteCustom.executeUpdate();
    }


    @Override
    public List<Custom> afficher() throws SQLException {
        List<Custom> customList = new ArrayList<>();

        String req = "SELECT c.ID_Custom, c.ID_User, c.dureeEstimee, c.dateCreation, c.commentairesClient, m.nom, m.type, m.description, m.date, m.heure, m.equipe1, m.equipe2 FROM custom c JOIN matc m ON c.ID_Custom = m.ID_Matc";
        PreparedStatement pre = con.prepareStatement(req);
        ResultSet res = pre.executeQuery();

        while (res.next()) {
            int idCustom = res.getInt("ID_Custom");
            Utilisateur utilisateur = getUtilisateurById(res.getInt("ID_User"));
            int dureeEstimee = res.getInt("dureeEstimee");
            Date dateCreation = res.getDate("dateCreation");
            String commentairesClient = res.getString("commentairesClient");
            String nomMatch = res.getString("nom");
            String typeMatch = res.getString("type");
            String descriptionMatch = res.getString("description");
            Date dateMatch = res.getDate("date");
            Time heureMatch = res.getTime("heure");
            int idEquipe1 = res.getInt("equipe1");
            int idEquipe2 = res.getInt("equipe2");

            Equipe equipe1 = serviceMatch.getEquipeById(idEquipe1);
            Equipe equipe2 = serviceMatch.getEquipeById(idEquipe1);



            Custom custom = new Custom(idCustom, utilisateur, dureeEstimee, dateCreation, commentairesClient, nomMatch, typeMatch, descriptionMatch, dateMatch, heureMatch, equipe1, equipe2);
            customList.add(custom);
        }

        return customList;
    }
    public Utilisateur getUtilisateurById(int userId) throws SQLException {
        Utilisateur utilisateur = null;
        String req = "SELECT * FROM utilisateur WHERE ID_User = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, userId);
        ResultSet res = pre.executeQuery();

        if (res.next()) {
            // Construire un objet Utilisateur à partir des données du ResultSet
            utilisateur = new Utilisateur();
            utilisateur.setId(res.getInt("ID_User"));
            utilisateur.setNom(res.getString("nom"));
            utilisateur.setPrenom(res.getString("prenom"));
            utilisateur.setEmail(res.getString("email"));
            // Autres attributs de l'utilisateur

            return utilisateur;
        } else {
            // L'utilisateur n'a pas été trouvé, vous pouvez choisir de renvoyer null ou lancer une exception
            throw new SQLException("Utilisateur non trouvé avec l'ID : " + userId);
        }
    }
}
