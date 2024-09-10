package DaoImpl.documents;

import DB.DatabaseConnection;
import Dao.DocumentDao;
import entities.documents.Document;
import entities.documents.TheseUniversitaire;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TheseUniversitaireDaoImpl implements DocumentDao {

    private Connection conn;

    public TheseUniversitaireDaoImpl() {
        try {
            this.conn = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database.", e);
        }
    }

    @Override
    public void addDocument(Document document) {
        TheseUniversitaire these = (TheseUniversitaire) document;
        String sql = "INSERT INTO these_universitaire (titre, auteur, datePublication, nombreDePages, university) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, these.getTitre());
            statement.setString(2, these.getAuteur());
            statement.setDate(3, Date.valueOf(these.getDatePublication()));
            statement.setInt(4, these.getNombreDePages());
            statement.setString(5, these.getUniversity());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void editDocument(Document document) {
        TheseUniversitaire these = (TheseUniversitaire) document;
        String sql = "UPDATE these_universitaire SET titre = ?, auteur = ?, datePublication = ?, nombreDePages = ?, university = ? WHERE id = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, these.getTitre());
            statement.setString(2, these.getAuteur());
            statement.setDate(3, Date.valueOf(these.getDatePublication()));
            statement.setInt(4, these.getNombreDePages());
            statement.setString(5, these.getUniversity());
            statement.setInt(6, these.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void displayDocument(int documentId) {
        String sql = "SELECT * FROM these_universitaire WHERE id = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, documentId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Titre: " + resultSet.getString("titre"));
                System.out.println("Auteur: " + resultSet.getString("auteur"));
                System.out.println("Date de publication: " + resultSet.getDate("datePublication").toLocalDate());
                System.out.println("Nombre de pages: " + resultSet.getInt("nombreDePages"));
                System.out.println("Université: " + resultSet.getString("university"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Document> displayAllDocuments() {
        List<Document> theses = new ArrayList<>();
        String sql = "SELECT * FROM these_universitaire";

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                TheseUniversitaire these = new TheseUniversitaire(
                        resultSet.getInt("id"),
                        resultSet.getString("titre"),
                        resultSet.getString("auteur"),
                        resultSet.getDate("datePublication").toLocalDate(),
                        resultSet.getInt("nombreDePages"),
                        resultSet.getString("university")
                );
                theses.add(these);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return theses;
    }

    @Override
    public void deleteDocument(int documentId) {
        String sql = "DELETE FROM these_universitaire WHERE id = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, documentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Document> searchDocument(String titre) {
        List<Document> theses = new ArrayList<>();
        String sql = "SELECT * FROM these_universitaire WHERE titre LIKE ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            String searchPattern = "%" + titre + "%";
            statement.setString(1, searchPattern);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                TheseUniversitaire these = new TheseUniversitaire(
                        resultSet.getInt("id"),
                        resultSet.getString("titre"),
                        resultSet.getString("auteur"),
                        resultSet.getDate("datePublication").toLocalDate(),
                        resultSet.getInt("nombreDePages"),
                        resultSet.getString("university")
                );
                theses.add(these);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return theses;
    }
}
