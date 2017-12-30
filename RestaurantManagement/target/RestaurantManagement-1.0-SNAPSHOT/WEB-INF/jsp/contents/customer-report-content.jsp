<%-- 
    Document   : report-content
    Created on : Oct 26, 2017, 9:25:57 PM
    Author     : USER
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="Entity.Branch"%>
<%@page import="Entity.DishDirectory"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Breadcrumbs-->
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="#">Report management</a>
    </li>
    <li class="breadcrumb-item active">Customer report</li>
</ol>

<form action="/RestaurantManagement/customer-report" method="post">            
    <div class="row">
        <div class="col-md-4">
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

        <div class="col-md-4">
            <div class="form-group">
                <%List<Object[]> yearList = (List<Object[]>) request.getAttribute("yearList"); %> 
                <label style="font-weight: bold">Year</label>
                <%int year = (int) request.getAttribute("year"); %> 
                <select class="form-control" name="year">
                    <% for (Object[] y : yearList) {%> 
                    <% if ((int) y[0] != year) {%>
                    <option name="year" value="<%= y[0]%>"><%= y[0]%></option> 
                    <% } %>
                    <% if ((int) y[0] == year) {%>
                    <option selected name="year" value="<%= y[0]%>"><%= y[0]%></option> 
                    <% } %>
                    <% } %>
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
<br/><br/>
    <%List<Object[]> monthlyCustomer = (List<Object[]>) request.getAttribute("monthlyCustomer"); %>
        <% DecimalFormat formatter = new DecimalFormat("#,###"); %> 
        <div class="card mb-3">
            <div class="card-header">
                <i class="fa fa-table"></i>Monthly detail in <%= year %></div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                            <tr>
                                <th>January</th>
                                <th>February</th>
                                <th>Match</th>
                                <th>April</th>
                                <th>May</th>
                                <th>June</th>
                                <th>July</th>
                                <th>August</th>
                                <th>September</th>
                                <th>October</th>
                                <th>November</th>
                                <th>December</th>
                            </tr>
                        </thead>
                        <tbody>

                            <tr>
                                <td><%= formatter.format(monthlyCustomer.get(0)[1])%></td>
                                <td><%= formatter.format(monthlyCustomer.get(1)[1])%></td>
                                <td><%= formatter.format(monthlyCustomer.get(2)[1])%></td>
                                <td><%= formatter.format(monthlyCustomer.get(3)[1])%></td>
                                <td><%= formatter.format(monthlyCustomer.get(4)[1])%></td>
                                <td><%= formatter.format(monthlyCustomer.get(5)[1])%></td>
                                <td><%= formatter.format(monthlyCustomer.get(6)[1])%></td>
                                <td><%= formatter.format(monthlyCustomer.get(7)[1])%></td>
                                <td><%= formatter.format(monthlyCustomer.get(8)[1])%></td>
                                <td><%= formatter.format(monthlyCustomer.get(9)[1])%></td>
                                <td><%= formatter.format(monthlyCustomer.get(10)[1])%></td>
                                <td><%= formatter.format(monthlyCustomer.get(11)[1])%></td>
                            </tr>
                        </tbody>

                    </table>
                </div>
            </div>
        </div>
                    <br/>        
<div id="chartContainer" style="height: 250px; width: 100%;">
    <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</div>
                <br/><br/>
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

<script>
    window.onload = function () {

    var chart = new CanvasJS.Chart("chartContainer", {
    animationEnabled: true,
            theme: "light2",
            title: {
            text: "Monthly customer in <%= year %>"
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

<br/><br/>