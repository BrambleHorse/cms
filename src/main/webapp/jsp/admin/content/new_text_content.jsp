<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/js/validation/text.content.validation.js"></script>

    <div class="new-text-content">
        <form id="admin-form" action="javascript:void(0);" method="post" onsubmit="ajax()">
            <input type="hidden" name="action" value="create">
            <input type="hidden" name="categoryId" value="${param.categoryId}">
            <table class="admin-input">
                <tr>
                    <td>Позиция:</td>
                    <td><input type="text" name="contentPosition" class="number-input"></td>
                </tr>
                <tr>
                    <td>Текст:</td>
                    <td><textarea name="textValue" rows="12" cols="60"></textarea></td>
                </tr>
                <tr>
                    <td>Отображать</td>
                    <td><input type="checkbox" name="isVisible" checked="true" value="visible"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Создать"></td>
                </tr>
            </table>
        </form>
    </div>
