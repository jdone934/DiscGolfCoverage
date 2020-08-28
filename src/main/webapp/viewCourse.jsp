<html>
<head>
    <%@include file="template/head.jsp" %>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <script src="js/toggleFavorite.js"></script>
</head>
<body>
    <%@include file="template/navbar.jsp"%>
    <h1 class="text-center">${course.name}&nbsp;

    <c:if test="${not empty pageContext.request.getRemoteUser()}">
        <c:if test="${not empty favoriteCourse}">
            <i class="material-icons favoriteButton" id="course${course.courseId}">favorite</i>
        </c:if>

        <c:if test="${empty favoriteCourse}">
            <i class="material-icons favoriteButton" id="course${course.courseId}">favorite_border</i>
        </c:if>
    </c:if>
    </h1>

    <div class="row justify-content-center">
        <c:if test="${course.locationCountry == 'US'}">
            ${course.locationCity}, ${course.locationState}
        </c:if>
        <c:if test="${course.locationCountry != 'US'}">
            ${course.locationCity} ${course.locationCountry}
        </c:if>
    </div>

    <c:set var="tournamentsAtCourseList" value="${course.tournamentsAtCourse}" />
    <c:if test="${not empty tournamentsAtCourseList}">
        <h2 class="text-center">Tournaments</h2>
        <c:forEach var="tournamentList" items="${tournamentsAtCourseList}">
            <c:set var="tournament" value="${tournamentList.tournament}" />
            <div class="row justify-content-center">
                <div>
                    <a href="viewTournament?id=${tournament.tournamentId}">
                            ${tournament.name} (${tournament.year})
                    </a>
                </div>
                <div>
                    ${tournament.tournamentSeries}
                </div>
                <div>
                    <c:if test="${not empty tournament.website}">
                        <a href="${tournament.website}">Tournament Website</a>
                    </c:if>
                </div>
            </div>
        </c:forEach>
    </c:if>
</body>
</html>
