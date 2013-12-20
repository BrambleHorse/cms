<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="edit-brand">
    <form action="/admin.catalog.brand.do" method="post">
        <input type="hidden" name="action" value="edit">
        <input type="hidden" name="brandId" value="${brand.brandId}">
        <table class="admin-input">
                <td>Название бренда:</td>
            <td><input type="text" name="brandName">${brand.brandName}</td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Сохранить"></td>
            </tr>
        </table>
    </form>
</div>