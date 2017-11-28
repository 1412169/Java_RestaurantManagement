/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DAO.orderBillDAO;
import Entity.OrderBill;
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
public class reportManagementController {
    @Autowired
    
    @RequestMapping(value="/general-report", method=RequestMethod.GET)
    public ModelAndView generalReportView(HttpServletRequest request) {
        return new ModelAndView("generalReport.jsp");
    }
    
    @RequestMapping(value="/revenue-report", method=RequestMethod.GET)
    public ModelAndView revenueReportView(HttpServletRequest request) {
        return new ModelAndView("revenueReport.jsp");
    }
    
    @RequestMapping(value="/order-report", method=RequestMethod.GET)
    public ModelAndView orderReportView(HttpServletRequest request, Model model) {
        List<OrderBill> orderBillList = orderBillDAO.getOrderBillList();
        model.addAttribute("orderBillList",orderBillList);
        
        return new ModelAndView("orderReport.jsp");
    }
    
    @RequestMapping(value="/cost-report", method=RequestMethod.GET)
    public ModelAndView costReportView(HttpServletRequest request) {
        return new ModelAndView("costReport.jsp");
    }
    
    @RequestMapping(value="/dish-report", method=RequestMethod.GET)
    public ModelAndView dishReportView(HttpServletRequest request) {
        return new ModelAndView("dishReport.jsp");
    }
    
    @RequestMapping(value="/customer-report", method=RequestMethod.GET)
    public ModelAndView customerReportView(HttpServletRequest request) {
        return new ModelAndView("customerReport.jsp");
    }
}
