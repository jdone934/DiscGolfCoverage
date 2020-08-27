<html>
<head>
    <%@include file="template/head.jsp" %>
    <%@taglib uri="http://doneyTag.com/tags" prefix="jd" %>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <script src="js/toggleFavorite.js"></script>
</head>
<body>
    <%@include file="template/navbar.jsp"%>
    <c:set var="tournament" value="${round.tournament}" />
    <h1>${tournament.name} (${tournament.year}): Round ${round.roundNumber} ${round.frontVsBack} ${round.numberOfHoles}</h1>
    <iframe id="ytplayer" type="text/html" width="640" height="360"
            allowfullscreen="allowfullscreen"
            src="${embedUrl}"></iframe>

    <h2>Players in Round</h2>
    <c:forEach var="playerFromList" items="${round.playersInRound}">
        <c:set var="player" value="${playerFromList.player}" />
        <c:if test="${not empty user}">
            <jd:playerCard player="${player}" user="${user}"/>
        </c:if>
        <c:if test="${empty user}">
            <jd:playerCard player="${player}"/>
        </c:if>
    </c:forEach>

    <h2>Commentators</h2>
    <c:forEach var="commentatorsList" items="${round.commentators}">
        <c:set var="commentator" value="${commentatorsList.player}" />
        <c:if test="${not empty user}">
            <jd:playerCard player="${commentator}" user="${user}"/>
        </c:if>
        <c:if test="${empty user}">
            <jd:playerCard player="${commentator}"/>
        </c:if>
    </c:forEach>
</body>
</html>
