<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="new-filter-criterion">
    <form id="admin-form" action="/admin.catalog.filter.criterion.do" method="post">
        <input type="hidden" name="action" value="create">
        <input type="hidden" name="catalogCategoryFilterId" value="${param.catalogCategoryFilterId}">
        <table class="admin-input">
            <td>Название критерия:</td>
            <td><input type="text" name="filterCriterionValue" class="text-input"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Создать"></td>
            </tr>
        </table>
    </form>
</div>