/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

//import hibernate_source.Product;
//import hibernate_source.ProductDAO;
//import hibernate_source.User;
//import hibernate_source.UserDAO;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

/**
 *
 * @author USER
 */
@Controller
public class homeController {
//    private User userDao = null;
    
    @Autowired
    
    @RequestMapping(value="/home", method=RequestMethod.GET)
    public ModelAndView homeView(HttpServletRequest request) {
        return new ModelAndView("home.jsp");
    }
    
//    @RequestMapping(value="/login", method=RequestMethod.POST)
//    public String login(HttpServletRequest request, Model model) {
//        //request.getParameterValues("username")[0];
//        String user = "";
//        String username = request.getParameterValues("username")[0];
//        userDao = UserDAO.getUserByUsername(username);
//        if (userDao != null) {
//            if (userDao.getPassword().compareTo(request.getParameterValues("password")[0]) == 0) {
//                user = userDao.getFullName();
//                if (userDao.getRole() == 1) {
//                    //return new ModelAndView("admindashboard.jsp", "fullname", user);
//                    //return "redirect:admin";
//                    return "redirect:admin/productlist";
//                }
//                //return new ModelAndView("userdashboard.jsp", "fullname", user);
//                //return "redirect:user";
//                return "redirect:user/profile";
//            }
//        }
//        model.addAttribute("message", "Invalid username or password!");
//        return "redirect:login";
//    }
//    
//    @RequestMapping(value="/admin", method=RequestMethod.GET)
//    public ModelAndView adminDashboardView(HttpServletRequest request) {
//        if (userDao == null) {
//            return new ModelAndView("error.jsp");
//        }
//        if (userDao.getRole() == 0) {
//            return new ModelAndView("error.jsp");
//        }
//        return new ModelAndView("admindashboard.jsp", "fullname", userDao.getFullName());
//    }
//    
//    @RequestMapping(value="/admin/productlist", method=RequestMethod.GET)
//    public ModelAndView adminProductListView(HttpServletRequest request) {
//        if (userDao == null) {
//            return new ModelAndView("error.jsp");
//        }
//        if (userDao.getRole() == 0) {
//            return new ModelAndView("error.jsp");
//        }
//        return new ModelAndView("adminproductlist.jsp", "productlist", ProductDAO.getListProduct());
//    }
//    
//    @RequestMapping(value="/admin/addProduct", method=RequestMethod.GET)
//    public ModelAndView addProductView(HttpServletRequest request) {
//        if (userDao == null) {
//            return new ModelAndView("error.jsp");
//        }
//        if (userDao.getRole() == 0) {
//            return new ModelAndView("error.jsp");
//        }
//        return new ModelAndView("addproduct.jsp");
//    }
//    
//    @RequestMapping(value="/admin/addProduct", method=RequestMethod.POST)
//    public String addProduct(HttpServletRequest request, Model model) {
//        if (userDao == null) {
//            return "error";
//        }
//        if (userDao.getRole() == 0) {
//            return "error";
//        }
//        Product p = new Product();
//        p.setId(request.getParameterValues("id")[0]);
//        p.setName(request.getParameterValues("name")[0]);
//        p.setCost(request.getParameterValues("cost")[0]);
//        p.setAmount(Integer.parseInt(request.getParameterValues("amount")[0]));
//        //p.setAmount(20);
//        p.setDescription(request.getParameterValues("description")[0]);
//        p.setImage("Not yet");
//        
//        ProductDAO.addProduct(p);
//        //return "redirect:productlist";
//        model.addAttribute("message", "Add " + p.getName() + " successfully");
//        return "redirect:addProduct";
//    }
//    
//    @RequestMapping(value="/admin/editProduct/{idProduct}", method=RequestMethod.GET)
//    public ModelAndView editProductView(HttpServletRequest request, @PathVariable String idProduct) {
//        if (userDao == null) {
//            return new ModelAndView("error.jsp");
//        }
//        if (userDao.getRole() == 0) {
//            return new ModelAndView("error.jsp");
//        }
//        Product p = ProductDAO.getProductById(idProduct);
//        return new ModelAndView("editproduct.jsp", "product", p);
//    }
//    
//    @RequestMapping(value="/admin/editProduct/{idProduct}", method=RequestMethod.POST)
//    public String editProduct(HttpServletRequest request, @PathVariable String idProduct, Model model) {
//        if (userDao == null) {
//            return "error";
//        }
//        if (userDao.getRole() == 0) {
//            return "error";
//        }
//        Product p = ProductDAO.getProductById(idProduct);
//        p.setName(request.getParameterValues("name")[0]);
//        p.setCost(request.getParameterValues("cost")[0]);
//        p.setAmount(Integer.parseInt(request.getParameterValues("amount")[0]));
//        p.setDescription(request.getParameterValues("description")[0]);
//        
//        ProductDAO.editProduct(p);
//        
//        model.addAttribute("message", "Edit product successfully");
//        return "redirect:../editProduct/" + p.getId();
//        //return new ModelAndView("editproduct.jsp", "product", p);
//        //String str = request.getParameterValues("username")[0];
//    }
//    
//    @RequestMapping(value="/admin/deleteProduct/{idProduct}", method=RequestMethod.GET)
//    public ModelAndView deleteProductView(HttpServletRequest request, @PathVariable String idProduct) {
//        if (userDao == null) {
//            return new ModelAndView("error.jsp");
//        }
//        if (userDao.getRole() == 0) {
//            return new ModelAndView("error.jsp");
//        }
//        Product p = ProductDAO.getProductById(idProduct);
//        return new ModelAndView("deleteproduct.jsp", "product", p);
//    }
//    
//    @RequestMapping(value="/admin/deleteProduct/{idProduct}/confirmed", method=RequestMethod.GET)
//    public String deleteProduct(HttpServletRequest request, @PathVariable String idProduct, Model model) {
//        if (userDao == null) {
//            return "error";
//        }
//        if (userDao.getRole() == 0) {
//            return "error";
//        }
//        ProductDAO.deleteProduct(idProduct);
//        model.addAttribute("message", "delete product successfully");
//        return "redirect:../../productlist";
//    }
//    
//    @RequestMapping(value="/admin/deleteSelectedProducts", method=RequestMethod.POST)
//    public ModelAndView deleteSeletedProductsView(HttpServletRequest request, @RequestParam("checkedProducts")String[] checkboxvalues) {
//        if (userDao == null) {
//            return new ModelAndView("error.jsp");
//        }
//        if (userDao.getRole() == 0) {
//            return new ModelAndView("error.jsp");
//        }
//        return new ModelAndView("deleteproduct.jsp", "selectedProducts", String.join(",", checkboxvalues));
//    }
//    
//    @RequestMapping(value="/admin/deleteSelectedProducts/{selectedProducts}/confirmed", method=RequestMethod.GET)
//    public String deleteSeletedProducts(HttpServletRequest request, @PathVariable String selectedProducts, Model model) {
//        //return new ModelAndView("deleteproduct.jsp", "selectedProducts", String.join(",", checkboxvalues));
//        if (userDao == null) {
//            return "error";
//        }
//        if (userDao.getRole() == 0) {
//            return "error";
//        }
//        String idSelectedProducts[] = selectedProducts.split(",");
//        for (int i = 0; i < idSelectedProducts.length; i++) {
//            ProductDAO.deleteProduct(idSelectedProducts[i]);
//        }
//        model.addAttribute("message", "delete product successfully");
//        return "redirect:../../productlist";
//    }
//    
//    @RequestMapping(value="/user", method=RequestMethod.GET)
//    public ModelAndView userDashboardView(HttpServletRequest request) {
//        if (userDao == null) {
//            return new ModelAndView("error.jsp");
//        }
//        if (userDao.getRole() == 1) {
//            return new ModelAndView("error.jsp");
//        }
//        return new ModelAndView("userdashboard.jsp", "fullname", userDao.getFullName());
//    }
//    
//    @RequestMapping(value="/user/profile", method=RequestMethod.GET)
//    public ModelAndView userProfileView(HttpServletRequest request) {
//        if (userDao == null) {
//            return new ModelAndView("error.jsp");
//        }
//        if (userDao.getRole() == 1) {
//            return new ModelAndView("error.jsp");
//        }
//        return new ModelAndView("userprofile.jsp", "profile", userDao);
//    }
//    
//    @RequestMapping(value="/user/productlist", method=RequestMethod.GET)
//    public ModelAndView userProductListView(HttpServletRequest request) {
//        if (userDao == null) {
//            return new ModelAndView("error.jsp");
//        }
//        if (userDao.getRole() == 1) {
//            return new ModelAndView("error.jsp");
//        }
//        return new ModelAndView("userproductlist.jsp", "productlist", ProductDAO.getListProduct());
//    }
//    
//    @RequestMapping(value="/logout", method=RequestMethod.GET)
//    public String logout(HttpServletRequest request) {
//        userDao = null;
//        return "redirect:login";
//    }
    
    /*@RequestMapping(value="/login", method=RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request) {
        //request.getParameterValues("username")[0];
        String user = "";
        String username = request.getParameterValues("username")[0];
        userDao = UserDAO.getUserByUsername(username);
        if (userDao.getPassword().compareTo(request.getParameterValues("password")[0]) == 0) {
            user = userDao.getFullName();
            if (userDao.getRole() == 1) {
                return new ModelAndView("admindashboard.jsp", "fullname", user);
            }
            return new ModelAndView("userdashboard.jsp", "fullname", user);
        }
        return new ModelAndView("login.jsp");
    }*/
}