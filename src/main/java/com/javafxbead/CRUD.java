package com.javafxbead;
import com.database.models.Gep;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;


public class CRUD {
    public static void Create(Session session){
        System.out.println("Beír()........");
        Transaction t = session.beginTransaction();
        Gep gep = new Gep("gyartoo","tipusa","14.5",16,128,"van",(new BigDecimal(25253)),3);
        session.save(gep);
        t.commit();
        System.out.println("Adatok beírva a táblába!");
    }

}
