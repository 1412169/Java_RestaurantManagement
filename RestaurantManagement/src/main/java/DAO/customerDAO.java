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
public class customerDAO {

    public static java.util.List getCustomerBuy(int year, int month) {
        java.util.List dishList = new ArrayList();
        dishList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String queryString;
        try {
//            if (year == 0 && month == 0){
//                queryString = "select c.id, c.name, c.phone, count(ob.id), sum(od.price) "
//                        + "from Customer c, OrderBill ob, OrderDetail od "
//                        + "where c.id = ob.customerId and ob.id = od.orderId "
//                         + "and DATE(ob.createdAt) >= DATE(c.createdAt) "
//                        + "group by c.id, c.name, c.phone";
//            }
//            else{
               queryString = "select c.id, c.name, c.phone, count(ob.id), sum(od.price) "
                       + "from Customer c, OrderBill ob, OrderDetail od "
                       + "where c.id = ob.customerId and ob.id = od.orderId "
                       + "and DATE(ob.createdAt) >= DATE(c.createdAt) "
                       + "and MONTH(od.createdAt) = " +month+ " and YEAR(od.createdAt) = "+ year +" "
                       + "group by c.id, c.name, c.phone"; 
//            }
            
        Query query = session.createQuery(queryString);
        dishList = query.list();
    }
    catch (HibernateException ex) {
            System.err.println(ex);
    }
    finally {
            session.close();
    }
    return dishList ;
}
    public static java.util.List getCustomerMonthly(int year) {
        java.util.List dishList = new ArrayList();
        dishList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String queryString;
        try {
//            if (year == 0){
//                queryString = "select MONTH(createdAt),count(id) from Customer where YEAR(createdAt) = YEAR(current_date) group by MONTH(createdAt)" ;
//            }
//            else{
               queryString = "select MONTH(createdAt),count(id) from Customer where YEAR(createdAt) = " + year + " group by MONTH(createdAt)"; 
//            }
            
        Query query = session.createQuery(queryString);
        dishList = query.list();
    }
    catch (HibernateException ex) {
            System.err.println(ex);
    }
    finally {
            session.close();
    }
    return dishList ;
}
}
