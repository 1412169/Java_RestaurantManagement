
<%@page import="Entity.Branch"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Breadcrumbs-->
<!-- Breadcrumbs-->
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="#">Home</a>
    </li>
</ol>

<!-- Icon Cards-->
<div class="row">
    <%List<Object[]> revenueReportDate = (List<Object[]>) request.getAttribute("revenueReportDate");%>
    <div class="col-xl-4 col-sm-6 mb-4">
        <div class="card text-white bg-warning o-hidden h-100">
            <div class="card-body">
                <div class="card-body-icon">
                    <i class="fa fa-fw fa-list"></i>
                </div>
                <div class="mr-5">REVENUE OF TODAY</div>
                <div class="mr-5"><%= revenueReportDate.toString().substring(1, revenueReportDate.toString().length() - 1)%></div>
            </div>
    <%List<Object[]> numOrderDate = (List<Object[]>) request.getAttribute("numOrderDate");%>
            <div class="card-body">
                <div class="card-body-icon">
                    <i class="fa fa-fw fa-list"></i>
                </div>
                <div class="mr-5">TOTAL ORDER OF TODAY</div>
                <div class="mr-5"><%= numOrderDate.toString().substring(1, numOrderDate.toString().length() - 1)%></div>
            </div>
        </div>
    </div>

    <%List<Object[]> revenueReportMonth = (List<Object[]>) request.getAttribute("revenueReportMonth");%>
    <div class="col-xl-4 col-sm-6 mb-4">
        <div class="card text-white bg-success o-hidden h-100">
            <div class="card-body">
                <div class="card-body-icon">
                    <i class="fa fa-fw fa-shopping-cart"></i>
                </div>
                <div class="mr-5">REVENUE OF MONTH</div>
                <div class="mr-5"><%= revenueReportMonth.toString().substring(1, revenueReportMonth.toString().length() - 1)%></div>
            </div>
             <%List<Object[]> numOrderMonth = (List<Object[]>) request.getAttribute("numOrderMonth");%>
            <div class="card-body">
                <div class="card-body-icon">
                    <i class="fa fa-fw fa-shopping-cart"></i>
                </div>
                <div class="mr-5">TOTAL ORDER OF MONTH</div>
                <div class="mr-5"><%= numOrderMonth.toString().substring(1, numOrderMonth.toString().length() - 1)%></div>
            </div>
        </div>
    </div>
    <div class="col-xl-4 col-sm-6 mb-4">
        <div class="card text-white bg-primary o-hidden h-100">
            <%List<Object[]> revenueReportYear = (List<Object[]>) request.getAttribute("revenueReportYear");%>
            <div class="card-body">
                <div class="card-body-icon">
                    <i class="fa fa-fw fa-comments"></i>
                </div>
                <div class="mr-5">REVENUE OF YEAR</div>
                <div class="mr-5"><%= revenueReportYear.toString().substring(1, revenueReportYear.toString().length() - 1)%></div>
            </div>
             <%List<Object[]> numOrderYear = (List<Object[]>) request.getAttribute("numOrderYear");%>
            <div class="card-body">
                <div class="card-body-icon">
                    <i class="fa fa-fw fa-comments"></i>
                </div>
                <div class="mr-5">TOTAL ORDER OF YEAR</div>
                <div class="mr-5"><%= numOrderYear.toString().substring(1, numOrderYear.toString().length() - 1)%></div>
            </div>
        </div>
    </div>

</div>
<br/>
<div class="row">
    <div class="col-xl-4 col-sm-6 mb-4">
        <div id="chartContainerDate" style="height: 150px; width: 100%;">
            <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
        </div>
    </div>
    <div class="col-xl-4 col-sm-6 mb-4">
        <div id="chartContainerMonth" style="height: 150px; width: 100%;">
            <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
        </div>
    </div>
    <div class="col-xl-4 col-sm-6 mb-4">
        <div id="chartContainerYear" style="height: 150px; width: 100%;">
            <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
        </div>
    </div>

</div>
<br/><br/><br/>
<script>
    window.onload = function () {

    var chartRevenueBranch = new CanvasJS.Chart("chartRevenueBranch", {
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
                    name: "Actual Revenue",
                    showInLegend: false,
                    xValueFormatString: "MMMM YYYY",
                    yValueFormatString: "VND #,##0",
                    dataPoints: [
    <%List<Object[]> monthlyRevenueBranch = (List<Object[]>) request.getAttribute("monthlyRevenueBranch"); %>
    <% for (Object[] obj : monthlyRevenueBranch) {%>
                    { x: new Date(2017, <%= (int) obj[0] - 1%>), y: <%=  obj[1]%> },
    <% }%>
                    ]
            }]
    });
    chartRevenueBranch.render();
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


    var chartOrderBranch = new CanvasJS.Chart("chartOrderBranch", {
    animationEnabled: true,
            theme: "light2",
            title: {
            text: "Monthly total order"
            },
            axisX: {
            valueFormatString: "MMM"
            },
            axisY: {
            prefix: "",
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
                    yValueFormatString: "#,##0",
                    dataPoints: [
                    <%List<Object[]> monthlyOrderBranch = (List<Object[]>) request.getAttribute("monthlyOrderBranch"); %>
                    <% for (Object[] obj : monthlyOrderBranch) {%>
                        { x: new Date(2017, <%= (int) obj[0] - 1%>), y: <%=  obj[1]%> },
                    <% }%>
                    ]
            }]
    });
    chartOrderBranch.render();
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
            text: "Shares of Electricity Generation by Fuel"
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
            text: "Shares of Electricity Generation by Fuel"
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
            text: "Shares of Electricity Generation by Fuel"
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

<div class="row">
    <div class="col-xl-6 col-sm-6 mb-6">
        <div id="chartRevenueBranch" style="height: 250px; width: 100%;">
    <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</div>
    <br/><br/>
    </div>
    <div class="col-xl-6 col-sm-6 mb-6">
       <div id="chartOrderBranch" style="height: 250px; width: 100%;">
    <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</div>
    </div>
</div>
<br/><br/> 

<br/><br/>  

