<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <%@ include file="template/head.jsp"%>
    <script src="/DiscGolfCoverage/js/searchTournament.js" charset="utf-8"></script>
    <script src="/DiscGolfCoverage/js/searchPlayer.js" charset="utf-8"></script>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
</head>
<body>
<div class="header">
    <%@ include file="template/navbar.jsp"%>
    <div class="display-4 text-center">
        Edit Round
    </div>
</div>

<div class="adminMain container">
<form id="newRound" method="post" action="editRound">
    <div id="errorMessage">
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger" role="alert">
                    ${errorMessage}
            </div>
        </c:if>
    </div>
    <div id="successMessage">
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success" role="alert">
                    ${successMessage}
            </div>
        </c:if>
    </div>

    <div class="form-group">
        <label for="roundNumber">Round Number</label>
        <input type="number" class="form-control" name="roundNumber" id="roundNumber" required
               <c:if test="${not empty round.roundNumber}">value="${round.roundNumber}" </c:if> >

    </div>

    <div class="form-group">
        <label for="coverageLink">Coverage Link</label>
        <input type="text" class="form-control" name="coverageLink" id="coverageLink" required
               <c:if test="${not empty round.coverageLink}">value="${round.coverageLink}" </c:if> >

    </div>

    <div class="form-group">
        <label for="coverageProvider">Coverage Provider</label>
        <input type="text" class="form-control" name="coverageProvider" id="coverageProvider" required
               <c:if test="${not empty round.coverageProvider}">value="${round.coverageProvider}" </c:if> >

    </div>

    <div class="form-group">
        <label for="frontVsBack">Front or Back</label>
        <select type="select" class="form-control" name="frontVsBack" id="frontVsBack">
            <option value="front">Front</option>
            <option value="back">Back</option>
        </select>
    </div>

    <div class="form-group">
        <label for="numberOfHoles">Number Of Holes</label>
        <input type="number" class="form-control" name="numberOfHoles" id="numberOfHoles" required
               <c:if test="${not empty round.numberOfHoles}">value="${round.numberOfHoles}" </c:if>
    </div>

    <div class="form-group">
        <label for="tournamentSearch">Tournament</label>
        <input type="text" class="form-control" name="tournamentSearch" id="tournamentSearch">
        <div id="searchForTournaments" class="btn btn-primary" tabindex="0">Search</div>
    </div>

    <div id="tournamentSearchResults">

    </div>

    <div id="addedTournament">
        <h2>Added Tournament</h2>
        <label class="tournamentResult d-flex justify-content-center checkbox-label">
            <c:set var="tournament" value="${round.tournament}" />
            <input type="checkbox" name="tournamentForRound" value="${tournament.tournamentId}" class="addedCourse">
            <a href="/DiscGolfCoverage/viewTournament?id=${tournament.tournamentId}" target="_blank">${tournament.name}</a>
        </label>
    </div>

    <div class="form-group">
        <label for="playerFirstName">Player First Name</label>
        <input type="text" class="form-control" name="playerFirstName" id="playerFirstName">
        <label for="playerLastName">Player Last Name</label>
        <input type="text" class="form-control" name="playerLastName" id="playerLastName">
        <div id="searchForPlayers" class="btn btn-primary" tabindex="0">Search</div>
    </div>

    <div id="playerSearchResults">

    </div>

    <div id="addedPlayers">
        <h2>Added Players</h2>
        <c:forEach var="playerConnector" items="${round.playersInRound}" varStatus="loop">
            <c:set var="player" value="${playerConnector.player}" />
            <label class="playerResult d-flex justify-content-center checkbox-label">
                <input type="checkbox" name="playersInRound" class="addedPlayer" value="${player.playerId}">
                <a href="/DiscGolfCoverage/playerProfile?id=${player.playerId}" target="_blank">${player.firstName} ${player.lastName}</a>
                <i class="material-icons addOrRemoveButton">delete</i>
            </label>
        </c:forEach>
    </div>

    <div class="form-group">
        <label for="commentatorFirstName">Commentator First Name</label>
        <input type="text" class="form-control" name="commentatorFirstName" id="commentatorFirstName">
        <label for="commentatorLastName">Commentator Last Name</label>
        <input type="text" class="form-control" name="commentatorLastName" id="commentatorLastName">
        <div id="searchForCommentators" class="btn btn-primary" tabindex="0">Search</div>
    </div>

    <div id="commentatorSearchResults">

    </div>

    <div id="addedCommentators">
        <h2>Added Commentators</h2>
        <c:forEach var="commentatorConnector" items="${round.commentators}" varStatus="loop">
            <c:set var="commentator" value="${commentatorConnector.player}" />
            <label class="playerResult d-flex justify-content-center checkbox-label">
                <input type="checkbox" name="playersInRound" class="addedPlayer" value="${commentator.playerId}">
                <a href="/DiscGolfCoverage/playerProfile?id=${commentator.playerId}" target="_blank">${commentator.firstName} ${commentator.lastName}</a>
                <i class="material-icons addOrRemoveButton">delete</i>
            </label>
        </c:forEach>
    </div>

    <button type="submit" id="submitButton" class="btn btn-primary">Add Round</button>
</form>
</div>

</body>
</html>
