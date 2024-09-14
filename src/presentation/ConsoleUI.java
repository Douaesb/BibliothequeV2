package presentation;

import entities.users.Utilisateur;
import service.DocumentService;
import service.EmpruntService;
import service.ReservationService;
import service.UtilisateurService;
import utilitaire.InputValidator;

import java.util.Optional;
import java.util.Scanner;

public class ConsoleUI {

    private Scanner scanner = new Scanner(System.in);
    private DocumentService documentService = new DocumentService();
    private UtilisateurService utilisateurService = new UtilisateurService();
    private EmpruntService empruntService = new EmpruntService();
    private ReservationService reservationService = new ReservationService();

    private DocumentUI DocumentUI = new DocumentUI(documentService);
    private UserUI UserUI = new UserUI(utilisateurService);
    private EmpruntUI EmpruntUI = new EmpruntUI(empruntService,documentService);
    private ReservationUI ReservationUI = new ReservationUI(reservationService,documentService);

    public void start() {
        System.out.println("=== Système de Gestion de Bibliothèque ===");

        String input;
        boolean validInput = false;

        while (!validInput) {
            input = promptInput();

            if (InputValidator.isValidEmail(input)) {
                Optional<Utilisateur> userOptional = utilisateurService.getUtilisateurByEmail(input);

                if (userOptional.isPresent()) {
                    Utilisateur user = userOptional.get();
                    int userType = utilisateurService.checkUserType(user);

                    switch (userType) {
                        case 1: // Etudiant
                            displayEtudiantMenu(user);
                            validInput = true;
                            break;
                        case 2: // Professeur
                            displayProfesseurMenu(user);
                            validInput = true;
                            break;
                        default:
                            System.out.println("Utilisateur non trouvé ou type non reconnu.");
                            break;
                    }
                } else {
                    System.out.println("Utilisateur non trouvé.");
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


    private void displayEtudiantMenu(Utilisateur user) {
        int choice;
        do {
            System.out.println("\n=== Menu Étudiant ===");
            System.out.println("1. Emprunter un livre ou une magazine");
            System.out.println("2. Retourner un livre");
            System.out.println("3. Réserver un livre ou une magazine");
            System.out.println("4. Annuler une réservation");
            System.out.println("5. Afficher");
            System.out.println("6. Se déconnecter");
            System.out.print("Veuillez choisir une option : ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    EmpruntUI.borrowDocument(user);  // Pass the user object
                    break;
                case 2:
                    EmpruntUI.returnDocument();  // Pass the user object for return
                    break;
                case 3:
                    ReservationUI.reserveDocument(user);  // Pass the user object for reservation
                    break;
                case 4:
                    ReservationUI.cancelReservation();  // Pass the user object for canceling reservation
                    break;
                case 5:
                     // Any other method that needs user info
                    break;
                case 6:
                    System.out.println("Déconnexion...");
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        } while (choice != 6);
    }

    private void displayProfesseurMenu(Utilisateur user) {
        int choice;
        do {
            System.out.println("\n=== Menu Professeur ===");
            System.out.println("1. Emprunter une thèse ou un journal scientifique");
            System.out.println("2. Retourner une thèse ou un journal scientifique");
            System.out.println("3. Réserver une thèse ou un journal scientifique");
            System.out.println("4. Annuler reservation");
            System.out.println("5. Afficher");
            System.out.println("6. Se déconnecter");
            System.out.print("Veuillez choisir une option : ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    EmpruntUI.borrowDocument(user);  // Pass the user object
                    break;
                case 2:
                    EmpruntUI.returnDocument();  // Pass the user object for return
                    break;
                case 3:
                    ReservationUI.reserveDocument(user);  // Pass the user object for reservation
                    break;
                case 4:
                    ReservationUI.cancelReservation();  // Pass the user object for canceling reservation
                    break;
                case 5:
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
            System.out.println("5. Afficher un utilisateur");
            System.out.println("6. Retour au menu principal");
            System.out.print("Veuillez choisir une option : ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    UserUI.addUtilisateur();
                    break;
                case 2:
                    UserUI.editUtilisateur();
                    break;
                case 3:
                    UserUI.deleteUtilisateur();
                    break;
                case 4:
                    UserUI.displayAllUsers();
                    break;
                case 5:
                    UserUI.displayUtilisateur();
                    break;
                case 6:
                    System.out.println("Retour au menu principal...");
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        } while (choice != 6);
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
            System.out.println("6. Rechercher un document");
            System.out.println("7. Retour au menu principal");
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
                    DocumentUI.searchDocument();
                    break;
                case 7:
                    System.out.println("Retour au menu principal...");
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        } while (choice != 7);
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


}
