package presentation;

import metier.Bibliotheque;
import metier.Document;
import metier.Livre;
import metier.Magazine;
import utilitaire.DateUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class ConsoleUI {
    private final Bibliotheque bibliotheque;
    private final Scanner scanner;
    private final Map<Integer, Runnable> menuOptions;

    // Constructeur de la classe ConsoleUI
    public ConsoleUI() {
        bibliotheque = new Bibliotheque();
        scanner = new Scanner(System.in);
        menuOptions = new HashMap<>();
        menuOptions.put(1, () -> ajouterDocument());
        menuOptions.put(2, () -> emprunterDocument());
        menuOptions.put(3, () -> retournerDocument());
        menuOptions.put(4, () -> afficherTousLesDocuments());
        menuOptions.put(5, () -> rechercherDocument());

        menuOptions.put(6, () -> System.out.println("Merci d'avoir utilise la bibliotheque !"));

    }

    // Méthode pour afficher le menu
    public void afficherMenu() {
        int choix = 0;
        boolean validInput = false;

        do {
            System.out.println("\n=== Menu de la Bibliotheque ===");
            System.out.println("1. Ajouter un document");
            System.out.println("2. Emprunter un document");
            System.out.println("3. Retourner un document");
            System.out.println("4. Afficher tous les documents");
            System.out.println("5. Rechercher un document");
            System.out.println("6. Quitter");
            System.out.print("Veuillez entrer votre choix (1-6) : ");

            while (!validInput) {
                try {
                    choix = Integer.parseInt(scanner.nextLine());
                    validInput = true; // Input is valid, exit the loop
                } catch (NumberFormatException e) {
                    System.out.println("Entree invalide. Veuillez entrer un nombre entier.");
                    System.out.print("Veuillez entrer votre choix (1-6) : ");
                }
            }

            Runnable action = menuOptions.get(choix);
            if (action != null) {
                action.run();
            } else {
                System.out.println("Choix invalide. Veuillez reessayer.");
            }
            // Reset validInput for the next iteration
            validInput = false;
        } while (choix != 6);
    }

    private LocalDate lireDate() {
        while (true) {
            System.out.print("Entrez la date de publication (au format dd-MM-yyyy) : ");
            String dateStr = scanner.nextLine();
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                return LocalDate.parse(dateStr, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Format de date invalide. Veuillez reessayer.");
            }
        }
    }

    private int lireNombreEntier(String message) {
        while (true) {
            System.out.print(message);
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entree invalide. Veuillez entrer un nombre entier.");
                scanner.next(); // Consomme l'entrée incorrecte
            }
        }
    }

    private String lireChaine(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
    // Méthode pour ajouter un document
    private void ajouterDocument() {
        int type = lireNombreEntier("Entrez le type de document (1: Livre, 2: Magazine) : ");
        int id = Bibliotheque.generateId();
        scanner.nextLine();

        String titre = lireChaine("Entrez le titre : ");

        String auteur = lireChaine("Entrez l'auteur : ");
        LocalDate datePublication = lireDate();
        int nombreDePages = lireNombreEntier("Entrez le nombre de pages : ");
            scanner.nextLine();
        if (type == 1) { // Ajouter un livre
            String isbn = lireChaine("Entrez l'ISBN : ");
            Livre livre = new Livre(id, titre, auteur, datePublication, nombreDePages, isbn);
            bibliotheque.ajouter(livre);
            System.out.println("Livre ajoute avec succes !");
        } else if (type == 2) { // Ajouter un magazine
            int numero = lireNombreEntier("Entrez le numero : ");
            scanner.nextLine();
            Magazine magazine = new Magazine(id, titre, auteur, datePublication, nombreDePages, numero);
            bibliotheque.ajouter(magazine);
            System.out.println("Magazine ajoute avec succes !");
        } else {
            System.out.println("Type de document invalide.");
        }
    }

    // Méthode pour emprunter un document
    private void emprunterDocument() {
        System.out.print("Entrez le titre du document a emprunter : ");
        String titre = scanner.nextLine();
        boolean success = bibliotheque.emprunterDocument(titre);
        if (success) {
            System.out.println("Document emprunte avec succes !");
        } else {
            System.out.println("Echec de l'emprunt.");
        }
    }

    // Méthode pour retourner un document
    private void retournerDocument() {
        System.out.print("Entrez le titre du document a retourner : ");
        String titre = scanner.nextLine();
        boolean success = bibliotheque.retournerDocument(titre);
        if (success) {
            System.out.println("Document retourne avec succes !");
        } else {
            System.out.println("Echec du retour.");
        }
    }

    // Méthode pour afficher tous les documents
    private void afficherTousLesDocuments() {
        bibliotheque.afficherTousLesDocuments();
    }

    // Méthode pour rechercher un document par son titre
    private void rechercherDocument() {
        System.out.print("Entrez le titre du document a rechercher : ");
        String titre = scanner.nextLine();
        Document document = bibliotheque.rechercher(titre);
        if (document != null) {
            document.afficherDetails();
        } else {
            System.out.println("Document non trouve.");
        }
    }
}
