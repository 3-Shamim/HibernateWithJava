package TableClass;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "Depertment")
public class Depertment implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String depName;
    private String depAddress;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "Depertment_Faculty", 
            joinColumns = {@JoinColumn(name = "Depertment_Id")}, 
            inverseJoinColumns = {@JoinColumn(name = "Faculty_Id")})
    private Set<Faculty> facultySet;

    public Depertment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getDepAddress() {
        return depAddress;
    }

    public void setDepAddress(String depAddress) {
        this.depAddress = depAddress;
    }

    public Set<Faculty> getFacultySet() {
        return facultySet;
    }

    public void setFacultySet(Set<Faculty> facultySet) {
        this.facultySet = facultySet;
    }
}
