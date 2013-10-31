<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit table</title>

</head>

<body>
<div class="wrapper">
    <jsp:include page="admin_header.jsp"></jsp:include>
    <div class="edit-content">
        <form action="/admin.content.do" method="POST">
            <input type="hidden" name="action" value="edit">
            <input type="hidden" name="contentType" value="TABLE">
            <input type="hidden" name="contentId" value="${content.contentId}">
            <table class="admin-input">
                <tr>
                    <td>Укажите категорию, в которой будет отображено содержимое:</td>
                    <td></td>
                </tr>
                <c:set var="categoryLength" value="${fn:length(categoryList)}"/>
                <c:choose>
                    <c:when test="${categoryLength > 0}">
                        <c:forEach items="${categoryList}" var="category">
                            <tr>
                                <td><input type="radio" <c:if test="${param.categoryId eq category.id}"> checked="checked" </c:if> name="categoryId" value="${category.id}"></td>
                                <td>${category.name}</td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <p> No categories . . </p>
                    </c:otherwise>
                </c:choose>
                <tr>
                    <td>Место содержимого в списке:</td>
                    <td><input type="text" name="contentPosition" value="${content.contentPosition}"></td>
                </tr>
                <tr>
                    <td>HTML таблица:</td>
                    <td><textarea name="tableValue" rows="12" cols="60">${content.htmlTable}</textarea></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Сохранить"></td>
                </tr>
            </table>
        </form>
    </div>
</div>
<jsp:include page="admin_footer.jsp"></jsp:include>
</body>
</html>
