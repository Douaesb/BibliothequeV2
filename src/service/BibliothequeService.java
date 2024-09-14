package service;

import Dao.DocumentDao;
import DaoImpl.documents.JournalScientifiqueDaoImpl;
import DaoImpl.documents.LivreDaoImpl;
import DaoImpl.documents.MagazineDaoImpl;
import DaoImpl.documents.TheseUniversitaireDaoImpl;
import entities.documents.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BibliothequeService {

    private DocumentDao livreDao = new LivreDaoImpl();
    private DocumentDao magazineDao = new MagazineDaoImpl();
    private DocumentDao theseUniversitaireDao = new TheseUniversitaireDaoImpl();
    private DocumentDao journalScientifiqueDao = new JournalScientifiqueDaoImpl();

    private List<DocumentDao> getAllDaos() {
        List<DocumentDao> daos = new ArrayList<>();
        daos.add(livreDao);
        daos.add(magazineDao);
        daos.add(theseUniversitaireDao);
        daos.add(journalScientifiqueDao);
        //System.out.println("here daos"+daos);
        return daos;
    }

    public List<Document> displayAllDocuments() {
        return getAllDaos().stream()
                .flatMap(dao -> dao.displayAllDocuments().stream())
                .collect(Collectors.toList());
    }

}
