<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="../jsp/css/styles.css" type="text/css">
    <script type="text/javascript" src="../jsp/js/jquery-1.10.2.js"></script>
</head>

<body>
<div class="wrapper">
    <jsp:include page="admin_header.jsp"/>

    <div class="edit-category">
        <form action="/admin?mode=categories&action=edit" method="POST">
            <table class="admin-input">
                <tr>
                    <input type="hidden" name="categoryId" value="${currentCategory.id}"/>
                    <td>Название категории:</td>
                    <td><input type="text" name="title" value="${currentCategory.name}"></td>
                    <td>Место категории в списке:</td>
                    <td><input type="text" name="category_position" value="${currentCategory.categoryPosition}"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Сохранить"></td>
                </tr>
            </table>
        </form>
    </div>
</div>
<jsp:include page="admin_footer.jsp"/>
</body>
</html>