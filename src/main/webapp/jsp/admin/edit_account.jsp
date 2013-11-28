<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="new-account">
    <form action="/admin.accounts.do" method="POST">
        <input type="hidden" name="accountsAction" value="edit">
        <input type="hidden" name="userName" value="${account.userName}">
        <table class="admin-input">
            <tr>
                <td>Имя пользователя: ${account.userName}</td>
                <td><</td>
            </tr>
            <tr>
                <td>Новый пароль:</td>
                <td><input type="password" name="userPassword"></td>
            </tr>
            <tr>
                <td>Повторите пароль:</td>
                <td><input type="password" name="userPasswordRepeat"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Сохранить"></td>
            </tr>
        </table>
    </form>
</div>