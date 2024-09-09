package Entities.users;

public class Etudiant extends Utilisateur {
    private String CNE;

    public Etudiant(int id, String name, int age, String email, String CNE){
        super(id,name,age, email);
        this.CNE = CNE;
    }

    public String getCNE(){
        return CNE;
    }

    public void setCNE(String CNE) {
        this.CNE = CNE;
    }
}
