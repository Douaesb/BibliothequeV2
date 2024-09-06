package metier;

import java.time.LocalDate;

public class TheseUniversitaire extends Document{
    public TheseUniversitaire(int id, String titre, String auteur, LocalDate datePublication, int nombreDePages) {
        super(id, titre, auteur, datePublication, nombreDePages);
    }

    // Méthode pour emprunter un livre
    @Override
    public boolean emprunter() {
        if (!estEmprunte) {
            estEmprunte = true;
            System.out.println("Le livre '" + getTitre() + "' a ete emprunte.");
            return true;
        } else {
            System.out.println("Le livre '" + getTitre() + "' est deja emprunte.");
            return false;
        }
    }

    // Méthode pour retourner un livre
    @Override
    public boolean retourner() {
        if (estEmprunte) {
            estEmprunte = false;
            System.out.println("Le livre '" + getTitre() + "' a ete retourne.");
            return true;
        } else {
            System.out.println("Le livre '" + getTitre() + "' n'est pas emprunte.");
            return false;
        }
    }

    @Override
    public void afficherDetails() {
        System.out.println("Livre:");

    }

}

