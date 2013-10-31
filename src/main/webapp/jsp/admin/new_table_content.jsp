<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>New table</title>
</head>

<body>
<div class="wrapper">
    <jsp:include page="admin_header.jsp"></jsp:include>
    <div class="create-content">
        <form action="/admin.content.do" method="POST">
            <input type="hidden" name="action" value="create">
            <input type="hidden" name="contentType" value="TABLE">
            <input type="hidden" name="categoryId" value="${param.categoryId}">
            <table class="admin-input">
                <tr>
                    <td>Место таблицы в списке:</td>
                    <td><input type="text" name="contentPosition"></td>
                </tr>
                <tr>
                    <td>HTML таблица:</td>
                    <td><textarea name="tableValue" rows="12" cols="60"></textarea></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Создать"></td>
                </tr>
            </table>
        </form>
    </div>
</div>
<jsp:include page="admin_footer.jsp"></jsp:include>
</body>
</html>
