/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.BranchTable;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author hotha
 */
public class tableDAO {
    public static java.util.List<BranchTable> getBranchTableByBranchId(int branchId){
        java.util.List<BranchTable> tableList = new ArrayList<BranchTable>();
        tableList = null;
        Session session = HibernateUtil.getSessionFactory().openSession(); 
         try{
             String queryString = "from BranchTable where delFlag = 0 and branchId = " + branchId;
             Query query = session.createQuery(queryString);
             tableList = query.list();
         }catch (HibernateException ex){
             System.err.println(ex);
         }finally{
             session.close();
         }
         return tableList;
    }
}
