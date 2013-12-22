<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="edit-catalog-category-filter">
    <form action="/admin.catalog.filter.do" method="post">
        <input type="hidden" name="action" value="edit">
        <input type="hidden" name="catalogCategoryId" value="${catalogCategoryFilter.filterCatalogCategory.catalogCategoryId}">
        <input type="hidden" name="catalogCategoryFilterId" value="${catalogCategoryFilter.catalogCategoryFilterId}">
        <table class="admin-input">
            <tr>
                <td>Название фильтра:</td>
                <td><input type="text" name="catalogCategoryFilterName" value="${catalogCategoryFilter.catalogCategoryFilterName}"></td>
            </tr>
            <tr>
                <td>Позиция:</td>
                <td><input type="text" name="catalogCategoryFilterPosition" value="${catalogCategoryFilter.catalogCategoryFilterPosition}"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Сохранить"></td>
            </tr>
        </table>
    </form>
</div>