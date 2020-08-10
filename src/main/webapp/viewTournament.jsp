<html>
<head>
    <%@include file="template/head.jsp" %>
</head>
<body class="container-fluid">
    <%@include file="template/navbar.jsp"%>

    <h1>${tournament.name}</h1>

    <c:if test="${not empty tournament.rounds}">
        <h2>Rounds</h2>
        <c:forEach var="round" items="${tournament.rounds}">
            <div class="row">
                <div class="col-4">
                    <a href="viewRound?id=${round.roundId}">
                        Round ${round.roundNumber} ${round.frontVsBack} ${round.numberOfHoles}
                    </a>
                </div>
                <div class="col-4">
                    Put stuff for which card (if filled in)
                </div>
                <div class="col-4">
                    ${round.coverageProvider}
                </div>
            </div>
            <div class="row">
                Players:
                <c:forEach var="playerListItem" items="${round.playersInRound}" varStatus="status">
                    <c:set var="player" value="${playerListItem.player}" />
                    <a href="playerProfile?id=${player.playerId}">
                        &nbsp;${player.firstName} ${player.lastName}<c:if test="${not status.last}">,</c:if>
                    </a>
                </c:forEach> 
            </div>

            <div class="row">
                Commentators:
                <c:forEach var="playerListItem" items="${round.commentators}" varStatus="status">
                    <c:set var="commentator" value="${playerListItem.player}" />
                    <a href="playerProfile?id=${commentator.playerId}">
                        &nbsp;${commentator.firstName} ${commentator.lastName}<c:if test="${not status.last}">,</c:if>
                    </a>
                </c:forEach>
            </div>
        </c:forEach>
    </c:if>
</body>
</html>
