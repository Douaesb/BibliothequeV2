package entities.documents;

import java.time.LocalDate;

public class Magazine extends Document {

    private int numero;

    // Constructeur pour initialiser les attributs de Magazine
    public Magazine(int id, String titre, String auteur, LocalDate datePublication, int nombreDePages, int numero) {
        super(id, titre, auteur, datePublication, nombreDePages);
        this.numero = numero;
    }

    // Getter pour l'attribut numéro
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
