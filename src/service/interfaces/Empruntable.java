package service.interfaces;

import entities.users.Utilisateur;

public interface Empruntable {
     boolean emprunter(int userId, Utilisateur user);
     boolean retourner(int userId);
}
