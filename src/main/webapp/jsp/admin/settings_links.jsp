<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="settings-links">
    <form action="/settings.admin.do" method="POST">
        <input type="hidden" name="settingsMode" value="links">
        <table class="admin-input">
            <tr>
                <td>Отображать ссылки</td>
                <td><input type="checkbox" name="showFooterLinks" <c:if test="${showFooterLinks eq 'true'}">checked="true"</c:if> value="true"></td>
            </tr>
            <tr>
                <td>Размер иконки:</td>
                <td><input type="text" name="footerLinksSize" value="${footerLinksSize}"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Сохранить настройки"></td>
            </tr>
        </table>
    </form>
</div>
