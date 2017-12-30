<%-- 
    Document   : food-index-content
    Created on : Oct 26, 2017, 9:31:32 PM
    Author     : USER
--%>


<%@page import="Entity.DishDirectory"%>
<%@page import="Entity.Dish"%>
<%@page import="java.util.List"%>
<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>

<!-- Breadcrumbs-->
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="#">Food management</a>
    </li>
    <li class="breadcrumb-item active">Edit dish</li>
</ol>
<% Dish dish = (Dish) request.getAttribute("dish"); %>  
<!-- Example DataTables Card-->
<form action="/RestaurantManagement/edit-dish/<%= dish.getId()%>" method="post">
    <div class="row">
        <div class="col-6">
            <div class="form-group">
                <label style="font-weight: bold">Dish name</label>
                <spring:nestedPath path="name">
                    <spring:bind path="value">
                        <input type="text" class="form-control" id="exampleFormControlInput1" name="name" value="<%= dish.getName()%>">
                    </spring:bind>
                </spring:nestedPath>
            </div>
            <div class="form-group">
                <label style="font-weight: bold">Price</label>
                <spring:nestedPath path="price">
                    <spring:bind path="value">
                        <input type="number" class="form-control" id="exampleFormControlInput1" name="price" value="<%= dish.getPrice()%>">
                    </spring:bind>
                </spring:nestedPath>
            </div>
            <div class="form-group">
                <label style="font-weight: bold">Description</label>
                <spring:nestedPath path="description">
                    <spring:bind path="value">
                        <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="description"><%= dish.getDescription()%></textarea>
                    </spring:bind>
                </spring:nestedPath>

            </div>
            <div class="form-group text-center">
                <input class="btn btn-primary btn-lg btn-sm" type="submit" value="Edit dish">

            </div>
        </div>
        <div class="col-6">
            <div class="form-group">
                <label style="font-weight: bold">Dish directory</label>
                <select class="form-control" name="dishDirectory">
                    <%List<DishDirectory> dishDirectory = (List<DishDirectory>) request.getAttribute("dishDirectoryList"); %>
                    <% for (DishDirectory dd : dishDirectory) {%> 
                    <spring:nestedPath path="dishDirectory">
                        <spring:bind path="value">
                            <%if (dish.getDishDirectoryId() == dd.getId()) {%> 
                                <option selected name="dishDirectory" value="<%=dd.getId()%>"><%= dd.getName()%></option>
                            <% }%>
                            <option name="dishDirectory" value="<%=dd.getId()%>"><%= dd.getName()%></option> 
                        </spring:bind>
                    </spring:nestedPath> 
                    <% }%>
                </select>
            </div> 
            <div class="form-group">
                <label style="font-weight: bold">Choose image</label>
                <spring:nestedPath path="img">
                    <spring:bind path="value">
                        <input type="file" class="form-control-file" id="exampleFormControlFile1" name="img" value="<%=dish.getImgUrl()%>">
                        <%=dish.getImgUrl()%>
                    </spring:bind>
                </spring:nestedPath> 
            </div>

<%--            <div class="col-8">
                <label style="font-weight: bold">Branch directory</label> <br>
                <%List<Branch> branchList = (List<Branch>) request.getAttribute("branchList"); %>
                <% for (Branch bd : branchList) {%> 
                <spring:nestedPath path="branch">
                    <spring:bind path="value">
                        <input style="float: right; transform: scale(1.5); margin-right: 10px; margin-top: 10px" type="checkbox" name="branch" value="<%=bd.getId()%>"><%=bd.getName()%> <br>
                    </spring:bind>
                </spring:nestedPath> 
                <% }%>  
            </div>--%>
        </div>
</form>
