package service;

import Dao.UtilisateurDao;
import DaoImpl.users.EtudiantDaoImpl;
import DaoImpl.users.ProfesseurDaoImpl;
import entities.users.Etudiant;
import entities.users.Professeur;
import entities.users.Utilisateur;
import utilitaire.InputValidator;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurService {
    private UtilisateurDao etudiantDao = new EtudiantDaoImpl();
    private UtilisateurDao professeurDao = new ProfesseurDaoImpl();

    private boolean validateUtilisateur(Utilisateur utilisateur) {
        if (utilisateur == null) return false;
        boolean isValidName = InputValidator.isValidTitre(utilisateur.getName());
        boolean isValidEmail = InputValidator.isValidEmail(utilisateur.getEmail());
        boolean isValidAge = InputValidator.isValidAge(utilisateur.getAge());

        if (utilisateur instanceof Etudiant) {
            return isValidName && isValidEmail && isValidAge && InputValidator.isValidCNE(((Etudiant) utilisateur).getCNE());
        } else if (utilisateur instanceof Professeur) {
            return isValidName && isValidEmail && isValidAge && InputValidator.isValidCIN(((Professeur) utilisateur).getCIN());
        }
        return false;
    }

    public void addUtilisateur(Utilisateur utilisateur) {
        if (validateUtilisateur(utilisateur)) {
            if (utilisateur instanceof Etudiant) {
                etudiantDao.addUser(utilisateur);
            } else if (utilisateur instanceof Professeur) {
                professeurDao.addUser(utilisateur);
            } else {
                System.out.println("Type d'utilisateur non supporté.");
            }
        } else {
            System.out.println("Les données de l'utilisateur ne sont pas valides.");
        }
    }

    public void editUtilisateur(Utilisateur utilisateur) {
        if (validateUtilisateur(utilisateur)) {
            if (utilisateur instanceof Etudiant) {
                etudiantDao.editUser(utilisateur);
            } else if (utilisateur instanceof Professeur) {
                professeurDao.editUser(utilisateur);
            } else {
                System.out.println("Type d'utilisateur non supporté.");
            }
        } else {
            System.out.println("Les données de l'utilisateur ne sont pas valides.");
        }
    }

    public void displayUtilisateur(int utilisateurId) {
        etudiantDao.displayUser(utilisateurId);
        professeurDao.displayUser(utilisateurId);
    }

    public void displayAllUtilisateurs() {
        List<Utilisateur> allUtilisateurs = new ArrayList<>();
        allUtilisateurs.addAll(etudiantDao.displayAllUsers());
        allUtilisateurs.addAll(professeurDao.displayAllUsers());

        for (Utilisateur utilisateur : allUtilisateurs) {
            System.out.println(utilisateur);
        }
    }

    public void deleteUtilisateur(int utilisateurId) {
        etudiantDao.deleteUser(utilisateurId);
        professeurDao.deleteUser(utilisateurId);
    }

    public int checkUserType(String email) {
        if (etudiantDao.emailExists(email)) {
            return 1; // Étudiant
        }
        if (professeurDao.emailExists(email)) {
            return 2; // Professeur
        }
        if (email.equalsIgnoreCase("admin@bibliotheque.com")) {
            return 3; // Admin
        }
        return -1;
    }

}
