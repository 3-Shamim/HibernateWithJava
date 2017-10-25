package student.dto;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "Student")
public class Student implements Serializable {
    @Id
    @GeneratedValue
    private int Id;
    @Column(name = "Name")
    private String Name;
    @Column(name = "Cgpa")
    private double cgpa;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Id")
    private Semester semester;
    

    public Student() {
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

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }
    
}
