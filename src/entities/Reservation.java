package entities;

import java.time.LocalDate;

public class Reservation {
    private int id;
    private int documentId;
    private int utilisateurId;
    private LocalDate dateReservation;
    private String statut; // "reserve" or "annule"

    // Constructors
    public Reservation() {}

    public Reservation(int id, int documentId, int utilisateurId, LocalDate dateReservation, String statut) {
        this.id = id;
        this.documentId = documentId;
        this.utilisateurId = utilisateurId;
        this.dateReservation = dateReservation;
        this.statut = statut;
    }

    public Reservation(int documentId, int utilisateurId, LocalDate dateReservation, String statut) {
        this.documentId = documentId;
        this.utilisateurId = utilisateurId;
        this.dateReservation = dateReservation;
        this.statut = statut;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public int getUserId() {
        return utilisateurId;
    }

    public void setUserId(int utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public LocalDate getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDate dateReservation) {
        this.dateReservation = dateReservation;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    // Override toString for better logging and debugging
    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", documentId=" + documentId +
                ", utilisateurId=" + utilisateurId +
                ", dateReservation=" + dateReservation +
                ", statut='" + statut + '\'' +
                '}';
    }
}
