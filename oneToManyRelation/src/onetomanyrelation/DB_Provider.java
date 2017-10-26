package onetomanyrelation;

import DataBaseTables.Course;
import DataBaseTables.StudentOne;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.*;

public class DB_Provider implements SessionFactory_Provider {

    private Session session;
    private Transaction transaction;
    private List<Course> list;

    public void setData() {
        session = sessionFactory.openSession();
        transaction = null;

        try {

            transaction = session.beginTransaction();

            StudentOne st = new StudentOne();
            st.setName("Shamim");
            st.setEmail("Shamim@gmail.com");
            st.setNumber("016.0.0.0.0");

            List<Course> courseList = new ArrayList<>();
            courseList.add(new Course("Cse2015", "Java", 3, st));
            courseList.add(new Course("Cse2016", "Java Lab", 1, st));
            courseList.add(new Course("Cse3011", "Database", 3, st));
            courseList.add(new Course("Cse3012", "Database Lab", 1, st));

            st.setCourseList(courseList);

            StudentOne st1 = new StudentOne();
            st1.setName("Kamrul");
            st1.setEmail("Kamrul@gmail.com");
            st1.setNumber("017.0.0.0.0");

            List<Course> courseList1 = new ArrayList<>();
            courseList1.add(new Course("Cse4011", "Networking", 3, st1));
            courseList1.add(new Course("Cse4012", "Networking Lab", 1, st1));

            st1.setCourseList(courseList1);

            session.save(st);
            session.save(st1);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e);
        } finally {
            session.close();
        }
    }

    public void getData() {
        session = sessionFactory.openSession();
        transaction = null;
        
        try {

            transaction = session.beginTransaction();

//            StudentOne st = (StudentOne) session.get(StudentOne.class, 2);
//            System.out.println("Student Name : " + st.getName());
//            System.out.println("Student Email : " + st.getEmail());
//            System.out.println("Student Number : " + st.getNumber());
//
//            for (Course cr : st.getCourseList()) {
//                System.out.println("Course Code : " + cr.getCourseCode()
//                        + " | Course Title : " + cr.getCourseTitle()
//                        + " | Course Credit : " + cr.getCredit());
//            }
//            Course cr = (Course) session.get(Course.class, 1);
//            System.out.println("Course Code : " + cr.getCourseCode()
//                    + " | Course Title : " + cr.getCourseTitle()
//                    + " | Course Credit : " + cr.getCredit());
//            System.out.println("Student Name : " + cr.getStudent().getName());

            Query query = session.createQuery("FROM Course cr WHERE cr.Credit = :cr_credit");
            query.setParameter("cr_credit", 3);
            list = query.list();
            

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e);
        } finally {
            session.close();
        }
        for (Course cr : list) {
            System.out.println("Course Code : " + cr.getCourseCode()
                        + " | Course Title : " + cr.getCourseTitle()
                        + " | Course Credit : " + cr.getCredit());
            System.out.println("Student Name : " + cr.getStudent().getName());
        }
    }
}
