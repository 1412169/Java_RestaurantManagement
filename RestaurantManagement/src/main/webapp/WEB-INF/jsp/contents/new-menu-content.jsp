<%-- 
    Document   : food-index-content
    Created on : Oct 26, 2017, 9:31:32 PM
    Author     : USER
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="Entity.Dish"%>
<%@page import="Entity.DishDirectory"%>
<%@page import="Entity.DishDirectory"%>
<%@page import="java.util.List"%>
<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>

<!-- Breadcrumbs-->
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="#">Food management</a>
    </li>
    <li class="breadcrumb-item active">New menu</li>
</ol>
<% DecimalFormat formatter = new DecimalFormat("#,###"); %> 
<!-- Example DataTables Card-->
<form action="/RestaurantManagement/new-menu" method="post">
    <div class="row">
        <div class="col-6">
            <div class="form-group">
                <label style="font-weight: bold">Menu name</label>
                <input required type="text" class="form-control" id="exampleFormControlInput1" name="name">

            </div>
        </div>
        <div class="col-6">
            <!--<div class="form-group">-->
            <input style="margin-top:30px" class="btn btn-primary" type="submit" value="Add new menu">
            <!--</div>-->
        </div>
    </div>
    <div>
        <%List<DishDirectory> dishDirectory = (List<DishDirectory>) request.getAttribute("dishDirectoryList"); %>
        <%List<Dish> dish = (List<Dish>) request.getAttribute("dishList"); %> 
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
                        <% for (Dish d : dish) { %>
                        <% if (d.getDishDirectoryId() == dd.getId()) {%>
                        <tbody>
                        <td>  <input style="float: right; transform: scale(1.5); margin-right: 10px; margin-top: 10px" 
                                     type="checkbox" name="dishList" value="<%=d.getId()%>"></td>
                        <td><%= d.getId()%></td>
                        <td><%= d.getName()%></td>
                        <td><%= d.getDescription()%></td>
                        <td><%= formatter.format(d.getPrice()) %></td>
                        </tbody>
                        <% }%>
                        <% }%>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <% }%>

</form>
