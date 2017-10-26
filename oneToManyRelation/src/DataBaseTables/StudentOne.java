package DataBaseTables;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "StudentOne")
public class StudentOne implements Serializable {
    @Id
    @GeneratedValue
    private int Id;
    @Column(name = "Name")
    private String Name;
    @Column(name = "Email")
    private String Email;
    @Column(name = "Number")
    private String Number;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student", fetch = FetchType.EAGER) //mappedBy must be a single word
                                                               //FetchType.EAGER for one time select query
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "")   
    private List<Course> courseList;
    
    
    public StudentOne() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String Number) {
        this.Number = Number;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
    
}
