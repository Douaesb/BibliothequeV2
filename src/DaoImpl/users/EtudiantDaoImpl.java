package DaoImpl.users;

import DB.DatabaseConnection;
import Dao.UtilisateurDao;
import entities.users.Etudiant;
import entities.users.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EtudiantDaoImpl implements UtilisateurDao {

    private Connection conn;

    public EtudiantDaoImpl() {
        try {
            this.conn = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {

            throw new RuntimeException("Failed to connect to the database.", e);
        }
    }

    @Override
    public void addUser(Utilisateur utilisateur){
        if(!(utilisateur instanceof Etudiant)){
            throw new IllegalArgumentException("User must be of type Etudiant");
        }

        Etudiant etudiant = (Etudiant) utilisateur;
        String sql = "INSERT INTO etudiant (name, email, age, CNE) VALUES (?,?,?,?)";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,etudiant.getName());
            pstmt.setString(2, etudiant.getEmail());
            pstmt.setInt(3, etudiant.getAge());
            pstmt.setString(4, etudiant.getCNE());
            pstmt.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }



}
