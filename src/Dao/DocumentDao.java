package Dao;

import entities.documents.Document;

import java.util.List;

public interface DocumentDao {

    void addDocument(Document document);

    void editDocument(Document document);

    void displayDocument(int documentId);

    List<Document> displayAllDocuments();

    void deleteDocument(int documentId);

    List<Document> searchDocument(String titre);

      Document getDocumentById(int id);

}
