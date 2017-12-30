<%-- 
    Document   : table-content
    Created on : Dec 8, 2017, 7:49:16 AM
    Author     : hotha
--%>

<%@page import="Entity.BranchTable"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>





<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="#">Food management</a>
    </li>
    <li class="breadcrumb-item active">New dish</li>
</ol>

<!-- Example DataTables Card-->
<form action="/RestaurantManagement/new-dish" method="post">
    <div class="row">
        <div class="col-6">
            <div class="form-group">
                <%List<BranchTable> table = (List<BranchTable>) request.getAttribute("tableList"); %>
                <label style="font-weight: bold">Dish name</label>
                <% for (BranchTable t : table) {%>
                <input type="text" class="form-control" id="exampleFormControlInput1" name="name" value="<%= t.getName()%>">
                <%}%>
            </div>
        </div>
</form>