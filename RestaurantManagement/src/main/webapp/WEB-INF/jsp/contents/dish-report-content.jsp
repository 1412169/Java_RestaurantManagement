<%-- 
    Document   : report-content
    Created on : Oct 26, 2017, 9:25:57 PM
    Author     : USER
--%>

<%@page import="Entity.Branch"%>
<%@page import="Entity.DishDirectory"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Breadcrumbs-->
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="#">Report management</a>
    </li>
    <li class="breadcrumb-item active">Dish report</li>
</ol>
<form action="/RestaurantManagement/dish-report" method="post">
    <div class="row">
        <div class="col-md-2">
            <div class="form-group">
                <label style="font-weight: bold">Month</label>
                <%int month = (int) request.getAttribute("month"); %> 
                <select class="form-control" name="month">
                    <% for (int i = 1; i <= 12; i++) {%>
                    <% if (i != month) {%>
                    <option name="month" value="<%= i%>">Tháng <%= i%></option> 
                    <% } %>
                    <% if (i == month) {%>
                    <option selected name="month" value="<%= i%>">Tháng <%= i%></option> 
                    <% } %>
                    <% } %>
                </select>
            </div> 
        </div>
        <div class="col-md-2">
            <div class="form-group">
                <label style="font-weight: bold">Year</label>
                <%int year = (int) request.getAttribute("year"); %> 
                <%List<Object[]> yearList = (List<Object[]>) request.getAttribute("yearList"); %> 
                <select class="form-control" name="year">
                    <% for (Object[] y : yearList) {%> 
                    <% if ((int) y[0] == year) {%>
                    <option selected name="year" value="<%= y[0]%>"><%= y[0]%></option> 
                    <% } %>
                    <% if ((int) y[0] != year) {%>
                    <option name="year" value="<%= y[0]%>"><%= y[0]%></option> 
                    <% } %> 
                    <% } %> 
                </select>
            </div>
        </div>
        <div class="col-md-5">
            <div class="form-group">
                <%List<Branch> branch = (List<Branch>) request.getAttribute("branchList"); %> 
                <%int branchId = (int) request.getAttribute("branchId"); %> 
                <label style="font-weight: bold">Branch</label>
                <select class="form-control" name="branch">
                    <% if (branchId == 0) {%>
                    <option selected name="branch" value="0">---Choose branch---</option>
                    <% for (Branch b : branch) {%> 
                    <option name="branch" value="<%= b.getId()%>"><%= b.getName()%></option> 
                    <% } %>
                    <% } %>

                    <% if (branchId != 0) {%>
                    <% for (Branch b : branch) {%> 
                    <% if (b.getId() == branchId) {%>
                    <option selected name="branch" value="<%= b.getId()%>"><%= b.getName()%></option> 
                    <% } %>
                    <% if (b.getId() != branchId) {%>
                    <option name="branch" value="<%= b.getId()%>"><%= b.getName()%></option> 
                    <% } %>
                    <% } %>
                    <% } %>
                </select>
            </div>
        </div>
        <div class="col-md-3">
            <div class="form-group">
                <input style="margin-top: 30px" class="btn btn-primary" type="submit" value="Search">
                <input style="margin-top: 30px" class="btn btn-primary" type="submit" value="Export CSV">
            </div>
        </div>
    </div>


<%List<DishDirectory> dishDirectoryList = (List<DishDirectory>) request.getAttribute("dishDirectoryList"); %>
<%List<Object[]> dishList = (List<Object[]>) request.getAttribute("dishList"); %> 
<% for (DishDirectory dd : dishDirectoryList) {%> 
<!-- Example DataTables Card-->
<div class="card mb-3">
    <div class="card-header">
        <i class="fa fa-table"></i> <%= dd.getName()%> </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                    <tr>
                        <th>Dish name</th>
                        <th>Price</th>
                        <th>Total dish</th>
                    </tr>
                </thead>
                <% for (Object[] obj : dishList) { %>
                <% if ((int) obj[0] == dd.getId()) {%>
                <tbody>
                    <tr>
                        <td><%= obj[3]%></td>
                        <td><%= obj[4]%></td>
                        <td><%= obj[5]%></td>
                    </tr>
                </tbody>
                <% } %>
                <% } %>
            </table>
        </div>
    </div>
</div>
<% }%>
</form>