<html>
<head>
    <%@include file="template/head.jsp" %>
    <script src="js/formControl.js"></script>
    <link rel="stylesheet" href="style/searchForm.css" />
</head>
<body>
<div class="container-fluid">
    <%@include file="template/navbar.jsp"%>

    <h1>Find Coverage</h1>

    <form id="findCoverage" method="post" action="searchByPlayer" class="" onsubmit="">
        <div id="errorMessage"></div>
        <div class="form-group">
            <label for="searchType">Search Type</label>
            <select id="searchType" class="form-control" name="searchType">
                <option value="player">Player</option>
                <option value="tournament">Tournament</option>
                <option value="course">Course</option>
                <option value="year">Year</option>
            </select>
        </div>

        <div class="form-group player searchField">
            <label for="firstName">First Name</label>
            <input type="text" class="form-control" name="firstName" id="firstName">
        </div>

        <div class="form-group player searchField">
            <label for="lastName">Last Name</label>
            <input type="text" class="form-control" name="lastName" id="lastName">
        </div>

        <div class="form-group tournament searchField">
            <label class="player" for="tournamentName">Tournament Name</label>
            <input type="text" class="form-control" name="tournamentName" id="tournamentName">
        </div>

        <div class="form-group tournament searchField">
            <label class="player" for="tournamentYear">Tournament Year</label>
            <input type="number" class="form-control" name="tournamentYear" id="tournamentYear">
        </div>



        <button type="submit" id="submitButton" class="btn btn-primary">Search</button>
    </form>
</div>
</body>
</body>
</html>
