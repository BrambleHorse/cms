<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="manage-filter-criteria">
    <h3><li><a href="/admin.catalog.item.do?action=create&catalogCategoryId=${param.catalogCategoryId}">Добавить новый товар</a></li></h3>

    <c:set var="itemLength" value="${fn:length(itemList)}"/>
    <c:if test="${itemLength > 0}">
        <c:forEach items="${itemList}" var="item">
            <li>${item.itemName}</li>
            <li><a href="/admin.catalog.item.do?action=edit&editMode=description&itemId=${item.itemId}">
                Редактировать описание</a></li>
            <li><a href="/admin.catalog.item.do?action=edit&editMode=image&itemId=${item.itemId}">Загрузить новое изображение</a></li>
            <li><a href="/admin.catalog.item.do?action=delete&itemId=${item.itemId}&path=${item.itemImageFilePath}&thumbPath=${item.itemThumbImageFilePath}">Удалить</a></li>
            <hr/>
        </c:forEach>
    </c:if>
</div>