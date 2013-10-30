<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/css/styles.css" type="text/css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/jsp/js/jquery-1.10.2.js"></script>
</head>

<body>
<div class="wrapper">
    <jsp:include page="admin_header.jsp"/>

    <div class="create-category">
        <form action="/admin.categories.do" method="POST">
            <input type="hidden" name="action" value="create">
            <table class="admin-input">
                <tr>
                    <td>Название категории:</td>
                    <td><input type="text" name="title"></td>
                    <td>Место категории в списке:</td>
                    <td><input type="text" name="categoryPosition"></td>
                </tr>
                <tr>
                    <td>Укажите родительскую категорию:</td>
                    <td></td>
                </tr>
                <tr>
                    <td><input type="radio" name="parentCategory" checked="checked" value="null"></td>
                    <td>Нет (категория верхнего уровня)</td>
                </tr>
                <c:set var="categoryLength" value="${fn:length(categoryList)}"/>
                <c:choose>
                    <c:when test="${categoryLength > 0}">
                        <c:forEach items="${categoryList}" var="category">
                            <tr>
                                <td><input type="radio"  name="parentCategory" value="${category.id}"></td>
                                <td>${category.name}</td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <p> No categories . . </p>
                    </c:otherwise>
                </c:choose>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Создать"></td>
                </tr>
            </table>
        </form>
    </div>

</div>
<jsp:include page="admin_footer.jsp"/>
</body>
</html>