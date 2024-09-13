package service;

import Dao.EmpruntDao;
import DaoImpl.EmpruntDaoImpl;
import entities.Emprunt;

import java.time.LocalDate;

public class EmpruntService {
    private EmpruntDao empruntDao = new EmpruntDaoImpl();

    // Method to borrow a document
    public boolean borrowDocument(int documentId, int userId) {
        Emprunt activeEmprunt = empruntDao.getActiveEmpruntByDocumentIdAndUserId(documentId, userId);
        if (activeEmprunt != null) {
            return false; // Document already borrowed by user
        }

        // Proceed with borrowing
        Emprunt emprunt = new Emprunt(0, documentId, userId, LocalDate.now(), "emprunte");
        empruntDao.addEmprunt(emprunt);
        return true;
    }

    // Method to return a document
    public boolean returnDocument(int documentId, int userId) {
        Emprunt activeEmprunt = empruntDao.getActiveEmpruntByDocumentIdAndUserId(documentId, userId);
        if (activeEmprunt == null) {
            return false; // No active borrowing found
        }

        // Delete the borrowing record (return the document)
        empruntDao.deleteEmprunt(activeEmprunt.getId());
        return true;
    }
}