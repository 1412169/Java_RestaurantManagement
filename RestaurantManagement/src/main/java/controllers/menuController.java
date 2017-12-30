/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DAO.branchMenuDAO;
import DAO.dishDAO;
import DAO.dishDirectoryDAO;
import DAO.menuDAO;
import DAO.menuDetailDAO;
import Entity.BranchMenu;
import Entity.Dish;
import Entity.DishDirectory;
import Entity.Menu;
import Entity.MenuDetail;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author hotha
 */
@Controller
public class menuController {

    @RequestMapping(value = "/menu-list", method = RequestMethod.GET)
    public ModelAndView menuListView(HttpServletRequest request, Model model) {
        List<Menu> menuList = menuDAO.getMenuList();
        model.addAttribute("menuList", menuList);
        return new ModelAndView("menuList.jsp");
    }

    @RequestMapping(value = "/new-menu", method = RequestMethod.GET)
    public ModelAndView createNewMenu(HttpServletRequest request, Model model) {
        List<DishDirectory> dishDirectoryList = dishDirectoryDAO.getDishDirectoryList();
        model.addAttribute("dishDirectoryList", dishDirectoryList);
        List<Dish> dishList = dishDAO.getDishList();
        model.addAttribute("dishList", dishList);
        return new ModelAndView("newMenu.jsp");
    }

    @RequestMapping(value = "/new-menu", method = RequestMethod.POST)
    public String createNewMenu(HttpServletRequest request, HttpServletResponse response) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String name = request.getParameterValues("name")[0];
        String[] dishList = request.getParameterValues("dishList");
        Menu menu = new Menu();
        menu.setMenuName(name);
        menu.setCreatedAt(date);
        int menuId = menuDAO.createMenu(menu);
        if (menuId > 0) {
            for (String d : dishList) {
                MenuDetail md = new MenuDetail();
                md.setIdDish(Integer.parseInt(d));
                md.setMenuId(menuId);
                md.setCreatedAt(date);
                menuDetailDAO.createMenuDetail(md);
            }
            return "redirect:menu-list";
        }
        return "redirect:home";

    }

    @RequestMapping(value = "/menu/{menuId}/add-dish", method = RequestMethod.GET)
    public ModelAndView editMenu(@PathVariable("menuId") int menuId, HttpServletRequest request, Model model) {
        List<DishDirectory> dishDirectoryList = dishDirectoryDAO.getDishDirectoryList();
        Menu menu = menuDAO.getMenuInfo(menuId);
        List<MenuDetail> menuDetailList = menuDetailDAO.getMenuDetailListByMenuId(menuId);
        List<Dish> dishList = dishDAO.getDishList();
        List<Integer> menuDetailListId = new ArrayList<Integer>();
        List<Dish> chosenDishList = new ArrayList<Dish>();
        List<Dish> notChosenDishList = new ArrayList<Dish>();
        for (MenuDetail md : menuDetailList) {
            menuDetailListId.add(md.getIdDish());
        }
        for (Dish d : dishList) {
            if (menuDetailListId.contains(d.getId())) {
                chosenDishList.add(d);
            } else {
                notChosenDishList.add(d);
            }
        }
        model.addAttribute("menu", menu);
        model.addAttribute("dishDirectoryList", dishDirectoryList);
        model.addAttribute("chosenDishList", chosenDishList);
        model.addAttribute("notChosenDishList", notChosenDishList);
        return new ModelAndView("addMenuDish.jsp");
    }

    @RequestMapping(value = "/menu/{menuId}/add-dish", method = RequestMethod.POST)
    public String editMenu(@PathVariable("menuId") int menuId, HttpServletRequest request, HttpServletResponse response) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String[] dishList = request.getParameterValues("dishList");
        for (String d : dishList) {
            MenuDetail md = new MenuDetail();
            md.setIdDish(Integer.parseInt(d));
            md.setMenuId(menuId);
            md.setCreatedAt(date);
            menuDetailDAO.createMenuDetail(md);
        }
        return "redirect:../../detail-menu/{menuId}";
    }

    @RequestMapping(value = "/detail-menu/{menuId}", method = RequestMethod.GET)
    public ModelAndView detailMenu(@PathVariable("menuId") int menuId, HttpServletRequest request, Model model) {
        Menu menu = menuDAO.getMenuInfo(menuId);
        List<MenuDetail> menuDetailList = menuDetailDAO.getMenuDetailListByMenuId(menuId);
        List<Dish> dishList = dishDAO.getDishList();
        model.addAttribute("menu", menu);
        model.addAttribute("menuDetailList", menuDetailList);
        model.addAttribute("dishList", dishList);
        return new ModelAndView("detailMenu.jsp");
    }

    @RequestMapping(value = "/delete-menu", method = RequestMethod.GET)
    public String deleteDish(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "menuId", required = true) String menuId, Model model) {
        Menu menu = menuDAO.getMenuInfo(Integer.parseInt(menuId));
        menu.setDelFlag(1);
        boolean result = menuDAO.deleteMenu(menu);
        if (result == true) {
            return "redirect:menu-list";
        } else {
            return "redirect:home";
        }
    }

    @RequestMapping(value = "menu/{menuId}/delete-menu-detail", method = RequestMethod.GET)
    public String deleteMenuDetail(@PathVariable("menuId") int menuId, HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "menuDetailId", required = true) String menuDetailId, Model model) {
        MenuDetail menuDetail = menuDetailDAO.getMenuDetailInfo(Integer.parseInt(menuDetailId));
        menuDetail.setDelFlag(1);
        boolean result = menuDetailDAO.deleteMenuDetail(menuDetail);
        if (result == true) {
            return "redirect:../../detail-menu/{menuId}";
        } else {
            return "redirect:home";
        }
    }
}
