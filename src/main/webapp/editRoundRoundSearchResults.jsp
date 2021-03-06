<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <%@ include file="template/head.jsp"%>
    <link rel="stylesheet" href="../style/adminSearchForm.css">
</head>
<body>
<div class="header">
    <%@ include file="template/navbar.jsp"%>
    <div class="display-4 text-center">
        Edit Round Search Results
    </div>
</div>

<div class="editRecipesResults container">
    <h1 class="text-center">Rounds</h1>
    <c:forEach var="round" items="${rounds}" varStatus="loop">
        <p class="text-center">
            <a href="editRound?roundId=${round.roundId}">${round.tournament.name}: Round ${round.roundNumber} ${round.frontVsBack}
                    ${round.numberOfHoles} by ${round.coverageProvider}</a>
        </p>
    </c:forEach>
</div>

</body>
</html>
