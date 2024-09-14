package presentation;

import entities.users.Utilisateur;
import service.DocumentService;
import service.EmpruntService;
import service.interfaces.Empruntable;

import java.util.Optional;
import java.util.Scanner;

public class EmpruntUI {

    private final EmpruntService empruntService;
    private final DocumentService documentService;
    private final Scanner scanner;

    public EmpruntUI(EmpruntService empruntService, DocumentService documentService) {
        this.empruntService = empruntService;
        this.documentService = documentService;
        this.scanner = new Scanner(System.in);
    }

    public void borrowDocument(Utilisateur user) {
        System.out.print("Entrez l'ID du document à emprunter : ");
        int documentId = scanner.nextInt();
        int userId = user.getId(); // Get the user ID from the passed Utilisateur object
        scanner.nextLine(); // Consume newline

        // Fetch document by ID and check if it's an instance of Empruntable
        Optional<Empruntable> document = documentService.getDocumentById(documentId)
                .filter(doc -> doc instanceof Empruntable)
                .map(doc -> (Empruntable) doc); // Cast to Empruntable

        // Handle the case where the document is not found or not empruntable
        if (document.isPresent()) {
            boolean success = document.get().emprunter(userId, user); // Pass both userId and user
            if (success) {
                System.out.println("Document emprunté avec succès.");
            } else {
                System.out.println("Échec de l'emprunt. Le document est peut-être déjà emprunté.");
            }
        } else {
            System.out.println("Document non trouvé ou non empruntable.");
        }
    }



    public void returnDocument() {
        System.out.print("Entrez l'ID du document à retourner : ");
        int documentId = scanner.nextInt();
        System.out.print("Entrez l'ID de l'utilisateur : ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Optional<Empruntable> document = documentService.getDocumentById(documentId)
                .filter(doc -> doc instanceof Empruntable)
                .map(doc -> doc);

        if (document.isPresent()) {
            boolean success = document.get().retourner(userId);
            if (success) {
                System.out.println("Document retourné avec succès.");
            } else {
                System.out.println("Échec du retour. Vérifiez les détails et réessayez.");
            }
        } else {
            System.out.println("Document non trouvé ou non retournable.");
        }
    }
}
