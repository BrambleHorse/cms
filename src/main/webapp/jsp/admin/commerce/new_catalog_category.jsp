<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/js/validation/category.commerce.validation.js"></script>

<div class="new-catalog-category">
    <form id="admin-catalog-category-form" action="/admin.catalog.category.do" method="post">
        <input type="hidden" name="action" value="create">
        <table class="admin-input">
            <tr>
                <td>Название категории:</td>
                <td><input type="text" name="catalogCategoryName" class="text-input"></td>
            </tr>
            <tr>
                <td>Позиция:</td>
                <td><input type="text" name="catalogCategoryPosition" class="number-input"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Создать"></td>
            </tr>
        </table>
    </form>
</div>
