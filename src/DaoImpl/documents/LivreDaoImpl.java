package DaoImpl.documents;

import DB.DatabaseConnection;
import Dao.DocumentDao;
import entities.documents.Document;
import entities.documents.Livre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivreDaoImpl implements DocumentDao {

    private Connection conn;

    public LivreDaoImpl() {
        try {
            this.conn = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database.", e);
        }
    }

    @Override
    public void addDocument(Document document) {
        Livre livre = (Livre) document;
        String sql = "INSERT INTO livre (titre, auteur, datePublication, nombreDePages, isbn) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, livre.getTitre());
            statement.setString(2, livre.getAuteur());
            statement.setDate(3, Date.valueOf(livre.getDatePublication()));
            statement.setInt(4, livre.getNombreDePages());
            statement.setString(5, livre.getIsbn());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    @Override
    public void editDocument(Document document) {
        System.out.println("enter here");

        Livre livre = (Livre) document;
        String sql = "UPDATE livre SET titre = ?, auteur = ?, datePublication = ?, nombreDePages = ?, isbn = ? WHERE id = ?";
        System.out.println("query here");

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, livre.getTitre());
            statement.setString(2, livre.getAuteur());
            statement.setDate(3, Date.valueOf(livre.getDatePublication()));
            statement.setInt(4, livre.getNombreDePages());
            statement.setString(5, livre.getIsbn());
            statement.setInt(6, livre.getId());
            System.out.println("ID du document à modifier: " + livre.getId());

            int rowsAffected = statement.executeUpdate();
            System.out.println("Nombre de lignes affectées: " + rowsAffected);

            System.out.println("execcccuuutteeeddddd"+(statement.executeUpdate()));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void displayDocument(int documentId) {
        String sql = "SELECT * FROM livre WHERE id = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, documentId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Titre: " + resultSet.getString("titre"));
                System.out.println("Auteur: " + resultSet.getString("auteur"));
                System.out.println("Date de publication: " + resultSet.getDate("datePublication").toLocalDate());
                System.out.println("Nombre de pages: " + resultSet.getInt("nombreDePages"));
                System.out.println("ISBN: " + resultSet.getString("isbn"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Document> displayAllDocuments() {
        List<Document> livres = new ArrayList<>();
        String sql = "SELECT * FROM livre";

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Livre livre = new Livre(
                        resultSet.getInt("id"),
                        resultSet.getString("titre"),
                        resultSet.getString("auteur"),
                        resultSet.getDate("datePublication").toLocalDate(),
                        resultSet.getInt("nombreDePages"),
                        resultSet.getString("isbn")
                );
                livres.add(livre);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return livres;
    }

    @Override
    public void deleteDocument(int documentId) {
        String sql = "DELETE FROM livre WHERE id = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, documentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    @Override
    public List<Document> searchDocument(String titre) {
        List<Document> livres = new ArrayList<>();
        String sql = "SELECT * FROM livre WHERE titre LIKE ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            String searchPattern = "%" + titre + "%";
            statement.setString(1, searchPattern);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Livre livre = new Livre(
                       resultSet.getInt("id"),
                        resultSet.getString("titre"),
                        resultSet.getString("auteur"),
                        resultSet.getDate("datePublication").toLocalDate(),
                        resultSet.getInt("nombreDePages"),
                        resultSet.getString("isbn")
                );
                livres.add(livre);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return livres;
    }

    @Override
    public Livre getDocumentById(int id) {
        Livre livre = null;
        String query = "SELECT * FROM livre WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                livre = new Livre(
                        rs.getInt("id"),
                        rs.getString("titre"),
                        rs.getString("auteur"),
                        rs.getDate("datePublication").toLocalDate(),
                        rs.getInt("nombreDePages"),
                        rs.getString("isbn")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return livre;
    }

}
