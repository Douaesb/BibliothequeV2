package metier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bibliotheque {
    private final List<Document> documents;
    private final Map<String, Document> documentMap;
    private static int currentId = 0;

    public static synchronized int generateId() {
        return ++currentId;
    }
    // Constructeur de la classe Bibliotheque
    public Bibliotheque() {
        this.documents = new ArrayList<>();
        this.documentMap = new HashMap<>();
    }

    // Méthode pour ajouter un document à la bibliothèque
    public void ajouter(Document document) {
        documents.add(document);
        documentMap.put(document.getTitre(), document);
    }

    // Méthode pour rechercher un document par son titre
    public Document rechercher(String titre) {
        return documentMap.get(titre);
    }

    // Méthode pour afficher tous les documents dans la bibliothèque
    public void afficherTousLesDocuments() {
        if (documents.isEmpty()) {
            System.out.println("La bibliotheque ne contient aucun document.");
        } else {
            /* for (Document doc : documents) {
                doc.afficherDetails();
            }*/
            documents.forEach(doc -> doc.afficherDetails());
        }
    }

    // Méthode pour emprunter un document
    public boolean emprunterDocument(String titre) {
        Document document = rechercher(titre);
        if (document != null) {
            return document.emprunter();
        }
        System.out.println("Document non trouve.");
        return false;

    }

    // Méthode pour retourner un document
    public boolean retournerDocument(String titre) {
        Document document = rechercher(titre);
        if (document != null) {
            return document.retourner();
        }
        System.out.println("Document non trouve.");
        return false;
    }
}
