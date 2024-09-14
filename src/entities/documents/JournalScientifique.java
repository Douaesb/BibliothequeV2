package entities.documents;

import entities.users.Utilisateur;
import service.EmpruntService;
import service.ReservationService;
import service.interfaces.Empruntable;
import service.interfaces.Reservable;

import java.time.LocalDate;

public class JournalScientifique extends Document implements Empruntable, Reservable {

    private String domaineRecherche;
    private EmpruntService empruntService = new EmpruntService();
    private ReservationService reservationService = new ReservationService();

    public JournalScientifique( String titre, String auteur, LocalDate datePublication, int nombreDePages, String domaineRecherche) {
        super( titre, auteur, datePublication, nombreDePages);
        this.domaineRecherche = domaineRecherche;
    }

    public JournalScientifique(int id, String titre, String auteur, LocalDate datePublication, int nombreDePages, String domaineRecherche) {
        super( id,titre, auteur, datePublication, nombreDePages);
        this.domaineRecherche = domaineRecherche;
    }

    public JournalScientifique(){};

    public String getDomaineRecherche(){
        return domaineRecherche;
    }

    public void setDomaineRecherche(String domaineRecherche) {
        this.domaineRecherche = domaineRecherche;
    }

    @Override
    public String toString() {
        return "Journal Scientifique{" +
                super.toString() +
                "domaineRecherche='" + domaineRecherche + '\'' +
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
