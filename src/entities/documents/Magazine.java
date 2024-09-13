package entities.documents;

import service.EmpruntService;
import service.interfaces.Empruntable;

import java.time.LocalDate;

public class Magazine extends Document implements Empruntable {

    private int numero;
    private EmpruntService empruntService;

    // Constructeur pour initialiser les attributs de Magazine
    public Magazine( String titre, String auteur, LocalDate datePublication, int nombreDePages, int numero) {
        super(titre, auteur, datePublication, nombreDePages);
        this.numero = numero;
    }

    public Magazine(int id, String titre, String auteur, LocalDate datePublication, int nombreDePages, int numero) {
        super(id,titre, auteur, datePublication, nombreDePages);
        this.numero = numero;
    }

    public Magazine(){};

    // Getter pour l'attribut numéro
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                super.toString() +
                "numero='" + numero + '\'' +
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
