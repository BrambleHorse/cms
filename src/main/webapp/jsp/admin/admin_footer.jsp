<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="admin-footer">
  Admin page footer:<br/>
    <div class="current-admin-action">

        <c:choose>
            <c:when test="${adminAction eq 'categories'}">Текущее действие: список категорий</c:when>
            <c:when test="${adminAction eq 'content'}">Текущее действие: список контента</c:when>
            <c:when test="${adminAction eq 'edit_category'}">Текущее действие: редактирование категории</c:when>
            <c:when test="${adminAction eq 'edit_image_content'}">Текущее действие: редактирование изображения</c:when>
            <c:when test="${adminAction eq 'edit_table_content'}">Текущее действие: редактирование таблицы</c:when>
            <c:when test="${adminAction eq 'edit_text_content'}">Текущее действие: редактирование текста</c:when>
            <c:when test="${adminAction eq 'new_category'}">Текущее действие: создание новой категории</c:when>
            <c:when test="${adminAction eq 'new_content'}">Текущее действие: добавление нового контента</c:when>
            <c:when test="${adminAction eq 'new_image_content'}">Текущее действие: добавление нового изображения</c:when>
            <c:when test="${adminAction eq 'new_table_content'}">Текущее действие: добавление новой таблицы</c:when>
            <c:when test="${adminAction eq 'new_text_content'}">Текущее действие: добавление нового текста</c:when>
            <c:otherwise>
                Текущее действие: главная страница административной панели
            </c:otherwise>
        </c:choose>
    </div>
</div>