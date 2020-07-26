<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ page isELIgnored="false"%>
</head>
<body>
<h2>Hello World!</h2>
<c:set var="testvar" value="37" />
<h3>Value in variable: ${testvar}</h3>
</body>
</html>
