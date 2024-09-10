package DaoImpl.documents;

import DB.DatabaseConnection;
import Dao.DocumentDao;
import entities.documents.Document;
import entities.documents.JournalScientifique;
import entities.documents.Livre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JournalScientifiqueDaoImpl implements DocumentDao {

    private Connection conn;

    public JournalScientifiqueDaoImpl() {
        try {
            this.conn = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database.", e);
        }
    }

    @Override
    public void addDocument(Document document) {
        JournalScientifique journal = (JournalScientifique) document;
        String sql = "INSERT INTO journal_scientifique (titre, auteur, datePublication, nombreDePages, domaineRecherche) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, journal.getTitre());
            statement.setString(2, journal.getAuteur());
            statement.setDate(3, Date.valueOf(journal.getDatePublication()));
            statement.setInt(4, journal.getNombreDePages());
            statement.setString(5, journal.getDomaineRecherche());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void editDocument(Document document) {
        JournalScientifique journal = (JournalScientifique) document;
        String sql = "UPDATE journal_scientifique SET titre = ?, auteur = ?, datePublication = ?, nombreDePages = ?, domaineRecherche = ? WHERE id = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, journal.getTitre());
            statement.setString(2, journal.getAuteur());
            statement.setDate(3, Date.valueOf(journal.getDatePublication()));
            statement.setInt(4, journal.getNombreDePages());
            statement.setString(5, journal.getDomaineRecherche());
            statement.setInt(6, journal.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void displayDocument(int documentId) {
        String sql = "SELECT * FROM journal_scientifique WHERE id = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, documentId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Titre: " + resultSet.getString("titre"));
                System.out.println("Auteur: " + resultSet.getString("auteur"));
                System.out.println("Date de publication: " + resultSet.getDate("datePublication").toLocalDate());
                System.out.println("Nombre de pages: " + resultSet.getInt("nombreDePages"));
                System.out.println("Domaine de recherche: " + resultSet.getString("domaineRecherche"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Document> displayAllDocuments() {
        List<Document> journaux = new ArrayList<>();
        String sql = "SELECT * FROM journal_scientifique";

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                JournalScientifique journal = new JournalScientifique(
                       // resultSet.getInt("id"),
                        resultSet.getString("titre"),
                        resultSet.getString("auteur"),
                        resultSet.getDate("datePublication").toLocalDate(),
                        resultSet.getInt("nombreDePages"),
                        resultSet.getString("domaineRecherche")
                );
                journaux.add(journal);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return journaux;
    }

    @Override
    public void deleteDocument(int documentId) {
        String sql = "DELETE FROM journal_scientifique WHERE id = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, documentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Document> searchDocument(String titre) {
        List<Document> journaux = new ArrayList<>();
        String sql = "SELECT * FROM journal_scientifique WHERE titre LIKE ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            String searchPattern = "%" + titre + "%";
            statement.setString(1, searchPattern);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                JournalScientifique journal = new JournalScientifique(
                       // resultSet.getInt("id"),
                        resultSet.getString("titre"),
                        resultSet.getString("auteur"),
                        resultSet.getDate("datePublication").toLocalDate(),
                        resultSet.getInt("nombreDePages"),
                        resultSet.getString("domaineRecherche")
                );
                journaux.add(journal);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return journaux;
    }

    @Override
    public JournalScientifique getDocumentById(int id) {
        JournalScientifique journal = null;
        String query = "SELECT * FROM journal_scientifique WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                journal = new JournalScientifique(
                        //rs.getInt("id"),
                        rs.getString("titre"),
                        rs.getString("auteur"),
                        rs.getDate("datePublication").toLocalDate(),
                        rs.getInt("nombreDePages"),
                        rs.getString("domaineRecherche")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return journal;
    }
}
