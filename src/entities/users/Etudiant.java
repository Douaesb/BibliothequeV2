package entities.users;

public class Etudiant extends Utilisateur {
    private String CNE;

    public Etudiant(int id, String name, int age, String email, String CNE){
        super(id,name,age, email);
        this.CNE = CNE;
    }

    public Etudiant( String name, int age, String email, String CNE){
        super(name,age, email);
        this.CNE = CNE;
    }

    public Etudiant(){
    }

    public String getCNE(){
        return CNE;
    }

    public void setCNE(String CNE) {
        this.CNE = CNE;
    }

    @Override
    public String toString() {
        return "Etudiant {" +
                super.toString() +
                "CNE='" + CNE + '\'' +
                "} " ;
    }
}
