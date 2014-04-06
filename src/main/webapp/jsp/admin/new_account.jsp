<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="new-account">
    <form action="/admin.accounts.do" method="POST">
        <input type="hidden" name="accountsAction" value="create">
        <table class="admin-input">
            <tr>
                <td>Имя пользователя:</td>
                <td><input type="text" name="userName"></td>
            </tr>
            <tr>
                <td>Пароль:</td>
                <td><input type="password" name="userPassword"></td>
            </tr>
            <tr>
                <td>Повторите пароль:</td>
                <td><input type="password" name="userPasswordRepeat"></td>
            </tr>

            <tr>
                <td></td>
                <td><input type="submit" value="Создать"></td>
            </tr>
        </table>
    </form>
</div>
