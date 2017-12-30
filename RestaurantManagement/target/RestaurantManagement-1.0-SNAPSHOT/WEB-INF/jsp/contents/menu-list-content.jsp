<%-- 
    Document   : food-index-content
    Created on : Oct 26, 2017, 9:31:32 PM
    Author     : USER
--%>
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
        <a href="#">Food management</a>
    </li>
    <li class="breadcrumb-item active">Menu list</li>
</ol>

<%List<Menu> menuList = (List<Menu>) request.getAttribute("menuList"); %> 
<div class="card mb-3">
    <div class="card-header">  
        <i class="fa fa-table"></i>Menu list   
        <a href="/RestaurantManagement/new-menu"><button style="float:right" type="button" class="btn btn-primary btn-sm">New menu</button></a>
    </div>
    
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered dataTable" width="100%" cellspacing="0">
                <thead>
                    <tr>
                        <th style="width:10%">Menu id</th>
                        <th style="">Menu name</th>
                        <th class="text-center" style="width:15%">Action</th>
                    </tr>
                </thead>
                 <% for (Menu m : menuList) {%>
                <tbody>
                <td><%= m.getId()%></td>
                <td><%= m.getMenuName()%></td>
                <td>
                    <a href="/RestaurantManagement/detail-menu/<%=m.getId()%>">
                        <button title = "View detail" type="button" class="btn btn-info btn-sm" >View detail</button>
                    </a>
                    
                    <!--<button onclick="return edit();" title = "Edit" type="button" class="btn btn-success btn-sm" ><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button>-->
                    <button onclick="return confirm_delete(<%=m.getId()%>);" title = "Delete" type="button" class="btn btn-danger btn-sm"><i class="fa fa-trash-o fa-lg" aria-hidden="true"></i></button>
                </td>
                <script>
                    function confirm_delete(id) {
                        if (confirm("You want to delete menu " + id + " ?")) // this will pop up confirmation box and if yes is clicked it call servlet else return to page
                        {
                            window.location = "/RestaurantManagement/delete-menu?menuId=" + id
                        } else {
                            return false;
                        }
                        return true;
                    }
                </script>

                </tbody>
                 <% }%>
            </table>
        </div>
    </div>
   
</div>
