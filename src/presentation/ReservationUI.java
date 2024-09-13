package presentation;

import service.EmpruntService;
import service.ReservationService;

import java.util.Scanner;

public class ReservationUI {

    private ReservationService reservationService = new ReservationService();
    private Scanner scanner = new Scanner(System.in);

    public ReservationUI(ReservationService reservationService) {
        this.reservationService = reservationService;
        this.scanner = new Scanner(System.in);
    }


    public void reserveDocument() {
        int documentId = getUserInput("Enter the document ID: ");
        int userId = getUserInput("Enter your user ID: ");

        boolean isReserved = reservationService.reserveDocument(documentId, userId);
          if (isReserved) {
            System.out.println("Document successfully reserved.");
        } else {
            System.out.println("Failed to reserve the document. It may already be borrowed or reserved.");
        }
    }

    public void cancelReservation() {
        int documentId = getUserInput("Enter the document ID: ");
        int userId = getUserInput("Enter your user ID: ");

        boolean isCancelled = reservationService.cancelReservation(documentId, userId);
        if (isCancelled) {
            System.out.println("Reservation successfully cancelled.");
        } else {
            System.out.println("Failed to cancel the reservation. It may not exist or does not belong to you.");
        }
    }

    public int getUserInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            System.out.print(prompt);
        }
        return scanner.nextInt();
    }
}
