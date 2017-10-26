package newhibernate;

import com.hibernate.dto.Gender;
import com.hibernate.dto.userDetails;
import java.util.Arrays;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class NewHibernate implements sessionFactoryProvider {

    private static Session session;
    private static Transaction transaction;
    private static userDetails user;
    private static String hql;
    private static Query query;
    private static List<userDetails> userList;
    private static boolean isUpdate = false;

    private static void getData() {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();

        try {
            user = (userDetails) session.get(userDetails.class, 0L);
            System.out.println("Id = " + user.getId());
            System.out.println("UserName = " + user.getUser());
            String[] phone = user.getPhone();
            for (String phn : phone) {
                System.out.println(phn);
            }
            Gender gender = user.getGender();
            System.out.println(gender.getAge());
            System.out.println(gender.isIsMale());
           
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
            throw new ExceptionInInitializerError(e);
            
        } finally {
            session.close();
        }

    }

    private static void setData() {
        userDetails user = new userDetails();
        user.setId(9);
        user.setUser("Nine User");

        session = sessionFactory.openSession();
        transaction = session.beginTransaction();

        try {
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ExceptionInInitializerError(e);
        } finally {
            session.close();
        }
    }
    private static void setDataAsObject() {
        userDetails user = new userDetails();
        user.setId(0);
        user.setUser("First User");
        
        Gender gender = new Gender();
        gender.setAge(12.5);
        gender.setIsMale(false);
        user.setGender(gender);
        
        String[] phone = new String[3];
        phone[0] = "015000000000";
        phone[1] = "017000000000";
        phone[2] = "016000000000";
        user.setPhone(phone);
        
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();

        try {
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ExceptionInInitializerError(e);
        } finally {
            session.close();
        }
    }

    private static void getDataByHQL() {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        try {
//            hql = "FROM userDetails where id=100";
            hql = "FROM userDetails";
            query = session.createQuery(hql);
            userList = query.list();

            transaction.commit();
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        } finally {
            session.close();
        }

        if (userList != null && userList.size() > 0) {
            for (userDetails dt : userList) {
                System.out.println(dt.getId());
                System.out.println(dt.getUser());
                String[] phn = dt.getPhone();
                for (String phns : phn) {
                    System.out.println(phns);
                }
                Gender gender = dt.getGender();
                System.out.println(gender.getAge());
                System.out.println(gender.isIsMale());
            }
            
        }
    }

    private static void updateData() {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        try {
            //Frist Process
//            user = (userDetails) session.get(userDetails.class, 8L);  //This take the Primary of a row
//            user.setUser("Change User Eight");  // This set the value of Users

            //Second Process | There is a problem that if you forget to set any of single cloumn 
            //then it will set as a NULL
//            user = new userDetails();
//            user.setId(7L);
//            user.setUser("Changed seven user");
//            session.update(user);
            //HQL Procsess 
//            hql = "update userDetails set User = :User where Id = :Id";  Problem ??
            hql = "update userDetails user set user.User=? where user.Id=5";
            query = session.createQuery(hql);
            query.setParameter(0, "There is HQL Change");
//            query.setParameter(1, "5");
            int result = query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        } finally {
            session.close();
        }
    }
    private static void deleteData() {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        try {
            //Frist Process
//            user = (userDetails) session.get(userDetails.class, 8L);  //This take the Primary of a row
            
            //Second Process 
//            user =  new userDetails();
//            user.setId(6L);
//            session.delete(user); // This Delete the value of Users
            
            //HQL Process
            //Single value
//            hql = "FROM userDetails UD where UD.Id = 1";  // As a class of DB Table
//            query = session.createQuery(hql);
//            userList = query.list();
//            session.delete(userList.get(0));

            //Multiple Row Delete 
            hql = "FROM userDetails UD where UD.User like 'u-2%'";
            query = session.createQuery(hql);
            userList = query.list();
            for (userDetails UD : userList) {
                session.delete(UD);
            }
            transaction.commit();
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        } finally {
            session.close();
        }
    }

    public static void main(String[] args) {
//        setData();
//        getData();
        getDataByHQL();
        if(HQLupadate() == true)
        {
            System.out.println("Update Successfull..");
        }
        else
        {
            System.out.println("Update Faild..");
        }
//        updateData();
//        deleteData();
//        setDataAsObject();
        sessionFactory.close();

    }
    
    public static boolean HQLupadate()
    {
        session = sessionFactory.openSession();
        transaction = null;
        
        try {
            
            transaction = session.beginTransaction();
            
            hql = "UPDATE userDetails UD SET UD.User = :ud_user WHERE UD.Id = :ud_id";
            query = session.createQuery(hql);
            query.setParameter("ud_user", "Changed by HQL");
            query.setParameter("ud_id", 0L);
            
            query.executeUpdate();
            
            transaction.commit();
            
            isUpdate = true;
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
        return isUpdate;
    }
}




























