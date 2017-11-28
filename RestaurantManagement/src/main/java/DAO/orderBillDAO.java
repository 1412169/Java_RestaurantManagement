/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.OrderBill;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author hotha
 */
public class orderBillDAO {
    public static java.util.List<OrderBill> getOrderBillList(){
        java.util.List<OrderBill> branchList = new ArrayList<OrderBill>();
        branchList = null;
        Session session = HibernateUtil.getSessionFactory().openSession(); 
         try{
             String queryString = "from OrderBill";
             Query query = session.createQuery(queryString);
             branchList = query.list();
         }catch (HibernateException ex){
             System.err.println(ex);
         }finally{
             session.close();
         }
         return branchList;
    }
}
