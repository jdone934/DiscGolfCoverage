<html>
<head>
    <%@include file="template/head.jsp" %>
    <link rel="stylesheet" href="style/searchForm.css" />
</head>
<body>
<div class="container-fluid">
    <%@include file="template/navbar.jsp"%>

    <h1 class="text-center">Add New Tournament</h1>

    <form id="newPlayer" method="post" action="newTournament" class="" onsubmit="" enctype="multipart/form-data">
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
            <input type="text" class="form-control", name="tournamentName" id="tournamentName">
        </div>

        <div class="form-group">
            <label for="year">Year</label>
            <input type="number" class="form-control", name="year" id="year" value="2020">
        </div>

        <div class="form-group">
            <label for="tournamentSeries">Tournament Series</label>
            <input type="text" class="form-control", name="tournamentSeries" id="tournamentSeries">
        </div>

        <div class="form-group">
            <label for="courseSearch">Courses</label>
            <input type="text" class="form-control", name="courseSearch" id="courseSearch">
            <div id="searchForCourses" class="btn btn-primary">Search</div>
        </div>

        <button type="submit" id="submitButton" class="btn btn-primary">Submit</button>
    </form>

    <a href="newCoverageProvider">Add a new Coverage Provider</a>
</div>
</body>
</body>
</html>
