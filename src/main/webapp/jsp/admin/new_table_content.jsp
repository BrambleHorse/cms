<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

    <div class="new-table-content">
        <form action="/admin.content.do" method="POST">
            <input type="hidden" name="action" value="create">
            <input type="hidden" name="contentType" value="TABLE">
            <input type="hidden" name="categoryId" value="${param.categoryId}">
            <table class="admin-input">
                <tr>
                    <td>Позиция::</td>
                    <td><input type="text" name="contentPosition"></td>
                </tr>
                <tr>
                    <td>HTML таблица:</td>
                    <td><textarea name="tableValue" rows="12" cols="60"></textarea></td>
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
