package entities;

import java.time.LocalDate;

public class Emprunt {
    private int id;
    private int documentId;
    private int userId;
    private LocalDate dateEmprunt;
    private String statut;

    // Constructor
    public Emprunt(int id, int documentId, int userId, LocalDate dateEmprunt, String statut) {
        this.id = id;
        this.documentId = documentId;
        this.userId = userId;
        this.dateEmprunt = dateEmprunt;
        this.statut = statut;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getDocumentId() { return documentId; }
    public void setDocumentId(int documentId) { this.documentId = documentId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public LocalDate getDateEmprunt() { return dateEmprunt; }
    public void setDateEmprunt(LocalDate dateEmprunt) { this.dateEmprunt = dateEmprunt; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
}
