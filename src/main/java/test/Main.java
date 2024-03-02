package test;

import entities.*;

import services.ServiceCommentaire;
import services.ServicePost;
import utils.DB;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        //ServicePost servicePost = new ServicePost();
        ServiceCommentaire serviceCommentaire = new ServiceCommentaire();

        try {
            // Récupérer un objet post valide de la base de données ou en créer un
            Post post = getPostFromDatabase();
            if (post != null) {
                // Créer un objet commentaire
                Commentaire commentaire = new Commentaire();
                commentaire.setID_User(123); // Remplacez par l'ID utilisateur approprié
                commentaire.setDate(LocalDate.now()); // Remplacez par la date appropriée
                commentaire.setHeure(LocalTime.now()); // Remplacez par l'heure appropriée
                commentaire.setID_commentaire(555);

                // Récupérer un objet commentaire à mettre à jour par son ID
                int ID_commentaireToUpdate = 4;
                Commentaire commentaireToUpdate = serviceCommentaire.getByID(ID_commentaireToUpdate);
                if (commentaireToUpdate != null) {
                    // Mettre à jour les propriétés de l'objet commentaire
                    commentaireToUpdate.setID_User(456); // Remplacez par le nouvel ID utilisateur
                    commentaireToUpdate.setDate(LocalDate.now()); // Remplacez par la nouvelle date
                    commentaireToUpdate.setHeure(LocalTime.now()); // Remplacez par la nouvelle heure
                    commentaireToUpdate.setDescription("Updated description");

                    // Mettre à jour l'objet commentaire dans la base de données
                    serviceCommentaire.modifier(commentaireToUpdate);
                    System.out.println("Commentaire mis à jour avec succès.");
                } else {
                    System.out.println("Commentaire avec l'ID " + ID_commentaireToUpdate + " non trouvé.");
                }

                // Reste du code pour ajouter, mettre à jour et supprimer des posts...
            }
        } catch (NumberFormatException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Post getPostFromDatabase() throws SQLException {
        ServicePost servicePost = new ServicePost();
        List<Post> posts = servicePost.afficher();
        if (!posts.isEmpty()) {
            // Retourne le premier post trouvé (vous devrez peut-être modifier cette logique en fonction de vos besoins)
            return posts.get(0);
        } else {
            System.out.println("Aucun post trouvé dans la base de données.");
            return null;
        }
    }
}
