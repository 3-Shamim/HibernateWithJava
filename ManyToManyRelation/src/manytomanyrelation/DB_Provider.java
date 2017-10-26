package manytomanyrelation;

import TableClass.Depertment;
import TableClass.Faculty;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.*;

public class DB_Provider implements SessionFactory_Provider{
    
    private Session session;
    private Transaction transaction;
    private List<Depertment> depResult;
    
    public void setData()
    {
        session = sessionFactory.openSession();
        transaction = null;
        try {
            transaction = session.beginTransaction();
            
            //Number of Faculty 
            Faculty faculty = new Faculty();
            faculty.setFacultyName("Kamrul");
            faculty.setFacultyEmail("Kamrul@mail.com");
            faculty.setFacultNumber("016.2.2.2.2.2");
            
            Faculty faculty1 = new Faculty();
            faculty1.setFacultyName("Rajon");
            faculty1.setFacultyEmail("Rajon@mail.com");
            faculty1.setFacultNumber("015.2.2.2.2.2");
            
            Faculty faculty2 = new Faculty();
            faculty2.setFacultyName("Sourav");
            faculty2.setFacultyEmail("Sourav@mail.com");
            faculty2.setFacultNumber("018.2.2.2.2.2");
            
            //Number of Faculty Set
            
            Set<Faculty> s1 = new HashSet<>();
            s1.add(faculty);
            s1.add(faculty1);
            
            Set<Faculty> s2 = new HashSet<>();
            s2.add(faculty1);
            s2.add(faculty2);
            
            Depertment depertment = new Depertment();
            depertment.setDepName("CSE");
            depertment.setDepAddress("Banani, Dhaka");
            depertment.setFacultySet(s1);
            
            
            Depertment depertment1 = new Depertment();
            depertment1.setDepName("BBA");
            depertment1.setDepAddress("Banani, Dhaka");
            depertment1.setFacultySet(s2);
            
            session.save(depertment);
            session.save(depertment1);
            transaction.commit();
        } 
        catch (Exception e) {
            if(transaction != null)
            {
                transaction.rollback();
            }
            System.out.println(e);
        }finally{
            session.close();
        }
    }
    
    public void getData(){
        session = sessionFactory.openSession();
        transaction = null;
        try {
            transaction = session.beginTransaction();
            
            Query query = session.createQuery("FROM Depertment dp WHERE dp.id = :dp_Id");
            query.setParameter("dp_Id", 1);
            
            depResult = query.list();
            
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e);
        }finally{
            session.close();
        }
        
        for (Depertment d : depResult) {
            System.out.println("Dep Name : " + d.getDepName());
            System.out.println("Dep Address : " + d.getDepAddress());
            for (Faculty f : d.getFacultySet()) {
                System.out.println("Facult Name : " + f.getFacultyName());
                System.out.println("Facult Number : " + f.getFacultNumber());
                System.out.println("Facult Email : " + f.getFacultyEmail());
            }
        }
    }
    
}
