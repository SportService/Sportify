package test;

import entities.Role;
import entities.Utilisateur;
import services.ServicUtilisateur;

import java.util.Date;

import java.sql.SQLException;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        ServicUtilisateur serviceUtilisateur=new ServicUtilisateur();
        Utilisateur newUser = new Utilisateur("BBB", "AA", "password123", "john.doe@example.com", "image.jpg", "123 Street", "Gold", new Date(), Role.USER);
        try {
            //serviceUtilisateur.ajouter(newUser);
            //System.out.println("User added successfully.");

            //newUser.setImage("user-2.png");
            //serviceUtilisateur.modifier(2, newUser);
           // System.out.println("User modified successfully.");




            /*serviceUtilisateur.supprimer(1);
            System.out.println("User deleted successfully.");*/

        // Display all users
            List<Utilisateur> users = serviceUtilisateur.afficher();
            System.out.println("All users:");
            for (Utilisateur user : users) {
                System.out.println(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
