/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.MenuDetail;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author hotha
 */
public class menuDetailDAO {

    public static java.util.List<MenuDetail> getMenuDetailListByMenuId(int menuId) {
        java.util.List<MenuDetail> menuDetailList = new ArrayList<MenuDetail>();
        menuDetailList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String queryString = "from MenuDetail where menuId = " + menuId + "and delFlag = 0";
            Query query = session.createQuery(queryString);
            menuDetailList = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return menuDetailList;
    }

    public static MenuDetail getMenuDetailInfo(int menuDetailId) {
        MenuDetail menuDetail = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            menuDetail = (MenuDetail) session.get(MenuDetail.class, menuDetailId);
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return menuDetail;
    }

    public static int createMenuDetail(MenuDetail menuDetail) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        int kq = -1;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            kq = (Integer) session.save(menuDetail);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
            kq = -1;
        } finally {
            session.close();
        }
        return kq;
    }

    public static boolean deleteMenuDetail(MenuDetail menuDetail) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (menuDetailDAO.getMenuDetailInfo(menuDetail.getId()) == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(menuDetail);
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
