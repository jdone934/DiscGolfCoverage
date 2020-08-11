<html>
<head>
    <%@include file="template/head.jsp" %>
</head>
<body>
    <%@include file="template/navbar.jsp"%>
    <h1>${course.name}</h1>

    <div class="row">
        <c:if test="${course.locationCountry == 'US'}">
            ${course.locationCity}, ${course.locationState}
        </c:if>
        <c:if test="${course.locationCountry != 'US'}">
            ${course.locationCity} ${course.locationCountry}
        </c:if>
    </div>

    <c:set var="tournamentsAtCourseList" value="${course.tournamentsAtCourse}" />
    <c:if test="${not empty tournamentsAtCourseList}">
        <h2>Tournaments</h2>
        <c:forEach var="tournamentList" items="${tournamentsAtCourseList}">
            <c:set var="tournament" value="${tournamentList.tournament}" />
            <div class="row">
                <div class="col-4">
                    <a href="viewTournament?id=${tournament.tournamentId}">
                            ${tournament.name} (${tournament.year})
                    </a>
                </div>
                <div class="col-4">
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
