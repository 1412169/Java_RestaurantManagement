<%-- 
    Document   : food-index-content
    Created on : Oct 26, 2017, 9:31:32 PM
    Author     : USER
--%>

<%@page import="Entity.Branch"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Breadcrumbs-->
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="#">Branch management</a>
    </li>
    <li class="breadcrumb-item active">Branch list</li>
</ol>

<%List<Branch> branch = (List<Branch>) request.getAttribute("branchList"); %> 
<div class="card mb-3">
    <div class="card-header">
        <i class="fa fa-table"></i>Branch list
        <a href="/RestaurantManagement/new-branch"><button style="float:right" type="button" class="btn btn-primary btn-sm">New branch</button></a>
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Branch name</th>
                        <th>Address</th>
                        <th>Phone</th>
                        <th>Province/City</th>
                        <th class="text-center">Table</th>
                        <th class="text-center" style="width: 10%">Action</th>
                    </tr>
                </thead>
                <% for (Branch b : branch) {%>
                <tbody>
                <td><%= b.getId()%></td>
                <td><%= b.getName()%></td>
                <td><%= b.getAddress()%></td>
                <td><%= b.getPhone()%></td>
                <td><%= b.getProvince()%></td>
                <td><%= b.getNumTable()%></td>
                
                <td class="text-center">
                     <a href="/RestaurantManagement/edit-branch/<%=b.getId()%>">
                        <button title = "Edit" type="button" class="btn btn-success btn-sm" ><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button>
                    </a>
                    <!--<button title = "Edit" type="button" class="btn btn-success btn-sm"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button>-->
                    <span><button onclick="return confirm_decision(<%=b.getId()%>);" title = "Delete" type="button" class="btn btn-danger btn-sm"><i class="fa fa-trash-o fa-lg" aria-hidden="true"></i></button></span>
                    <%--<a href="/RestaurantManagement/table/branchId/<%=b.getId()%>"><button style ="margin-top: 10px" title= "View table" type="button" class="btn btn-outline-primary btn-sm">View Table</button></a>--%>
                </td>
                <script>
                    function confirm_decision(id) {
                        if (confirm("You want to delete branch " + id + " ?")) // this will pop up confirmation box and if yes is clicked it call servlet else return to page
                        {
                            window.location = "/RestaurantManagement/delete-branch?branchId=" + id
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