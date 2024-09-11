package DaoImpl.documents;

import DB.DatabaseConnection;
import Dao.DocumentDao;
import entities.documents.Document;
import entities.documents.Magazine;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MagazineDaoImpl implements DocumentDao {

    private Connection conn;

    public MagazineDaoImpl() {
        try {
            this.conn = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database.", e);
        }
    }

    @Override
    public void addDocument(Document document) {
        Magazine magazine = (Magazine) document;
        String sql = "INSERT INTO magazine (titre, auteur, datePublication, nombreDePages, numero) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, magazine.getTitre());
            statement.setString(2, magazine.getAuteur());
            statement.setDate(3, Date.valueOf(magazine.getDatePublication()));
            statement.setInt(4, magazine.getNombreDePages());
            statement.setInt(5, magazine.getNumero());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void editDocument(Document document) {
        Magazine magazine = (Magazine) document;
        String sql = "UPDATE magazine SET titre = ?, auteur = ?, datePublication = ?, nombreDePages = ?, numero = ? WHERE id = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, magazine.getTitre());
            statement.setString(2, magazine.getAuteur());
            statement.setDate(3, Date.valueOf(magazine.getDatePublication()));
            statement.setInt(4, magazine.getNombreDePages());
            statement.setInt(5, magazine.getNumero());
            statement.setInt(6, magazine.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void displayDocument(int documentId) {
        String sql = "SELECT * FROM magazine WHERE id = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, documentId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Titre: " + resultSet.getString("titre"));
                System.out.println("Auteur: " + resultSet.getString("auteur"));
                System.out.println("Date de publication: " + resultSet.getDate("datePublication").toLocalDate());
                System.out.println("Nombre de pages: " + resultSet.getInt("nombreDePages"));
                System.out.println("Numéro: " + resultSet.getInt("numero"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Document> displayAllDocuments() {
        List<Document> magazines = new ArrayList<>();
        String sql = "SELECT * FROM magazine";

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Magazine magazine = new Magazine(
                        resultSet.getInt("id"),
                        resultSet.getString("titre"),
                        resultSet.getString("auteur"),
                        resultSet.getDate("datePublication").toLocalDate(),
                        resultSet.getInt("nombreDePages"),
                        resultSet.getInt("numero")
                );
                magazines.add(magazine);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return magazines;
    }

    @Override
    public void deleteDocument(int documentId) {
        String sql = "DELETE FROM magazine WHERE id = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, documentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Document> searchDocument(String titre) {
        List<Document> magazines = new ArrayList<>();
        String sql = "SELECT * FROM magazine WHERE titre LIKE ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            String searchPattern = "%" + titre + "%";
            statement.setString(1, searchPattern);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Magazine magazine = new Magazine(
                         resultSet.getInt("id"),
                        resultSet.getString("titre"),
                        resultSet.getString("auteur"),
                        resultSet.getDate("datePublication").toLocalDate(),
                        resultSet.getInt("nombreDePages"),
                        resultSet.getInt("numero")
                );
                magazines.add(magazine);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return magazines;
    }

    @Override
    public Magazine getDocumentById(int id) {
        Magazine magazine = null;
        String query = "SELECT * FROM magazine WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                magazine = new Magazine(
                        rs.getInt("id"),
                        rs.getString("titre"),
                        rs.getString("auteur"),
                        rs.getDate("datePublication").toLocalDate(),
                        rs.getInt("nombreDePages"),
                        rs.getInt("numero")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return magazine;
    }
}
