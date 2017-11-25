<%-- 
    Document   : error
    Created on : Jun 4, 2017, 6:20:31 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/resources/css/bootstrap-social.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/bootstrap-theme.min.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/font-awesome.min.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/metisMenu.min.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/startmin.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/morris.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
        
        <script src="<c:url value="/resources/javascripts/jquery.min.js" />"></script>
        <script src="<c:url value="/resources/javascripts/bootstrap.min.js" />"></script>
        <script src="<c:url value="/resources/javascripts/metisMenu.min.js" />"></script>
        <script src="<c:url value="/resources/javascripts/morris-data.js" />"></script>
        <script src="<c:url value="/resources/javascripts/morris.min.js" />"></script>
        <script src="<c:url value="/resources/javascripts/raphael.min.js" />"></script>
        <script src="<c:url value="/resources/javascripts/startmin.js" />"></script>
        <title>Error</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <div class="login-panel panel panel-default panel-danger">
                        <div class="panel-heading">
                            <h3 class="panel-title">Error</h3>
                        </div>
                        <div class="panel-body">
                            <p>You must login as right role first!</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
