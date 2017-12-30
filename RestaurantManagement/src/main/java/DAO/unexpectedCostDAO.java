/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.UnexpectedCost;
import java.util.ArrayList;
import java.util.Date;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author hotha
 */
public class unexpectedCostDAO {
     public static java.util.List getCostMonthly(int branchId, int year) {
        java.util.List costMonthly = new ArrayList();
        costMonthly = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String queryString = null;
        try {
            if (branchId == 0){
                queryString = "select MONTH(ob.createdAt),sum(ob.cost) from UnexpectedCost ob where  YEAR(ob.createdAt) = "+ year +" group by MONTH(ob.createdAt)";
            }
            else {
                queryString = "select MONTH(ob.createdAt),sum(ob.cost) from UnexpectedCost ob where ob.branchId = "+ branchId +" and YEAR(ob.createdAt) = "+ year +" group by MONTH(ob.createdAt)";    
            }
            Query query = session.createQuery(queryString);
            costMonthly = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return costMonthly;
    }

    public static java.util.List getCostYear(int branchId, int year) {
        java.util.List costYear = new ArrayList();
        costYear = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String queryString;
            if (branchId == 0) {
                queryString = "select sum(od.cost) from UnexpectedCost od where YEAR(od.createdAt) = " + year;
            } else {
                queryString = "select sum(od.cost) from UnexpectedCost od where od.branchId = " + branchId + " and YEAR(od.createdAt) = " + year;
            }
            Query query = session.createQuery(queryString);
            costYear = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return costYear;
    }
   

    public static java.util.List getCostMonth(int branchId, int month, int year) {
        java.util.List totalBillMonth = new ArrayList();
        totalBillMonth = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String queryString;
            if (branchId == 0) {
                queryString = "select sum(od.cost) from UnexpectedCost od where MONTH(od.createdAt) = " + month + " and YEAR(od.createdAt) = " + year;
            } else {
                queryString = "select sum(od.cost) from UnexpectedCost od where od.branchId = " + branchId + " and MONTH(od.createdAt) = " + month + " and YEAR(od.createdAt) = " + year;
            }
            Query query = session.createQuery(queryString);
            totalBillMonth = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return totalBillMonth;
    }
//    
    public static java.util.List getCostDate(int branchId, Date date) {
        java.util.List costMonth = new ArrayList();
        costMonth = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String queryString;
            if (branchId == 0) {
                queryString = "select sum(od.cost) from UnexpectedCost od where DATE(od.createdAt) = DATE(current_date)";
            } else {
                queryString = "select sum(od.cost) from UnexpectedCost od where od.branchId = " + branchId + " and DATE(od.createdAt) = DATE(current_date)";
            }
            Query query = session.createQuery(queryString);
            costMonth = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return costMonth;
    }
    
    
     public static java.util.List getCostDayMonthYear(int branchId, int day, int month, int year) {
        java.util.List costDayMonthYear = new ArrayList();
        costDayMonthYear = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String queryString;
            if (branchId == 0) {
                queryString = "select sum(od.cost) from UnexpectedCost od "
                        + "where DAY(od.createdAt) = " + day + " and MONTH(od.createdAt) = " + month + " and YEAR(od.createdAt) = " + year;
            } else {
                queryString = "select sum(od.cost) from UnexpectedCost od "
                        + "where branchId = " + branchId + " and DAY(od.createdAt) = " + day + " and MONTH(od.createdAt) = " + month + " and YEAR(od.createdAt) = " + year;
            }
            Query query = session.createQuery(queryString);
            costDayMonthYear = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return costDayMonthYear;
    }
     
     public static java.util.List<UnexpectedCost> getCostList(int branchId) {
        java.util.List<UnexpectedCost> costList = new ArrayList<UnexpectedCost>();
        costList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String queryString;
        try {
            if (branchId == 0){
                queryString = "from UnexpectedCost where delFlag=0" ;
            }
            else{
                queryString = "from UnexpectedCost where delFlag=0 and branchId = " + branchId;
            }
            Query query = session.createQuery(queryString);
            costList = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return costList;
    }
}
