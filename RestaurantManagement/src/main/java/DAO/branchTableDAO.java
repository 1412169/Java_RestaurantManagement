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
import org.hibernate.Transaction;

/**
 *
 * @author hotha
 */
public class branchTableDAO {

    public static boolean createBranchTable(BranchTable bt) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        boolean kq = true;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(bt);
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

    public static java.util.List<BranchTable> getBranchTableListByBranchId(int branchId) {
        java.util.List<BranchTable> branchList = new ArrayList<BranchTable>();
        branchList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String queryString = "from BranchTable where delFlag=0 and branchId = " + branchId;
            Query query = session.createQuery(queryString);
            branchList = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return branchList;
    }

    public static BranchTable getBranchTableInfo(int branchTableId) {
        BranchTable branchTable = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            branchTable = (BranchTable) session.get(BranchTable.class, branchTableId);
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return branchTable;
    }

    public static boolean deleteBranchTable(BranchTable branchTable) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (branchTableDAO.getBranchTableInfo(branchTable.getId()) == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(branchTable);
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
