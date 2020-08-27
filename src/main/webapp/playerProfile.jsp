<html>
<head>
    <%@include file="template/head.jsp" %>
</head>
<body class="container-fluid">
    <%@include file="template/navbar.jsp"%>

    <h1>${player.firstName} ${player.lastName}</h1>
    <c:if test="${not empty pageContext.request.getRemoteUser()}">
        <c:if test="${not empty favoritePlayer}">
            <i class="material-icons" onclick="toggleFavorite(${player.playerId})">favorite</i>
        </c:if>

        <c:if test="${empty favoritePlayer}">
            <i class="material-icons" onclick="toggleFavorite(${player.playerId})">favorite_border</i>
        </c:if>
    </c:if>
    <c:if test="${not empty player.roundsPlayedIn}">
        <h2>Rounds Played In</h2>
        <c:forEach var="roundPlayedIn" items="${player.roundsPlayedIn}" varStatus="loop">
            <c:set var="round" value="${roundPlayedIn.round}" />
            <c:set var="tournament" value="${round.tournament}" />
            <a href="viewRound?id=${round.roundId}">${tournament.name} (${tournament.year}): Round ${round.roundNumber} ${round.frontVsBack} ${round.numberOfHoles}</a> </br>
        </c:forEach>
    </c:if>

    <c:if test="${not empty player.roundsCommentatedIn}">
        <h2>Rounds Commentated On</h2>
        <c:forEach var="roundsCommentatedOn" items="${player.roundsCommentatedIn}">
            <c:set var="round" value="${roundsCommentatedOn.round}" />
            <c:set var="tournament" value="${round.tournament}" />
            <a href="viewRound?id=${round.roundId}">${tournament.name} (${tournament.year}): Round ${round.roundNumber} ${round.frontVsBack} ${round.numberOfHoles}</a> </br>
        </c:forEach>
    </c:if>
</body>
</html>
