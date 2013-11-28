<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>CMS Demo</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/css/styles.css">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/admin/admin_header.jsp"></jsp:include>
<div class="admin-login">
    <form method="POST" action="j_security_check">
        <table class="admin-login-input">
            <tr>
                <td>Имя пользователя:</td>
                <td><input type="text" name="j_username"></td>
            </tr>
            <tr>
                <td>Пароль:</td>
                <td><input type="password" name="j_password"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit"  value="Вход"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>