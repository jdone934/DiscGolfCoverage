<html>
<head>
    <%@include file="template/head.jsp" %>
    <%@taglib uri="http://doneyTag.com/tags" prefix="jd" %>
</head>
<body class="container-fluid">
    <%@include file="template/navbar.jsp"%>

    <c:if test="${searchType == 'player'}">
        <c:if test="${not empty players}">
            <h1>Players Found</h1>
        </c:if>
        <c:forEach var="player" items="${players}" varStatus="loop">
            <jd:playerCard player="${player}"/>
        </c:forEach>
    </c:if>

    <c:if test="${searchType == 'tournament'}">
        <c:if test="${not empty tournaments}">
            <h1>Tournaments Found</h1>
        </c:if>
        <c:forEach var="tournament" items="${tournaments}" varStatus="loop">
            <div class="row">
                <div class="col-4">
                    <a href="viewTournament?id=${tournament.tournamentId}">${tournament.name}: ${tournament.year}</a>
                </div>
                <div class="col-4 offset-4">
                    <div class="row">
                        <c:forEach var="courseList" items="${tournament.coursesAtTournament}">
                            <c:set var="course" value="${courseList.course}" />
                            <div class="col-12">
                                <a href="viewCourse?id=${course.courseId}">${course.name}</a>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </c:forEach>
    </c:if>

    <c:if test="${searchType == 'course'}">
        <c:if test="${not empty courses}">
            <h1>Courses Found</h1>
        </c:if>
        <c:forEach var="course" items="${courses}" varStatus="loop">
            ${course.name}: ${course.locationCity}, ${course.locationState}<hr>
        </c:forEach>
    </c:if>
</body>
</html>
