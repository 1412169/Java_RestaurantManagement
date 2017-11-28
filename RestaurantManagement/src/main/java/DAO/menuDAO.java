/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Menu;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author hotha
 */
public class menuDAO {
     public static java.util.List<Menu> getMenuList(){
        java.util.List<Menu> menuList = new ArrayList<Menu>();
        menuList = null;
        Session session = HibernateUtil.getSessionFactory().openSession(); 
         try{
             String queryString = "from Menu";
             Query query = session.createQuery(queryString);
             menuList = query.list();
         }catch (HibernateException ex){
             System.err.println(ex);
         }finally{
             session.close();
         }
         return menuList;
    }
}
