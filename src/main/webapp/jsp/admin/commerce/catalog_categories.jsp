<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="manage-catalog-categories">
    <h3><li><a href="/admin.catalog.category.do?action=create">Добавить новую категорию в каталог</a></li></h3>
    <c:set var="catalogCategoryLength" value="${fn:length(catalogCategoryList)}"/>
    <c:if test="${catalogCategoryLength > 0}">
        <c:forEach items="${catalogCategoryList}" var="catalogCategory">
            <li>${catalogCategory.catalogCategoryName}</li>
            <li><a href="/admin.catalog.do?mode=filters&catalogCategoryId=${catalogCategory.catalogCategoryId}">Управление фильтрами категории</a></li>
            <li><a href="/admin.catalog.do?mode=items&catalogCategoryId=${catalogCategory.catalogCategoryId}">Управление товарами категории</a></li>
            <li><a href="/admin.catalog.category.do?action=edit&catalogCategoryId=${catalogCategory.catalogCategoryId}">Редактировать</a></li>
            <li><a href="/admin.catalog.category.do?action=delete&catalogCategoryId=${catalogCategory.catalogCategoryId}">Удалить</a></li>
            <hr/>
        </c:forEach>
    </c:if>

</div>