package service.interfaces;

public interface Empruntable {
     boolean emprunter(int userId);
     boolean retourner(int userId);
}
