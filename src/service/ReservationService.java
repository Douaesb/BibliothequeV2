package service;

import Dao.ReservationDao;
import DaoImpl.ReservationDaoImpl;
import entities.Reservation;
import entities.Emprunt;
import entities.documents.Document;
import entities.users.Utilisateur;
import utilitaire.BorrowReserveValidator;

import java.time.LocalDate;
import java.util.Scanner;

public class ReservationService {
    private ReservationDao reservationDao = new ReservationDaoImpl();
    private EmpruntService empruntService = new EmpruntService(); // To check if the document is borrowed

    // Method to reserve a document
    public boolean reserveDocument(int documentId, int userId, Document document, Utilisateur user) {
        // Check if the user is allowed to reserve the document type
        if (!BorrowReserveValidator.canBorrowOrReserve(user, document)) {
            System.out.println("User not allowed to reserve this type of document.");
            return false;
        }

        // Check if the document is already borrowed by another user
        Emprunt activeEmprunt = empruntService.getActiveEmpruntByDocumentId(documentId);
        if (activeEmprunt == null) {
            // Document is not currently borrowed, prompt the user to borrow instead
            System.out.println("This document is available. Would you like to borrow it instead? (yes/no): ");
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine().toLowerCase();

            if (choice.equals("yes")) {
                // Proceed with borrowing the document
                return empruntService.borrowDocument(documentId, userId, document, user);
            } else {
                return false; // User chose not to borrow
            }
        }

        // If the document is borrowed, proceed with the reservation
        Reservation activeReservation = reservationDao.getActiveReservationByDocumentIdAndUserId(documentId, userId);
        if (activeReservation != null) {
            return false; // The user has already reserved this document
        }

        // Proceed with reservation
        Reservation reservation = new Reservation(0, documentId, userId, LocalDate.now(), "reserve");
        reservationDao.addReservation(reservation);
        return true;
    }

    // Method to cancel a reservation
    public boolean cancelReservation(int documentId, int userId) {
        Reservation reservation = reservationDao.getActiveReservationByDocumentIdAndUserId(documentId, userId);
        if (reservation == null) {
            return false; // No active reservation found or the reservation does not belong to the user
        }

        // Delete the reservation record
        reservationDao.deleteReservation(reservation.getId());
        return true;
    }
}
