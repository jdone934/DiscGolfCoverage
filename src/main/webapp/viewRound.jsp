<html>
<head>
    <%@include file="template/head.jsp" %>
</head>
<body>
    <%@include file="template/navbar.jsp"%>
    <c:set var="tournament" value="${round.tournament}" />
    <h1>${tournament.name} (${tournament.year}): Round ${round.roundNumber} ${round.frontVsBack} ${round.numberOfHoles}</h1>
    <a href="${round.coverageLink}">View Round Here</a>
    <h2>Players in Round</h2>
    <c:forEach var="playerFromList" items="${round.playersInRound}">
        <c:set var="player" value="${playerFromList.player}" />
        <div class="card" style="width: 18rem;">
            <img class="card-img-top" src="playerProfilePictures/${player.profilePicture}" alt="player profile picture">
            <div class="card-body">
                <a class="card-text" href="playerProfile?id=${player.playerId}">${player.firstName} ${player.lastName}</a>
            </div>
        </div>
    </c:forEach>

    <h2>Commentators</h2>
    <c:forEach var="commentatorsList" items="${round.commentators}">
        <c:set var="commentator" value="${commentatorsList.player}" />
        <div class="card" style="width: 18rem;">
            <img class="card-img-top" src="playerProfilePictures/${commentator.profilePicture}" alt="player profile picture">
            <div class="card-body">
                <a class="card-text" href="playerProfile?id=${commentator.playerId}">${commentator.firstName} ${commentator.lastName}</a>
            </div>
        </div>
    </c:forEach>
</body>
</html>
