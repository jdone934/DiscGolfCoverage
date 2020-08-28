<html>
<head>
    <%@include file="template/head.jsp" %>
    <%@taglib uri="http://doneyTag.com/tags" prefix="jd" %>
    <script src="../js/nextPage.js" charset="utf-8"></script>
</head>
<body onload="nextPageInit()">
<div class="container-fluid">
    <%@include file="template/navbar.jsp"%>

    <h1 class="text-center">Add New Course</h1>

    <form id="newPlayer" method="post" action="newCourse" class="" onsubmit="">
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
            <label for="name">Course Name</label>
            <input type="text" class="form-control" name="name" id="name">
        </div>

        <div class="form-group">
            <label for="locationCity">Course City</label>
            <input type="text" class="form-control" name="locationCity" id="locationCity">
        </div>

        <jd:stateSelect selectId="locationState" divClass="form-group"/>

        <div class="form-group">
            <label for="locationCountry">Course Country</label>
            <input type="text" class="form-control" name="locationCountry" id="locationCountry">
        </div>

        <div class="form-group">
            <label for="website">Course Site</label>
            <input type="text" class="form-control" name="website" id="website">
        </div>

        <button type="submit" id="submitButton" class="btn btn-primary">Submit</button>
        <div class="btn btn-primary nextPage" id="newTournament">Add New Tournament</div>
    </form>
</div>
</body>
</body>
</html>
