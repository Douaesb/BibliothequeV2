package utilitaire;


import entities.documents.Document;
import entities.documents.Livre;
import entities.documents.Magazine;
import entities.documents.TheseUniversitaire;
import entities.documents.JournalScientifique;
import entities.users.Etudiant;
import entities.users.Professeur;
import entities.users.Utilisateur;

public class BorrowReserveValidator {

    // Method to check if the user can borrow or reserve the document based on their type
    public static boolean canBorrowOrReserve(Utilisateur utilisateur, Document document) {
        if (utilisateur instanceof Etudiant) {
            return (document instanceof Livre || document instanceof Magazine);
        } else if (utilisateur instanceof Professeur) {
            return (document instanceof TheseUniversitaire || document instanceof JournalScientifique);
        }
        return false;
    }
}

