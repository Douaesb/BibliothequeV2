package presentation;

import service.BibliothequeService;
import service.DocumentService;
import entities.documents.*;
import utilitaire.DateUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class DocumentUI {

    private DocumentService documentService;
    private BibliothequeService bibliothequeService = new BibliothequeService();

    private Scanner scanner;

    public DocumentUI(DocumentService documentService) {
        this.documentService = documentService;
        this.scanner = new Scanner(System.in);
    }

    public void addDocument() {
        System.out.println("=== Ajouter un Document ===");
        System.out.print("Type de document (entrer un nombre) (1:Livre, 2:Magazine, 3:ThèseUniversitaire, 4:JournalScientifique) : ");
        int type = scanner.nextInt();
        Document document = null;
        scanner.nextLine();
        // Saisie des informations communes
        System.out.print("Titre : ");
        String titre = scanner.nextLine();
        System.out.print("Auteur : ");
        String auteur = scanner.nextLine();
        System.out.print("Date de publication (dd-MM-yyyy) : ");
        LocalDate datePublication = DateUtils.parseDate(scanner.nextLine());
        System.out.print("Nombre de pages : ");
        int nombreDePages = scanner.nextInt();
        scanner.nextLine();  // Consomme la nouvelle ligne après l'entier

        switch (type) {
            case 1:
                System.out.print("ISBN : ");
                String isbn = scanner.nextLine();
                document = new Livre(titre, auteur, datePublication, nombreDePages, isbn);
                break;
            case 2:
                System.out.print("Numéro : ");
                int numero = scanner.nextInt();
                scanner.nextLine();  // Consomme la nouvelle ligne après l'entier
                document = new Magazine(titre, auteur, datePublication, nombreDePages, numero);
                break;
            case 3:
                System.out.print("Université : ");
                String university = scanner.nextLine();
                document = new TheseUniversitaire(titre, auteur, datePublication, nombreDePages, university);
                break;
            case 4:
                System.out.print("Domaine de recherche : ");
                String domaineRecherche = scanner.nextLine();
                document = new JournalScientifique(titre, auteur, datePublication, nombreDePages, domaineRecherche);
                break;
            default:
                System.out.println("Type de document non reconnu.");
                return;
        }

        documentService.addDocument(document);
        System.out.println("Document ajouté avec succès !");
    }

    public void editDocument() {
        System.out.println("=== Éditer un Document ===");
        System.out.print("ID du document à éditer : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        // Fetching the document wrapped in Optional
        Optional<Document> optionalDocument = documentService.getDocumentById(id);

        // Handle case where document is not found
        if (!optionalDocument.isPresent()) {
            System.out.println("Document non trouvé.");
            return;
        }

        Document document = optionalDocument.get();

        // Display old information
        System.out.println("Anciennes informations du document :");
        displayDocumentInfo(document);

        // Input new information
        System.out.println("Modifiez les informations du document (laisser vide pour conserver l'ancien) :");
        System.out.print("Titre (" + document.getTitre() + ") : ");
        String titre = scanner.nextLine();
        if (!titre.isEmpty()) document.setTitre(titre);

        System.out.print("Auteur (" + document.getAuteur() + ") : ");
        String auteur = scanner.nextLine();
        if (!auteur.isEmpty()) document.setAuteur(auteur);

        System.out.print("Date de publication (dd-MM-yyyy, actuelle : " + DateUtils.formatDate(document.getDatePublication()) + ") : ");
        String dateStr = scanner.nextLine();
        if (!dateStr.isEmpty()) document.setDatePublication(DateUtils.parseDate(dateStr));

        System.out.print("Nombre de pages (" + document.getNombreDePages() + ") : ");
        String pagesStr = scanner.nextLine();
        if (!pagesStr.isEmpty()) document.setNombreDePages(Integer.parseInt(pagesStr));

        // Modify additional fields based on document type
        if (document instanceof Livre) {
            Livre livre = (Livre) document;
            System.out.print("ISBN (" + livre.getIsbn() + ") : ");
            String isbn = scanner.nextLine();
            if (!isbn.isEmpty()) livre.setIsbn(isbn);
        } else if (document instanceof Magazine) {
            Magazine magazine = (Magazine) document;
            System.out.print("Numéro (" + magazine.getNumero() + ") : ");
            String numeroStr = scanner.nextLine();
            if (!numeroStr.isEmpty()) magazine.setNumero(Integer.parseInt(numeroStr));
        } else if (document instanceof TheseUniversitaire) {
            TheseUniversitaire these = (TheseUniversitaire) document;
            System.out.print("Université (" + these.getUniversity() + ") : ");
            String university = scanner.nextLine();
            if (!university.isEmpty()) these.setUniversity(university);
        } else if (document instanceof JournalScientifique) {
            JournalScientifique journal = (JournalScientifique) document;
            System.out.print("Domaine de recherche (" + journal.getDomaineRecherche() + ") : ");
            String domaine = scanner.nextLine();
            if (!domaine.isEmpty()) journal.setDomaineRecherche(domaine);
        }

        // Display new information
        System.out.println("Nouvelles informations du document :");
        displayDocumentInfo(document);

        // Update the document in the service
        documentService.editDocument(document);
    }


    private void displayDocumentInfo(Document document) {
        System.out.println("Titre: " + document.getTitre());
        System.out.println("Auteur: " + document.getAuteur());
        System.out.println("Date de publication: " + DateUtils.formatDate(document.getDatePublication()));
        System.out.println("Nombre de pages: " + document.getNombreDePages());

        if (document instanceof Livre) {
            Livre livre = (Livre) document;
            System.out.println("ISBN: " + livre.getIsbn());
        } else if (document instanceof Magazine) {
            Magazine magazine = (Magazine) document;
            System.out.println("Numéro: " + magazine.getNumero());
        } else if (document instanceof TheseUniversitaire) {
            TheseUniversitaire these = (TheseUniversitaire) document;
            System.out.println("Université: " + these.getUniversity());
        } else if (document instanceof JournalScientifique) {
            JournalScientifique journal = (JournalScientifique) document;
            System.out.println("Domaine de recherche: " + journal.getDomaineRecherche());
        }
    }


    public void deleteDocument() {
        System.out.println("=== Supprimer un Document ===");
        System.out.print("ID du document à supprimer : ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consomme la nouvelle ligne après l'entier

        documentService.deleteDocument(id);
        System.out.println("Document supprimé avec succès !");
    }

    public void displayDocument() {
        System.out.println("=== Afficher un Document ===");
        System.out.print("ID du document à afficher : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Optional<Document> optionalDocument = documentService.getDocumentById(id);

        if (optionalDocument.isPresent()) {
            Document document = optionalDocument.get();
            System.out.println(document);
        } else {
            System.out.println("Document non trouvé.");
        }
    }


    public void displayAllDocuments() {
        System.out.println("=== Afficher Tous les Documents ===");
        List<Document> documents = bibliothequeService.displayAllDocuments();
        for (Document doc : documents) {
            System.out.println(doc);
        }
    }

    public void searchDocument() {
        System.out.println("=== Rechercher un Document ===");
        System.out.print("Titre du document à rechercher : ");
        String titre = scanner.nextLine();

        // Fetching the search results wrapped in Optional
        Optional<List<Document>> optionalDocuments = documentService.searchDocument(titre);

        if (optionalDocuments.isPresent()) {
            List<Document> documents = optionalDocuments.get();
//            for (Document doc : documents) {
//                System.out.println(doc);
//            }
            documents.forEach(System.out::println);

        } else {
            System.out.println("Aucun document trouvé.");
        }
    }

}
