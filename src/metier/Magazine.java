package metier;


import java.time.LocalDate;

public final class Magazine extends Document {
    private final int numero;

    // Constructeur pour initialiser les attributs de Magazine
    public Magazine(int id, String titre, String auteur, LocalDate datePublication, int nombreDePages, int numero) {
        super(id, titre, auteur, datePublication, nombreDePages);
        this.numero = numero;
    }

    // Méthode pour emprunter un magazine
    @Override
    public boolean emprunter() {
        if (!estEmprunte) {
            estEmprunte = true;
            System.out.println("Le magazine '" + getTitre() + "' (Numero: " + numero + ") a ete emprunte.");
            return true;
        } else {
            System.out.println("Le magazine '" + getTitre() + "' (Numero: " + numero + ") est deja emprunte.");
            return false;
        }
    }

    // Méthode pour retourner un magazine
    @Override
    public boolean retourner() {
        if (estEmprunte) {
            estEmprunte = false;
            System.out.println("Le magazine '" + getTitre() + "' (Numero: " + numero + ") a ete retourne.");
            return true;
        } else {
            System.out.println("Le magazine '" + getTitre() + "' (Numero: " + numero + ") n'est pas emprunte.");
            return false;
        }
    }

    // Méthode pour afficher les détails du magazine
    @Override
    public void afficherDetails() {
        System.out.println("Magazine:");
        System.out.println("ID: " + getId());
        System.out.println("Titre: " + getTitre());
        System.out.println("Auteur: " + getAuteur());
        System.out.println("Date de Publication: " + getFormattedDatePublication());
        System.out.println("Nombre de Pages: " + getNombreDePages());
        System.out.println("Numero: " + getNumero());
        System.out.println("Statut: " + (estEmprunte ? "Emprunte" : "Disponible"));
    }

    // Getter pour l'attribut numéro
    public int getNumero() {
        return numero;
    }
}
