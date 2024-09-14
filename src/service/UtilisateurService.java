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
import java.util.Optional;

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
        Utilisateur utilisateur = getUtilisateurById(utilisateurId);
        if (utilisateur != null) {
            System.out.println(utilisateur);
        } else {
            System.out.println("Utilisateur non trouvé.");
        }
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

    public int checkUserType(Utilisateur utilisateur) {
        if (utilisateur instanceof Etudiant) {
            return 1; // Étudiant
        }
        if (utilisateur instanceof Professeur) {
            return 2; // Professeur
        }
        return -1;
    }

    public Optional<Utilisateur> getUtilisateurByEmail(String email) {
        Utilisateur utilisateur = etudiantDao.getUserByEmail(email);  // Try to get the Etudiant
        if (utilisateur != null) {
            return Optional.of(utilisateur);
        }

        utilisateur = professeurDao.getUserByEmail(email);  // If not found, try with Professeur
        return Optional.ofNullable(utilisateur);
    }

    public Utilisateur getUtilisateurById(int id) {
        Utilisateur utilisateur = etudiantDao.getUserById(id);  // Try to get the Etudiant
        if (utilisateur != null) {
            return utilisateur;
        }

        utilisateur = professeurDao.getUserById(id);  // If not found, try with Professeur
        return utilisateur;
    }
}
