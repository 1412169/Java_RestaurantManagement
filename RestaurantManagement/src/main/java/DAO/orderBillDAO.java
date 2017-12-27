/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.OrderBill;
import java.util.ArrayList;
import java.util.Date;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author hotha
 */
public class orderBillDAO {

    public static java.util.List<OrderBill> getOrderBillList() {
        java.util.List<OrderBill> branchList = new ArrayList<OrderBill>();
        branchList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String queryString = "from OrderBill";
            Query query = session.createQuery(queryString);
            branchList = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return branchList;
    }
    
     public static java.util.List getTotalBillMonthly(int branchId, int year) {
        java.util.List revenueMonth = new ArrayList();
        revenueMonth = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String queryString = null;
        try {
            if (branchId == 0){
                queryString = "select MONTH(ob.createdAt),count(ob.id) as totalBill from OrderBill ob where  YEAR(createdAt) = "+ year +" group by MONTH(ob.createdAt)";
            }
            else {
                queryString = "select MONTH(ob.createdAt),count(ob.id) as totalBill from OrderBill ob where ob.branchId = "+ branchId +" and YEAR(ob.createdAt) = "+ year +" group by MONTH(ob.createdAt)";    
            }
            Query query = session.createQuery(queryString);
            revenueMonth = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return revenueMonth;
    }

    public static java.util.List getTotalBillYear(int branchId, int year) {
        java.util.List totalBillYear = new ArrayList();
        totalBillYear = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String queryString;
            if (branchId == 0) {
                queryString = "select count(od.id) as totalBill from OrderBill od where YEAR(od.createdAt) = " + year;
            } else {
                queryString = "select count(od.id) as totalBill from OrderBill od where branchId = " + branchId + " and YEAR(od.createdAt) = " + year;
            }
            Query query = session.createQuery(queryString);
            totalBillYear = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return totalBillYear;
    }
    

    public static java.util.List getTotalBillMonth(int branchId, int month) {
        java.util.List totalBillMonth = new ArrayList();
        totalBillMonth = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String queryString;
            if (branchId == 0) {
                queryString = "select count(od.id) as totalBill from OrderBill od where MONTH(od.createdAt) = " + month;
            } else {
                queryString = "select count(od.id) as totalBill from OrderBill od where branchId = " + branchId + " and MONTH(od.createdAt) = " + month;
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
    
    public static java.util.List getTotalBillDate(int branchId, Date date) {
        java.util.List totalBillMonth = new ArrayList();
        totalBillMonth = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String queryString;
            if (branchId == 0) {
                queryString = "select count(od.id) as totalBill from OrderBill od where DATE(od.createdAt) = DATE(current_date)";
            } else {
                queryString = "select count(od.id) as totalBill from OrderBill od where branchId = " + branchId + " and DATE(od.createdAt) = DATE(current_date)";
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
    
    
     public static java.util.List getTotalBillDayMonthYear(int branchId, int day, int month, int year) {
        java.util.List totalBillMonth = new ArrayList();
        totalBillMonth = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String queryString;
            if (branchId == 0) {
                queryString = "select count(od.id) as totalBill "
                        + "from OrderBill od "
                        + "where DAY(od.createdAt) = " + day + " and MONTH(od.createdAt) = " + month + " and YEAR(od.createdAt) = " + year;
            } else {
                queryString = "select count(od.id) as totalBill "
                        + "from OrderBill od "
                        + "where branchId = " + branchId + " and DAY(od.createdAt) = " + day + " and MONTH(od.createdAt) = " + month + " and YEAR(od.createdAt) = " + year;
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
    
    
    
    public static java.util.List getTotalBillOrderTypeYear(int branchId, int year) {
        java.util.List totalBillOrderYear = new ArrayList();
        totalBillOrderYear = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String queryString;
        try {
            if (branchId == 0){
                queryString = "select orderType, count(orderType) as numOrder from OrderBill where YEAR(createdAt) = "+ year + " group by orderType";
            }
            else{
                queryString = "select orderType, count(orderType) as numOrder from OrderBill "
                        + "where YEAR(createdAt) = "+ year + " and branchId = " + branchId + "group by branchId, orderType";
            }
            
            Query query = session.createQuery(queryString);
            totalBillOrderYear = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return totalBillOrderYear;
    }
    
    public static java.util.List getTotalBillOrderTypeMonth(int branchId, int month) {
        java.util.List totalBillOrderMonth = new ArrayList();
        totalBillOrderMonth = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String queryString;
        try {
            if (branchId == 0){
                queryString = "select orderType, count(orderType) as numOrder from OrderBill where MONTH(createdAt) = "+ month + " group by orderType";
            }
            else{
                queryString = "select orderType, count(orderType) as numOrder from OrderBill where MONTH(createdAt) = "+ month + " and branchId = " + branchId + "group by branchId, orderType";
            }
            
            Query query = session.createQuery(queryString);
            totalBillOrderMonth = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return totalBillOrderMonth;
    }
    
    
    public static java.util.List getTotalBillOrderTypeDate(int branchId, Date date) {
        java.util.List totalBillOrderDate = new ArrayList();
        totalBillOrderDate = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String queryString;
        try {
            if (branchId == 0){
                queryString = "select orderType, count(orderType) as numOrder "
                        + "from OrderBill where DATE(createdAt) = DATE(current_date) group by orderType";
            }
            else{
                queryString = "select orderType, count(orderType) as numOrder "
                        + "from OrderBill where DATE(createdAt) = DATE(current_date) "
                        + "and branchId = " + branchId + "group by branchId, orderType";
            }
            
            Query query = session.createQuery(queryString);
            totalBillOrderDate = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return totalBillOrderDate;
    }
    
    
    public static java.util.List getTotalBillOrderTypeDayMonthYear(int branchId, int day, int month, int year) {
        java.util.List totalBillOrderDate = new ArrayList();
        totalBillOrderDate = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String queryString;
        try {
            if (branchId == 0){
                queryString = "select orderType, count(orderType) as numOrder "
                        + "from OrderBill " 
                        + "where DAY(createdAt) = " + day + "  and MONTH(createdAt) = " + month + " and YEAR(createdAt) = " + year  
                        + "group by orderType";
            }
            else{
                queryString = "select orderType, count(orderType) as numOrder "
                        + "from OrderBill " 
                        + "where branchId = "+ branchId +" and DAY(createdAt) = " + day + "  and MONTH(createdAt) = " + month + " and YEAR(createdAt) = " + year  
                        + "group by orderType";
            }
            
            Query query = session.createQuery(queryString);
            totalBillOrderDate = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return totalBillOrderDate;
    }
}

