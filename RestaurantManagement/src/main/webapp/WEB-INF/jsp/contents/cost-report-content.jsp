
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
                </div>
            </div>
        </div>
        <br/>
 </form>
</body>