<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="content">
    <c:set var="contentLength" value="${fn:length(contentList)}"/>
    <c:choose>
        <c:when test="${contentLength > 0}">
            <c:forEach items="${contentList}" var="content">

                <c:if test="${content.type eq 'TABLE'}">
                    <table class="content-sheet">${content.htmlTable}</table>
                </c:if>
                <c:if test="${content.type eq 'TEXT'}">
                    <p class="content-text"> ${content.text} </p>
                </c:if>
                <c:if test="${content.type eq 'IMAGE'}">
                    <img src="${content.imagePath}"/>
                </c:if>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <h2>There are no content.&nbsp;You can add content using admin page.</h2>
        </c:otherwise>
    </c:choose>
</div>