<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="../jsp/css/style.css" type="text/css">
    <script type="text/javascript" src="../jsp/js/jquery-1.10.2.js"></script>
    <script>
        onload = function ()
        {
            for (var lnk = document.links, j = 0; j < lnk.length; j++)
                if (lnk [j].href == document.URL){lnk [j].style.background = 'blue'; lnk[j].style.color = 'white';}}
    </script>
</head>
<body>
<div class="wrapper">
    <jsp:include page="header.jsp"/>
    <div class="sidebar">  </div>
    <div class="content">
        <c:set var="contentLength" value="${fn:length(contentList)}"/>
        <c:choose>
            <c:when test="${contentLength > 0}">
                <c:forEach items="${contentList}" var="content">
                    <li>${content.text}</li>
                    <li>${content.type}</li>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <h2>There are no content.&nbsp;You can add content using admin page.</h2>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="clear"></div>
    <div class="clear-footer"></div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
