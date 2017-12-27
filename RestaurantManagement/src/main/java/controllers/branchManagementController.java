/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DAO.branchDAO;
import DAO.branchMenuDAO;
import DAO.branchTableDAO;
import DAO.dishDirectoryDAO;
import DAO.menuDAO;
import DAO.tableDAO;
import Entity.Branch;
import Entity.BranchMenu;
import Entity.BranchTable;
import Entity.DishDirectory;
import Entity.Menu;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
public class branchManagementController {

    @RequestMapping(value = "/branch-list", method = RequestMethod.GET)
    public ModelAndView branchListView(HttpServletRequest request, Model model) {
        List<Branch> branchList = branchDAO.getBranchList();
        model.addAttribute("branchList", branchList);
        return new ModelAndView("branchList.jsp");
    }

    @RequestMapping(value = "/new-branch", method = RequestMethod.GET)
    public ModelAndView createBranchView(HttpServletRequest request, Model model) {
        List<Menu> menuList = menuDAO.getMenuList();
        model.addAttribute("menuList", menuList);
        List<DishDirectory> dishDirectoryList = dishDirectoryDAO.getDishDirectoryList();
        model.addAttribute("dishDirectoryList", dishDirectoryList);
        return new ModelAndView("newBranch.jsp");
    }

    @RequestMapping(value = "/new-branch", method = RequestMethod.POST)
    public String createNewBranch(HttpServletRequest request) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String name = request.getParameterValues("name")[0];
        String phone = request.getParameterValues("phone")[0];
        String address = request.getParameterValues("address")[0];
        String province = request.getParameterValues("province")[0];
        int numTable = Integer.parseInt(request.getParameterValues("numberTable")[0]);
        String[] menu = request.getParameterValues("menu");
        Branch branch = new Branch();
        branch.setName(name);
        branch.setPhone(phone);
        branch.setAddress(address);
        branch.setProvince(province);
        branch.setNumTable(numTable);
        branch.setCreatedAt(date);
        branch.setDelFlag(0);
        int branchId = branchDAO.createBranch(branch);
        if (branchId > 0) {
            for (String m : menu) {
                BranchMenu bm = new BranchMenu();
                bm.setBranchId(branchId);
                bm.setMenuId(Integer.parseInt(m));
                bm.setCreatedAt(date);
                bm.setDelFlag(0);
                branchMenuDAO.createBranchMenu(bm);
            }
            for (int i = 1; i <= numTable; i++) {
                BranchTable bt = new BranchTable();
                bt.setBranchId(branchId);
                bt.setName("Table" + i);
                bt.setCreatedAt(date);
                bt.setStatus(0);
                bt.setDelFlag(0);
                branchTableDAO.createBranchTable(bt);
            }
            return "redirect:branch-list";
        } else {
            return "redirect:home";
        }
    }

    @RequestMapping(value = "/edit-branch/{branchId}", method = RequestMethod.GET)
    public String editBranch(@PathVariable("branchId") int branchId, Model model, ModelMap mm) {
        List<BranchMenu> branchMenuList = branchMenuDAO.getMenuListByBranchId(branchId);
        Branch branch = branchDAO.getBranchInfo(branchId);
        List<Menu> menuList = menuDAO.getMenuList();
        List<DishDirectory> dishDirectoryList = dishDirectoryDAO.getDishDirectoryList();
        List<Menu> checkedMenu = new ArrayList<Menu>();
        List<Menu> uncheckedMenu = new ArrayList<Menu>();
        List<Integer> menuInt = new ArrayList<Integer>();
        for (BranchMenu bm : branchMenuList) {
            menuInt.add(bm.getMenuId());
        }
        for (Menu m : menuList) {
            if (menuInt.contains(m.getId())) {
                checkedMenu.add(m);
            } else {
                uncheckedMenu.add(m);
            }
        }
        model.addAttribute("branch", branch);
        model.addAttribute("dishDirectoryList", dishDirectoryList);
        model.addAttribute("uncheckedMenu", uncheckedMenu);
        model.addAttribute("checkedMenu", checkedMenu);
        return "editBranch.jsp";
    }

    @RequestMapping(value = "/edit-branch/{branchId}", method = RequestMethod.POST)
    public String editBranch(@PathVariable("branchId") int branchId, HttpServletRequest request,Model model) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String name = request.getParameterValues("name")[0];
        String phone = request.getParameterValues("phone")[0];
        String address = request.getParameterValues("address")[0];
        String province = request.getParameterValues("province")[0];
        int numTable = Integer.parseInt(request.getParameterValues("numberTable")[0]);
        String[] menu = request.getParameterValues("menu");
        Branch branchEdit = branchDAO.getBranchInfo(branchId);
        branchEdit.setName(name);
        branchEdit.setPhone(phone);
        branchEdit.setAddress(address);
        branchEdit.setProvince(province);
        branchEdit.setNumTable(numTable);
        branchEdit.setCreatedAt(date);
        branchEdit.setDelFlag(0);
        boolean result = branchDAO.updateBranch(branchEdit);
        if (result == true) {
            List<BranchTable> branchTableList = branchTableDAO.getBranchTableListByBranchId(branchEdit.getId());
            List<BranchMenu> branchMenuList = branchMenuDAO.getMenuListByBranchId(branchEdit.getId());
            for (BranchTable bt : branchTableList) {
                branchTableDAO.deleteBranchTable(bt);
            }
            for (BranchMenu bm : branchMenuList) {
                branchMenuDAO.deleteBranchMenu(bm);
            }
            for (String m : menu) {
                BranchMenu bm = new BranchMenu();
                bm.setBranchId(branchId);
                bm.setMenuId(Integer.parseInt(m));
                bm.setCreatedAt(date);
                bm.setDelFlag(0);
                branchMenuDAO.createBranchMenu(bm);
            }
            for (int i = 1; i <= numTable; i++) {
                BranchTable bt = new BranchTable();
                bt.setBranchId(branchId);
                bt.setName("Table" + i);
                bt.setCreatedAt(date);
                bt.setStatus(0);
                bt.setDelFlag(0);
                branchTableDAO.createBranchTable(bt);
            }
            return "redirect:../branch-list";
        } else {
            return "redirect:home";
        }

    }

    @RequestMapping(value = "/delete-branch", method = RequestMethod.GET)
    public String editDish(HttpServletRequest request,
            @RequestParam(value = "branchId", required = true) String branchId, Model model
    ) {
        Branch branch = branchDAO.getBranchInfo(Integer.parseInt(branchId));
        branch.setDelFlag(1);
        boolean result = branchDAO.deleteBranch(branch);
        if (result == true) {
            return "redirect:branch-list";
        } else {
            return "redirect:home";
        }
    }

    @RequestMapping(value = "/table", method = RequestMethod.GET)
    public ModelAndView tableView(Model model,
            @RequestParam(value = "branchId", required = true) String branchId
    ) {
        List<BranchTable> tableList = tableDAO.getBranchTableByBranchId(Integer.parseInt(branchId));
        model.addAttribute("tableList", tableList);
        Branch branch = branchDAO.getBranchInfo(Integer.parseInt(branchId));
        model.addAttribute("branch", branch);
        return new ModelAndView("table.jsp");
    }

    @RequestMapping(value = "/table", method = RequestMethod.POST)
    public ModelAndView tableView(HttpServletRequest request, Model model,
             @RequestParam(value = "branchId", required = true) String branchId
    ) {
        List<BranchTable> tableList = tableDAO.getBranchTableByBranchId(Integer.parseInt(branchId));
        model.addAttribute("tableList", tableList);
        Branch branch = branchDAO.getBranchInfo(Integer.parseInt(branchId));
        model.addAttribute("branch", branch);
        return new ModelAndView("table.jsp");
    }

}
