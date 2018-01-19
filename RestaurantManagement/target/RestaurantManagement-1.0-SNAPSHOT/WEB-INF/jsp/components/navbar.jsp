<%-- 
    Document   : navbar
    Created on : Oct 26, 2017, 5:40:57 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
    <a class="navbar-brand" href="/RestaurantManagement/home">Restaurant M@ster</a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
            <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Dashboard">
                <a class="nav-link" href="/RestaurantManagement/home">
                    <i class="fa fa-fw fa-dashboard"></i>
                    <span class="nav-link-text">Home</span>
                </a>
            </li>
            <li class="nav-item" data-toggle="tooltip" data-placement="right" title="FoodManagement">
                <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseFoodManagement" data-parent="#exampleAccordion">
                    <i class="fa fa-fw fa-shopping-cart"></i>
                    <span class="nav-link-text">Food management</span>
                </a>
                <ul class="sidenav-second-level collapse" id="collapseFoodManagement">
                    <li>
                        <a href="/RestaurantManagement/food-catalog">Food catalog</a>
                    </li>
                    <li>
                        <a href="/RestaurantManagement/menu-list">Menu list</a>
                    </li>
                    <li>
                        <a href="/RestaurantManagement/new-menu">New menu</a>
                    </li>
                    <li>
                        <a href="/RestaurantManagement/new-dish">New dish</a>
                    </li>
                </ul>
            </li>
            <li class="nav-item" data-toggle="tooltip" data-placement="right" title="BranchManagement">
                <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseBranchManagement" data-parent="#exampleAccordion">
                    <i class="fa fa-home"></i>
                    <span class="nav-link-text">Branch management</span>
                </a>
                <ul class="sidenav-second-level collapse" id="collapseBranchManagement">
                    <li>
                        <a href="/RestaurantManagement/branch-list">Branch list</a>
                    </li>
                     <li>
                        <a href="/RestaurantManagement/new-branch">New branch</a>
                    </li>
                </ul>
            </li>
            <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Charts">
                <a  class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseReportManagement" data-parent="#exampleAccordion">
                    <i class="fa fa-fw fa-area-chart"></i>
                    <span class="nav-link-text">Report management</span>
                </a>
                 <ul class="sidenav-second-level collapse" id="collapseReportManagement">
                    <li>
                        <a href="/RestaurantManagement/revenue-report">Revenue report</a>
                    </li>
                   
                     <li>
                        <a href="/RestaurantManagement/cost-report">Cost report</a>
                    </li>
                     <li>
                        <a href="/RestaurantManagement/dish-report">Dish report</a>
                    </li>
                    <li>
                        <a href="/RestaurantManagement/customer-report">Customer report</a>
                    </li>
                </ul>
            </li>
        </ul>
        <ul class="navbar-nav sidenav-toggler">
            <li class="nav-item">
                <a class="nav-link text-center" id="sidenavToggler">
                    <i class="fa fa-fw fa-angle-left"></i>
                </a>
            </li>
        </ul>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" data-toggle="modal" data-target="#exampleModal">
                    <i class="fa fa-fw fa-sign-out"></i>Đăng xuất</a>
            </li>
        </ul>
    </div>
</nav>
