package manytomanyrelation;

public class ManyToManyRelation implements SessionFactory_Provider{

    public ManyToManyRelation() {
        DB_Provider db = new DB_Provider();
//        db.setData();
        db.getData();
    }
    

    public static void main(String[] args) {
        new ManyToManyRelation();
        sessionFactory.close();
    }
    
}
