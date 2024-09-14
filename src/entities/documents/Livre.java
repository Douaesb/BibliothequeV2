package entities.documents;

import entities.users.Utilisateur;
import service.EmpruntService;
import service.ReservationService;
import service.interfaces.Empruntable;

import java.time.LocalDate;

public class Livre extends Document implements Empruntable {

    private String isbn;
    private EmpruntService empruntService = new EmpruntService();
    private ReservationService reservationService = new ReservationService();

    // Constructeur pour initialiser les attributs de Livre
    public Livre( String titre, String auteur, LocalDate datePublication, int nombreDePages, String isbn) {
        super( titre, auteur, datePublication, nombreDePages);
        this.isbn = isbn;
    }

    public Livre(int id, String titre, String auteur, LocalDate datePublication, int nombreDePages, String isbn) {
        super( id,titre, auteur, datePublication, nombreDePages);
        this.isbn = isbn;
    }

    public Livre(){};


    // Getter pour l'attribut ISBN
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Livre{" +
                 super.toString() +
                "isbn='" + isbn + '\'' +
                "} " ;
    }

    @Override
    public boolean emprunter(int userId, Utilisateur user) {
        // Pass the current instance (this) as the Document and the user as Utilisateur
        return empruntService.borrowDocument(this.getId(), userId, this, user);
    }


    @Override
    public boolean retourner(int userId) {
        return empruntService.returnDocument(this.getId(), userId);
    }

    @Override
    public boolean reserver(int userId, Utilisateur user) {
        return reservationService.reserveDocument(this.getId(), userId, this, user);
    }

    @Override
    public boolean annulerReservation(int userId) {
        return reservationService.cancelReservation(this.getId(), userId);
    }


}
