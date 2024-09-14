package service.interfaces;

import entities.users.Utilisateur;

public interface Reservable {
     boolean reserver(int userId, Utilisateur user);
     boolean annulerReservation(int userId);
}
