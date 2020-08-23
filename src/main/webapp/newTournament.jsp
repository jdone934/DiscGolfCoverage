<html>
<head>
    <%@include file="template/head.jsp" %>
    <script src="js/formControl.js"></script>
    <link rel="stylesheet" href="style/searchForm.css" />
</head>
<body>
<div class="container-fluid">
    <%@include file="template/navbar.jsp"%>

    <h1 class="text-center">Add New Tournament</h1>

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
    </form>

    <a href="newCoverageProvider">Add a new Coverage Provider</a>
</div>
</body>
</body>
</html>
