
<%@page import="java.text.DecimalFormat"%>
<%@page import="Entity.Branch"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<head>

    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.common-material.min.css" />
    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.material.min.css" />
    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.material.mobile.min.css" />

    <script src="https://kendo.cdn.telerik.com/2017.3.1026/js/jquery.min.js"></script>
    <script src="https://kendo.cdn.telerik.com/2017.3.1026/js/kendo.all.min.js"></script>
</head>
<body>
    <ol class="breadcrumb">
        <li class="breadcrumb-item">
            <a href="#">Report management</a>
        </li>
        <li class="breadcrumb-item active">Cost report</li>
    </ol>
    <!-- Breadcrumbs-->
    <form action="/RestaurantManagement/cost-report" method="post">
        <div id="example ">
            <div class="row text-center">
                <div class="col-4 ">
                    <%String string = (String) request.getAttribute("string");%>
                    <div class=" k-content ">
                        <label style="font-weight: bold">Choose date</label>
                        <input style="padding-top:2px; padding-bottom: 2px;" class="date" id="datepicker" 
                               title="datepicker" name="date" value="<%= string%>" />
                    </div> 
                </div>
                <div class="col-5">
                    <div class="form-group">
                        <%List<Branch> branch = (List<Branch>) request.getAttribute("branchList"); %> 
                        <%int branchId = (int) request.getAttribute("branchId"); %> 

                        <select class="form-control" name="branch">

                            <% if (branchId == 0) {%>
                            <option selected name="branch" value="0">---All branch---</option>
                            <% for (Branch b : branch) {%> 
                            <option name="branch" value="<%= b.getId()%>"><%= b.getName()%></option> 
                            <% } %>
                            <% } %>

                            <% if (branchId != 0) {%>
                            <option selected name="branch" value="0">---All branch---</option>
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
                <div class="col-3">
                    <!--<div class="form-group">-->
                    <input class="btn btn-primary" type="submit" value="Search">
                    <input class="btn btn-primary" type="submit" value="Export CSV">
                    <!--</div>-->

                </div>
            </div>


            <script>
                $("#datepicker").kendoDatePicker({
                    animation: false
                });
            </script>       
        </div>
        <!-- Icon Cards-->
        <br/>
        <%int day = (int) request.getAttribute("day");%>
        <%int month = (int) request.getAttribute("month");%>
        <%int year = (int) request.getAttribute("year");%>
        <div class="row">
            <div class="col-xl-4 col-sm-6 mb-4">
                <div class="card text-white bg-warning o-hidden h-100">
                    <div class="card-body">
                        <div class="card-body-icon">
                            <i class="fa fa-fw fa-list"></i>
                        </div>
                        <div class="mr-5" style="font-size: 20px; color: #18191c">DATE: <%= day%>/<%= month%>/<%= year%> </div>
                    </div>
                    <%String costDate = (String) request.getAttribute("costDate");%>
                    <div class="card-body">
                        <div class="card-body-icon">
                            <i class="fa fa-fw fa-list"></i>
                        </div>
                        <div class="mr-5">REVENUE OF DATE</div>
                        <div class="mr-5" style="font-weight: bold; font-size: 30px;"><%= costDate%></div>
                    </div>
                </div>
            </div>

            <div class="col-xl-4 col-sm-6 mb-4">
                <div class="card text-white bg-success o-hidden h-100">
                    <div class="card-body">
                        <div class="card-body-icon">
                            <i class="fa fa-fw fa-list"></i>
                        </div>
                        <div class="mr-5" style="font-size: 20px; color: #18191c">MONTH: <%= month%>/<%= year%> </div>
                    </div>
                    <%String costMonth = (String) request.getAttribute("costMonth");%>
                    <div class="card-body">
                        <div class="card-body-icon">
                            <i class="fa fa-fw fa-list"></i>
                        </div>
                        <div class="mr-5">REVENUE OF MONTH</div>
                        <div class="mr-5" style="font-weight: bold; font-size: 30px;"><%= costMonth%></div>
                    </div>
                </div>
            </div>

            <div class="col-xl-4 col-sm-6 mb-4">
                <div class="card text-white bg-primary o-hidden h-100">
                    <div class="card-body">
                        <div class="card-body-icon">
                            <i class="fa fa-fw fa-list"></i>
                        </div>
                        <div class="mr-5" style="font-size: 20px; color: #18191c">YEAR: <%= year%> </div>
                    </div>
                    <% String costYear = (String) request.getAttribute("costYear");%>
                    <div class="card-body">
                        <div class="card-body-icon">
                            <i class="fa fa-fw fa-list"></i>
                        </div>
                        <div class="mr-5">REVENUE OF YEAR</div>
                        <div class="mr-5" style="font-weight: bold; font-size: 30px;"><%= costYear%></div>
                    </div>
                </div>
            </div>
        </div>
        <br/>
        <%List<Object[]> monthlyCost = (List<Object[]>) request.getAttribute("monthlyCost");%>
        <script>
            window.onload = function () {

                var chart = new CanvasJS.Chart("chartContainer", {
                    animationEnabled: true,
                    theme: "light2",
                    title: {
                        text: "Monthly cost in <%= year%>"
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
                            name: "Cost",
                            showInLegend: false,
                            xValueFormatString: "MMMM YYYY",
                            yValueFormatString: "$#,##0",
                            dataPoints: [

            <% for (Object[] obj : monthlyCost) {%>
                                {x: new Date(<%= year%>, <%= (int) obj[0] - 1%>), y: <%=  obj[1]%>},
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

        <% DecimalFormat formatter = new DecimalFormat("#,###");%> 
        <div class="card mb-3">
            <div class="card-header">
                <i class="fa fa-table"></i>Monthly detail in <%= year%></div>
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
                                <td><%= formatter.format(monthlyCost.get(0)[1])%></td>
                                <td><%= formatter.format(monthlyCost.get(1)[1])%></td>
                                <td><%= formatter.format(monthlyCost.get(2)[1])%></td>
                                <td><%= formatter.format(monthlyCost.get(3)[1])%></td>
                                <td><%= formatter.format(monthlyCost.get(4)[1])%></td>
                                <td><%= formatter.format(monthlyCost.get(5)[1])%></td>
                                <td><%= formatter.format(monthlyCost.get(6)[1])%></td>
                                <td><%= formatter.format(monthlyCost.get(7)[1])%></td>
                                <td><%= formatter.format(monthlyCost.get(8)[1])%></td>
                                <td><%= formatter.format(monthlyCost.get(9)[1])%></td>
                                <td><%= formatter.format(monthlyCost.get(10)[1])%></td>
                                <td><%= formatter.format(monthlyCost.get(11)[1])%></td>
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
    </form>
</body>