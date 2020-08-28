<html>
<head>
    <%@include file="template/head.jsp" %>
    <script src="/DiscGolfCoverage/js/searchTournament.js" charset="utf-8"></script>
    <script src="/DiscGolfCoverage/js/searchPlayer.js" charset="utf-8"></script>
    <script src="/DiscGolfCoverage/js/nextPage.js" charset="utf-8"></script>
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
                <option value="Front">Front</option>
                <option value="Back">Back</option>
            </select>
        </div>

        <div class="form-group">
            <label for="numberOfHoles">Number Of Holes</label>
            <input type="number" class="form-control" name="numberOfHoles" id="numberOfHoles" required
                   <c:if test="${not empty numberOfHoles}">value="${numberOfHoles}" </c:if>
                   <c:if test="${empty numberOfHoles}">value="9" </c:if>>

        </div>

        <h2>Find Tournament</h2>

        <div class="form-group">
            <label for="tournamentSearch">Tournament</label>
            <input type="text" class="form-control" name="tournamentSearch" id="tournamentSearch">
            <div id="searchForTournaments" class="btn btn-primary" tabindex="0">Search</div>
        </div>

        <div id="tournamentSearchResults">

        </div>

        <div id="addedTournament">
            <h3>Added Tournament</h3>
        </div>

        <h2>Find Player(s)</h2>

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
            <h3>Added Player(s)</h3>
        </div>

        <h2>Find Commentator(s)</h2>

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
            <h3>Added Commentator(s)</h3>
        </div>

        <button type="submit" id="newRoundSubmit" class="btn btn-primary">Add Round</button>
        <div class="btn btn-primary nextPage nextPageMargin" id="home">Admin Home</div>
    </form>
</div>
</body>
</html>
