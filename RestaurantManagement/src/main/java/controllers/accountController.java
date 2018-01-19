/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DAO.employeeDAO;
import Entity.Employee;
import Util.Password;
import javax.inject.Scope;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author USER
 */
@Controller
public class accountController {

//    @Autowired

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginView(Model model) {
        model.addAttribute("message", "");
        return new ModelAndView("login.jsp");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginView(HttpServletRequest request, Model model) {
        String username = request.getParameterValues("username")[0];
        String password = request.getParameterValues("password")[0];
        Employee user = employeeDAO.getEmployeeByUsername(username);
        if (user == null) {
            model.addAttribute("message", "Invalid user name");
            return "login.jsp";
        }
        boolean compare_computed = Password.checkPassword(password, user.getPassword());
        if (compare_computed == true) {
            if (user.getPosition().equalsIgnoreCase("ROLE_MANAGER")) {
                HttpSession session = request.getSession(); // 
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(3000); // 3000 seconds
                return "redirect:home";
            } else {
                return "redirect:403";
            }
        } else {
            model.addAttribute("message", "Invalid password");
            return "login.jsp";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutView(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String errorAccessPage(HttpServletRequest request) {
        return "403.jsp";
    }
   
}
