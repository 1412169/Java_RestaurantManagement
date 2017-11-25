/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DAO.dishDAO;
import DAO.dishDirectoryDAO;
import Entity.Dish;
import Entity.DishDirectory;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author USER
 */

@Controller
public class foodManagementController {
    @RequestMapping(value="/food-catalog", method=RequestMethod.GET)
    public String foodCatalogView(HttpServletRequest request, HttpServletResponse response, Model model) {
        System.out.print("Hello");
        List<DishDirectory> dishDirectoryList = dishDirectoryDAO.getDishDirectoryList();
        model.addAttribute("dishDirectoryList",dishDirectoryList);
        return "foodCatalog.jsp";
    }
    
    @RequestMapping(value="/food-list", method=RequestMethod.GET)
    public ModelAndView foodListView(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<Dish> dishList = dishDAO.getDishList();
        model.addAttribute("dishList",dishList);
        return new ModelAndView("foodList.jsp");
    }
}
