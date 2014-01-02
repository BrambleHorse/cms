<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="manage-filter-criteria">
    <h3><li><a href="/admin.catalog.filter.criterion.do?action=create&catalogCategoryFilterId=${param.catalogCategoryFilterId}">Добавить новый критерий фильтра</a></li></h3>
    <c:set var="filterCriterionLength" value="${fn:length(filterCriterionList)}"/>
    <c:if test="${filterCriterionLength > 0}">
        <c:forEach items="${filterCriterionList}" var="filterCriterion">
            <li>${filterCriterion.filterCriterionValue}</li>
            <li><a href="/admin.catalog.filter.criterion.do?action=edit&filterCriterionId=${filterCriterion.filterCriterionId}">Редактировать</a></li>
            <li><a href="/admin.catalog.filter.criterion.do?action=delete&filterCriterionId=${filterCriterion.filterCriterionId}">Удалить</a></li>
            <hr/>
        </c:forEach>
    </c:if>

</div>