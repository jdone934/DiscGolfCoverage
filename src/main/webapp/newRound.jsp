<html>
<head>
    <%@include file="template/head.jsp" %>
    <script src="/DiscGolfCoverage/js/searchTournament.js" charset="utf-8"></script>
    <script src="/DiscGolfCoverage/js/searchPlayer.js" charset="utf-8"></script>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
</head>
<body>
<div class="container-fluid">
    <%@include file="template/navbar.jsp"%>

    <h1 class="text-center">Add New Round</h1>

    <form id="newRound" method="post" action="newRound" class="" onsubmit="">
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
                   <c:if test="${not empty roundNumber}">value="${roundNumber}" </c:if> >

        </div>

        <div class="form-group">
            <label for="coverageLink">Coverage Link</label>
            <input type="text" class="form-control" name="coverageLink" id="coverageLink" required
                   <c:if test="${not empty coverageLink}">value="${coverageLink}" </c:if> >

        </div>

        <div class="form-group">
            <label for="coverageProvider">Coverage Provider</label>
            <input type="text" class="form-control" name="coverageProvider" id="coverageProvider" required
                   <c:if test="${not empty coverageProvider}">value="${coverageProvider}" </c:if> >

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
                   <c:if test="${not empty numberOfHoles}">value="${numberOfHoles}" </c:if>
                   <c:if test="${empty numberOfHoles}">value="9" </c:if>>

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
        </div>

        <button type="submit" id="submitButton" class="btn btn-primary">Add Round</button>
    </form>

    <a href="newRound">Add new coverage of a round</a>
</div>
</body>
</html>
