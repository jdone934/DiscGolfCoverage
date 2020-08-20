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
        Profile
    </div>
</div>

<div class="profileMain container">
    <c:set var="user" value="${user}" />

    <c:forEach var="role" items="${user.roles}">
        <c:if test="${role.roleName == 'admin'}">
            <a href="adminOnly/home">Admin Page</a>
        </c:if>
    </c:forEach>

    <h2 class="text-center">Account Information</h2>

    <div class="row">
        <table class="table table-striped col-12 col-sm-8 offset-sm-2">
            <tr>
                <td>User Name</td>
                <td>${user.username}</td>
            </tr>
            <tr>
                <td>Email</td>
                <td>${user.email}</td>
            </tr>
            <tr>
                <td>First Name</td>
                <td>${user.firstName}</td>
            </tr>
            <tr>
                <td>Last Name</td>
                <td>${user.lastName}</td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>