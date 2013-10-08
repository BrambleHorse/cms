<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit content</title>
    <link rel="stylesheet" href="../jsp/css/styles.css" type="text/css">
    <script type="text/javascript" src="../jsp/js/jquery-1.10.2.js"></script>
</head>

<body>
<div class="wrapper">
    <jsp:include page="admin_header.jsp"/>

    <div class="content-list">
        <li><a href="/admin.do?mode=content&action=create&categoryid=${categoryId}">Создать</a></li>
        <c:set var="contentLength" value="${fn:length(contentList)}"/>
        <c:choose>
            <c:when test="${contentLength > 0}">
                <c:forEach items="${contentList}" var="content">
                    <c:if test="${content.type eq 'TABLE'}">
                        <table class="content-sheet">${content.htmlTable}</table>
                        <li><a href="/admin.do?mode=content&action=edit&type=table&contentId=${content.contentId}">Редактировать</a></li>
                        <li><a href="/admin.do?mode=content&action=delete&categoryId=${categoryId}&contentId=${content.contentId}&contentType=${content.type}">Удалить</a></li>
                    </c:if>
                    <c:if test="${content.type eq 'TEXT'}">
                      <li> <p class="content-text"> ${content.text} </p></li>
                        <li><a href="/admin.do?mode=content&action=edit&type=text&contentId=${content.contentId}">Редактировать</a></li>
                        <li><a href="/admin.do?mode=content&action=delete&categoryId=${categoryId}&contentId=${content.contentId}&contentType=${content.type}">Удалить</a></li>
                    </c:if>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <h2>You can add content using admin page.</h2>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<jsp:include page="admin_footer.jsp"/>
</body>
</html>