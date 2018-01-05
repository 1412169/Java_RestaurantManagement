/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

//import hibernate_source.Product;
//import hibernate_source.ProductDAO;
//import hibernate_source.User;
//import hibernate_source.UserDAO;
import javax.inject.Scope;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.spi.http.HttpContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

/**
 *
 * @author USER
 */
@Controller
public class homeController {
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homeView(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("user") == null){
            return "redirect:403";
        }
        return "home.jsp";
    }
}
