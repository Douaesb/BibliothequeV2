package entities.documents;

import entities.users.Utilisateur;
import service.EmpruntService;
import service.ReservationService;
import service.interfaces.Empruntable;

import java.time.LocalDate;

public class Magazine extends Document implements Empruntable {

    private int numero;
    private EmpruntService empruntService = new EmpruntService();
    private ReservationService reservationService = new ReservationService();

    // Constructeur pour initialiser les attributs de Magazine
    public Magazine( String titre, String auteur, LocalDate datePublication, int nombreDePages, int numero) {
        super(titre, auteur, datePublication, nombreDePages);
        this.numero = numero;
    }

    public Magazine(int id, String titre, String auteur, LocalDate datePublication, int nombreDePages, int numero) {
        super(id,titre, auteur, datePublication, nombreDePages);
        this.numero = numero;
    }

    public Magazine(){};

    // Getter pour l'attribut numéro
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                super.toString() +
                "numero='" + numero + '\'' +
                "} " ;
    }


    @Override
    public boolean emprunter(int userId, Utilisateur user) {
        // Pass the current instance (this) as the Document and the user as Utilisateur
        return empruntService.borrowDocument(this.getId(), userId, this, user);
    }

    @Override
    public boolean retourner(int userId) {
        boolean success = empruntService.returnDocument(this.getId(), userId);
        if (!success) {
            System.out.println("Le document n'a pas été emprunté ou l'utilisateur ne correspond pas.");
            return false;
        }
        return true;
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
