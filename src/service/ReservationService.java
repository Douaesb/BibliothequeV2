package service;

import Dao.ReservationDao;
import DaoImpl.ReservationDaoImpl;
import entities.Reservation;
import java.time.LocalDate;

public class ReservationService {
    private ReservationDao reservationDao = new ReservationDaoImpl();
    private EmpruntService empruntService = new EmpruntService(); // To check if the document is borrowed

    // Méthode pour réserver un document
    public boolean reserveDocument(int documentId, int userId) {
        // Check if the document is currently borrowed
        boolean isDocumentBorrowed = empruntService.isDocumentBorrowed(documentId);

        if (isDocumentBorrowed) {
            // Document was successfully borrowed, so it cannot be reserved
            return false;
        }

        // If the document is not borrowed, check if it is already reserved by the user
        Reservation activeReservation = reservationDao.getActiveReservationByDocumentIdAndUserId(documentId, userId);
        if (activeReservation != null) {
            return false; // The user has already reserved this document
        }

        // Proceed with reservation
        Reservation reservation = new Reservation(0, documentId, userId, LocalDate.now(), "reserve");
        reservationDao.addReservation(reservation);
        return true;
    }

    // Méthode pour annuler une réservation
    public boolean cancelReservation(int documentId, int userId) {
        Reservation reservation = reservationDao.getActiveReservationByDocumentIdAndUserId(documentId, userId);
        if (reservation == null) {
            return false; // No active reservation found or the reservation does not belong to the user
        }

        // Delete the reservation record
        reservationDao.deleteReservation(reservation.getId());
        return true;
    }

    // Méthode pour emprunter un document directement (sans réservation)
    public boolean borrowDocumentDirectly(int documentId, int userId) {
        // Check if the document is available
        EmpruntService empruntService = new EmpruntService();
        boolean isDocumentAvailable = empruntService.borrowDocument(documentId, userId);

        if (isDocumentAvailable) {
            return true; // Document was borrowed successfully
        }

        // If the document is already borrowed, it cannot be borrowed directly
        return false;
    }
}
