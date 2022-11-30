package javafxbead;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CRUD {
    public static void Create(SessionFactory factory){
        System.out.println("Create()....");
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();



//        session.save();
        t.commit();
        session.close();
    }
}
