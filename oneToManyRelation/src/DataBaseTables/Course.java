package DataBaseTables;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "Course")
public class Course implements Serializable{
    @Id
    @GeneratedValue
    private int Id;
    @Column(name = "CourseCode")
    private String CourseCode;
    @Column(name = "CourseTitle")
    private String CourseTitle;
    @Column(name = "Credit")
    private int Credit;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private StudentOne student;
    
    public Course() {
    }

    public Course(String CourseCode, String CourseTitle, int Credit, StudentOne student) {
        this.CourseCode = CourseCode;
        this.CourseTitle = CourseTitle;
        this.Credit = Credit;
        this.student = student;
    }
    
    
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getCourseCode() {
        return CourseCode;
    }

    public void setCourseCode(String CourseCode) {
        this.CourseCode = CourseCode;
    }

    public String getCourseTitle() {
        return CourseTitle;
    }

    public void setCourseTitle(String CourseTitle) {
        this.CourseTitle = CourseTitle;
    }

    public int getCredit() {
        return Credit;
    }

    public void setCredit(int Credit) {
        this.Credit = Credit;
    }

    public StudentOne getStudent() {
        return student;
    }

    public void setStudent(StudentOne student) {
        this.student = student;
    }
    
}
