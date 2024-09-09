package Entities.users;

public class Professeur extends Utilisateur{

    private String CIN;

    public Professeur(int id, String name, int age, String email, String CIN){
        super(id,name,age, email);
        this.CIN = CIN;
    }

    public String getCIN(){
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }
}
