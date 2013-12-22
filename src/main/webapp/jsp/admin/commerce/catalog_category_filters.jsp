<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="manage-catalog-category-filters">
    <h3><li><a href="/admin.catalog.filter.do?action=create&catalogCategoryId=${param.catalogCategoryId}">Добавить новый фильтр</a></li></h3>
    <c:set var="catalogCategoryFilterLength" value="${fn:length(catalogCategoryFilterList)}"/>
    <c:if test="${catalogCategoryFilterLength > 0}">
        <c:forEach items="${catalogCategoryFilterList}" var="catalogCategoryFilter">
            <li>${catalogCategoryFilter.catalogCategoryFilterName}</li>
            <li><a href="/admin.catalog.do?mode=criteria&catalogCategoryFilterId=${catalogCategoryFilter.catalogCategoryFilterId}">Управление критериями фильтра</a></li>
            <li><a href="/admin.catalog.filter.do?action=edit&catalogCategoryFilterId=${catalogCategoryFilter.catalogCategoryFilterId}">Редактировать</a></li>
            <li><a href="/admin.catalog.filter.do?action=delete&catalogCategoryFilterId=${catalogCategoryFilter.catalogCategoryFilterId}">Удалить</a></li>
            <hr/>
        </c:forEach>
    </c:if>

</div>