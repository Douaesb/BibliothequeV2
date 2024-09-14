package Dao;

import entities.Emprunt;


public interface EmpruntDao {
    void addEmprunt(Emprunt emprunt);
    Emprunt getActiveEmpruntByDocumentIdAndUserId(int documentId, int userId);
    void deleteEmprunt(int empruntId);
    boolean isDocumentBorrowed(int documentId);
    Emprunt getActiveEmpruntByDocumentId(int documentId);
}

