<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin page</title>
</head>
<body>
<div class="wrapper">
    <jsp:include page="admin_header.jsp"/>
<div class="admin-content">
    <ul class="admin-menu">
        <li><a href="/admin.categories.do">Управление категориями и контентом</a></li>
    </ul>
</div>
    </div>
<jsp:include page="admin_footer.jsp"/>
</body>
</html>