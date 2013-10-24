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
    <jsp:include page="admin_header.jsp"></jsp:include>
    <div class="create-content">
        <fieldset>
            <legend>Загрузить изображение</legend>
            <form action="/image.upload.do" method="post" enctype="multipart/form-data">
                <input type="hidden" name="categoryId" value="${param.categoryId}">
                <label for="contentPosition">Место изображения в списке:</label>
                <input id="contentPosition" type="text" name="contentPosition">
                <label for="fileName">Укажите файл:</label>
                <input id="fileName" type="file" name="fileName" size="30"/><br/>
                <input type="submit" value="Загрузить"/>
            </form>
        </fieldset>
    </div>
</div>
<jsp:include page="admin_footer.jsp"></jsp:include>
</body>
</html>
