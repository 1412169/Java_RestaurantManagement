<%-- 
    Document   : food-index-content
    Created on : Oct 26, 2017, 9:31:32 PM
    Author     : USER
--%>


<%@page import="java.util.List"%>
<%@page import="Entity.DishDirectory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%List<DishDirectory> lstSanPham = (List<DishDirectory>) request.getAttribute("dishDirectoryList"); %>       
            <% for (DishDirectory sp : lstSanPham) { %> 

                              <h3> <%= sp.getName()%></h3>
                                  
            <% }%>