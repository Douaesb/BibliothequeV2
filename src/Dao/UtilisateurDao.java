package Dao;

import entities.users.Utilisateur;
import java.util.List;

public interface UtilisateurDao {

    void addUser(Utilisateur utilisateur);

    void editUser(Utilisateur utilisateur);

    Utilisateur displayUser(int id);

    List<Utilisateur> displayAllUsers();

    void deleteUser(int id);

//    Utilisateur findUserById(int id);
}
