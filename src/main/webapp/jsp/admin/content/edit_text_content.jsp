<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

    <div class="edit-text-content">
        <form id="admin-form" action="javascript:void(0);" method="post" onsubmit="ajax()">
            <input type="hidden" name="action" value="edit">
            <input type="hidden" name="contentId" value="${content.contentId}">
            <table class="admin-input">
                <tr>
                    <td>Укажите категорию, в которой будет отображено содержимое:</td>
                    <td></td>
                </tr>
                <c:set var="categoryLength" value="${fn:length(categoryList)}"/>
                <c:choose>
                    <c:when test="${categoryLength > 0}">
                        <c:forEach items="${categoryList}" var="category">
                            <tr>
                                <td><input type="radio" <c:if test="${param.categoryId eq category.id}"> checked="checked" </c:if> name="categoryId" value="${category.id}"></td>
                                <td>${category.name}</td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <p> No categories . . </p>
                    </c:otherwise>
                </c:choose>
                <tr>
                    <td>Позиция:</td>
                    <td><input type="text" name="contentPosition" class="number-input" value="${content.contentPosition}"></td>
                </tr>
                <tr>
                    <td>Текст:</td>
                    <td><textarea name="textValue" rows="12" cols="60" class="text-input">${content.text}</textarea></td>
                </tr>
                <tr>
                    <td>Отображать</td>
                    <td><input type="checkbox" name="isVisible" <c:if test="${content.isVisible}">checked="true"</c:if> value="visible"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" name="send" class="send" value="Сохранить"></td>
                </tr>
            </table>
        </form>
    </div>
