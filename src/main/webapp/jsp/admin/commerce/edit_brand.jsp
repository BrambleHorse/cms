<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/js/validation/brand.commerce.validation.js"></script>

<div class="edit-brand">
    <form id="admin-brand-form" action="/admin.catalog.brand.do" method="post">
        <input type="hidden" name="action" value="edit">
        <input type="hidden" name="brandId" value="${brand.brandId}">
        <table class="admin-input">
                <td>Название бренда:</td>
            <td><input type="text" name="brandName" class="text-input" value="${brand.brandName}"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Сохранить"></td>
            </tr>
        </table>
    </form>
</div>