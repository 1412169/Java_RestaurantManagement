<%-- 
    Document   : report-content
    Created on : Oct 26, 2017, 9:25:57 PM
    Author     : USER
--%>

<%@page import="Entity.OrderBill"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Breadcrumbs-->
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="#">Report management</a>
    </li>
    <li class="breadcrumb-item active">Order report</li>
</ol>

<!-- Area Chart Example-->
<div class="card mb-3">
    <div class="card-header">
        <i class="fa fa-area-chart"></i> Biểu đồ</div>
    <div class="card-body">
        <canvas id="myAreaChart" width="100%" height="30"></canvas>
    </div>
    <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
</div>

<!-- Example DataTables Card-->

<div class="card mb-3">
    <div class="card-header">
        <i class="fa fa-table"></i>Statistic</div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Status</th>
                        <th>Sum money</th>
                        <th>Order date</th>
                        <th>Action</th>
                    </tr>
                </thead>

                <tbody>
                    <%List<OrderBill> orderBillList = (List<OrderBill>) request.getAttribute("orderBillList"); %>
                    <% for (OrderBill ob : orderBillList) { %> 
                    <tr>
                        <td><%= ob.getId()%></td>
                        <td><%= ob.getStatus()%></td>
                        <td><%= ob.getSumMoney()%></td>
                        <td><%= ob.getCreatedAt()%></td>
                        <td>
                            <button title = "Edit" type="button" class="btn btn-success btn-sm" href=""><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button>
                            <span><button title = "Delete" type="button" class="btn btn-danger btn-sm" href=""><i class="fa fa-trash-o fa-lg" aria-hidden="true"></i></button></span>
                        </td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
        </div>
    </div>
</div>