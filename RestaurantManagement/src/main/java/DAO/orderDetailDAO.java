/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author hotha
 */
public class orderDetailDAO {

    public static java.util.List getRevenueMonthly(int branchId, int year) {
        java.util.List revenueMonth = new ArrayList();
        revenueMonth = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String queryString = null;
        try {
            if (branchId == 0){
                queryString = "select MONTH(od.createdAt),sum(od.price) as Revenue from OrderDetail od where  YEAR(createdAt) = "+ year +" group by MONTH(od.createdAt)";
            }
            else {
                queryString  = "select MONTH(od.createdAt),sum(od.price) as Revenue from OrderDetail od, OrderBill ob where od.orderId = ob.id and ob.branchId = "+ branchId +" and YEAR(od.createdAt) = "+ year +" group by MONTH(od.createdAt)";    
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

    public static java.util.List getRevenueDate(int branchId, Date date) {
        java.util.List revenueMonth = new ArrayList();
        revenueMonth = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String queryString = null;
        try {
            if (branchId == 0) { 
                queryString = "select SUM(od.price) as sum from OrderDetail od where DATE(od.createdAt) = DATE(current_date)" ;
            }
            else{
                queryString = "select SUM(od.price) as sum from OrderDetail od, OrderBill ob where od.orderId = ob.id and ob.branchId = " + branchId + " and DATE(od.createdAt) = DATE(current_date)";
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
    
    public static java.util.List getRevenueDayMonthYear(int branchId, int day, int month, int year) {
        java.util.List revenueMonth = new ArrayList();
        revenueMonth = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String queryString = null;
        try {
            if (branchId == 0) { 
                queryString = "select SUM(od.price) as sum from OrderDetail od "
                        + "where DAY(od.createdAt) = " + day + " and MONTH(od.createdAt) = " + month + " and YEAR(od.createdAt) = " + year ;
            }
            else{
                queryString = "select SUM(od.price) as sum from OrderDetail od, OrderBill ob "
                        + "where od.orderId = ob.id and ob.branchId = " + branchId + " and "
                        + "DAY(od.createdAt) = " + day + " and MONTH(od.createdAt) = " + month + " and YEAR(od.createdAt) = " + year;
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
    
     public static java.util.List getRevenueYear(int branchId, int year) {
        java.util.List revenueMonth = new ArrayList();
        revenueMonth = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String queryString = null;
        try {
            if (branchId == 0){
                queryString = "select SUM(od.price) as sum from OrderDetail od where YEAR(od.createdAt) = " + year;
            }
            else {
                queryString = "select SUM(od.price) as sum from OrderDetail od, OrderBill ob where od.orderId = ob.id and ob.branchId = " + branchId + " and YEAR(od.createdAt) = " + year;
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
     
    public static java.util.List getRevenueMonth(int branchId, int month, int year) {
        java.util.List revenueMonth = new ArrayList();
        revenueMonth = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String queryString = null;
        try {
            if (branchId == 0){
                queryString = "select SUM(od.price) as sum from OrderDetail od where MONTH(od.createdAt) = " + month + " and YEAR(od.createdAt) = " + year;
            }
            else{
                 queryString = "select SUM(od.price) as sum from OrderDetail od, OrderBill ob where od.orderId = ob.id and ob.branchId = " + branchId + " and MONTH(od.createdAt) = " + month + " and YEAR(od.createdAt) = " + year;
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
}
