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
    public void addUser(Utilisateur utilisateur){
        if(!(utilisateur instanceof Professeur)){
            throw new IllegalArgumentException("User must be of type Professeur");
        }

        Professeur professeur = (Professeur) utilisateur;
        String sql = "INSERT INTO professeur (name, email, age, CIN) VALUES (?,?,?,?)";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,professeur.getName());
            pstmt.setString(2, professeur.getEmail());
            pstmt.setInt(3, professeur.getAge());
            pstmt.setString(4, professeur.getCIN());
            pstmt.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void editUser(Utilisateur utilisateur){
        if(!(utilisateur instanceof Professeur)){
            throw new IllegalArgumentException("User must be of type Professeur");
        }

        Professeur professeur = (Professeur) utilisateur;
        String sql = "UPDATE professeur SET name = ?, email = ?, age = ?, CIN = ? WHERE id = ?";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,professeur.getName());
            pstmt.setString(2,professeur.getEmail());
            pstmt.setInt(3,professeur.getAge());
            pstmt.setString(4,professeur.getCIN());
            pstmt.setInt(5,professeur.getId());
            pstmt.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Utilisateur displayUser(int id){
        Professeur professeur = null;
        String sql = "SELECT * FROM professeur WHERE id = ?";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,id);
            ResultSet res = pstmt.executeQuery();
            if(res.next()){
                professeur = new Professeur(
                        res.getInt("id"),
                        res.getString("name"),
                        res.getInt("age"),
                        res.getString("email"),
                        res.getString("CIN")
                );
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return professeur;
    }

    @Override
    public List<Utilisateur> displayAllUsers(){
        List<Utilisateur> professeurs = new ArrayList<>();
        String sql = "SELECT * FROM professeur";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            ResultSet res = pstmt.executeQuery();
            if(res.next()){
                Professeur professeur = new Professeur(res.getInt("id"),
                        res.getString("name"),
                        res.getInt("age"),
                        res.getString("email"),
                        res.getString("CIN")
                );
                professeurs.add(professeur);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return professeurs;
    }

    @Override
    public void deleteUser(int id){
        String sql = "DELETE * FROM professeur WHERE id = ?";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }





}
