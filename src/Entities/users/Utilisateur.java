package Entities.users;

public class Utilisateur {

    protected int id;
    protected String name;
    protected int age;
    protected String email;

    public Utilisateur(int id,String name, int age, String email){
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public int getId(){
        return id;
    }

    public String name(){
        return name;
    }

    public int age(){
        return age;
    }

    public String email(){
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
