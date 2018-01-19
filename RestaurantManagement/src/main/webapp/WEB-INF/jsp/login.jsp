<%-- 
    Document   : login
    Created on : May 29, 2017, 6:11:28 PM
    Author     : USER
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/resources/vendor/bootstrap/css/bootstrap.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/vendor/font-awesome/css/font-awesome.min.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/vendor/datatables/dataTables.bootstrap4.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/sb-admin.css" />" rel="stylesheet">
        <title>Restaurant M@ster</title>
    </head>
    <body class="bg-dark">
        <div class="container">
            <div class="card card-login mx-auto mt-5">
                <div class="card-header">Login</div>
                <div class="card-body">
                    <form name='f' action="/RestaurantManagement/login" method='POST'>
                        <%String message = (String) request.getAttribute("message");%>
                        <span style="color: red; text-align: center"> <%= message%> </span>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Email</label>
                            <input class="form-control" input type='text' name='username' aria-describedby="emailHelp" placeholder="">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">Password</label>
                            <input class="form-control" type='password' name='password' placeholder="">
                        </div>
                        
                        <!--<a class="btn btn-primary btn-block" href="/RestaurantManagement/home">Login</a>-->
                        <input class="btn btn-primary btn-block" type="submit" value="Login">
                    </form>
                    <!--                    <div class="text-center">
                                            <a class="d-block small mt-3" href="register.html">Register an Account</a>
                                            <a class="d-block small" href="forgot-password.html">Forgot Password?</a>
                                        </div>-->
                </div>
            </div>
        </div>
        <script src="<c:url value="/resources/vendor/jquery/jquery.min.js" />"></script>
        <script src="<c:url value="/resources/vendor/popper/popper.min.js" />"></script>
        <script src="<c:url value="/resources/vendor/bootstrap/js/bootstrap.min.js" />"></script>
        <script src="<c:url value="/resources/vendor/jquery-easing/jquery.easing.min.js" />"></script>
        <script src="<c:url value="/resources/vendor/chart.js/Chart.min.js" />"></script>
        <script src="<c:url value="/resources/vendor/datatables/jquery.dataTables.js" />"></script>
        <script src="<c:url value="/resources/vendor/datatables/dataTables.bootstrap4.js" />"></script>
        <script src="<c:url value="/resources/js/sb-admin.min.js" />"></script>

    </body>
</html>
