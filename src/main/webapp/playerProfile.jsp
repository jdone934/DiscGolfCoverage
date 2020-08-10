<html>
<head>
    <%@include file="template/head.jsp" %>
</head>
<body class="container-fluid">
    <%@include file="template/navbar.jsp"%>

    <h1>${player.firstName} ${player.lastName}</h1>
    <h2>Rounds Played In</h2>
    <c:forEach var="roundPlayedIn" items="${player.roundsPlayedIn}" varStatus="loop">
        <c:set var="round" value="${roundPlayedIn.round}" />
        <c:set var="tournament" value="${round.tournament}" />
        <a href="viewRound?id=${round.roundId}">${tournament.name} (${tournament.year}): Round ${round.roundNumber} ${round.frontVsBack} ${round.numberOfHoles}</a> </br>
    </c:forEach>

    <h2>Rounds Commentated On</h2>
    <c:forEach var="roundsCommentatedOn" items="${player.roundsCommentatedIn}">
        <c:set var="round" value="${roundsCommentatedOn.round}" />
        <c:set var="tournament" value="${round.tournament}" />
        <a href="viewRound?id=${round.roundId}">${tournament.name} (${tournament.year}): Round ${round.roundNumber} ${round.frontVsBack} ${round.numberOfHoles}</a> </br>
    </c:forEach>
</body>
</html>
