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

        <link href="<c:url value="/resources/css/demo-page.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/examples.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/geochart.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/hover.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/magnific-popup.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
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

        <script src="<c:url value="/resources/js/Chart.min.js" />"></script>
        <script src="<c:url value="/resources/js/bars.js" />"></script>
        <script src="<c:url value="/resources/js/bootstrap.js" />"></script>
        <script src="<c:url value="/resources/js/chartinator.js" />"></script>
        <script src="<c:url value="/resources/js/gmaps.js" />"></script>
        <script src="<c:url value="/resources/js/jquery-2.1.1.min.js" />"></script>

        <script src="<c:url value="/resources/js/jquery.magnific-popup.js" />"></script>
        <script src="<c:url value="/resources/js/jquery.nicescroll.js" />"></script>
        <script src="<c:url value="/resources/js/modernizn.min.js" />"></script>
        <script src="<c:url value="/resources/js/nivo-lightbox.min.js" />"></script>
        <script src="<c:url value="/resources/js/script.js" />"></script>
        <script src="<c:url value="/resources/js/skycons.js" />"></script>

    </body>
</html>
