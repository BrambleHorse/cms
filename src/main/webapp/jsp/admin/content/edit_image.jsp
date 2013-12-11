<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

    <div class="edit-image">
        <form action="/admin.content.image.do" method="POST">
            <input type="hidden" name="contentId" value="${content.contentId}">
            <input type="hidden" name="imageName" value="${content.imageName}">
            <input type="hidden" name="imagePath" value="${content.imagePath}">
            <input type="hidden" name="thumbImagePath" value="${content.thumbImagePath}">
            <input type="hidden" name="imageFilePath" value="${content.imageFilePath}">
            <input type="hidden" name="thumbImageFilePath" value="${content.thumbImageFilePath}">
            <table class="admin-input">
                <tr>
                    <td>Изображение:</td>
                    <td><a href="${content.imagePath}"><img src="${content.thumbImagePath}" alt="no image"/></a></td>
                </tr>
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
                    <td><input type="text" name="contentPosition" value="${content.contentPosition}"></td>
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
