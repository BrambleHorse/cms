<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit categories</title>
</head>

<body>
<div class="wrapper">
    <jsp:include page="admin_header.jsp"/>

<div class="manage-categories">
    <h3> <li><a href="/admin.categories.do?action=create">Создать новую категорию</a></li></h3>
    <c:set var="categoryLength" value="${fn:length(categoryList)}"/>
    <c:choose>
        <c:when test="${categoryLength > 0}">
            <c:forEach items="${categoryList}" var="category">
                <li><a href="${pageContext.request.contextPath}/index.do?category=${category.id}">${category.name}</a></li>
                <li><a href="/admin.categories.do?action=edit&categoryId=${category.id}">Редактировать категорию</a></li>
                <li><a href="/admin.content.do?categoryId=${category.id}">Редактировать контент категории</a></li>
                <li><a href="/admin.categories.do?action=delete&categoryId=${category.id}">Удалить категорию</a></li>
                <hr/>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <h2>Категорий нет.</h2>
        </c:otherwise>
    </c:choose>
</div>
</div>
<jsp:include page="admin_footer.jsp"/>
</body>
</html>