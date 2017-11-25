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
        <title>Login Page</title>
    </head>
    <body class="fixed-nav sticky-footer bg-dark" id="page-top">
        <!--Navigation bar-->
        <jsp:include page="navbar.jsp" />
        
        <!--Page content-->
        <div class="content-wrapper">
            <div class="container-fluid">
                <jsp:include page="/WEB-INF/jsp/contents/${param.content}.jsp"/>
            </div>
            <!-- /.container-fluid-->
            <!--Footer-->
            <jsp:include page="footer.jsp" />
            <!-- Scroll to Top Button-->
            <a class="scroll-to-top rounded" href="#page-top">
                <i class="fa fa-angle-up"></i>
            </a>
            <jsp:include page="logout-modal.jsp" />
        </div>
        <!-- /.content-wrapper-->
        <script src="<c:url value="/resources/vendor/jquery/jquery.min.js" />"></script>
        <script src="<c:url value="/resources/vendor/popper/popper.min.js" />"></script>
        <script src="<c:url value="/resources/vendor/bootstrap/js/bootstrap.min.js" />"></script>
        <script src="<c:url value="/resources/vendor/jquery-easing/jquery.easing.min.js" />"></script>
        <script src="<c:url value="/resources/vendor/chart.js/Chart.min.js" />"></script>
        <script src="<c:url value="/resources/vendor/datatables/jquery.dataTables.js" />"></script>
        <script src="<c:url value="/resources/vendor/datatables/dataTables.bootstrap4.js" />"></script>
        <script src="<c:url value="/resources/js/sb-admin.min.js" />"></script>
        <script src="<c:url value="/resources/js/sb-admin-datatables.min.js" />"></script>
        <script src="<c:url value="/resources/js/sb-admin-charts.min.js" />"></script>

    </body>
</html>
