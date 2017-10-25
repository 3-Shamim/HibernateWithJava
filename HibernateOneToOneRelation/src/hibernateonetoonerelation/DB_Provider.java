package hibernateonetoonerelation;

import java.util.List;
import org.hibernate.*;
import student.dto.Semester;
import student.dto.Student;

public class DB_Provider implements SessionFactory_Provider {

    private Session session;
    private Transaction transction;
    private List<Student> listStudent;
    private List<Semester> listSemester;

    public void SetData(String Studentname, double cgpa, String semName, double semGpa) {
        session = sessionFactory.openSession();
        transction = null;
//        int id = 0;
//        String name = "Shamim";
//        double cgpa = 3.5;
        try {
            transction = session.beginTransaction();
//            String hql = "INSERT INTO Student(Id,Name,cgpa) values(:id,:name,:cgpa)";
//
//            Query query = session.createQuery(hql);
//            query.setParameter("id", id);
//            query.setParameter("name", name);
//            query.setParameter("cgpa", cgpa);
//            int res = query.executeUpdate();

            Student st = new Student();   //One Student Row For one Semester Row
            st.setName(Studentname);
            st.setCgpa(cgpa);

            Semester sem = new Semester();  //One Semester Row For one Student Row
            sem.setSemName(semName);
            sem.setSemGpa(semGpa);

            st.setSemester(sem);

            session.save(st);
            transction.commit();
        } catch (Exception e) {
            if (transction != null) {
                transction.rollback();
            }
            System.out.println(e);
        } finally {
            session.close();
        }
    }

    public void getData() {
        session = sessionFactory.openSession();
        transction = null;
        try {
            transction = session.beginTransaction();

//            Student student = (Student) session.get(Student.class, 1);
//            System.out.println(list.getName());
//            System.out.println(student.getCgpa());
//            Semester sem = student.getSemester();
//            System.out.println(sem.getSemName());
//            System.out.println(sem.getSemGpa());


            Query query = session.createQuery("From Semester");
//            listStudent = query.list();
            listSemester = query.list();
            transction.commit();
        } catch (Exception e) {
            if (transction != null) {
                transction.rollback();
            }
            System.out.println(e);
        } finally {
            session.close();
        }
        for (Semester st : listSemester) {
            
            // This is For Student Table
//            System.out.println("Student ID : " + st.getId());
//            System.out.println("Student Name : " + st.getName());
//            System.out.println("Student CGPA =  " + st.getCgpa());
//            System.out.println("Semester ID : " + st.getSemester().getSemId());
//            System.out.println("Semester Name : " + st.getSemester().getSemName());
//            System.out.println("Semester GPA : " + st.getSemester().getSemGpa());

            //This is for Semster Table
            System.out.println("Semester Name : " + st.getSemName());
            System.out.println("Semester GPA : " + st.getSemGpa());
            System.out.println("Student Name : " + st.getStudent().getName());
            System.out.println("Student CGPA = " + st.getStudent().getCgpa());
        }
    }
}
