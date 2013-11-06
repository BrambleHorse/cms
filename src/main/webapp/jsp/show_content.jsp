<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="content">
    <c:set var="contentLength" value="${fn:length(contentList)}"/>
    <c:choose>
        <c:when test="${contentLength > 0}">
            <c:forEach items="${contentList}" var="content">

                <c:choose>
                    <c:when test="${content.type eq 'TABLE'}">
                        <table class="content-sheet">${content.htmlTable}</table>
                    </c:when>
                    <c:when test="${content.type eq 'TEXT'}">
                        <p class="content-text"> ${content.text} </p>
                    </c:when>
                    <c:when test="${content.type eq 'IMAGE'}">
                        <img src="${content.imagePath}"/>
                    </c:when>
                    <c:when test="${content.type eq 'WYSIWYG'}">
                         ${content.wysiwygData}
                    </c:when>
                    <c:otherwise>

                    </c:otherwise>
                </c:choose>

            </c:forEach>
        </c:when>
        <c:otherwise>
           <jsp:include page="under_construction.jsp"></jsp:include>
        </c:otherwise>
    </c:choose>
</div>