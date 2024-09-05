package metier;

import utilitaire.DateUtils;

import java.time.LocalDate;


public abstract class Document {
    protected int id;
    protected String titre;
    protected String auteur;
    protected LocalDate datePublication;
    protected int nombreDePages;
    protected boolean estEmprunte;
    // Constructeur de la classe Document
    public Document(int id, String titre, String auteur, LocalDate datePublication, int nombreDePages) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.datePublication = datePublication;
        this.nombreDePages = nombreDePages;
        this.estEmprunte = false;
    }

    // Méthode abstraite pour emprunter un document
    public abstract boolean emprunter();

    // Méthode abstraite pour retourner un document
    public abstract boolean retourner();

    // Méthode abstraite pour afficher les détails du document
    public abstract void afficherDetails();

    // Getters pour les attributs de base
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
}
