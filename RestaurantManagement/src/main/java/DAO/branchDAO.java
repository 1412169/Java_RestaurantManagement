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
import org.hibernate.Transaction;

/**
 *
 * @author hotha
 */
public class branchDAO {

    public static java.util.List<Branch> getBranchList() {
        java.util.List<Branch> branchList = new ArrayList<Branch>();
        branchList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String queryString = "from Branch where delFlag=0";
            Query query = session.createQuery(queryString);
            branchList = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return branchList;
    }

    public static Branch getBranchInfo(int branchId) {
        Branch branch = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            branch = (Branch) session.get(Branch.class, branchId);
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return branch;
    }

    public static boolean deleteBranch(Branch branch) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (branchDAO.getBranchInfo(branch.getId()) == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(branch);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return true;
    }

    public static int createBranch(Branch branch) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        int branchId = -1;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            branchId = (Integer)session.save(branch);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
            return branchId;
        } finally {
            session.close();
        }
        return branchId;
    }
    
     public static boolean updateBranch(Branch branch) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (dishDAO.getDishInfo(branch.getId()) == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(branch);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return true;
    }
}
