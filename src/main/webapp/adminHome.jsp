<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <%@ include file="template/head.jsp"%>
    <link rel="stylesheet" href="../style/adminSearchForm.css">
    <script src="../js/adminHomeFormControl.js"></script>
</head>
<body>
<div class="header">
    <%@ include file="template/navbar.jsp"%>
    <div class="display-4 text-center">
        Admin Home
    </div>
</div>

<div class="adminMain container">
    <c:set var="user" value="${user}" />

    <h2 class="text-center">Add New Content</h2>
    <div class="row">
        <table class="table table-striped col-12 col-sm-8 offset-sm-2">
            <tr><td><a href="newPlayer">New Player</a></td></tr>
            <tr><td><a href="newCourse">New Course</a></td></tr>
            <tr><td><a href="newTournament">New Tournament</a></td></tr>
            <tr><td><a href="newRound">New Round Coverage</a></td></tr>
        </table>
    </div>

    <h2 class="text-center">Edit Round</h2>
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger" role="alert">
                ${errorMessage}
        </div>
    </c:if>
    <c:if test="${not empty successMessage}">
        <div class="alert alert-success" role="alert">
                ${successMessage}
        </div>
    </c:if>
    <form id="findRound" method="post" action="findRoundToEdit">
        <div class="form-group">
            <label for="searchType">Search Type</label>
            <select id="searchType" name="searchType" class="form-control">
                <option value="roundId">Round Id</option>
                <option value="tournament">Tournament</option>
            </select>
        </div>

        <div class="form-group roundId searchField">
            <label for="roundId">Id</label>
            <input type="text" name="roundId" id="roundId" class="form-control">
        </div>

        <div class="form-group tournament searchField">
            <label for="tournamentName">Tournament Name</label>
            <input type="text" name="tournamentName" id="tournamentName" class="form-control">
        </div>

        <div class="form-group tournament searchField">
            <label for="tournamentYear">Tournament Year</label>
            <input type="number" name="tournamentYear" id="tournamentYear" class="form-control">
        </div>

        <button type="submit" id="submitButton" class="btn btn-primary">Search</button>
    </form>
</div>

</body>
</html>
