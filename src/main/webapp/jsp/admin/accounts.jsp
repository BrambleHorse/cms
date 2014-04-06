<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="manage-accounts">
    <h3>
        <ul>
            <li><a href="/admin.accounts.do?accountsAction=create">Добавить новую учетную запись администратора</a></li>
        </ul>
    </h3>

    <c:set var="accountsLength" value="${fn:length(accountsList)}"/>
    <c:if test="${accountsLength > 0}">
        <c:forEach items="${accountsList}" var="account">
            <ul>
                <li>${account.userName}</li>
                <li><a href="/admin.accounts.do?accountsAction=edit&userName=${account.userName}">Изменить пароль</a></li>
               <c:if test="${account.userName ne 'admin'}"><li><a href="/admin.accounts.do?accountsAction=delete&userName=${account.userName}">Удалить</a></li></c:if>
            </ul>
            <hr/>
        </c:forEach>
    </c:if>

</div>