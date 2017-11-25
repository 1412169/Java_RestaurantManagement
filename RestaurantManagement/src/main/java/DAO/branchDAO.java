/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Branch;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;



/**
 *
 * @author hotha
 */
public class branchDAO {
     public static java.util.List<Branch> getBranchList(){
        java.util.List<Branch> branchList = new ArrayList<Branch>();
        branchList = null;
        Session session = HibernateUtil.getSessionFactory().openSession(); 
         try{
             String queryString = "from Branch";
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
