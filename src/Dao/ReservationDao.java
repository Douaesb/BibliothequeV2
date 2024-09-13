package Dao;

import entities.Reservation;

public interface ReservationDao {
    void addReservation(Reservation reservation);
    Reservation getActiveReservationByDocumentIdAndUserId(int documentId, int userId);
    void deleteReservation(int reservationId);
}
