/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author hotha
 */
public class dateTimeDAO {

    public static java.util.List getYear() {
        java.util.List list = new ArrayList();
        list = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String queryString = "select YEAR(createdAt), YEAR(createdAt) from OrderBill group by YEAR(createdAt)";
            Query query = session.createQuery(queryString);
            list = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return list;
    }
}
