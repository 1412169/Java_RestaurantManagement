<%-- 
    Document   : food-index-content
    Created on : Oct 26, 2017, 9:31:32 PM
    Author     : USER
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@page import="Entity.Dish"%>
<%@page import="java.util.List"%>
<%@page import="Entity.DishDirectory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

<%List<DishDirectory> dishDirectory = (List<DishDirectory>) request.getAttribute("dishDirectoryList"); %>
<%List<Dish> dish = (List<Dish>) request.getAttribute("dishList"); %> 
<% for (DishDirectory dd : dishDirectory) {%> 
<div class="card mb-3">
    <div class="card-header">  
        <i class="fa fa-table"></i><%= dd.getName()%>   
        <a href="/RestaurantManagement/new-dish"><button type="button" class="btn btn-primary btn-sm">New dish</button></a>
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="" width="100%" cellspacing="0">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Dish name</th>
                        <th>Description</th>
                        <th>Created at</th>
                        <th>Price</th>
                        <th style="width: 10%">Action</th>
                    </tr>
                </thead>
                <% for (Dish d : dish) { %>
                <% if (d.getDishDirectoryId() == dd.getId()) {%>
                <tbody>

                <td><%= d.getId()%></td>
                <td><%= d.getName()%></td>
                <td><%= d.getDescription()%></td>
                <td><%= d.getCreatedAt()%></td>
                <td><%= d.getPrice()%></td>
                <td>
                    <a href="/RestaurantManagement/edit-dish?dishId=<%=d.getId()%>">
                        <button title = "Edit" type="button" class="btn btn-success btn-sm" ><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button>
                    </a>
                    <button onclick="return confirm_decision();" title = "Delete" type="button" class="btn btn-danger btn-sm"><i class="fa fa-trash-o fa-lg" aria-hidden="true"></i></button>
                </td>
                <script>
                    function confirm_decision() {
                        if (confirm("You want to delete dish <%=d.getId()%> - <%=d.getName()%> ?")) // this will pop up confirmation box and if yes is clicked it call servlet else return to page
                        {
                            window.location = "/RestaurantManagement/delete-dish?dishId=" + dishId;

                        } else {
                            return false;
                        }
                        return true;
                    }
                </script>
                </tbody>
                <% }%>
                <% }%>
            </table>
        </div>
    </div>
</div>
<% }%>