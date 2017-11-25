/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.BranchDirectory;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;


/**
 *
 * @author hotha
 */
public class branchDirectoryDAO {
    public static java.util.List<BranchDirectory> getBranchDirectoryList(){
        java.util.List<BranchDirectory> dishList = new ArrayList<BranchDirectory>();
        dishList = null;
        Session session = HibernateUtil.getSessionFactory().openSession(); 
         try{
             String queryString = "from BranchDirectory";
             Query query = session.createQuery(queryString);
             dishList = query.list();
         }catch (HibernateException ex){
             System.err.println(ex);
         }finally{
             session.close();
         }
         return dishList;
    }
}
