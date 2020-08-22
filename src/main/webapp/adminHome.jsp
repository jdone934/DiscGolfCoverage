<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <%@ include file="template/head.jsp"%>
</head>
<body>
<div class="header">
    <%@ include file="template/navbar.jsp"%>
    <div class="display-4 text-center">
        Admin Home
    </div>
</div>

<div class="adminMain container">
    <c:set var="user" value="${user}" />

    <div class="row">
        <table class="table table-striped col-12 col-sm-8 offset-sm-2">

        </table>
    </div>
</div>

</body>
</html>
