
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
    <!-- Breadcrumbs-->
    <ol class="breadcrumb">
        <li class="breadcrumb-item">
            <a href="#">Home</a>
        </li>
    </ol>
    <form action="/RestaurantManagement/revenue-report" method="post">
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
                    <%List<Object[]> revenueReportDate = (List<Object[]>) request.getAttribute("revenueReportDate");%>
                    <div class="card-body">
                        <div class="card-body-icon">
                            <i class="fa fa-fw fa-list"></i>
                        </div>
                        <div class="mr-5">REVENUE OF DATE</div>
                        <div class="mr-5" style="font-weight: bold; font-size: 30px;"><%= revenueReportDate.toString().substring(1, revenueReportDate.toString().length() - 1)%></div>
                    </div>

                    <%List<Object[]> numOrderDate = (List<Object[]>) request.getAttribute("numOrderDate");%>
                    <div class="card-body">
                        <div class="card-body-icon">
                            <i class="fa fa-fw fa-list"></i>
                        </div>
                        <div class="mr-5">TOTAL ORDER OF DATE</div>
                        <div class="mr-5" style="font-weight: bold; font-size: 30px;"><%= numOrderDate.toString().substring(1, numOrderDate.toString().length() - 1)%></div>
                    </div>
                    <div id="chartContainerDate" style="height: 150px; width: 100%;">
                        <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
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
                    <%List<Object[]> revenueReportMonth = (List<Object[]>) request.getAttribute("revenueReportMonth");%>
                    <div class="card-body">
                        <div class="card-body-icon">
                            <i class="fa fa-fw fa-list"></i>
                        </div>
                        <div class="mr-5">REVENUE OF MONTH</div>
                        <div class="mr-5" style="font-weight: bold; font-size: 30px;"><%= revenueReportMonth.toString().substring(1, revenueReportMonth.toString().length() - 1)%></div>
                    </div>
                    <%List<Object[]> numOrderMonth = (List<Object[]>) request.getAttribute("numOrderMonth");%>
                    <div class="card-body">
                        <div class="card-body-icon">
                            <i class="fa fa-fw fa-list"></i>
                        </div>
                        <div class="mr-5">TOTAL ORDER OF MONTH</div>
                        <div class="mr-5" style="font-weight: bold; font-size: 30px;"><%= numOrderMonth.toString().substring(1, numOrderMonth.toString().length() - 1)%></div>
                    </div>
                    <div id="chartContainerMonth" style="height: 150px; width: 100%;">
                        <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
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
                    <%List<Object[]> revenueReportYear = (List<Object[]>) request.getAttribute("revenueReportYear");%>
                    <div class="card-body">
                        <div class="card-body-icon">
                            <i class="fa fa-fw fa-list"></i>
                        </div>
                        <div class="mr-5">REVENUE OF YEAR</div>
                        <div class="mr-5" style="font-weight: bold; font-size: 30px;"><%= revenueReportYear.toString().substring(1, revenueReportYear.toString().length() - 1)%></div>
                    </div>
                    <%List<Object[]> numOrderYear = (List<Object[]>) request.getAttribute("numOrderYear");%>
                    <div class="card-body">
                        <div class="card-body-icon">
                            <i class="fa fa-fw fa-list"></i>
                        </div>
                        <div class="mr-5">TOTAL ORDER OF YEAR</div>
                        <div class="mr-5" style="font-weight: bold; font-size: 30px;"><%= numOrderYear.toString().substring(1, numOrderYear.toString().length() - 1)%></div>
                    </div>

                    <div id="chartContainerYear" style="height: 150px; width: 100%;">
                        <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
                    </div>

                </div>
            </div>


        </div>
        <br/>

        <script>
                window.onload = function () {

                var chart12MonthRevenue = new CanvasJS.Chart("chartContainer12MonthRevenue", {
                animationEnabled: true,
                        theme: "light2",
                        title: {
                        text: "Monthly revenue"
                        },
                        axisX: {
                        valueFormatString: "MMM"
                        },
                        axisY: {
                        prefix: "VND ",
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
                                name: "Actual Revenue Monthly",
                                showInLegend: false,
                                xValueFormatString: "MMMM YYYY",
                                yValueFormatString: "VND #,##0",
                                dataPoints: [
            <%List<Object[]> monthlyRevenue = (List<Object[]>) request.getAttribute("monthlyRevenue"); %>
            <% for (Object[] obj : monthlyRevenue) {%>
                                { x: new Date(2017, <%= (int) obj[0] - 1%>), y: <%=  obj[1]%> },
            <% }%>

                                ]
                        }
                        ]
                });
                chart12MonthRevenue.render();
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

                var chart12MonthOrder = new CanvasJS.Chart("chartContainer12MonthOrder", {
                animationEnabled: true,
                        theme: "light2",
                        title: {
                        text: "Monthly total order"
                        },
                        axisX: {
                        valueFormatString: "MMM"
                        },
                        axisY: {
                        prefix: " ",
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
                                name: "Total Order Monthly",
                                showInLegend: false,
                                xValueFormatString: "MMMM YYYY",
                                yValueFormatString: " #,##0",
                                dataPoints: [
            <%List<Object[]> monthlyOrder = (List<Object[]>) request.getAttribute("monthlyOrder"); %>
            <% for (Object[] obj : monthlyOrder) {%>
                                { x: new Date(2017, <%= (int) obj[0] - 1%>), y: <%=  obj[1]%> },
            <% }%>

                                ]
                        }
                        ]
                });
                chart12MonthOrder.render();
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

                var chartYear = new CanvasJS.Chart("chartContainerYear", {
                theme: "light2",
                        animationEnabled: true,
                        title: {
                        text: ""
                        },
                        subtitles: [{
                        text: " ",
                                fontSize: 16
                        }],
                        data: [{

                        type: "pie",
                                indexLabelFontSize: 18,
                                radius: 80,
                                indexLabel: "{label} - {y}",
                                yValueFormatString: "#,##0",
                                click: explodePie,
                                dataPoints: [
            <%List<Object[]> numOrderTypeYear = (List<Object[]>) request.getAttribute("numOrderTypeYear"); %>
            <% for (Object[] obj : numOrderTypeYear) {%>
            <% if ((int) obj[0] == 1) {%>
                                { y: <%=  obj[1]%>, label: "Store" },
            <% } %>
            <% if ((int) obj[0] == 2) {%>
                                { y: <%=  obj[1]%>, label: "Dilivery" },
            <% } %>
            <% if ((int) obj[0] == 3) {%>
                                { y: <%=  obj[1]%>, label: "Other" },
            <% } %>
            <% } %>
                                ]


                        }]
                });
                chartYear.render();
                function explodePie(e) {
                for (var i = 0; i < e.dataSeries.dataPoints.length; i++) {
                if (i !== e.dataPointIndex)
                        e.dataSeries.dataPoints[i].exploded = false;
                }
                }

                var chartMonth = new CanvasJS.Chart("chartContainerMonth", {
                theme: "light2",
                        animationEnabled: true,
                        title: {
                        text: ""
                        },
                        subtitles: [{
                        text: " ",
                                fontSize: 16
                        }],
                        data: [{
                        type: "pie",
                                indexLabelFontSize: 18,
                                radius: 80,
                                indexLabel: "{label} - {y}",
                                yValueFormatString: "#,##0",
                                click: explodePie,
                                dataPoints: [
            <%List<Object[]> numOrderTypeMonth = (List<Object[]>) request.getAttribute("numOrderTypeMonth"); %>
            <% for (Object[] obj : numOrderTypeMonth) {%>
            <% if ((int) obj[0] == 1) {%>
                                { y: <%=  obj[1]%>, label: "Store" },
            <% } %>
            <% if ((int) obj[0] == 2) {%>
                                { y: <%=  obj[1]%>, label: "Dilivery" },
            <% } %>
            <% if ((int) obj[0] == 3) {%>
                                { y: <%=  obj[1]%>, label: "Other" },
            <% } %>
            <% } %>
                                ]
                        }]
                });
                chartMonth.render();
                function explodePie(e) {
                for (var i = 0; i < e.dataSeries.dataPoints.length; i++) {
                if (i !== e.dataPointIndex)
                        e.dataSeries.dataPoints[i].exploded = false;
                }
                }


                var chartDate = new CanvasJS.Chart("chartContainerDate", {
                theme: "light2",
                        animationEnabled: true,
                        title: {
                        text: ""
                        },
                        subtitles: [{
                        text: " ",
                                fontSize: 16
                        }],
                        data: [{
                        type: "pie",
                                indexLabelFontSize: 18,
                                radius: 80,
                                indexLabel: "{label} - {y}",
                                yValueFormatString: "#,##0",
                                click: explodePie,
                                dataPoints: [
            <%List<Object[]> numOrderTypeDate = (List<Object[]>) request.getAttribute("numOrderTypeDate"); %>
            <% for (Object[] obj : numOrderTypeDate) {%>
            <% if ((int) obj[0] == 1) {%>
                                { y: <%=  obj[1]%>, label: "Store" },
            <% } %>
            <% if ((int) obj[0] == 2) {%>
                                { y: <%=  obj[1]%>, label: "Dilivery" },
            <% } %>
            <% if ((int) obj[0] == 3) {%>
                                { y: <%=  obj[1]%>, label: "Other" },
            <% } %>
            <% }%>
                                ]
                        }]
                });
                chartDate.render();
                function explodePie(e) {
                for (var i = 0; i < e.dataSeries.dataPoints.length; i++) {
                if (i !== e.dataPointIndex)
                        e.dataSeries.dataPoints[i].exploded = false;
                }
                }

                }
        </script>
        <br/>
        <div class="row">
             <div class="col-xl-6 col-sm-6 mb-6">
                <div id="chartContainer12MonthOrder" style="height: 250px; width: 100%;">
                    <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
                </div>
            </div>
            <div class="col-xl-6 col-sm-6 mb-6">
                <div id="chartContainer12MonthRevenue" style="height: 250px; width: 100%;">
                    <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
                </div>
                <br/><br/>
            </div>
           
        </div>
   

    <div class="card mb-3">
        <div class="card-header">
            <i class="fa fa-table"></i>Monthly detail</div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>Month</th>
                            <th>Total order </th>
                            <th>Total revenue</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (int i=0; i<monthlyRevenue.size(); i++) {%>
                        <tr>
                            
                           
                           <td>Month <%= monthlyRevenue.get(i)[0] %></td>
                           <td><%= monthlyOrder.get(i)[1] %></td>
                           <td><%= monthlyRevenue.get(i)[1] %></td>
                           
                        </tr>
                         <% }%>
                    </tbody>

                </table>
            </div>
        </div>
    </div>
 </form>
</body>