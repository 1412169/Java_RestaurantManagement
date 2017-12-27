<%-- 
    Document   : food-index-content
    Created on : Oct 26, 2017, 9:31:32 PM
    Author     : USER
--%>

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
    <li class="breadcrumb-item active">New branch</li>
</ol>

<!-- Example DataTables Card-->
<form action="/RestaurantManagement/new-branch" method="post">
    <div class="row">
        <div class="col-6">
            <div class="form-group">
                <label style="font-weight: bold">Branch name</label>
                <spring:nestedPath path="name">
                    <spring:bind path="value">
                        <input type="text" class="form-control" id="exampleFormControlInput1" name="name">
                    </spring:bind>
                </spring:nestedPath>
            </div>
            <div class="form-group">
                <label style="font-weight: bold">Phone</label>
                <spring:nestedPath path="phone">
                    <spring:bind path="value">
                        <input type="text" class="form-control" id="exampleFormControlInput1" name="phone">
                    </spring:bind>
                </spring:nestedPath>
            </div>
            <div class="form-group">
                <label style="font-weight: bold">City/Province</label>
                <spring:nestedPath path="province">
                    <spring:bind path="value">
                        <input type="text" class="form-control" id="exampleFormControlInput1" name="province">
                    </spring:bind>
                </spring:nestedPath>
            </div>
            <div class="form-group">
                <label style="font-weight: bold">Number table</label>
                <spring:nestedPath path="numberTable">
                    <spring:bind path="value">
                        <input type="number" class="form-control" id="exampleFormControlInput1" name="numberTable"> <span></span>
                    </spring:bind>
                </spring:nestedPath>
            </div>
            <div class="form-group">
                <label style="font-weight: bold">Address</label>
                <spring:nestedPath path="address">
                    <spring:bind path="value">
                        <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="address"></textarea>
                    </spring:bind>
                </spring:nestedPath>
            </div>
            <div class="form-group text-center">
                <input class="btn btn-primary btn-lg btn-sm" type="submit" value="Add new branch">
            </div>
        </div>
        <div class="col-6">
            <div class="row">
                <div class="col-8">
                    <label style="font-weight: bold">Menu</label> <br>
                    <%List<Menu> menuList = (List<Menu>) request.getAttribute("menuList"); %>
                    <% for (Menu m : menuList) {%> 
                    <spring:nestedPath path="menu">
                        <spring:bind path="value">
                            <input style="float: right; transform: scale(1.5); margin-right: 10px; margin-top: 10px" 
                                   type="checkbox" name="menu" value="<%=m.getId()%>"><%=m.getMenuName()%> <br>
                        </spring:bind>
                    </spring:nestedPath> 
                    <% }%>  
                </div>
                <div class="col-2"></div>
            </div>
        </div>
</form>
