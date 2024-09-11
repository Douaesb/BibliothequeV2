package entities.documents;

import java.time.LocalDate;

public class TheseUniversitaire extends Document {

    private String university;

    public TheseUniversitaire( String titre, String auteur, LocalDate datePublication, int nombreDePages, String university) {
        super( titre, auteur, datePublication, nombreDePages);
        this.university = university;
    }
    public TheseUniversitaire( int id,String titre, String auteur, LocalDate datePublication, int nombreDePages, String university) {
        super(id, titre, auteur, datePublication, nombreDePages);
        this.university = university;
    }

    public String getUniversity(){
        return university;
    }
    public void setUniversity(String university) {
        this.university = university;
    }


    @Override
    public String toString() {
        return " These Universitaire{" +
                super.toString() +
                "university='" + university + '\'' +
                "} " ;
    }


}
