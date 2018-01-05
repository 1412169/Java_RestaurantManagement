/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DAO.branchDAO;
import DAO.dishDAO;
import DAO.dishDirectoryDAO;
import Entity.Branch;
import Entity.Dish;
import Entity.DishDirectory;
import static java.lang.Double.parseDouble;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

    @RequestMapping(value = "/food-catalog", method = RequestMethod.GET)
    public String foodCatalogView(HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("user") == null) {
            return "redirect:403";
        }
        List<DishDirectory> dishDirectoryList = dishDirectoryDAO.getDishDirectoryList();
        model.addAttribute("dishDirectoryList", dishDirectoryList);
        List<Dish> dishList = dishDAO.getDishList();
        model.addAttribute("dishList", dishList);
        return "foodCatalog.jsp";
    }

    @RequestMapping(value = "/new-dish", method = RequestMethod.GET)
    public String foodListView(HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("user") == null) {
            return "redirect:403";
        }
        List<DishDirectory> dishDirectoryList = dishDirectoryDAO.getDishDirectoryList();
        model.addAttribute("dishDirectoryList", dishDirectoryList);
        List<Branch> branchList = branchDAO.getBranchList();
        model.addAttribute("branchList", branchList);
        return "newDish.jsp";
    }

    @RequestMapping(value = "/new-dish", method = RequestMethod.POST)
    public String createNewDish(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("user") == null) {
            return "redirect:403";
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String name = request.getParameterValues("name")[0];
        String price = request.getParameterValues("price")[0];
        String description = request.getParameterValues("description")[0];
        String img = request.getParameterValues("img")[0];
        String dishDirectory = request.getParameterValues("dishDirectory")[0];
        Dish dish = new Dish();
        dish.setName(name);
        dish.setPrice(ParseDouble(price));
        dish.setDescription(description);
        dish.setImgUrl(img);
        dish.setDishDirectoryId(Integer.parseInt(dishDirectory));
        dish.setCreatedAt(date);
        dish.setDelFlag(0);
        boolean result = dishDAO.createDish(dish);
        if (result == true) {
            System.out.print("Hello1");
            return "redirect:food-catalog";
        } else {
            System.out.print("out");
            return "redirect:home";
        }
    }

    double ParseDouble(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                return Double.parseDouble(strNumber);
            } catch (Exception e) {
                return -1;   // or some value to mark this field is wrong. or make a function validates field first ...
            }
        } else {
            return 0;
        }
    }

    @RequestMapping(value = "/edit-dish/{dishId}", method = RequestMethod.GET)
    public String editDish(HttpServletRequest request, @PathVariable("dishId") int dishId, Model model, ModelMap mm) {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("user") == null) {
            return "redirect:403";
        }
        Dish dish = dishDAO.getDishInfo(dishId);
        List<DishDirectory> dishDirectoryList = dishDirectoryDAO.getDishDirectoryList();
        List<Branch> branchList = branchDAO.getBranchList();
        model.addAttribute("dishDirectoryList", dishDirectoryList);
        model.addAttribute("branchList", branchList);
        model.addAttribute("dish", dish);
        return "editDish.jsp";
    }

    @RequestMapping(value = "/edit-dish/{dishId}", method = RequestMethod.POST)
    public String editDish(@PathVariable("dishId") int dishId, HttpServletRequest request, HttpServletResponse response, Model model, ModelMap mm) {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("user") == null) {
            return "redirect:403";
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String name = request.getParameterValues("name")[0];
        String price = request.getParameterValues("price")[0];
        String description = request.getParameterValues("description")[0];
        String img = request.getParameterValues("img")[0];
        String dishDirectory = request.getParameterValues("dishDirectory")[0];
        Dish dish = dishDAO.getDishInfo(dishId);
        dish.setName(name);
        dish.setPrice(ParseDouble(price));
        dish.setDescription(description);
        dish.setImgUrl(img);
        dish.setDishDirectoryId(Integer.parseInt(dishDirectory));
        dish.setCreatedAt(date);
        dish.setDelFlag(0);
        boolean result = dishDAO.updateDish(dish);
        if (result == true) {
            System.out.print("Hello1");
            return "redirect:../food-catalog";
        } else {
            System.out.print("out");
            return "redirect:../food-catalog";
        }
    }

    @RequestMapping(value = "/delete-dish", method = RequestMethod.GET)
    public String deleteDish(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "dishId", required = true) String dishId, Model model) {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("user") == null) {
            return "redirect:403";
        }
        Dish dish = dishDAO.getDishInfo(Integer.parseInt(dishId));
        dish.setDelFlag(1);
        boolean result = dishDAO.deleteDish(dish);
        if (result == true) {
            return "redirect:food-catalog";
        } else {
            return "redirect:home";
        }
    }
}
