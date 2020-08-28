<html>
<head>
    <%@include file="template/head.jsp" %>
    <script src="/DiscGolfCoverage/js/searchCourse.js" charset="utf-8"></script>
    <script src="/DiscGolfCoverage/js/nextPage.js" charset="utf-8"></script>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
</head>
<body>
<div class="container-fluid">
    <%@include file="template/navbar.jsp"%>

    <h1 class="text-center">Add New Tournament</h1>

    <form id="newTournament" method="post" action="newTournament" class="" onsubmit="">
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
            <label for="tournamentName">Tournament Name</label>
            <input type="text" class="form-control" name="tournamentName" id="tournamentName" required
            <c:if test="${not empty tournamentName}">value="${tournamentName}" </c:if> >

        </div>

        <div class="form-group">
            <label for="year">Year</label>
            <input type="number" class="form-control" name="year" id="year" value="2020" required
                   <c:if test="${not empty year}">value="${year}" </c:if>>
        </div>

        <div class="form-group">
            <label for="tournamentSeries">Tournament Series</label>
            <input type="text" class="form-control" name="tournamentSeries" id="tournamentSeries"
                   <c:if test="${not empty tournamentSeries}">value="${tournamentSeries}" </c:if>>
        </div>

        <div class="form-group">
            <label for="website">Tournament Website</label>
            <input type="text" class="form-control" name="website" id="website"
                   <c:if test="${not empty website}">value="${website}" </c:if>>
        </div>

        <h2>Find Course(s)</h2>

        <div class="form-group">
            <label for="courseSearch">Courses</label>
            <input type="text" class="form-control" name="courseSearch" id="courseSearch">
            <div id="searchForCourses" class="btn btn-primary" tabindex="0">Search</div>
        </div>

        <div id="searchResults">

        </div>

        <div id="addedCourses">
            <h3>Added Course(s)</h3>
        </div>

        <button type="submit" id="newTournamentSubmit" class="btn btn-primary">Add Tournament</button>
        <div class="btn btn-primary nextPage nextPageMargin" id="newRound">Add New Round</div>
    </form>
</div>
</body>
</html>
