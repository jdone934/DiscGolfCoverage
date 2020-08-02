<html>
<head>
    <%@include file="template/head.jsp" %>
</head>
<body>
<div class="container-fluid">
    <%@include file="template/navbar.jsp"%>

    <h1>Find Coverage</h1>

    <form id="findCoverage" method="post" action="searchCoverage" class="" onsubmit="">
        <div id="errorMessage"></div>
        <div class="form-group">
            <label for="searchType">Search Type</label>
            <input type="text" class="form-control" name="searchType" id="searchType" required>
        </div>

</div>
</body>
</body>
</html>
