/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import DAO.tableDAO;
import Entity.BranchTable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author hotha
 */
public class tableController {
    @RequestMapping(value = "/table", method = RequestMethod.POST)
    public ModelAndView tableView(HttpServletRequest request, Model model,@RequestParam(value = "branchId", required = true) String branchId) {
        List<BranchTable> tableList = tableDAO.getBranchTableByBranchId(Integer.parseInt(branchId));
        model.addAttribute("tableList", tableList);
        return new ModelAndView("table.jsp");
    }
    
//    @RequestMapping(value = "/table", method = RequestMethod.POST)
//    public ModelAndView tableView(HttpServletRequest request, Model model, @RequestParam(value = "branchId", required = true) String branchId) {
//        List<BranchTable> tableList = tableDAO.getBranchTableByBranchId(Integer.parseInt(branchId));
//        model.addAttribute("tableList", tableList);
//        return new ModelAndView("table.jsp");
//    }
}


