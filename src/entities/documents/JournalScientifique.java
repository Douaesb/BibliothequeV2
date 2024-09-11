package entities.documents;

import java.time.LocalDate;

public class JournalScientifique extends Document {

    private String domaineRecherche;

    public JournalScientifique( String titre, String auteur, LocalDate datePublication, int nombreDePages, String domaineRecherche) {
        super( titre, auteur, datePublication, nombreDePages);
        this.domaineRecherche = domaineRecherche;
    }

    public JournalScientifique(int id, String titre, String auteur, LocalDate datePublication, int nombreDePages, String domaineRecherche) {
        super( id,titre, auteur, datePublication, nombreDePages);
        this.domaineRecherche = domaineRecherche;
    }

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

}
