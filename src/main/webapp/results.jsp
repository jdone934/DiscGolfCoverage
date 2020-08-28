<html>
<head>
    <%@include file="template/head.jsp" %>
    <%@taglib uri="http://doneyTag.com/tags" prefix="jd" %>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <script src="js/toggleFavorite.js"></script>
</head>
<body class="container-fluid">
    <%@include file="template/navbar.jsp"%>

    <c:if test="${searchType == 'player'}">
        <c:if test="${not empty players}">
            <h1 class="text-center">Players Found</h1>
        </c:if>
        <div class="d-flex justify-content-center flex-wrap">
            <c:forEach var="player" items="${players}" varStatus="loop">
                <c:if test="${not empty user}">
                    <jd:playerCard player="${player}" user="${user}"/>
                </c:if>
                <c:if test="${empty user}">
                    <jd:playerCard player="${player}"/>
                </c:if>
            </c:forEach>
        </div>
    </c:if>

    <c:if test="${searchType == 'tournament'}">
        <c:if test="${not empty tournaments}">
            <h1 class="text-center">Tournaments Found</h1>
        </c:if>
        <c:forEach var="tournament" items="${tournaments}" varStatus="loop">
            <p class="text-center">
                <a href="viewTournament?id=${tournament.tournamentId}">${tournament.name}: ${tournament.year}</a>
                played at
                <c:forEach var="courseConnector" items="${tournament.coursesAtTournament}" varStatus="courseLoop">
                    <c:set var="course" value="${courseConnector.course}" />
                    <a href="viewCourse?id=${course.courseId}">${course.name}</a><c:if test="${! courseLoop.last}">,&nbsp;</c:if>
                </c:forEach>
            </p>
        </c:forEach>
    </c:if>

    <c:if test="${searchType == 'course'}">
        <c:if test="${not empty courses}">
            <h1 class="text-center">Courses Found</h1>
        </c:if>
        <c:forEach var="course" items="${courses}" varStatus="loop">
            <a href="viewCourse?id=${course.courseId}">
                ${course.name}: ${course.locationCity}, ${course.locationState}
            </a>
            <hr>
        </c:forEach>
    </c:if>
</body>
</html>
