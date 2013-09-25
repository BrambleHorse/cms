<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="header">
    <ul class="admin-link"><a href="/admin">admin</a></ul>
    <ul class="nav">
        <c:set var="categoryLength" value="${fn:length(categoryList)}"/>
        <c:choose>
            <c:when test="${categoryLength > 0}">
                <c:forEach items="${categoryList}" var="category">
                 <li><a href="${pageContext.request.contextPath}/index?category=${category.id}">${category.name}</a></li>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <h2>There are no categories.&nbsp;You can add categories using admin page.</h2>
            </c:otherwise>
        </c:choose>
    </ul>

</div>