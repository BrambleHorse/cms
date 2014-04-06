<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/admin/admin_header.jsp"></jsp:include>
<div class="login-error">
    <h3>Неверное имя пользователя или пароль!</h3>
</div>
</body>
</html>