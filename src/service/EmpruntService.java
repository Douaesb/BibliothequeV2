package service;

import Dao.EmpruntDao;
import DaoImpl.EmpruntDaoImpl;
import entities.Emprunt;
import entities.documents.Document;
import entities.users.Utilisateur;
import utilitaire.BorrowReserveValidator;

import java.time.LocalDate;

public class EmpruntService {
    private EmpruntDao empruntDao;

    public EmpruntService() {
        try {
            this.empruntDao = new EmpruntDaoImpl();
        } catch (Exception e) {
            throw new IllegalStateException("Failed to initialize EmpruntDao.", e);
        }
    }

    // Method to borrow a document
    public boolean borrowDocument(int documentId, int userId, Document document, Utilisateur user) {
        try {
            // Check if the user is allowed to borrow the document type
            if (!BorrowReserveValidator.canBorrowOrReserve(user, document)) {
                System.out.println("User not allowed to borrow this type of document.");
                return false;
            }

            Emprunt activeEmprunt = empruntDao.getActiveEmpruntByDocumentIdAndUserId(documentId, userId);
            if (activeEmprunt != null) {
                return false; // Document already borrowed by user
            }

            // Proceed with borrowing
            Emprunt emprunt = new Emprunt(0, documentId, userId, LocalDate.now(), "emprunte");
            empruntDao.addEmprunt(emprunt);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Indicate failure if an exception occurs
        }
    }

    // Method to return a document
    public boolean returnDocument(int documentId, int userId) {
        try {
            Emprunt activeEmprunt = empruntDao.getActiveEmpruntByDocumentIdAndUserId(documentId, userId);

            if (activeEmprunt == null) {
                return false; // No active borrowing found
            }

            // Delete the borrowing record (return the document)
            empruntDao.deleteEmprunt(activeEmprunt.getId());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Indicate failure if an exception occurs
        }
    }

    // Method to get active emprunt for a document by its ID
    public Emprunt getActiveEmpruntByDocumentId(int documentId) {
        return empruntDao.getActiveEmpruntByDocumentId(documentId);
    }
}
