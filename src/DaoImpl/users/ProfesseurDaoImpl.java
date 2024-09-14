package DaoImpl.users;

import DB.DatabaseConnection;
import Dao.UtilisateurDao;
import entities.users.Professeur;
import entities.users.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfesseurDaoImpl implements UtilisateurDao {

    private Connection conn;

    public ProfesseurDaoImpl() {
        try {
            this.conn = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database.", e);
        }
    }

    @Override
    public void addUser(Utilisateur utilisateur) {
        if (!(utilisateur instanceof Professeur)) {
            throw new IllegalArgumentException("User must be of type Professeur");
        }

        Professeur professeur = (Professeur) utilisateur;
        String sql = "INSERT INTO professeur (name, email, age, CIN) VALUES (?,?,?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, professeur.getName());
            pstmt.setString(2, professeur.getEmail());
            pstmt.setInt(3, professeur.getAge());
            pstmt.setString(4, professeur.getCIN());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void editUser(Utilisateur utilisateur) {
        if (!(utilisateur instanceof Professeur)) {
            throw new IllegalArgumentException("User must be of type Professeur");
        }

        Professeur professeur = (Professeur) utilisateur;
        String sql = "UPDATE professeur SET name = ?, email = ?, age = ?, CIN = ? WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, professeur.getName());
            pstmt.setString(2, professeur.getEmail());
            pstmt.setInt(3, professeur.getAge());
            pstmt.setString(4, professeur.getCIN());
            pstmt.setInt(5, professeur.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void displayUser(int id) {
        String sql = "SELECT * FROM professeur WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Nom: " + resultSet.getString("name"));
                System.out.println("Email: " + resultSet.getString("email"));
                System.out.println("Age: " + resultSet.getInt("age"));
                System.out.println("CIN: " + resultSet.getString("CIN")); // Fixed column name
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Utilisateur> displayAllUsers() {
        List<Utilisateur> professeurs = new ArrayList<>();
        String sql = "SELECT * FROM professeur";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet res = pstmt.executeQuery();
            while (res.next()) { // Use while to iterate over all results
                Professeur professeur = new Professeur(
                        res.getInt("id"),
                        res.getString("name"),
                        res.getInt("age"),
                        res.getString("email"),
                        res.getString("CIN")
                );
                professeurs.add(professeur);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return professeurs;
    }

    @Override
    public void deleteUser(int id) {
        String sql = "DELETE FROM professeur WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean emailExists(String email) {
        String sql = "SELECT 1 FROM professeur WHERE email = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Returns true if the email exists
        } catch (SQLException e) {
            System.out.println("Erreur lors de la vérification de l'email : " + e.getMessage());
            return false;
        }
    }

    @Override
    public Utilisateur getUserById(int id) {
        Utilisateur professeur = null;
        String sql = "SELECT * FROM professeur WHERE id = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                professeur = new Professeur();
                professeur.setId(resultSet.getInt("id"));
                professeur.setName(resultSet.getString("name"));
                professeur.setEmail(resultSet.getString("email"));
                professeur.setAge(resultSet.getInt("age"));
                ((Professeur) professeur).setCIN(resultSet.getString("CIN")); // Fixed column name
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du professeur : " + e.getMessage());
        }

        return professeur;
    }

    // New method to get user by email
    @Override
    public Utilisateur getUserByEmail(String email) {
        Utilisateur professeur = null;
        String sql = "SELECT * FROM professeur WHERE email = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                professeur = new Professeur();
                professeur.setId(resultSet.getInt("id"));
                professeur.setName(resultSet.getString("name"));
                professeur.setEmail(resultSet.getString("email"));
                professeur.setAge(resultSet.getInt("age"));
                ((Professeur) professeur).setCIN(resultSet.getString("CIN")); // Fixed column name
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du professeur : " + e.getMessage());
        }

        return professeur;
    }
}
