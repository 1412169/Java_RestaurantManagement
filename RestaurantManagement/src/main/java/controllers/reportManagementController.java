/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DAO.branchDAO;
import DAO.unexpectedCostDAO;
import DAO.customerDAO;
import DAO.dateTimeDAO;
import DAO.dishDAO;
import DAO.dishDirectoryDAO;
import DAO.orderBillDAO;
import DAO.orderDetailDAO;
import Entity.Branch;
import Entity.DishDirectory;
import Entity.OrderBill;
import Entity.UnexpectedCost;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author USER
 */
@Controller
public class reportManagementController {

//    @Autowired

    @RequestMapping(value = "/general-report", method = RequestMethod.GET)
    public String generalReportView(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("user") == null) {
            return "redirect:403";
        }
        return "generalReport.jsp";
    }

    @RequestMapping(value = "/revenue-report", method = RequestMethod.GET)
    public String revenueReportView(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("user") == null) {
            return "redirect:403";
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String string = Integer.toString(month) + "/" + Integer.toString(day) + "/" + Integer.toString(year);
        List<Object[]> revenueReportMonthly = orderDetailDAO.getRevenueMonthly(0, year);
        List<Object[]> numOrderMonthly = orderBillDAO.getTotalBillMonthly(0, year);

        List<Object[]> revenueReportYear = orderDetailDAO.getRevenueYear(0, year);
        List<Object[]> revenueReportDate = orderDetailDAO.getRevenueDate(0, date);
        List<Object[]> revenueReportMonth = orderDetailDAO.getRevenueMonth(0, month, year);

        DecimalFormat formatter = new DecimalFormat("#,###");
//        String revenueYearString = String.format("%.0f", new BigDecimal(Double.parseDouble(revenueReportYear.toString().substring(1, revenueReportYear.toString().length() - 1))));
        String revenueYearString = revenueReportYear.toString().substring(1, revenueReportYear.toString().length() - 1);
        String revenueMonthString = revenueReportMonth.toString().substring(1, revenueReportMonth.toString().length() - 1);
        String revenueDateString = revenueReportDate.toString().substring(1, revenueReportDate.toString().length() - 1);

        if (revenueYearString.equalsIgnoreCase("null")) {
            revenueYearString = "0";
        } else {
            revenueYearString = formatter.format(Double.parseDouble(revenueReportYear.toString().substring(1, revenueReportYear.toString().length() - 1)));
        }
        if (revenueMonthString.equalsIgnoreCase("null")) {
            revenueMonthString = "0";
        } else {
            revenueMonthString = formatter.format(Double.parseDouble(revenueReportMonth.toString().substring(1, revenueReportMonth.toString().length() - 1)));
        }
        if (revenueDateString.equalsIgnoreCase("null")) {
            revenueDateString = "0";
        } else {
            revenueDateString = formatter.format(Double.parseDouble(revenueReportDate.toString().substring(1, revenueReportDate.toString().length() - 1)));
        }

        List<Object[]> numOrderYear = orderBillDAO.getTotalBillYear(0, year);
        List<Object[]> numOrderMonth = orderBillDAO.getTotalBillMonth(0, month, year);
        List<Object[]> numOrderDate = orderBillDAO.getTotalBillDate(0, date);
        List<Object[]> numOrderTypeYear = orderBillDAO.getTotalBillOrderTypeYear(0, year);
        List<Object[]> numOrderTypeMonth = orderBillDAO.getTotalBillOrderTypeMonth(0, month, year);
        List<Object[]> numOrderTypeDate = orderBillDAO.getTotalBillOrderTypeDate(0, date);
        List<Object[]> monthlyRevenue = new ArrayList();
        List<Object[]> monthlyOrder = new ArrayList();
        for (int i = 1; i <= 12; i++) {
            Object[] tempRevenue = new Object[2];
            Object[] tempOrder = new Object[2];
            tempRevenue[0] = i;
            tempRevenue[1] = (double) (0);
            tempOrder[0] = i;
            tempOrder[1] = (double) (0);
            monthlyRevenue.add(tempRevenue);
            monthlyOrder.add(tempOrder);
        }

        for (int i = 1; i <= 12; i++) {
            for (Object[] obj : revenueReportMonthly) {
                if ((int) obj[0] == i) {
                    monthlyRevenue.get(i - 1)[1] = obj[1];
                }
            }
            for (Object[] obj : numOrderMonthly) {
                if ((int) obj[0] == i) {
                    monthlyOrder.get(i - 1)[1] = obj[1];
                }
            }
        }
        List<Branch> branchList = branchDAO.getBranchList();
        model.addAttribute("branchList", branchList);
        model.addAttribute("branchId", 0);
        model.addAttribute("monthlyRevenue", monthlyRevenue);
        model.addAttribute("monthlyOrder", monthlyOrder);
        model.addAttribute("revenueReportYear", revenueYearString);
        model.addAttribute("revenueReportMonth", revenueMonthString);
        model.addAttribute("revenueReportDate", revenueDateString);
        model.addAttribute("numOrderYear", numOrderYear);
        model.addAttribute("numOrderMonth", numOrderMonth);
        model.addAttribute("numOrderDate", numOrderDate);
        model.addAttribute("numOrderTypeYear", numOrderTypeYear);
        model.addAttribute("numOrderTypeMonth", numOrderTypeMonth);
        model.addAttribute("numOrderTypeDate", numOrderTypeDate);
        model.addAttribute("string", string);
        model.addAttribute("day", day);
        model.addAttribute("month", month);
        model.addAttribute("year", year);
        return "revenueReport.jsp";
    }

    @RequestMapping(value = "/revenue-report", method = RequestMethod.POST)
    public String revenueReportView(HttpServletResponse response, HttpServletRequest request, Model model) throws ParseException {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("user") == null) {
            return "redirect:403";
        }
        int branchId = Integer.parseInt(request.getParameterValues("branch")[0]);
        String string = request.getParameterValues("date")[0];
        String[] parts = string.split("/");
        int month = Integer.parseInt(parts[0]);
        int day = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        List<Object[]> revenueReportMonthly = orderDetailDAO.getRevenueMonthly(branchId, year);
        List<Object[]> numOrderMonthly = orderBillDAO.getTotalBillMonthly(branchId, year);
        List<Object[]> revenueReportYear = orderDetailDAO.getRevenueYear(branchId, year);
        List<Object[]> revenueReportDate = orderDetailDAO.getRevenueDayMonthYear(branchId, day, month, year);
        List<Object[]> revenueReportMonth = orderDetailDAO.getRevenueMonth(branchId, month, year);
        List<Object[]> numOrderYear = orderBillDAO.getTotalBillYear(branchId, year);
        List<Object[]> numOrderMonth = orderBillDAO.getTotalBillMonth(branchId, month, year);
        List<Object[]> numOrderDate = orderBillDAO.getTotalBillDayMonthYear(branchId, day, month, year);
        List<Object[]> numOrderTypeYear = orderBillDAO.getTotalBillOrderTypeYear(branchId, year);
        List<Object[]> numOrderTypeMonth = orderBillDAO.getTotalBillOrderTypeMonth(branchId, month, year);
        List<Object[]> numOrderTypeDate = orderBillDAO.getTotalBillOrderTypeDayMonthYear(branchId, day, month, year);
        List<Object[]> monthlyRevenue = new ArrayList();
        List<Object[]> monthlyOrder = new ArrayList();
        for (int i = 1; i <= 12; i++) {
            Object[] tempRevenue = new Object[2];
            Object[] tempOrder = new Object[2];
            tempRevenue[0] = i;
            tempRevenue[1] = (double) (0);
            tempOrder[0] = i;
            tempOrder[1] = (double) (0);
            monthlyRevenue.add(tempRevenue);
            monthlyOrder.add(tempOrder);
        }

        for (int i = 1; i <= 12; i++) {
            for (Object[] obj : revenueReportMonthly) {
                if ((int) obj[0] == i) {
                    monthlyRevenue.get(i - 1)[1] = obj[1];
                }
            }
            for (Object[] obj : numOrderMonthly) {
                if ((int) obj[0] == i) {
                    monthlyOrder.get(i - 1)[1] = obj[1];
                }
            }
        }
        DecimalFormat formatter = new DecimalFormat("#,###");
        String revenueYearString = revenueReportYear.toString().substring(1, revenueReportYear.toString().length() - 1);
        String revenueMonthString = revenueReportMonth.toString().substring(1, revenueReportMonth.toString().length() - 1);
        String revenueDateString = revenueReportDate.toString().substring(1, revenueReportDate.toString().length() - 1);

        if (revenueYearString.equalsIgnoreCase("null")) {
            revenueYearString = "0";
        } else {
            revenueYearString = formatter.format(Double.parseDouble(revenueReportYear.toString().substring(1, revenueReportYear.toString().length() - 1)));
        }
        if (revenueMonthString.equalsIgnoreCase("null")) {
            revenueMonthString = "0";
        } else {
            revenueMonthString = formatter.format(Double.parseDouble(revenueReportMonth.toString().substring(1, revenueReportMonth.toString().length() - 1)));
        }
        if (revenueDateString.equalsIgnoreCase("null")) {
            revenueDateString = "0";
        } else {
            revenueDateString = formatter.format(Double.parseDouble(revenueReportDate.toString().substring(1, revenueReportDate.toString().length() - 1)));
        }
        List<Branch> branchList = branchDAO.getBranchList();
        model.addAttribute("branchList", branchList);
        model.addAttribute("branchId", branchId);
        model.addAttribute("monthlyRevenue", monthlyRevenue);
        model.addAttribute("monthlyOrder", monthlyOrder);
        model.addAttribute("revenueReportYear", revenueYearString);
        model.addAttribute("revenueReportMonth", revenueMonthString);
        model.addAttribute("revenueReportDate", revenueDateString);
        model.addAttribute("numOrderYear", numOrderYear);
        model.addAttribute("numOrderMonth", numOrderMonth);
        model.addAttribute("numOrderDate", numOrderDate);
        model.addAttribute("numOrderTypeYear", numOrderTypeYear);
        model.addAttribute("numOrderTypeMonth", numOrderTypeMonth);
        model.addAttribute("numOrderTypeDate", numOrderTypeDate);
        model.addAttribute("string", string);
        model.addAttribute("day", day);
        model.addAttribute("month", month);
        model.addAttribute("year", year);
        return "revenueReport.jsp";
    }

//    @RequestMapping(value = "/report-revenue/branch/{branchId}", method = RequestMethod.GET)
//    public String revenueBranchDetail(@PathVariable("branchId") int branchId, HttpServletRequest request, Model model) {
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = new Date();
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//        int year = cal.get(Calendar.YEAR);
//        int month = cal.get(Calendar.MONTH) + 1;
//        int day = cal.get(Calendar.DAY_OF_MONTH);
//        List<Object[]> revenueReportMonthly = orderDetailDAO.getRevenueMonthly(branchId, year);
//        List<Object[]> revenueReportYear = orderDetailDAO.getRevenueYear(branchId, year);
//        List<Object[]> revenueReportDate = orderDetailDAO.getRevenueDate(branchId, date);
//        List<Object[]> revenueReportMonth = orderDetailDAO.getRevenueMonth(branchId, month);
//
//        List<Object[]> numOrderMonthly = orderBillDAO.getTotalBillMonthly(branchId, year);
//        List<Object[]> numOrderYear = orderBillDAO.getTotalBillYear(branchId, year);
//        List<Object[]> numOrderMonth = orderBillDAO.getTotalBillMonth(branchId, month);
//        List<Object[]> numOrderDate = orderBillDAO.getTotalBillDate(branchId, date);
//        List<Object[]> numOrderTypeYear = orderBillDAO.getTotalBillOrderTypeYear(branchId, year);
//        List<Object[]> numOrderTypeMonth = orderBillDAO.getTotalBillOrderTypeMonth(branchId, month);
//        List<Object[]> numOrderTypeDate = orderBillDAO.getTotalBillOrderTypeDate(branchId, date);
//        List<Object[]> monthlyRevenueBranch = new ArrayList();
//        List<Object[]> monthlyOrderBranch = new ArrayList();
//        for (int i = 1; i <= 12; i++) {
//            Object[] tempRevenue = new Object[2];
//            Object[] tempOrder = new Object[2];
//            tempRevenue[0] = i;
//            tempRevenue[1] = (double) (0);
//            tempOrder[0] = i;
//            tempOrder[1] = (double) (0);
//            monthlyRevenueBranch.add(tempRevenue);
//            monthlyOrderBranch.add(tempOrder);;
//        }
//        for (int i = 1; i <= 12; i++) {
//            for (Object[] obj : revenueReportMonthly) {
//                if ((int) obj[0] == i) {
//                    monthlyRevenueBranch.get(i - 1)[1] = obj[1];
//                }
//            }
//            for (Object[] obj : numOrderMonthly) {
//                if ((int) obj[0] == i) {
//                    monthlyOrderBranch.get(i - 1)[1] = obj[1];
//                }
//            }
//        }
//        model.addAttribute("monthlyOrderBranch", monthlyOrderBranch);
//        model.addAttribute("monthlyRevenueBranch", monthlyRevenueBranch);
//        model.addAttribute("revenueReportYear", revenueReportYear);
//        model.addAttribute("revenueReportMonth", revenueReportMonth);
//        model.addAttribute("revenueReportDate", revenueReportDate);
//
//        model.addAttribute("numOrderYear", numOrderYear);
//        model.addAttribute("numOrderMonth", numOrderMonth);
//        model.addAttribute("numOrderDate", numOrderDate);
//        model.addAttribute("numOrderTypeYear", numOrderTypeYear);
//        model.addAttribute("numOrderTypeMonth", numOrderTypeMonth);
//        model.addAttribute("numOrderTypeDate", numOrderTypeDate);
//        return "reportRevenueBranch.jsp";
//    }
    @RequestMapping(value = "/dish-report", method = RequestMethod.GET)
    public String dishReportView(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("user") == null) {
            return "redirect:403";
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        List<DishDirectory> dishDirectoryList = dishDirectoryDAO.getDishDirectoryList();
        List<Object[]> dishList = dishDAO.getCountDish(0, year, month);
        List<Object[]> yearList = dateTimeDAO.getYear();
        List<Branch> branchList = branchDAO.getBranchList();
        model.addAttribute("branchList", branchList);
        model.addAttribute("dishDirectoryList", dishDirectoryList);
        model.addAttribute("dishList", dishList);
        model.addAttribute("yearList", yearList);
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("branchId", 0);
        return "dishReport.jsp";
    }

    @RequestMapping(value = "/dish-report", method = RequestMethod.POST)
    public String dishReportView(HttpServletRequest request, Model model, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("user") == null) {
            return "redirect:403";
        }
        int branchId = Integer.parseInt(request.getParameterValues("branch")[0]);
        int year = Integer.parseInt(request.getParameterValues("year")[0]);
        int month = Integer.parseInt(request.getParameterValues("month")[0]);

        List<DishDirectory> dishDirectoryList = dishDirectoryDAO.getDishDirectoryList();
        List<Object[]> dishList = dishDAO.getCountDish(branchId, year, month);
        List<Object[]> yearList = dateTimeDAO.getYear();
        List<Branch> branchList = branchDAO.getBranchList();

        model.addAttribute("branchList", branchList);
        model.addAttribute("dishDirectoryList", dishDirectoryList);
        model.addAttribute("dishList", dishList);
        model.addAttribute("yearList", yearList);
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("branchId", branchId);
        return "dishReport.jsp";
    }

    @RequestMapping(value = "/customer-report", method = RequestMethod.GET)
    public String customerReportView(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("user") == null) {
            return "redirect:403";
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        List<Object[]> yearList = dateTimeDAO.getYear();
        List<Object[]> customerBuy = customerDAO.getCustomerBuy(year, month);
        List<Object[]> customerMontly = customerDAO.getCustomerMonthly(year);
        List<Object[]> monthlyCustomer = new ArrayList();
        for (int i = 1; i <= 12; i++) {
            Object[] temp = new Object[2];
            temp[0] = i;
            temp[1] = (double) (0);
            monthlyCustomer.add(temp);;
        }
        for (int i = 1; i <= 12; i++) {
            for (Object[] obj : customerMontly) {
                if ((int) obj[0] == i) {
                    monthlyCustomer.get(i - 1)[1] = obj[1];
                }
            }
        }
        model.addAttribute("monthlyCustomer", monthlyCustomer);
        model.addAttribute("yearList", yearList);
        model.addAttribute("customerBuy", customerBuy);
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        return "customerReport.jsp";
    }

    @RequestMapping(value = "/customer-report", method = RequestMethod.POST)
    public String customerReportView(HttpServletResponse response, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("user") == null) {
            return "redirect:403";
        }
        int year = Integer.parseInt(request.getParameterValues("year")[0]);
        int month = Integer.parseInt(request.getParameterValues("month")[0]);
        List<Object[]> yearList = dateTimeDAO.getYear();
        List<Object[]> customerBuy = customerDAO.getCustomerBuy(year, month);
        List<Object[]> customerMontly = customerDAO.getCustomerMonthly(year);
        List<Object[]> monthlyCustomer = new ArrayList();
        for (int i = 1; i <= 12; i++) {
            Object[] temp = new Object[2];
            temp[0] = i;
            temp[1] = (double) (0);
            monthlyCustomer.add(temp);;
        }
        for (int i = 1; i <= 12; i++) {
            for (Object[] obj : customerMontly) {
                if ((int) obj[0] == i) {
                    monthlyCustomer.get(i - 1)[1] = obj[1];
                }
            }
        }
        model.addAttribute("monthlyCustomer", monthlyCustomer);
        model.addAttribute("yearList", yearList);
        model.addAttribute("customerBuy", customerBuy);
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        return "customerReport.jsp";
    }

    @RequestMapping(value = "/cost-report", method = RequestMethod.GET)
    public String costReportView(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("user") == null) {
            return "redirect:403";
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String string = Integer.toString(month) + "/" + Integer.toString(day) + "/" + Integer.toString(year);

        List<Branch> branchList = branchDAO.getBranchList();
        List<Object[]> costMonthly = unexpectedCostDAO.getCostMonthly(0, year);
        List<Object[]> costYear = unexpectedCostDAO.getCostYear(0, year);
        List<Object[]> costDate = unexpectedCostDAO.getCostDate(0, date);
        List<Object[]> costMonth = unexpectedCostDAO.getCostMonth(0, month, year);
        List<UnexpectedCost> costList = unexpectedCostDAO.getCostList(0);
        List<Object[]> monthlyCost = new ArrayList();
        for (int i = 1; i <= 12; i++) {
            Object[] temp = new Object[2];
            temp[0] = i;
            temp[1] = (double) (0);
            monthlyCost.add(temp);
        }
        for (int i = 1; i <= 12; i++) {
            for (Object[] obj : costMonthly) {
                if ((int) obj[0] == i) {
                    monthlyCost.get(i - 1)[1] = obj[1];
                }
            }
        }

        DecimalFormat formatter = new DecimalFormat("#,###");
        String costYearString = costYear.toString().substring(1, costYear.toString().length() - 1);
        String costMonthString = costMonth.toString().substring(1, costMonth.toString().length() - 1);
        String costDateString = costDate.toString().substring(1, costDate.toString().length() - 1);

        if (costYearString.equalsIgnoreCase("null")) {
            costYearString = "0";
        } else {
            costYearString = formatter.format(Double.parseDouble(costYear.toString().substring(1, costYear.toString().length() - 1)));
        }
        if (costMonthString.equalsIgnoreCase("null")) {
            costMonthString = "0";
        } else {
            costMonthString = formatter.format(Double.parseDouble(costMonth.toString().substring(1, costMonth.toString().length() - 1)));
        }
        if (costDateString.equalsIgnoreCase("null")) {
            costDateString = "0";
        } else {
            costDateString = formatter.format(Double.parseDouble(costDate.toString().substring(1, costDate.toString().length() - 1)));
        }

        model.addAttribute("monthlyCost", monthlyCost);
        model.addAttribute("costYear", costYearString);
        model.addAttribute("costDate", costDateString);
        model.addAttribute("costMonth", costMonthString);
        model.addAttribute("branchList", branchList);
        model.addAttribute("costList", costList);

        model.addAttribute("branchId", 0);
        model.addAttribute("day", day);
        model.addAttribute("month", month);
        model.addAttribute("year", year);
        model.addAttribute("string", string);
        return "costReport.jsp";
    }

    @RequestMapping(value = "/cost-report", method = RequestMethod.POST)
    public String costReportView(HttpServletResponse response,HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("user") == null) {
            return "redirect:403";
        }
        int branchId = Integer.parseInt(request.getParameterValues("branch")[0]);
        String string = request.getParameterValues("date")[0];
        String[] parts = string.split("/");
        int month = Integer.parseInt(parts[0]);
        int day = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        List<Branch> branchList = branchDAO.getBranchList();
        List<Object[]> costMonthly = unexpectedCostDAO.getCostMonthly(branchId, year);
        List<Object[]> costYear = unexpectedCostDAO.getCostYear(branchId, year);
        List<Object[]> costDate = unexpectedCostDAO.getCostDayMonthYear(branchId, day, month, year);
        List<Object[]> costMonth = unexpectedCostDAO.getCostMonth(branchId, month, year);
        List<UnexpectedCost> costList = unexpectedCostDAO.getCostList(branchId);
        List<Object[]> monthlyCost = new ArrayList();
        for (int i = 1; i <= 12; i++) {
            Object[] temp = new Object[2];
            temp[0] = i;
            temp[1] = (double) (0);
            monthlyCost.add(temp);
        }
        for (int i = 1; i <= 12; i++) {
            for (Object[] obj : costMonthly) {
                if ((int) obj[0] == i) {
                    monthlyCost.get(i - 1)[1] = obj[1];
                }
            }
        }

        DecimalFormat formatter = new DecimalFormat("#,###");
        String costYearString = costYear.toString().substring(1, costYear.toString().length() - 1);
        String costMonthString = costMonth.toString().substring(1, costMonth.toString().length() - 1);
        String costDateString = costDate.toString().substring(1, costDate.toString().length() - 1);

        if (costYearString.equalsIgnoreCase("null")) {
            costYearString = "0";
        } else {
            costYearString = formatter.format(Double.parseDouble(costYear.toString().substring(1, costYear.toString().length() - 1)));
        }
        if (costMonthString.equalsIgnoreCase("null")) {
            costMonthString = "0";
        } else {
            costMonthString = formatter.format(Double.parseDouble(costMonth.toString().substring(1, costMonth.toString().length() - 1)));
        }
        if (costDateString.equalsIgnoreCase("null")) {
            costDateString = "0";
        } else {
            costDateString = formatter.format(Double.parseDouble(costDate.toString().substring(1, costDate.toString().length() - 1)));
        }

        model.addAttribute("monthlyCost", monthlyCost);
        model.addAttribute("costYear", costYearString);
        model.addAttribute("costDate", costDateString);
        model.addAttribute("costMonth", costMonthString);
        model.addAttribute("branchList", branchList);
        model.addAttribute("costList", costList);

        model.addAttribute("branchId", 0);
        model.addAttribute("day", day);
        model.addAttribute("month", month);
        model.addAttribute("year", year);
        model.addAttribute("string", string);
        return "costReport.jsp";
    }
}
