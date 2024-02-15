package test;

import entities.Categorie;
import entities.Equipe;

import entities.Utilisateur;
import services.ServiceCategorie;
import services.ServiceEquipe;
import utils.DB;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        DB conn1 = DB.getInstance();

        ServiceCategorie serviceCategorie = new ServiceCategorie();
        ServiceEquipe serviceEquipe = new ServiceEquipe();

        try {
            // Retrieve a valid Categorie object from the database or create one
            Categorie categorie = getCategorieFromDatabase(); // Implement this method
            if (categorie != null) {
                // Create an Equipe object
                Equipe equipe = new Equipe();
                equipe.setNom("Sample Equipe");
                equipe.setNiveau("Sample Niveau");
                equipe.setRandom(true);
                equipe.setRank(1);
                // Set the IDCateg property of the Equipe object
                equipe.setIDCateg(categorie);

                // Add the Equipe to the database
               // serviceEquipe.ajouter(equipe);

                // Retrieve the generated id_User for the Utilisateur object from the database
                int id_User = retrieveGeneratedIdFromDatabase(); // Implement this method to retrieve the generated id_User from the database

                // Create a new Utilisateur object with the generated id_User
                Utilisateur createur = new Utilisateur("John", "Doe", "john@example.com", "password", "main\\resources\\img\\807f757c-tennis-gf8a2b0441_1920-768x513.jpg", "niveau", "role", "adresse", "sexe");
                createur.setID_User(id_User); // Assuming setId_User method is available in Utilisateur class

                // Update the id_createur field of the Equipe object with the new Utilisateur object
                equipe.setId_createur(createur); // Assuming setCreateur method is available in Equipe class

                // Update the Equipe object in the database with the updated id_createur
                serviceEquipe.modifier(equipe);
            } else {
                System.out.println("Failed to retrieve valid Categorie object.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
            return;
        }
        try {
            List<Equipe> equipes = serviceEquipe.afficher();
            for (Equipe equipe : equipes) {
                System.out.println(equipe); // Print each Equipe object
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }

        try {
            // Retrieve the ID of the Equipe object to delete
            int equipeIdToDelete = 15; // Replace 1 with the actual ID of the Equipe you want to delete

            // Create a new Equipe object with the retrieved ID
            Equipe equipeToDelete = new Equipe();
            equipeToDelete.setID(equipeIdToDelete);

            // Delete the Equipe object from the database
            serviceEquipe.supprimer(equipeToDelete);
            System.out.println("Equipe deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }


        try {
            // Retrieve an Equipe object to update by its ID
            int equipeIdToUpdate = 18; // Replace 1 with the actual ID of the Equipe you want to update
            Equipe equipeToUpdate = serviceEquipe.getByID(equipeIdToUpdate); // Assuming you have a method to retrieve an Equipe by its ID

            // Check if the Equipe object exists
            if (equipeToUpdate != null) {
                // Update the properties of the Equipe object
                equipeToUpdate.setNom("Updated Nom");
                equipeToUpdate.setNiveau("Updated Niveau");
                equipeToUpdate.setRandom(false); // Set random to false as an example
                equipeToUpdate.setRank(2); // Set rank to 2 as an example

                // Update the Equipe object in the database
                serviceEquipe.modifier(equipeToUpdate);
                System.out.println("Equipe updated successfully.");
            } else {
                System.out.println("Equipe with ID " + equipeIdToUpdate + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }


        try {
            /*
            // Add a new category
            Categorie newCategory = new Categorie();
            newCategory.setNom("New Category");
            newCategory.setDescription("Description of the new category");
            newCategory.setImage("image_path");
            serviceCategorie.ajouter(newCategory);
*/
            // Update an existing category
            Categorie categoryToUpdate = new Categorie();
            categoryToUpdate.setIDCateg(1); // ID of the category to update
            categoryToUpdate.setNom("Updated Category");
            categoryToUpdate.setDescription("Updated description");
            categoryToUpdate.setImage("updated_image_path");
            serviceCategorie.modifier(categoryToUpdate);

            // Retrieve all categories
            List<Categorie> categories = serviceCategorie.afficher();
            for (Categorie categorie : categories) {
                System.out.println(categorie);
            }


                // Delete a category
                Categorie categoryToDelete = new Categorie();
                categoryToDelete.setIDCateg(13); // ID of the category to delete
                serviceCategorie.supprimer(categoryToDelete);
                System.out.println("Category deleted successfully.");
            } catch (SQLException e) {
                // Inform the user that the category cannot be deleted
                System.out.println("Cannot delete the category because it is referenced by equipe records.");
                // Print the stack trace for debugging purposes
                e.printStackTrace();
            }



        }






    private static Categorie getCategorieFromDatabase() throws SQLException {
        ServiceCategorie serviceCategorie = new ServiceCategorie();
        List<Categorie> categories = serviceCategorie.afficher();
        if (!categories.isEmpty()) {
            // Return the first category found (you may need to modify this logic based on your requirements)
            return categories.get(0);
        } else {
            System.out.println("No categories found in the database.");
            return null;
        }


    }

    private static int retrieveGeneratedIdFromDatabase() throws SQLException {
        Connection
                connection = DB.getInstance().getConnection();
        String query = "SELECT LAST_INSERT_ID()";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new SQLException("Failed to retrieve generated ID from the database.");
            }
        }
    }



}
