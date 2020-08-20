<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@include file="/template/head.jsp"%>
</head>
<body>
<div class="header">
    <nav class="navbar navbar-expand-sm navbar-dark">
        <a href="/DiscGolfCoverage" id="homeLink">Easy Cook</a>
    </nav>
    <div class="display-4 text-center">
        Sign Up
    </div>
</div>

<div class="signUpMain container">
    <c:set var="message" value="${errorMessage}"/>
    <c:if test="${not empty message}">
        <div class="alert alert-danger" role="alert">
                ${message}
        </div>
    </c:if>
    <form method="POST" action="signUp" class="col-md-8 offset-md-2 col-lg-6 offset-lg-3">
        <c:set var="username" value="${username}" />
        <c:set var="email" value="${email}" />
        <c:set var="firstName" value="${firstName}" />
        <c:set var="lastName" value="${lastName}" />

        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" class="form-control" name="username" id="username" value="${username}">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" name="password" id="password">
        </div>
        <div class="form-group">
            <label for="confirmPassword">Confirm Password</label>
            <input type="password" class="form-control" name="confirmPassword" id="confirmPassword">
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="text" class="form-control" name="email" id="email" value="${email}">
        </div>
        <div class="form-group">
            <label for="firstName">First Name</label>
            <input type="text" class="form-control" name="firstName" id="firstName" value="${firstName}">
        </div>
        <div class="form-group">
            <label for="lastName">Last Name</label>
            <input type="text" class="form-control" name="lastName" id="lastName" value="${lastName}"/>
        </div>

        <c:set var="path" value="${path}" />
        <input type="hidden" name="path" value="${path}">
        <button type="submit" class="btn btn-primary">Create User</button>
    </form>
</div>

</body>
</html>