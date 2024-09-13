package DaoImpl;

import DB.DatabaseConnection;
import Dao.ReservationDao;
import entities.Reservation;
import java.sql.*;

public class ReservationDaoImpl implements ReservationDao {
    private Connection conn;

    public ReservationDaoImpl() {
        try {
            this.conn = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database.", e);
        }
    }

    @Override
    public void addReservation(Reservation reservation) {
        String sql = "INSERT INTO reservation (document_id, utilisateur_id, date_reservation, statut) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reservation.getDocumentId());
            stmt.setInt(2, reservation.getUserId());
            stmt.setDate(3, Date.valueOf(reservation.getDateReservation()));
            stmt.setString(4, reservation.getStatut());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Reservation getActiveReservationByDocumentIdAndUserId(int documentId, int userId) {
        String sql = "SELECT * FROM reservation WHERE document_id = ? AND utilisateur_id = ? AND statut = 'reserve'";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, documentId);
            stmt.setInt(2, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Reservation(rs.getInt("id"), rs.getInt("document_id"), rs.getInt("utilisateur_id"),
                        rs.getDate("date_reservation").toLocalDate(), rs.getString("statut"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteReservation(int reservationId) {
        String sql = "DELETE FROM reservation WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reservationId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
