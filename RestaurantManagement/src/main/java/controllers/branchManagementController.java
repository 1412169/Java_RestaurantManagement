/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DAO.branchDAO;
import DAO.branchDirectoryDAO;
import Entity.Branch;
import Entity.BranchDirectory;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
public class branchManagementController {
    
    @RequestMapping(value="/branch-list", method=RequestMethod.GET)
    public ModelAndView branchListView(HttpServletRequest request,Model model) {
        List<BranchDirectory> branchDirectoryList = branchDirectoryDAO.getBranchDirectoryList();
        model.addAttribute("branchDirectoryList",branchDirectoryList);
        List<Branch> branchList = branchDAO.getBranchList();
        model.addAttribute("branchList",branchList);
        return new ModelAndView("branchList.jsp");
    }
    
    @RequestMapping(value="/new-branch", method=RequestMethod.GET)
    public ModelAndView createBranchView(HttpServletRequest request) {
        return new ModelAndView("newBranch.jsp");
    }
    
    @RequestMapping(value="/new-branch", method=RequestMethod.POST)
    public ModelAndView createNewBranch(HttpServletRequest request) {
        return new ModelAndView("newBranch.jsp");
    }
    
}
