<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>CMS Demo</title>
</head>
<body>
<div class="wrapper">
   <jsp:include page="header.jsp"></jsp:include>
    <div class="content-wrap">
        <jsp:include page="show_content.jsp"></jsp:include>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>