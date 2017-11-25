/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author USER
 */
@Controller
public class branchManagementController {
    @Autowired
    
    @RequestMapping(value="/branch-list", method=RequestMethod.GET)
    public ModelAndView branchListView(HttpServletRequest request) {
        return new ModelAndView("branchList.jsp");
    }
    
    @RequestMapping(value="/branch/new", method=RequestMethod.GET)
    public ModelAndView createBranchView(HttpServletRequest request) {
        return new ModelAndView("createBranch.jsp");
    }
    
    @RequestMapping(value="/branch-food-list", method=RequestMethod.GET)
    public ModelAndView branchFoodListView(HttpServletRequest request) {
        return new ModelAndView("branchFoodList.jsp");
    }
}
