<%-- 
    Document   : food-index-content
    Created on : Oct 26, 2017, 9:31:32 PM
    Author     : USER
--%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="Entity.Menu"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@page import="Entity.Dish"%>
<%@page import="java.util.List"%>
<%@page import="Entity.DishDirectory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="#">Branch management</a>
    </li>
    <li class="breadcrumb-item active">Add dish to menu</li>
</ol>
<% DecimalFormat formatter = new DecimalFormat("#,###");%> 
<%List<DishDirectory> dishDirectory = (List<DishDirectory>) request.getAttribute("dishDirectoryList"); %>
<%List<Dish> chosenDishList = (List<Dish>) request.getAttribute("chosenDishList"); %> 
<%List<Dish> notChosenDishList = (List<Dish>) request.getAttribute("notChosenDishList"); %> 
<%Menu menu = (Menu) request.getAttribute("menu");%> 

<form action="/RestaurantManagement/menu/<%=menu.getId()%>/add-dish" method="post">
     <div class="card-header">  
        <i class=""></i>Menu <strong><%=menu.getMenuName()%></strong>
        <input required style="float:right" class="btn btn-primary btn-lg btn-sm" type="submit" value="Confirm adding chosen dish to menu">
    </div>
        <br/>
<!--    <div class="form-group text-center">
        
    </div>-->
    <% for (DishDirectory dd : dishDirectory) {%> 
    <div class="card mb-3">
        <div class="card-header">  
            <i class="fa fa-table"></i><%= dd.getName()%>   

        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th></th>
                            <th>Id</th>
                            <th style="width:20%">Dish name</th>
                            <th>Description</th>
                            <th>Price</th>
                        </tr>
                    </thead>
                    <% for (Dish d : notChosenDishList) { %>
                    <% if (d.getDishDirectoryId() == dd.getId()) {%>
                    <tbody>
                    <td> <input style="float: right; transform: scale(1.5); margin-right: 10px; margin-top: 10px" 
                                type="checkbox" name="dishList" value="<%=d.getId()%>"><br></td>
                    <td><%= d.getId()%></td>
                    <td><%= d.getName()%></td>
                    <td><%= d.getDescription()%></td>
                    <td><%= formatter.format(d.getPrice())%></td>
                    </tbody>
                    <% }%>
                    <% }%>
                </table>
            </div>
        </div>
    </div>
    <% }%>
</form>