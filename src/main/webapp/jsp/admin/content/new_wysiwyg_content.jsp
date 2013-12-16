<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script></script>
<div class="new-wysiwyg-content">
    <form action="/admin.content.wysiwyg.do" method="post">
        <input type="hidden" name="action" value="create">
        <input type="hidden" name="categoryId" value="${param.categoryId}">
        <table class="admin-input">
            <tr>
                <td>Позиция:</td>
                <td><input type="text" name="contentPosition"></td>
            </tr>
            <tr>
                <td></td>
                <td><textarea name="wysiwygValue"></textarea></td>
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
