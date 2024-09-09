package Entities.documents;

import java.time.LocalDate;

public class JournalScientifique extends Document {

    private String domaineRecherche;

    public JournalScientifique(int id, String titre, String auteur, LocalDate datePublication, int nombreDePages, String domaineRecherche) {
        super(id, titre, auteur, datePublication, nombreDePages);
        this.domaineRecherche = domaineRecherche;
    }

    public String getDomaineRecherche(){
        return domaineRecherche;
    }

    public void setDomaineRecherche(String domaineRecherche) {
        this.domaineRecherche = domaineRecherche;
    }
}
