package hibernateonetoonerelation;


public class HibernateOneToOneRelation implements SessionFactory_Provider{
    
    

    public HibernateOneToOneRelation() {
        DB_Provider db = new DB_Provider();
        db.SetData("Shamim", 3.7, "Spring2016", 3.25);
        db.SetData("Kamrul", 3.65, "Spring2016", 3.55);
        db.getData();
    }
    
    public static void main(String[] args) {
        new HibernateOneToOneRelation();
        sessionFactory.close();
    }
    
}
