package DaoImpl;

import DB.DatabaseConnection;
import Dao.EmpruntDao;
import entities.Emprunt;
import java.sql.*;

public class EmpruntDaoImpl implements EmpruntDao {
    private Connection conn;


    public EmpruntDaoImpl() {
        try {
            this.conn = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {

            throw new RuntimeException("Failed to connect to the database.", e);
        }
    }

    @Override
    public void addEmprunt(Emprunt emprunt) {
        String sql = "INSERT INTO emprunt (document_id, utilisateur_id, date_emprunt, statut) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, emprunt.getDocumentId());
            stmt.setInt(2, emprunt.getUserId());
            stmt.setDate(3, Date.valueOf(emprunt.getDateEmprunt()));
            stmt.setString(4, emprunt.getStatut());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Emprunt getActiveEmpruntByDocumentIdAndUserId(int documentId, int userId) {
        String sql = "SELECT * FROM emprunt WHERE document_id = ? AND utilisateur_id = ? AND statut = 'emprunte'";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, documentId);
            stmt.setInt(2, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Emprunt(rs.getInt("id"), rs.getInt("document_id"), rs.getInt("utilisateur_id"),
                        rs.getDate("date_emprunt").toLocalDate(), rs.getString("statut"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteEmprunt(int empruntId) {
        String sql = "DELETE FROM emprunt WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, empruntId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isDocumentBorrowed(int documentId) {
        String sql = "SELECT * FROM emprunt WHERE document_id = ? AND statut = 'emprunte'";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, documentId);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // If a record is found, the document is borrowed
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
