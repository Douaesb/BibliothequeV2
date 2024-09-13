package entities.documents;

import service.EmpruntService;
import service.interfaces.Empruntable;

import java.time.LocalDate;

public class TheseUniversitaire extends Document implements Empruntable {

    private String university;
    private EmpruntService empruntService;

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
    public boolean emprunter(int userId) {
        return empruntService.borrowDocument(this.getId(), userId);
    }

    @Override
    public boolean retourner(int userId) {
        return empruntService.returnDocument(this.getId(), userId);
    }


}
