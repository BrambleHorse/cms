<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>CMS Demo</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/css/styles.css">
</head>
<body>
<div id="wrap">
   <jsp:include page="header.jsp"></jsp:include>
    <div id="main" class="clearfix">
        <jsp:include page="show_content.jsp"></jsp:include>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>