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

    <h2 class="text-center">Add New Content</h2>
    <div class="row">
        <table class="table table-striped col-12 col-sm-8 offset-sm-2">
            <tr><td><a href="newPlayer">New Player</a></td></tr>
            <tr><td><a href="newCourse">New Course</a></td></tr>
            <tr><td><a href="newTournament">New Tournament</a></td></tr>
            <tr><td><a href="newRound">New Round Coverage</a></td></tr>
        </table>
    </div>
</div>

</body>
</html>
