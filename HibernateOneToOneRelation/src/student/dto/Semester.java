package student.dto;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "Semester")
public class Semester implements Serializable{
    @Id
    @GeneratedValue
    private int semId;
    @Column(name = "semName")
    private String semName;
    @Column(name = "semGpa")
    private double semGpa;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "semId")
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Semester() {
    }

    public int getSemId() {
        return semId;
    }

    public void setSemId(int semId) {
        this.semId = semId;
    }

    public String getSemName() {
        return semName;
    }

    public void setSemName(String semName) {
        this.semName = semName;
    }

    public double getSemGpa() {
        return semGpa;
    }

    public void setSemGpa(double semGpa) {
        this.semGpa = semGpa;
    }
}
