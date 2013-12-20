<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="manage-brands">
    <h3><li><a href="/admin.catalog.brand.do?action=create">Добавить новый бренд</a></li></h3>
    <c:set var="brandLength" value="${fn:length(brandList)}"/>
    <c:if test="${brandLength > 0}">
        <c:forEach items="${brandList}" var="brand">
            <li>${brand.brandName}</li>
            <li><a href="/admin.catalog.brand.do?action=edit&brandId=${brand.brandId}">Редактировать</a></li>
            <li><a href="/admin.catalog.brand.do?action=delete&brandId=${brand.brandId}">Удалить</a></li>
            <hr/>
        </c:forEach>
    </c:if>

</div>