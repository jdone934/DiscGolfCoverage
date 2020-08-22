<html>
<head>
    <%@include file="template/head.jsp" %>
    <script src="js/formControl.js"></script>
    <link rel="stylesheet" href="style/searchForm.css" />
</head>
<body>
<div class="container-fluid">
    <%@include file="template/navbar.jsp"%>

    <h1 class="text-center">Add New Player</h1>

    <form id="newPlayer" method="post" action="newPlayer" class="" onsubmit="" enctype="multipart/form-data">
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
            <label for="firstName">First Name</label>
            <input type="text" class="form-control" name="firstName" id="firstName">
        </div>

        <div class="form-group">
            <label for="lastName">Last Name</label>
            <input type="text" class="form-control" name="lastName" id="lastName">
        </div>

        <div class="form-group">
            <label for="profilePicture">Profile Picture</label>
            <input type="file" class="form-control" name="profilePicture" id="profilePicture" accept="image/*">
        </div>

        <button type="submit" id="submitButton" class="btn btn-primary">Submit</button>
    </form>

    <a href="newCourse">Add a new Course</a>
</div>
</body>
</body>
</html>
