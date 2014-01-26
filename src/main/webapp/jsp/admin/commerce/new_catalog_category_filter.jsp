<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="new-catalog-category-filter">
    <form id="admin-form" action="/admin.catalog.filter.do" method="post">
        <input type="hidden" name="action" value="create">
        <input type="hidden" name="catalogCategoryId" value="${param.catalogCategoryId}">
        <table class="admin-input">

                <td>Название фильтра:</td>
                <td><input type="text" name="catalogCategoryFilterName" class="text-input"></td>
            </tr>
            <tr>
                <td>Позиция:</td>
                <td><input type="text" name="catalogCategoryFilterPosition" class="number-input"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Создать"></td>
            </tr>
        </table>
    </form>
</div>