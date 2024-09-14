package entities.documents;

import entities.users.Utilisateur;
import service.EmpruntService;
import service.ReservationService;
import service.interfaces.Empruntable;

import java.time.LocalDate;

public class TheseUniversitaire extends Document implements Empruntable {

    private String university;
    private EmpruntService empruntService = new EmpruntService();
    private ReservationService reservationService = new ReservationService();

    public TheseUniversitaire( String titre, String auteur, LocalDate datePublication, int nombreDePages, String university) {
        super( titre, auteur, datePublication, nombreDePages);
        this.university = university;
    }
    public TheseUniversitaire( int id,String titre, String auteur, LocalDate datePublication, int nombreDePages, String university) {
        super(id, titre, auteur, datePublication, nombreDePages);
        this.university = university;
    }

    public TheseUniversitaire(){};

    public String getUniversity(){
        return university;
    }
    public void setUniversity(String university) {
        this.university = university;
    }


    @Override
    public String toString() {
        return "These Universitaire{" +
                super.toString() +
                "university='" + university + '\'' +
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
