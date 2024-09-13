package entities.documents;

import service.EmpruntService;
import service.interfaces.Empruntable;

import java.time.LocalDate;

public class JournalScientifique extends Document implements Empruntable {

    private String domaineRecherche;
    private EmpruntService empruntService;

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
    public boolean emprunter(int userId) {
        return empruntService.borrowDocument(this.getId(), userId);
    }

    @Override
    public boolean retourner(int userId) {
        return empruntService.returnDocument(this.getId(), userId);
    }
}
