<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <%@ include file="template/head.jsp"%>
    <link rel="stylesheet" href="../style/adminSearchForm.css">
    <script src="../js/adminHomeFormControl.js"></script>
</head>
<body>
<div class="header">
    <%@ include file="template/navbar.jsp"%>
    <div class="display-4 text-center">
        Edit Recipes Search Results
    </div>
</div>

<div class="editRecipesResults container">
    <h1>Rounds</h1>
    <c:forEach var="round" items="${rounds}" varStatus="loop">
        <div class="row">
            <a href="editRound?roundId=${round.roundId}">${round.tournament.name} ${round.frontVsBack} ${round.numberOfHoles} by ${round.coverageProvider}</a>
        </div>
    </c:forEach>
</div>

</body>
</html>
