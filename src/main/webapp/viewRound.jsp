<html>
<head>
    <%@include file="template/head.jsp" %>
    <%@taglib uri="http://doneyTag.com/tags" prefix="jd" %>
</head>
<body>
    <%@include file="template/navbar.jsp"%>
    <c:set var="tournament" value="${round.tournament}" />
    <h1>${tournament.name} (${tournament.year}): Round ${round.roundNumber} ${round.frontVsBack} ${round.numberOfHoles}</h1>
    <a href="${round.coverageLink}">View Round Here</a>
    <h2>Players in Round</h2>
    <c:forEach var="playerFromList" items="${round.playersInRound}">
        <c:set var="player" value="${playerFromList.player}" />
        <jd:Hello player="${player}"/>
    </c:forEach>

    <h2>Commentators</h2>
    <c:forEach var="commentatorsList" items="${round.commentators}">
        <c:set var="commentator" value="${commentatorsList.player}" />
        <jd:Hello player="${commentator}"/>
    </c:forEach>
</body>
</html>
