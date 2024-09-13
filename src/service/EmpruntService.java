package service;

import Dao.EmpruntDao;
import DaoImpl.EmpruntDaoImpl;
import entities.Emprunt;

import java.time.LocalDate;

public class EmpruntService {
    private EmpruntDao empruntDao = new EmpruntDaoImpl();

    // Méthode pour emprunter un document
    public boolean borrowDocument(int documentId, int userId) {
        Emprunt activeEmprunt = empruntDao.getActiveEmpruntByDocumentIdAndUserId(documentId, userId);
        if (activeEmprunt != null) {
            return false; // The document is already borrowed by the user
        }

        // Proceed with borrowing
        Emprunt emprunt = new Emprunt(0, documentId, userId, LocalDate.now(), "emprunte");
        empruntDao.addEmprunt(emprunt);
        return true;
    }

    // Méthode pour retourner un document
    public boolean returnDocument(int documentId, int userId) {
        Emprunt activeEmprunt = empruntDao.getActiveEmpruntByDocumentIdAndUserId(documentId, userId);
        if (activeEmprunt == null) {
            return false; // No active borrowing found
        }

        // Delete the borrowing record (as returning the document)
        empruntDao.deleteEmprunt(activeEmprunt.getId());
        return true;
    }

    // Méthode pour vérifier si un document est emprunté
    public boolean isDocumentBorrowed(int documentId) {
        return empruntDao.isDocumentBorrowed(documentId);
    }
}
