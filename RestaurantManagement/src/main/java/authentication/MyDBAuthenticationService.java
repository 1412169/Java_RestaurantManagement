///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package authentication;
//
//import DAO.employeeDAO;
//import Entity.Employee;
//import java.util.ArrayList;
//import java.util.List;
// 
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MyDBAuthenticationService implements UserDetailsService {
// 
//    private employeeDAO accountDAO;
// 
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Employee account = accountDAO.findAccount(username);
//        System.out.println("Account= " + account);
// 
//        if (account == null) {
//            throw new UsernameNotFoundException("User " //
//                    + username + " was not found in the database");
//        }
// 
//       
//        String role = account.getPosition();
// 
//        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
// 
//        // ROLE_EMPLOYEE, ROLE_MANAGER
//        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
// 
//        grantList.add(authority);
// 
//        UserDetails userDetails = (UserDetails) new User(account.getUsername(), //
//                account.getPassword(), grantList);
// 
//        return userDetails;
//    }
// 
//}
