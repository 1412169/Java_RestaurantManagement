/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author hotha
 */
public class employeeDAO {

//    public Employee findAccount(String username) {
//          Session session = HibernateUtil.getSessionFactory().openSession();
//        String sql = "Select * from EMPLOYEE Where username = :username";
//        Query query = session.createQuery(sql);
//        query.setParameter("username", username);
//        Employee e = (Employee)query.list().get(0);
//        return e;
//    }
    
     public static Employee getEmployeeByUsername(String username) {
        Employee user = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String queryString = "from Employee where delFlag=0 and username = :username";
            Query query = session.createQuery(queryString);
            query.setParameter("username", username);
            user = (Employee) query.uniqueResult();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return user;
    }
}
