/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Dish;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author hotha
 */
public class dishDAO {

    public static java.util.List<Dish> getDishList() {
        java.util.List<Dish> dishList = new ArrayList<Dish>();
        dishList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String queryString = "from Dish where delFlag = 0";
            Query query = session.createQuery(queryString);
            dishList = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return dishList;
    }

    public static Dish getDishInfo(int dishId) {
        Dish dish = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            dish = (Dish) session.get(Dish.class, dishId);
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return dish;
    }

    public static boolean deleteDish(Dish dish) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (dishDAO.getDishInfo(dish.getId()) == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(dish);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return true;
    }

    public static boolean createDish(Dish dish) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        boolean kq = true;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(dish);
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

    public static boolean updateDish(Dish dish) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (dishDAO.getDishInfo(dish.getId()) == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(dish);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return true;
    }
    
    public static java.util.List<Dish> getDishListByDirectoryId(int directoryId) {
        java.util.List<Dish> dishList = new ArrayList<Dish>();
        dishList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String queryString = "from Dish where dishDirectoryId = " + directoryId;
            Query query = session.createQuery(queryString);
            dishList = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return dishList;
    }
    
     public static java.util.List getCountDish(int branchId, int year, int month) {
        java.util.List dishList = new ArrayList();
        dishList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String queryString;
        try {
            if (branchId == 0){
                 queryString = "select dd.id, dd.name, d.id, d.name, d.price, count(d.id) "
                        + "from Dish d, DishDirectory dd, OrderDetail od, OrderBill ob "
                        + "where od.orderId = ob.id and  d.dishDirectoryId = dd.id and d.id = od.dishId and YEAR(ob.createdAt) = " + year + " and MONTH(ob.createdAt) = " + month + ""
                        + "group by d.dishDirectoryId, dd.name, d.name, d.id, MONTH(ob.createdAt)" ;
            }
            else {
                queryString = "select dd.id, dd.name, d.id, d.name ,d.price, count(d.id) "
                        + "from Dish d, DishDirectory dd, OrderDetail od, OrderBill ob "
                        + "where od.orderId = ob.id and  d.dishDirectoryId = dd.id and d.id = od.dishId and YEAR(ob.createdAt) = " + year + " and MONTH(ob.createdAt) = " + month + " and ob.branchId = " + branchId + ""
                        + "group by d.dishDirectoryId, dd.name, d.name, d.id, MONTH(ob.createdAt)" ;
            }
            
            Query query = session.createQuery(queryString);
            dishList = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return dishList;
    }
}
