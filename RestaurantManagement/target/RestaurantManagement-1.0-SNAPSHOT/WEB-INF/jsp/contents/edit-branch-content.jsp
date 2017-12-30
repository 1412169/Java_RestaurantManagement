<%-- 
    Document   : food-index-content
    Created on : Oct 26, 2017, 9:31:32 PM
    Author     : USER
--%>

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
    <li class="breadcrumb-item active">New branch</li>
</ol>
<% Branch branch = (Branch) request.getAttribute("branch");%> 
<!-- Example DataTables Card-->
<form action="/RestaurantManagement/edit-branch/<%=branch.getId()%>" method="post">
    <div class="row">
        <div class="col-6">
            <div class="form-group">
                <label style="font-weight: bold">Branch name</label>
                <spring:nestedPath path="name">
                    <spring:bind path="value">
                        <input type="text" class="form-control" id="exampleFormControlInput1" name="name" value="<%=branch.getName()%>">
                    </spring:bind>
                </spring:nestedPath>
            </div>
            <div class="form-group">
                <label style="font-weight: bold">Phone</label>
                <spring:nestedPath path="phone">
                    <spring:bind path="value">
                        <input type="text" class="form-control" id="exampleFormControlInput1" name="phone" value="<%=branch.getPhone()%>">
                    </spring:bind>
                </spring:nestedPath>
            </div>
            <div class="form-group">
                <label style="font-weight: bold">City/Province</label>
                <spring:nestedPath path="province">
                    <spring:bind path="value">
                        <input type="text" class="form-control" id="exampleFormControlInput1" name="province" value="<%=branch.getProvince()%>">
                    </spring:bind>
                </spring:nestedPath>
            </div>
            <div class="form-group">
                <label style="font-weight: bold">Number table</label>
                <spring:nestedPath path="numberTable">
                    <spring:bind path="value">
                        <input type="number" class="form-control" id="exampleFormControlInput1" name="numberTable" value="<%=branch.getNumTable()%>"> <span></span>
                    </spring:bind>
                </spring:nestedPath>
            </div>
            <div class="form-group">
                <label style="font-weight: bold">Address</label>
                <spring:nestedPath path="address">
                    <spring:bind path="value">
                        <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="address"><%=branch.getAddress()%></textarea>
                    </spring:bind>
                </spring:nestedPath>
            </div>

            <div class="form-group text-center">
                <input class="btn btn-primary btn-lg btn-sm" type="submit" value="Edit branch">
            </div>
        </div>
        <div class="col-6">
            <div class="row">
                <div class="col-8">
                    <label style="font-weight: bold">Menu</label> <br>
                    <%List<Menu> checkedMenu = (List<Menu>) request.getAttribute("checkedMenu"); %>
                    <%List<Menu> uncheckedMenu = (List<Menu>) request.getAttribute("uncheckedMenu"); %>
                    <% for (Menu m : checkedMenu) {%> 
                    <input checked style="float: right; transform: scale(1.5); margin-right: 10px; margin-top: 10px" 
                           type="checkbox" name="menu" value="<%=m.getId()%>"><%=m.getMenuName()%> <br>
                    <% }%>
                    <% for (Menu m : uncheckedMenu) {%>
                    <input style="float: right; transform: scale(1.5); margin-right: 10px; margin-top: 10px" 
                           type="checkbox" name="menu" value="<%=m.getId()%>"><%=m.getMenuName()%> <br>
                    <% }%> 
                </div>

                <%--                <div class="col-5">
                                    <label style="font-weight: bold">Dish Directory</label> <br>
                                    <%List<DishDirectory> dishDirectoryList = (List<DishDirectory>) request.getAttribute("dishDirectoryList"); %>
                                    <% for (DishDirectory dd : dishDirectoryList) {%> 
                                    <spring:nestedPath path="branchDirectory">
                                        <spring:bind path="value">
                                            <input style="float: right; transform: scale(1.5); margin-right: 10px; margin-top: 10px" type="checkbox" name="branchDirectory" value="<%=dd.getId()%>"><%=dd.getName()%> <br>
                                        </spring:bind>
                                    </spring:nestedPath> 
                                    <% }%>  
                                </div>--%>
                <div class="col-2"></div>
            </div>

        </div>
</form>
