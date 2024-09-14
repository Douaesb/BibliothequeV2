package entities.documents;

import service.interfaces.Empruntable;
import service.interfaces.Reservable;
import utilitaire.DateUtils;

import java.time.LocalDate;


public abstract class Document implements Empruntable, Reservable {


    protected int id;
    protected String titre;
    protected String auteur;
    protected LocalDate datePublication;
    protected int nombreDePages;


    // Constructeur de la classe Document

    public Document( int id, String titre, String auteur, LocalDate datePublication, int nombreDePages) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.datePublication = datePublication;
        this.nombreDePages = nombreDePages;
    }

    public Document( String titre, String auteur, LocalDate datePublication, int nombreDePages) {
        this.titre = titre;
        this.auteur = auteur;
        this.datePublication = datePublication;
        this.nombreDePages = nombreDePages;
    }

    public Document() {};


    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public LocalDate getDatePublication() {
        return datePublication;
    }

    public String getFormattedDatePublication() {
        return DateUtils.formatDate(datePublication);
    }

    public int getNombreDePages() {
        return nombreDePages;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }


    public void setNombreDePages(int nombreDePages) {
        this.nombreDePages = nombreDePages;
    }

    public void setDatePublication(LocalDate datePublication) {
        this.datePublication = datePublication;
    }

    //public abstract void afficherDetails();

    @Override
    public String toString() {
        return
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", auteur='" + auteur + '\'' +
                ", datePublication=" + datePublication +
                ", nombreDePages=" + nombreDePages + ", " ;

    }

}
