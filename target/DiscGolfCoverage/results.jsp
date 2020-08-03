<html>
<head>
    <%@include file="template/head.jsp" %>
</head>
<body class="container-fluid">
    <%@include file="template/navbar.jsp"%>

    <c:if test="${searchType == 'player'}">
        <c:if test="${not empty players}">
            <h1>Players Found</h1>
        </c:if>
        <c:forEach var="player" items="${players}" varStatus="loop">
            <div class="card" style="width: 18rem;">
                <img class="card-img-top" src="playerProfilePictures/${player.profilePicture}" alt="player profile picture">
                <div class="card-body">
                    <a class="card-text" href="playerProfile?id=${player.playerId}">${player.firstName} ${player.lastName}</a>
                </div>
            </div>
        </c:forEach>
    </c:if>
</body>
</html>
