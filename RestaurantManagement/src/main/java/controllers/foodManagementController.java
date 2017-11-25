/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DAO.branchDirectoryDAO;
import DAO.dishDAO;
import DAO.dishDirectoryDAO;
import Entity.BranchDirectory;
import Entity.Dish;
import Entity.DishDirectory;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.dialect.Dialect;
import org.hibernate.sql.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
        List<Dish> dishList = dishDAO.getDishList();
        model.addAttribute("dishList",dishList);
        return "foodCatalog.jsp";
    }
    
    @RequestMapping(value="/new-dish", method=RequestMethod.GET)
    public ModelAndView foodListView(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<DishDirectory> dishDirectoryList = dishDirectoryDAO.getDishDirectoryList();
        model.addAttribute("dishDirectoryList",dishDirectoryList);
        List<BranchDirectory> branchDirectoryList = branchDirectoryDAO.getBranchDirectoryList();
        model.addAttribute("branchDirectoryList",branchDirectoryList);
        return new ModelAndView("newDish.jsp");
    }
    
    @RequestMapping(value="/new-dish", method=RequestMethod.POST)
    public String createNewDish(HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute("dishDirectory") String dishDirectory, ModelMap mm){
        String name = request.getParameterValues("name")[0];
        String price = request.getParameterValues("price")[0];
        String description = request.getParameterValues("description")[0];
        String img = request.getParameterValues("img")[0];
        System.out.print(name);
        System.out.print(price);
        System.out.print(description);
        System.out.print(img);
        System.out.print(dishDirectory);
        String[] branchDirectory = request.getParameterValues("branchDirectory");
        mm.put ("branchDirectoryList",branchDirectory);
        return "redirect:food-catalog";
    }
}
