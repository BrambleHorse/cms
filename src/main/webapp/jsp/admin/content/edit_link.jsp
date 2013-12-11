<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="edit-link">
    <form action="/admin.content.link.do" method="post">
        <input type="hidden" name="contentId" value="${content.contentId}">
        <input type="hidden" name="linkImagePath" value="${content.linkImagePath}">
        <input type="hidden" name="linkImageFilePath" value="${content.linkImageFilePath}">
        <table class="admin-input">
            <tr>
                <td>Позиция:</td>
                <td><input type="text" name="contentPosition" value="${content.contentPosition}"></td>
            </tr>
            <tr>
                <td>Ссылка:</td>
                <td><input type="text" name="linkValue" value="${content.linkValue}"></td>
            </tr>
            <tr>
                <td>Отображать</td>
                <td><input type="checkbox" name="isVisible" <c:if test="${content.isVisible}">checked="true"</c:if> value="visible"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Сохранить"></td>
            </tr>
        </table>
    </form>
</div>
