/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.BranchMenu;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author hotha
 */
public class branchMenuDAO {
    public static boolean createBranchMenu(BranchMenu bm) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        boolean kq = true;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(bm);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
            kq = false;
        } finally {
            session.close();
        }
        return kq;
    }
    
     public static java.util.List<BranchMenu> getMenuListByBranchId(int branchId){
        java.util.List<BranchMenu> menuList = new ArrayList<BranchMenu>();
        menuList = null;
        Session session = HibernateUtil.getSessionFactory().openSession(); 
         try{
             String queryString = "from BranchMenu where delFlag = 0 and branchId = " + branchId;
             Query query = session.createQuery(queryString);
             menuList = query.list();
         }catch (HibernateException ex){
             System.err.println(ex);
         }finally{
             session.close();
         }
         return menuList;
    }
     
     
       public static boolean deleteBranchMenu(BranchMenu branchMenu) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (branchDAO.getBranchInfo(branchMenu.getId()) == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(branchMenu);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return true;
    }
       
        public static java.util.List<BranchMenu> getBranchMenuList() {
        java.util.List<BranchMenu> branchMenuList = new ArrayList<BranchMenu>();
        branchMenuList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String queryString = "from Branch where delFlag=0";
            Query query = session.createQuery(queryString);
            branchMenuList = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return branchMenuList;
    }
}
