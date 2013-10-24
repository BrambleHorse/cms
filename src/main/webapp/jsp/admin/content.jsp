<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit content</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/css/styles.css" type="text/css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/jsp/js/jquery-1.10.2.js"></script>
</head>

<body>
<div class="wrapper">
    <jsp:include page="admin_header.jsp"/>

    <div class="content-list">
        <li><a href="/admin.content.do?action=create&categoryId=${categoryId}">Создать</a></li>
        <c:set var="contentLength" value="${fn:length(contentList)}"/>
        <c:choose>
            <c:when test="${contentLength > 0}">
                <c:forEach items="${contentList}" var="content">
                    <c:if test="${content.type eq 'TABLE'}">
                        <table class="content-sheet">${content.htmlTable}</table>
                        <li><a href="/admin.content.do?action=edit&contentType=TABLE&contentId=${content.contentId}&categoryId=${categoryId}">Редактировать</a></li>
                        <li><a href="/admin.content.do?action=delete&categoryId=${categoryId}&contentId=${content.contentId}&contentType=TABLE">Удалить</a></li>
                    </c:if>
                    <c:if test="${content.type eq 'TEXT'}">
                      <li> <p class="content-text"> ${content.text} </p></li>
                        <li><a href="/admin.content.do?action=edit&contentType=TEXT&contentId=${content.contentId}&categoryId=${categoryId}">Редактировать</a></li>
                        <li><a href="/admin.content.do?action=delete&categoryId=${categoryId}&contentId=${content.contentId}&contentType=TEXT">Удалить</a></li>
                    </c:if>
                    <c:if test="${content.type eq 'IMAGE'}">
                        <li><img src="${content.imagePath}" alt="image not found"/></li>
                        <li><a href="/admin.content.do?action=edit&contentType=IMAGE&contentId=${content.contentId}&categoryId=${categoryId}">Редактировать</a></li>
                        <li><a href="/admin.content.do?action=delete&categoryId=${categoryId}&contentId=${content.contentId}&contentType=IMAGE&path=${content.imageFilePath}&thumbPath=${content.thumbImageFilePath}">Удалить</a></li>
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