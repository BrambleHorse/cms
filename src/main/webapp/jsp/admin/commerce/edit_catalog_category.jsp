<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="edit-catalog-category">
    <form id="admin-form" action="/admin.catalog.category.do" method="post">
        <input type="hidden" name="action" value="edit">
        <input type="hidden" name="catalogCategoryId" value="${catalogCategory.catalogCategoryId}">
        <table class="admin-input">
            <tr>
                <td>Название категории:</td>
                <td><input type="text" name="catalogCategoryName" class="text-input" value="${catalogCategory.catalogCategoryName}"></td>
            </tr>
            <tr>
                <td>Позиция:</td>
                <td><input type="text" name="catalogCategoryPosition" class="number-input" value="${catalogCategory.catalogCategoryPosition}"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Сохранить"></td>
            </tr>
        </table>
    </form>
</div>