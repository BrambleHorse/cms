<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="links">
    <c:set var="linkLength" value="${fn:length(linkList)}"/>
    <c:choose>
        <c:when test="${linkLength > 0}">
            <c:forEach items="${linkList}" var="link">
            <a href="${link.linkValue}"><img src="${link.linkImagePath}" width="${footerLinksSize}" height="${footerLinksSize}"/></a>

            </c:forEach>
        </c:when>
        <c:otherwise>

        </c:otherwise>
   </c:choose>
</div>