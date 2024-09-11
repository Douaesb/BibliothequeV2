package entities.documents;

import java.time.LocalDate;

public class Livre extends Document{

    private String isbn;

    // Constructeur pour initialiser les attributs de Livre
    public Livre( String titre, String auteur, LocalDate datePublication, int nombreDePages, String isbn) {
        super( titre, auteur, datePublication, nombreDePages);
        this.isbn = isbn;
    }

    public Livre(int id, String titre, String auteur, LocalDate datePublication, int nombreDePages, String isbn) {
        super( id,titre, auteur, datePublication, nombreDePages);
        this.isbn = isbn;
    }


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


}
