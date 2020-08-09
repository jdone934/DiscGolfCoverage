<html>
<head>
    <%@include file="template/head.jsp" %>
</head>
<body class="container-fluid">
    <%@include file="template/navbar.jsp"%>

    <h1>${player.firstName} ${player.lastName}</h1>

    <h2>Rounds</h2>
    <c:forEach var="roundPlayedIn" items="${player.roundsPlayedIn}" varStatus="loop">
        <c:set var="round" value="${roundPlayedIn.round}" />
        <c:set var="tournament" value="${round.tournament}" />
        <a href="${round.coverageLink}">${tournament.name} (${tournament.year}): Round ${round.roundNumber}</a>
    </c:forEach>
</body>
</html>
