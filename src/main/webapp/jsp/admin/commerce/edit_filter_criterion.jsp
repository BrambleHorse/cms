<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="edit-filter-criterion">
    <form action="/admin.catalog.filter.criterion.do" method="post">
        <input type="hidden" name="action" value="edit">
        <input type="hidden" name="filterCriterionId" value="${filterCriterion.filterCriterionId}">
        <table class="admin-input">
            <td>Название критерия:</td>
            <td><input type="text" name="filterCriterionValue" value="${filterCriterion.filterCriterionValue}"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Сохранить"></td>
            </tr>
        </table>
    </form>
</div>