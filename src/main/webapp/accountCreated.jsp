<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@include file="/template/head.jsp"%>
</head>
<body>
<div class="header">
    <%@include file="/template/navbar.jsp"%>
    <div class="display-4 text-center">
        Account Created
    </div>
</div>

<div class="accountCreatedMain container">
    <div class="alert alert-success" role="alert">
        Your account was successfully created! Use the button bellow to log in and return to the website.
    </div>

    <c:set var="path" value="${path}" />
    <a href="/DiscGolfCoverage/login">
        <button type="submit" class="btn btn-primary">Log In</button>
    </a>
</div>
</body>
</html>
