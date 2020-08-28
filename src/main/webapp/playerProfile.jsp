<html>
<head>
    <%@include file="template/head.jsp" %>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <script src="js/toggleFavorite.js"></script>
</head>
<body class="container-fluid">
    <%@include file="template/navbar.jsp"%>

    <div class="row">
        <h1 class="text-center col-11">${player.firstName} ${player.lastName}</h1>
        <c:if test="${not empty pageContext.request.getRemoteUser()}">
            <c:if test="${not empty favoritePlayer}">
                <i class="material-icons favoriteButton col-1" id="player${player.playerId}">favorite</i>
            </c:if>

            <c:if test="${empty favoritePlayer}">
                <i class="material-icons favoriteButton col-1" id="player${player.playerId}">favorite_border</i>
            </c:if>
        </c:if>
    </div>
    <img src="playerProfilePictures/${player.firstName}${player.lastName}.jpg" class="profilePagePicture mx-auto d-block"/>
    <c:if test="${not empty player.roundsPlayedIn}">
        <h2 class="text-center">Rounds Played In</h2>
        <c:forEach var="roundPlayedIn" items="${player.roundsPlayedIn}" varStatus="loop">
            <p class="text-center">
                <c:set var="round" value="${roundPlayedIn.round}" />
                <c:set var="tournament" value="${round.tournament}" />
                <a href="viewRound?id=${round.roundId}">${tournament.name} (${tournament.year}): Round ${round.roundNumber} ${round.frontVsBack} ${round.numberOfHoles}</a> </br>
            </p>
        </c:forEach>
    </c:if>

    <c:if test="${not empty player.roundsCommentatedIn}">
        <h2 class="text-center">Rounds Commentated On</h2>
        <c:forEach var="roundsCommentatedOn" items="${player.roundsCommentatedIn}">
            <p class="text-center">
                <c:set var="round" value="${roundsCommentatedOn.round}" />
                <c:set var="tournament" value="${round.tournament}" />
                <a href="viewRound?id=${round.roundId}">${tournament.name} (${tournament.year}): Round ${round.roundNumber} ${round.frontVsBack} ${round.numberOfHoles}</a> </br>
            </p>
        </c:forEach>
    </c:if>
</body>
</html>
