package DaoImpl.users;

import DB.DatabaseConnection;
import Dao.UtilisateurDao;
import entities.users.Etudiant;
import entities.users.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    @Override
    public void editUser(Utilisateur utilisateur){
        if(!(utilisateur instanceof Etudiant)){
            throw new IllegalArgumentException("User must be of type Etudiant");
        }

        Etudiant etudiant = (Etudiant) utilisateur;
        String sql = "UPDATE etudiant SET name = ?, email = ?, age = ?, CNE = ? WHERE id = ?";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,etudiant.getName());
            pstmt.setString(2,etudiant.getEmail());
            pstmt.setInt(3,etudiant.getAge());
            pstmt.setString(4,etudiant.getCNE());
            pstmt.setInt(5,etudiant.getId());
            pstmt.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
        public Utilisateur displayUser(int id){
            Etudiant etudiant = null;
            String sql = "SELECT * FROM etudiant WHERE id = ?";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,id);
            ResultSet res = pstmt.executeQuery();
            if(res.next()){
                etudiant = new Etudiant(
                        res.getInt("id"),
                        res.getString("name"),
                        res.getInt("age"),
                        res.getString("email"),
                        res.getString("CNE")
                );
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }



}
