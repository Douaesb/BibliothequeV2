package service;
import Dao.DocumentDao;
import DaoImpl.documents.JournalScientifiqueDaoImpl;
import DaoImpl.documents.LivreDaoImpl;
import DaoImpl.documents.MagazineDaoImpl;
import DaoImpl.documents.TheseUniversitaireDaoImpl;
import entities.documents.*;
import utilitaire.InputValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DocumentService {
    private DocumentDao livreDao = new LivreDaoImpl();
    private DocumentDao magazineDao = new MagazineDaoImpl();
    private DocumentDao theseUniversitaireDao = new TheseUniversitaireDaoImpl();
    private DocumentDao journalScientifiqueDao = new JournalScientifiqueDaoImpl();



    private boolean validateDocument(Document document) {
        if (document == null) return false;
        boolean isValidTitre = InputValidator.isValidTitre(document.getTitre());
        boolean isValidAuteur = InputValidator.isValidAuteur(document.getAuteur());
        boolean isValidDatePublication = document.getDatePublication() != null;
        boolean isValidNombreDePages = document.getNombreDePages() > 0;

        return isValidTitre && isValidAuteur && isValidDatePublication && isValidNombreDePages;
    }


    public void addDocument(Document document) {
        if (validateDocument(document)) {
            if (document instanceof Livre) {
                livreDao.addDocument(document);
            } else if (document instanceof Magazine) {
                magazineDao.addDocument(document);
            } else if (document instanceof TheseUniversitaire) {
                theseUniversitaireDao.addDocument(document);
            } else if (document instanceof JournalScientifique) {
                journalScientifiqueDao.addDocument(document);
            } else {
                System.out.println("Type de document non supporté.");
            }
        } else {
            System.out.println("Les données du document ne sont pas valides.");
        }
    }

    public void editDocument(Document document) {
        if (validateDocument(document)) {
            if (document instanceof Livre) {
                livreDao.editDocument(document);
            } else if (document instanceof Magazine) {
                magazineDao.editDocument(document);
            } else if (document instanceof TheseUniversitaire) {
                theseUniversitaireDao.editDocument(document);
            } else if (document instanceof JournalScientifique) {
                journalScientifiqueDao.editDocument(document);
            } else {
                System.out.println("Type de document non supporté.");
            }
        } else {
            System.out.println("Les données du document ne sont pas valides.");
        }
    }

    private List<DocumentDao> getAllDaos() {
        List<DocumentDao> daos = new ArrayList<>();
        daos.add(livreDao);
        daos.add(magazineDao);
        daos.add(theseUniversitaireDao);
        daos.add(journalScientifiqueDao);
        //System.out.println("here daos"+daos);
        return daos;
    }


   // public void displayDocument(int documentId) {
//        for (DocumentDao dao : getAllDaos()) {
//            dao.displayDocument(documentId);
//        }
//    }


    public List<Document> displayAllDocuments() {
        return getAllDaos().stream()
                .flatMap(dao -> dao.displayAllDocuments().stream())
                .collect(Collectors.toList());
    }


    public Optional<List<Document>> searchDocument(String titre) {
        List<Document> result = getAllDaos().stream()
                .map(dao -> dao.searchDocument(titre))
                .flatMap(List::stream)
                .collect(Collectors.toList());
        return result.isEmpty() ? Optional.empty() : Optional.of(result);
    }


    public void deleteDocument(int documentId) {
        getAllDaos().forEach(dao -> dao.deleteDocument(documentId));
    }


    public Optional<Document> getDocumentById(int documentId) {
        for (DocumentDao dao : getAllDaos()) {
            Optional<Document> document = Optional.ofNullable(dao.getDocumentById(documentId));
            if (document.isPresent()) {
                return document;
            }
        }
        return Optional.empty();
    }




}
