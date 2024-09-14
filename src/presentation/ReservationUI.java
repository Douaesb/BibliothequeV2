package presentation;

import entities.documents.Document;
import entities.users.Utilisateur;
import service.DocumentService;
import service.ReservationService;
import service.interfaces.Reservable;

import java.util.Optional;
import java.util.Scanner;

public class ReservationUI {

    private final ReservationService reservationService;
    private final DocumentService documentService;  // Pour récupérer le document
    private final Scanner scanner;

    public ReservationUI(ReservationService reservationService, DocumentService documentService) {
        this.reservationService = reservationService;
        this.documentService = documentService;
        this.scanner = new Scanner(System.in);
    }

    public void reserveDocument(Utilisateur user) {
        int documentId = getUserInput("Enter the document ID: ");
        int userId = user.getId(); // Get the user ID from the passed Utilisateur object

        Optional<Document> optionalDocument = documentService.getDocumentById(documentId);

        if (optionalDocument.isPresent()) {
            Document document = optionalDocument.get();

            if (document instanceof Reservable) {
                Reservable reservableDocument = (Reservable) document;

                // Pass the user along with the userId when reserving
                boolean isReserved = reservableDocument.reserver(userId, user);

                if (isReserved) {
                    System.out.println("Document successfully reserved.");
                } else {
                    System.out.println("Failed to reserve the document. It may already be borrowed or reserved.");
                }
            } else {
                System.out.println("This document cannot be reserved.");
            }
        } else {
            System.out.println("Document not found.");
        }
    }


    public void cancelReservation() {
        int documentId = getUserInput("Enter the document ID: ");
        int userId = getUserInput("Enter your user ID: ");

        Optional<Document> optionalDocument = documentService.getDocumentById(documentId);

        if (optionalDocument.isPresent()) {
            Document document = optionalDocument.get();
            if (document instanceof Reservable) {
                Reservable reservableDocument = (Reservable) document;
                boolean isCancelled = reservableDocument.annulerReservation(userId);

                if (isCancelled) {
                    System.out.println("Reservation successfully cancelled.");
                } else {
                    System.out.println("Failed to cancel the reservation. It may not exist or does not belong to you.");
                }
            } else {
                System.out.println("This document cannot be reserved or is not reserved.");
            }
        } else {
            System.out.println("Document not found.");
        }
    }

    private int getUserInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            System.out.print(prompt);
        }
        return scanner.nextInt();
    }
}
