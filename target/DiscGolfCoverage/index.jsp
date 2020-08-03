<html>
<head>
    <%@include file="template/head.jsp" %>
</head>
<body>
<div class="container-fluid">
    <%@include file="template/navbar.jsp"%>

    <h1>Find Coverage</h1>

    <form id="findCoverage" method="post" action="searchCoverage" class="" onsubmit="">
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

        <div class="form-group player">
            <label for="firstName">First Name</label>
            <input type="text" class="form-control" name="firstName" id="firstName">
        </div>

        <div class="form-group player">
            <label class="player" for="lastName">Last Name</label>
            <input type="text" class="form-control player" name="lastName" id="lastName">
        </div>

        <button type="submit" id="submitButton" class="btn btn-primary">Search</button>
    </form>
</div>
</body>
</body>
</html>
