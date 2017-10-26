package onetomanyrelation;

public class OneToManyRelation implements SessionFactory_Provider{

    public OneToManyRelation() {
        DB_Provider db = new DB_Provider();
        db.setData();
        db.getData();
    }
    

    public static void main(String[] args) {
        new OneToManyRelation();
        sessionFactory.close();
    }
    
}
