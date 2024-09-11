package presentation;

import service.DocumentService;
import service.UtilisateurService;
import utilitaire.InputValidator;

import java.util.Scanner;

public class ConsoleUI {

    private Scanner scanner = new Scanner(System.in);
    private DocumentService documentService = new DocumentService();
    private UtilisateurService utilisateurService = new UtilisateurService();
    private DocumentUI DocumentUI = new DocumentUI(documentService);

    public void start() {
        System.out.println("=== Système de Gestion de Bibliothèque ===");

        String input;
        boolean validInput = false;

        while (!validInput) {
            input = promptInput();

            if (InputValidator.isValidEmail(input)) {
                int userType = utilisateurService.checkUserType(input);

                switch (userType) {
                    case 1:
                        displayEtudiantMenu(input);
                        validInput = true;
                        break;
                    case 2:
                        displayProfesseurMenu(input);
                        validInput = true;
                        break;
                    default:
                        System.out.println("Utilisateur non trouvé ou type non reconnu.");
                        break;
                }
            } else if (isAdminSecretCodeValid(input)) {
                displayAdminMenu();
                validInput = true;
            } else {
                System.out.println("Entrée non valide. Veuillez entrer un email valide.");
            }
        }
    }


    private String promptInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Veuillez entrer votre email : ");
        return scanner.nextLine().trim();
    }

    private boolean isAdminSecretCodeValid(String code) {
        String adminSecretCode = "admin123";
        return code.equals(adminSecretCode);
    }


    private void displayEtudiantMenu(String email) {
        int choice;
        do {
            System.out.println("\n=== Menu Étudiant ===");
            System.out.println("1. Emprunter un livre ou un magazine");
            System.out.println("2. Réserver un livre ou un magazine");
            System.out.println("3. Afficher les documents disponibles (Livres et Magazines)");
            System.out.println("4. Voir mes emprunts et réservations");
            System.out.println("5. Retourner un livre / Annuler une réservation");
            System.out.println("6. Se déconnecter");
            System.out.print("Veuillez choisir une option : ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    // Appel à la méthode emprunt pour les étudiants
                    emprunterLivreOuMagazine(email);
                    break;
                case 2:
                    // Appel à la méthode réservation pour les étudiants
                    reserverLivreOuMagazine(email);
                    break;
                case 3:
                    // Afficher les livres et magazines disponibles
                    afficherDocumentsDisponibles("Etudiant");
                    break;
                case 4:
                    // Afficher les emprunts et réservations de l'étudiant
                    afficherEmpruntsReservations(email);
                    break;
                case 5:
                    // Retourner un livre ou annuler une réservation
                    retournerLivreOuAnnulerReservation(email);
                    break;
                case 6:
                    System.out.println("Déconnexion...");
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        } while (choice != 6);
    }

    private void displayProfesseurMenu(String email) {
        int choice;
        do {
            System.out.println("\n=== Menu Professeur ===");
            System.out.println("1. Emprunter une thèse ou un journal scientifique");
            System.out.println("2. Réserver une thèse ou un journal scientifique");
            System.out.println("3. Afficher les documents disponibles (Thèses et Journaux Scientifiques)");
            System.out.println("4. Voir mes emprunts et réservations");
            System.out.println("5. Retourner une thèse / Annuler une réservation");
            System.out.println("6. Se déconnecter");
            System.out.print("Veuillez choisir une option : ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    emprunterTheseOuJournal(email);
                    break;
                case 2:
                    reserverTheseOuJournal(email);
                    break;
                case 3:
                    afficherDocumentsDisponibles("Professeur");
                    break;
                case 4:
                    afficherEmpruntsReservations(email);
                    break;
                case 5:
                    retournerTheseOuAnnulerReservation(email);
                    break;
                case 6:
                    System.out.println("Déconnexion...");
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        } while (choice != 6);
    }
    private void displayUserManagementMenu() {
        int choice;
        do {
            System.out.println("\n=== Gestion des Utilisateurs ===");
            System.out.println("1. Ajouter un utilisateur");
            System.out.println("2. Modifier un utilisateur");
            System.out.println("3. Supprimer un utilisateur");
            System.out.println("4. Afficher tous les utilisateurs");
            System.out.println("5. Retour au menu principal");
            System.out.print("Veuillez choisir une option : ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    ajouterUtilisateur();
                    break;
                case 2:
                    modifierUtilisateur();
                    break;
                case 3:
                    supprimerUtilisateur();
                    break;
                case 4:
                    afficherTousLesUtilisateurs();
                    break;
                case 5:
                    System.out.println("Retour au menu principal...");
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        } while (choice != 5);
    }

    private void displayDocumentManagementMenu() {
        int choice;
        do {
            System.out.println("\n=== Gestion des Documents ===");
            System.out.println("1. Ajouter un document");
            System.out.println("2. Modifier un document");
            System.out.println("3. Supprimer un document");
            System.out.println("4. Afficher tous les documents");
            System.out.println("5. Afficher un document");
            System.out.println("6. Retour au menu principal");
            System.out.print("Veuillez choisir une option : ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    DocumentUI.addDocument();
                    break;
                case 2:
                    DocumentUI.editDocument();
                    break;
                case 3:
                    DocumentUI.deleteDocument();
                    break;
                case 4:
                    DocumentUI.displayAllDocuments();
                    break;
                case 5:
                    DocumentUI.displayDocument();
                    break;
                case 6:
                    System.out.println("Retour au menu principal...");
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        } while (choice != 6);
    }


    private void displayAdminMenu() {
        int choice;
        do {
            System.out.println("\n=== Menu Principal d'Administrateur ===");
            System.out.println("1. Gestion des utilisateurs");
            System.out.println("2. Gestion des documents");
            System.out.println("3. Se déconnecter");
            System.out.print("Veuillez choisir une option : ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    displayUserManagementMenu();
                    break;
                case 2:
                    displayDocumentManagementMenu();
                    break;
                case 3:
                    System.out.println("Déconnexion...");
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        } while (choice != 3);
    }

    // Méthodes spécifiques pour chaque action
    private void emprunterLivreOuMagazine(String email) {
        // Appel aux services pour emprunter un livre ou un magazine pour un étudiant
    }

    private void reserverLivreOuMagazine(String email) {
        // Appel aux services pour réserver un livre ou un magazine pour un étudiant
    }

    private void afficherDocumentsDisponibles(String userType) {
        // Appel aux services pour afficher les documents disponibles selon le type d'utilisateur
    }

    private void afficherEmpruntsReservations(String email) {
        // Appel aux services pour afficher les emprunts et réservations de l'utilisateur
    }

    private void retournerLivreOuAnnulerReservation(String email) {
        // Appel aux services pour retourner un livre ou annuler une réservation
    }

    private void emprunterTheseOuJournal(String email) {
        // Appel aux services pour emprunter une thèse ou un journal scientifique pour un professeur
    }

    private void reserverTheseOuJournal(String email) {
        // Appel aux services pour réserver une thèse ou un journal scientifique pour un professeur
    }

    private void retournerTheseOuAnnulerReservation(String email) {
        // Appel aux services pour retourner une thèse ou annuler une réservation pour un professeur
    }

    private void ajouterUtilisateur() {
        // Méthode pour ajouter un utilisateur (admin seulement)
    }

    private void modifierUtilisateur() {
        // Méthode pour modifier un utilisateur (admin seulement)
    }

    private void supprimerUtilisateur() {
        // Méthode pour supprimer un utilisateur (admin seulement)
    }



    private void afficherTousLesUtilisateurs() {
    }

}
