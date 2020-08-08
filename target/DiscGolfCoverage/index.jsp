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
            <label for="tournamentName">Tournament Name</label>
            <input type="text" class="form-control" name="tournamentName" id="tournamentName">
        </div>

        <div class="form-group tournament searchField">
            <label for="tournamentYear">Tournament Year</label>
            <input type="number" class="form-control" name="tournamentYear" id="tournamentYear">
        </div>

        <div class="form-group course searchField">
            <label for="courseName">Course Name</label>
            <input type="text" class="form-control" name="courseName" id="courseName">
        </div>

        <div class="form-group course searchField">
            <label for="courseCity">City</label>
            <input type="text" class="form-control" name="courseCity" id="courseCity">
        </div>

        <div class="form-group course searchField">
            <label for="courseState">State</label>
            <select class="form-control" name="courseState" id="courseState">
                <option value="">Select a State</option>
                <option value="AL">Alabama</option>
                <option value="AK">Alaska</option>
                <option value="AZ">Arizona</option>
                <option value="AR">Arkansas</option>
                <option value="CA">California</option>
                <option value="CO">Colorado</option>
                <option value="CT">Connecticut</option>
                <option value="DE">Delaware</option>
                <option value="DC">District Of Columbia</option>
                <option value="FL">Florida</option>
                <option value="GA">Georgia</option>
                <option value="HI">Hawaii</option>
                <option value="ID">Idaho</option>
                <option value="IL">Illinois</option>
                <option value="IN">Indiana</option>
                <option value="IA">Iowa</option>
                <option value="KS">Kansas</option>
                <option value="KY">Kentucky</option>
                <option value="LA">Louisiana</option>
                <option value="ME">Maine</option>
                <option value="MD">Maryland</option>
                <option value="MA">Massachusetts</option>
                <option value="MI">Michigan</option>
                <option value="MN">Minnesota</option>
                <option value="MS">Mississippi</option>
                <option value="MO">Missouri</option>
                <option value="MT">Montana</option>
                <option value="NE">Nebraska</option>
                <option value="NV">Nevada</option>
                <option value="NH">New Hampshire</option>
                <option value="NJ">New Jersey</option>
                <option value="NM">New Mexico</option>
                <option value="NY">New York</option>
                <option value="NC">North Carolina</option>
                <option value="ND">North Dakota</option>
                <option value="OH">Ohio</option>
                <option value="OK">Oklahoma</option>
                <option value="OR">Oregon</option>
                <option value="PA">Pennsylvania</option>
                <option value="RI">Rhode Island</option>
                <option value="SC">South Carolina</option>
                <option value="SD">South Dakota</option>
                <option value="TN">Tennessee</option>
                <option value="TX">Texas</option>
                <option value="UT">Utah</option>
                <option value="VT">Vermont</option>
                <option value="VA">Virginia</option>
                <option value="WA">Washington</option>
                <option value="WV">West Virginia</option>
                <option value="WI">Wisconsin</option>
                <option value="WY">Wyoming</option>
            </select>
        </div>

        <div class="form-group course searchField">
            <label for="courseCountry">Country</label>
            <input type="text" class="form-control" name="courseCountry" id="courseCountry">
        </div>

        <button type="submit" id="submitButton" class="btn btn-primary">Search</button>
    </form>
</div>
</body>
</body>
</html>
