<%-- 
    Document   : food-index-content
    Created on : Oct 26, 2017, 9:31:32 PM
    Author     : USER
--%>

<%@page import="Entity.Branch"%>
<%@page import="Entity.BranchDirectory"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Breadcrumbs-->
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="#">Branch management</a>
    </li>
    <li class="breadcrumb-item active">Branch list</li>
</ol>

<%List<BranchDirectory> branchDirectoryList = (List<BranchDirectory>) request.getAttribute("branchDirectoryList"); %>
<%List<Branch> branch = (List<Branch>) request.getAttribute("branchList"); %> 
<% for (BranchDirectory bd : branchDirectoryList) { %> 
<div class="card mb-3">
    <div class="card-header">
        <i class="fa fa-table"></i><%= bd.getName()%>
        <button type="button" class="btn btn-primary btn-sm">New dish</button>
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="" width="100%" cellspacing="0">
                <thead>
                    <tr>
                        <th>Dish name</th>
                        <th>Address</th>
                        <th>Created at</th>
                        <th style="width: 10%">Action</th>
                    </tr>
                </thead>
                <% for (Branch b : branch) { %>
                <% if(b.getDirectoryId() == bd.getId()) {%>
                <tbody>
                   
                        <td><%= b.getName()%></td>
                        <td><%= b.getAddress()%></td>
                        <td><%= b.getCreatedAt()%></td>
                        <td>
                            <button title = "Edit" type="button" class="btn btn-success btn-sm"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button>
                            <span><button title = "Delete" type="button" class="btn btn-danger btn-sm"><i class="fa fa-trash-o fa-lg" aria-hidden="true"></i></button></span>
                        </td>
                </tbody>
                <% }%>
                <% }%>
            </table>
        </div>
    </div>
</div>
<% }%>