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
    <c:forEach var="tournament" items="${tournaments}" varStatus="loop">
        <h2>${tournament.name}</h2>
        <c:forEach var="round" items="${tournament.rounds}" varStatus="loop">
            <div class="row">
                <div class="col-4">
                    <a href="editRound?roundId=${round.roundId}">
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
    </c:forEach>
</div>

</body>
</html>
