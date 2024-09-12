package presentation;

import entities.users.Etudiant;
import entities.users.Professeur;
import entities.users.Utilisateur;
import service.DocumentService;
import service.UtilisateurService;
import utilitaire.InputValidator;

import java.util.Scanner;

public class UserUI {
    private UtilisateurService utilisateurService;
    private Scanner scanner;

    public UserUI(UtilisateurService userService) {
        this.utilisateurService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void addUtilisateur() {
        System.out.println("=== Ajouter un Utilisateur ===");
        System.out.print("Type d'utilisateur (entrer un nombre) (1:Étudiant, 2:Professeur) : ");

        int type = scanner.nextInt();
        scanner.nextLine();  // consommer la nouvelle ligne

        // Collecte des informations de base
        System.out.print("Nom : ");
        String name = scanner.nextLine();
        System.out.print("Email : ");
        String email = scanner.nextLine();
        System.out.print("Âge : ");
        int age = scanner.nextInt();
        scanner.nextLine();  // consommer la nouvelle ligne

        Utilisateur utilisateur = null;

        // En fonction du type sélectionné, on demande les informations spécifiques
        if (type == 1) {
            // Étudiant
            System.out.print("CNE : ");
            String cne = scanner.nextLine();
            if (InputValidator.isValidCNE(cne)) {
                utilisateur = new Etudiant(name, age, email, cne);
            } else {
                System.out.println("CNE invalide.");
            }
        } else if (type == 2) {
            // Professeur
            System.out.print("CIN : ");
            String cin = scanner.nextLine();
            if (InputValidator.isValidCIN(cin)) {
                utilisateur = new Professeur(name, age, email, cin);
            } else {
                System.out.println("CIN invalide.");
            }
        } else {
            System.out.println("Type d'utilisateur invalide.");
        }

        // Validation et ajout de l'utilisateur
        if (utilisateur != null) {
            utilisateurService.addUtilisateur(utilisateur);
            System.out.println("Utilisateur ajouté avec succès !");
        } else {
            System.out.println("Erreur lors de l'ajout de l'utilisateur.");
        }
    }

    public void editUtilisateur() {
        System.out.println("=== Modifier un Utilisateur ===");
        System.out.print("ID de l'utilisateur à modifier : ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consommer la nouvelle ligne

        // Récupérer l'utilisateur en fonction de l'ID
        Utilisateur utilisateur = utilisateurService.getUtilisateurById(id);

        if (utilisateur != null) {
            System.out.println("Utilisateur trouvé : " + utilisateur);

            // Saisie des nouvelles informations
            System.out.println("Laissez vide si vous ne voulez pas modifier un champ.");

            System.out.print("Nom (" + utilisateur.getName() + ") : ");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) {
                utilisateur.setName(newName);
            }

            System.out.print("Email (" + utilisateur.getEmail() + ") : ");
            String newEmail = scanner.nextLine();
            if (!newEmail.isEmpty()) {
                utilisateur.setEmail(newEmail);
            }

            System.out.print("Âge (" + utilisateur.getAge() + ") : ");
            String newAgeStr = scanner.nextLine();
            if (!newAgeStr.isEmpty()) {
                int newAge = Integer.parseInt(newAgeStr);
                utilisateur.setAge(newAge);
            }

            // Modification des informations spécifiques en fonction du type d'utilisateur
            if (utilisateur instanceof Etudiant) {
                Etudiant etudiant = (Etudiant) utilisateur;
                System.out.print("CNE (" + etudiant.getCNE() + ") : ");
                String newCNE = scanner.nextLine();
                if (!newCNE.isEmpty()) {
                    etudiant.setCNE(newCNE);
                }
            } else if (utilisateur instanceof Professeur) {
                Professeur professeur = (Professeur) utilisateur;
                System.out.print("CIN (" + professeur.getCIN() + ") : ");
                String newCIN = scanner.nextLine();
                if (!newCIN.isEmpty()) {
                    professeur.setCIN(newCIN);
                }
            }

            // Enregistrer les modifications
            utilisateurService.editUtilisateur(utilisateur);
            System.out.println("Utilisateur modifié avec succès !");
        } else {
            System.out.println("Utilisateur non trouvé.");
        }
    }

    public void deleteUtilisateur() {
        System.out.println("=== Supprimer un Utilisateur ===");
        System.out.print("ID de l'utilisateur à supprimer : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        utilisateurService.deleteUtilisateur(id);
        System.out.println("Utilisateur supprimé avec succès !");
    }

    public void displayUtilisateur() {
        System.out.println("=== Afficher un Utilisateur ===");
        System.out.print("ID de l'utilisateur à afficher : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        utilisateurService.displayUtilisateur(id);
    }

    public void displayAllUsers() {
        System.out.println("=== Afficher Tous les Utilisateurs ===");
        utilisateurService.displayAllUtilisateurs();
    }
}
