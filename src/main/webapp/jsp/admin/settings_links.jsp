<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="settings-links">
    <form action="/admin.content.do" method="POST">
        <table class="admin-input">
            <tr>
                <td>Отображать</td>
                <td><input type="checkbox" name="showFooterLinks" <c:if test="${content.isVisible}">checked="true"</c:if> value="true"></td>
            </tr>
            <tr>
                <td>Ссылка:</td>
                <td><input type="text" name="linkValue" value="${content.linkValue}"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Сохранить настройки"></td>
            </tr>
        </table>
    </form>
</div>
