<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

    <div class="manage-content">
        <h3><li><a href="/admin.content.do?action=create&categoryId=${categoryId}">Добавить новый контент</a></li></h3>
        <c:set var="contentLength" value="${fn:length(contentList)}"/>
            <c:if test="${contentLength > 0}">
                <c:forEach items="${contentList}" var="content">
                    <c:choose>
                    <c:when test="${content.type eq 'TABLE'}">
                        <table class="content-sheet">${content.htmlTable}</table>
                        <li><a href="/admin.content.table.do?action=edit&contentId=${content.contentId}&categoryId=${categoryId}">Редактировать</a></li>
                        <li><a href="/admin.content.table.do?action=delete&categoryId=${categoryId}&contentId=${content.contentId}">Удалить</a></li>
                    </c:when>
                    <c:when test="${content.type eq 'TEXT'}">
                      <li> <p class="content-text"> ${content.text} </p></li>
                        <li><a href="/admin.content.text.do?action=edit&contentId=${content.contentId}&categoryId=${categoryId}">Редактировать</a></li>
                        <li><a href="/admin.content.text.do?action=delete&categoryId=${categoryId}&contentId=${content.contentId}">Удалить</a></li>
                    </c:when>
                    <c:when test="${content.type eq 'IMAGE'}">
                        <li><img src="${content.thumbImagePath}" alt="image not found"/></li>
                        <li><a href="/admin.content.image.do?action=edit&contentId=${content.contentId}&categoryId=${categoryId}">Редактировать</a></li>
                        <li><a href="/admin.content.image.do?action=delete&categoryId=${categoryId}&contentId=${content.contentId}&path=${content.imageFilePath}&thumbPath=${content.thumbImageFilePath}">Удалить</a></li>
                    </c:when>
                        <c:when test="${content.type eq 'WYSIWYG'}">
                            ${content.wysiwygData}
                            <li><a href="/admin.content.wysiwyg.do?action=edit&contentId=${content.contentId}&categoryId=${categoryId}">Редактировать</a></li>
                            <li><a href="/admin.content.wysiwyg.do?action=delete&categoryId=${categoryId}&contentId=${content.contentId}">Удалить</a></li>
                    </c:when>
                     </c:choose>
                    <hr/>
                </c:forEach>
            </c:if>

    </div>
