package presentation;

import service.EmpruntService;

import java.util.Scanner;

public class EmpruntUI {

    private final EmpruntService empruntService;
    private final Scanner scanner;

    public EmpruntUI(EmpruntService empruntService) {
        this.empruntService = empruntService;
        this.scanner = new Scanner(System.in);
    }
    public void borrowDocument() {
        System.out.print("Entrez l'ID du document à emprunter : ");
        int documentId = scanner.nextInt();
        System.out.print("Entrez l'ID de l'utilisateur : ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean success = empruntService.borrowDocument(documentId, userId);
        if (success) {
            System.out.println("Document emprunté avec succès.");
        } else {
            System.out.println("Échec de l'emprunt. Vérifiez les détails et réessayez.");
        }
    }

    public void returnDocument() {
        System.out.print("Entrez l'ID du document à retourner : ");
        int documentId = scanner.nextInt();
        System.out.print("Entrez l'ID de l'utilisateur : ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean success = empruntService.returnDocument(documentId, userId);
        if (success) {
            System.out.println("Document retourné avec succès.");
        } else {
            System.out.println("Échec du retour. Vérifiez les détails et réessayez.");
        }
    }
}
