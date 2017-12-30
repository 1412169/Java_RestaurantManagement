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
    <li class="breadcrumb-item active">Revenue report</li>
</ol>

<form action="/RestaurantManagement/customer-report" method="post">            
    <div class="row">
        <div class="col-md-4">
            <div class="form-group">
                <label style="font-weight: bold">Month</label>
                <%int month = (int) request.getAttribute("month"); %> 
                <select class="form-control" name="month">
                    <% if (month == 0) {%>
                    <option selected name="month" value="0">---Choose month---</option> 
                    <% for (int i = 1; i <= 12; i++) {%>
                    <option name="month" value="<%= i%>">Tháng <%= i%></option> 
                    <% } %>
                    <% }%>


                    <% if (month != 0) {%>
                    <% for (int i = 1; i <= 12; i++) {%>
                    <% if (i != month) {%>
                    <option name="month" value="<%= i%>">Tháng <%= i%></option> 
                    <% } %>
                    <% if (i == month) {%>
                    <option selected name="month" value="<%= i%>">Tháng <%= i%></option> 
                    <% } %>
                    <% } %>
                    <% }%>
                </select>
            </div> 
        </div>

        <div class="col-md-4">
            <div class="form-group">
                <%List<Object[]> yearList = (List<Object[]>) request.getAttribute("yearList"); %> 
                <label style="font-weight: bold">Year</label>
                <%int year = (int) request.getAttribute("year"); %> 
                <select class="form-control" name="year">
                    <% if (year == 0) {%>
                    <option selected name="year" value="0">---Choose year---</option> 
                    <% for (Object[] y : yearList) {%> 
                    <option name="year" value="<%= y[0]%>"><%= y[0]%></option> 
                    <% } %>
                    <% }%>


                    <% if (year != 0) {%>
                    <% for (Object[] y : yearList) {%> 
                    <% if ((int) y[0] != year) {%>
                    <option name="year" value="<%= y[0]%>"><%= y[0]%></option> 
                    <% } %>
                    <% if ((int) y[0] == year) {%>
                    <option selected name="year" value="<%= y[0]%>"><%= y[0]%></option> 
                    <% } %>
                    <% } %>
                    <% }%>
                </select>
            </div> 
        </div>

        <div class="col-md-3">
            <div class="form-group">
                <input style="margin-top: 30px" class="btn btn-primary" type="submit" value="Search">
                <input  style="margin-top: 30px" class="btn btn-primary" type="submit" value="Export CSV">
            </div>
        </div>
    </div>


    <%List<Object[]> customerBuy = (List<Object[]>) request.getAttribute("customerBuy"); %> 
    <% if (customerBuy != null) { %>
    <!-- Example DataTables Card-->
    <div class="card mb-3">
        <div class="card-header">
            <i class="fa fa-table"></i>Customer list</div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>Dish name</th>
                            <th>Price</th>
                            <th>Total dish</th>
                            <th>Total dish</th>
                        </tr>
                    </thead>
                    <% for (Object[] obj : customerBuy) {%>
                    <tbody>
                        <tr>
                            <td><%= obj[1]%></td>
                            <td><%= obj[2]%></td>
                            <td><%= obj[3]%></td>
                            <td><%= obj[4]%></td>
                        </tr>
                    </tbody>
                    <% } %>
                </table>
            </div>
        </div>
    </div>
    <% }%>

</form>

<%int currentYear = (int) request.getAttribute("currentYear");%> 
<script>
    window.onload = function () {

    var chart = new CanvasJS.Chart("chartContainer", {
    animationEnabled: true,
            theme: "light2",
            title: {
            text: "Monthly customer in <%= currentYear%>"
            },
            axisX: {
            valueFormatString: "MMM"
            },
            axisY: {
            prefix: "$",
                    labelFormatter: addSymbols
            },
            toolTip: {
            shared: true
            },
            legend: {
            cursor: "pointer",
                    itemclick: toggleDataSeries
            },
            data: [
            {
            type: "column",
                    name: "New customer",
                    xValueFormatString: "MMMM YYYY",
                    yValueFormatString: "$#,##0",
                    dataPoints: [
    <%List<Object[]> monthlyCustomer = (List<Object[]>) request.getAttribute("monthlyCustomer"); %>
    <% for (Object[] obj : monthlyCustomer) {%>
                    { x: new Date(2017, <%= (int) obj[0] - 1%>), y: <%=  obj[1]%> },
    <% }%>
                    ]
            }]
    });
    chart.render();
    function addSymbols(e) {
    var suffixes = ["", "K", "M", "B"];
    var order = Math.max(Math.floor(Math.log(e.value) / Math.log(1000)), 0);
    if (order > suffixes.length - 1)
            order = suffixes.length - 1;
    var suffix = suffixes[order];
    return CanvasJS.formatNumber(e.value / Math.pow(1000, order)) + suffix;
    }

    function toggleDataSeries(e) {
    if (typeof (e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
    e.dataSeries.visible = false;
    } else {
    e.dataSeries.visible = true;
    }
    e.chart.render();
    }

    }
</script>
<br/><br/>
<div id="chartContainer" style="height: 370px; width: 100%;">
    <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</div>
<br/><br/>