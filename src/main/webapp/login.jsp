
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@include file="/template/head.jsp"%>
</head>
<body>

<div class="header">
    <nav class="navbar navbar-expand-sm navbar-dark">
        <a href="/CookingRecipes" id="homeLink">Easy Cook</a>
    </nav>
    <div class="display-4 text-center">
        Log In
    </div>
</div>

<div class="container">
    <form action="j_security_check" method="POST" class="col-md-8 offset-md-2 col-lg-6 offset-lg-3">
        <div class="form-group">
            <label for="username">User name</label>
            <input type="text" class="form-control" name="j_username" id="username">
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" name="j_password" id="password">
        </div>

        <button type="submit" class="btn btn-primary" value="Log In">Login</button>
    </form>
</div>

</body>
</html>
