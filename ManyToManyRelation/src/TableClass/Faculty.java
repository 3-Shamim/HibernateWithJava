package TableClass;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "Faculty")
public class Faculty implements Serializable{
    @Id 
    @GeneratedValue
    private int id;
    private String facultyName;
    private String facultyEmail;
    private String facultNumber;

    public Faculty() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getFacultyEmail() {
        return facultyEmail;
    }

    public void setFacultyEmail(String facultyEmail) {
        this.facultyEmail = facultyEmail;
    }

    public String getFacultNumber() {
        return facultNumber;
    }

    public void setFacultNumber(String facultNumber) {
        this.facultNumber = facultNumber;
    }
    
}
