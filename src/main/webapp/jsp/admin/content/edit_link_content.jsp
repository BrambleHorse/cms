<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="edit-link-content">
    <fieldset>
        <legend>Загрузить иконку для ссылки</legend>
        <form action="/admin.content.link.do" method="post" enctype="multipart/form-data">
            <input type="hidden" name="action" value="edit" />
            <input type="hidden" name="contentId" value="${content.contentId}" />
            <input type="hidden" name="contentPosition" value="${content.contentPosition}" />
            <input type="hidden" name="isVisible"
            <c:choose>
                <c:when test="${content.isVisible eq true}">
                    value="visible"
                </c:when>
                <c:otherwise>
                    value="not visible"
                </c:otherwise>
            </c:choose> />
            <input type="hidden" name="linkValue" value="${content.linkValue}" />
            <input type="hidden" name="oldLinkImageFilePath" value="${content.linkImageFilePath}">
            <label for="fileName">Укажите файл:</label>
            <input id="fileName" type="file" name="fileName" size="30" /><br/>
            <input type="submit" value="Загрузить" />
        </form>
    </fieldset>
</div>
