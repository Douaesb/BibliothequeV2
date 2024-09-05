package metier;


import java.time.LocalDate;

public final class Livre extends Document {
    private final String isbn;

    // Constructeur pour initialiser les attributs de Livre
    public Livre(int id, String titre, String auteur, LocalDate datePublication, int nombreDePages, String isbn) {
        super(id, titre, auteur, datePublication, nombreDePages);
        this.isbn = isbn;
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

    // Méthode pour afficher les détails du livre
    @Override
    public void afficherDetails() {
        System.out.println("Livre:");
        System.out.println("ID: " + getId());
        System.out.println("Titre: " + getTitre());
        System.out.println("Auteur: " + getAuteur());
        System.out.println("Date de Publication: " + getFormattedDatePublication());
        System.out.println("Nombre de Pages: " + getNombreDePages());
        System.out.println("ISBN: " + getIsbn());
        System.out.println("Statut: " + (estEmprunte ? "Emprunte" : "Disponible"));
    }

    // Getter pour l'attribut ISBN
    public String getIsbn() {
        return isbn;
    }
}
