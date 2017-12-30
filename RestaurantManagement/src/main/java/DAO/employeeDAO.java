/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Employee;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author hotha
 */
public class employeeDAO {

    public Employee findAccount(String username) {
          Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "Select * from EMPLOYEE Where username = :username";
        Query query = session.createQuery(sql);
        query.setParameter("username", username);
        Employee e = (Employee)query.list().get(0);
        return e;
    }
    
}
