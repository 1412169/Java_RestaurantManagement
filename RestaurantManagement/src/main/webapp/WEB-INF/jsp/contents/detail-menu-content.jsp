<%-- 
    Document   : food-index-content
    Created on : Oct 26, 2017, 9:31:32 PM
    Author     : USER
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="Entity.Dish"%>
<%@page import="Entity.MenuDetail"%>
<%@page import="Entity.BranchMenu"%>
<%@page import="Entity.Branch"%>
<%@page import="Entity.Menu"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%-- 
    Document   : food-index-content
    Created on : Oct 26, 2017, 9:31:32 PM
    Author     : USER
--%>


<%@page import="Entity.DishDirectory"%>
<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>

<!-- Breadcrumbs-->
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="#">Branch management</a>
    </li>
    <li class="breadcrumb-item active">Menu detail</li>
</ol>
<% DecimalFormat formatter = new DecimalFormat("#,###");%> 
<% Menu menu = (Menu) request.getAttribute("menu");%> 
<%List<MenuDetail> menuDetailList = (List<MenuDetail>) request.getAttribute("menuDetailList"); %>
<%List<Dish> dishList = (List<Dish>) request.getAttribute("dishList");%> 
<div class="card mb-3">
    <div class="card-header">  
        <i class="fa fa-table"></i>Menu <strong><%=menu.getMenuName()%></strong>
        <a href="/RestaurantManagement/menu/<%=menu.getId()%>/add-dish"><button style="float:right" type="button" class="btn btn-primary btn-sm">Add dish</button></a>
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered dataTable" width="100%" cellspacing="0">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Dish name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th class="text-center" style="width: 10%">Delete</th>
                    </tr>
                </thead>
                <% for (MenuDetail md : menuDetailList) {%> 
                <% for (Dish d : dishList) {%>
                <tbody>
                 <% if (md.getIdDish() == d.getId()) {%>
                <td><%= d.getId()%></td>
                <td><%= d.getName()%></td>
                <td><%= d.getDescription()%></td>
                <td><%= formatter.format(d.getPrice())%></td>
                <td class="text-center"><span><button  onclick="return confirm_delete(<%= d.getId()%>,<%=md.getId()%>);" title = "Delete" type="button" class="btn btn-danger btn-sm"><i class="fa fa-trash-o fa-lg" aria-hidden="true"></i></button></span></td>
                <script>
                    function confirm_delete(dishId, id) {
                        if (confirm("You want to delete dish " + dishId + " from menu?")) // this will pop up confirmation box and if yes is clicked it call servlet else return to page
                        {
                             window.location = "/RestaurantManagement/menu/<%=menu.getId()%>/delete-menu-detail?menuDetailId=" + id;
                        } else {
                            return false;
                        }
                        return true;
                    }
                </script>
                <% }%>
                
                </tbody>
                <% }%>
                <% }%>
            </table>
        </div>
    </div>
</div>





