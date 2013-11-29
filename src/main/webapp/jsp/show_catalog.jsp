<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="catalog-nav">
    <ul id="catalog-nav-ul">
        <c:set var="catalogCategoriesLength" value="${fn:length(catalogCategoriesList)}"/>
        <c:choose>
            <c:when test="${catalogCategoriesLength > 0}">
                <c:forEach items="${catalogCategoriesList}" var="catalogCategory">
                    <li>
                        <a href="${pageContext.request.contextPath}/index.do?catalogCategoryId=${catalogCategory.catalogCategoryId}">${catalogCategory.catalogCategoryName}</a>
                        <c:if test="${catalogCategory.catalogCategoryId eq param.catalogCategoryId}">
                            <ul>
                                <li>Selected one category!</li>
                                <li>And so on!</li>
                            </ul>
                        </c:if>
                    </li>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <h2>There are no categories.&nbsp;You can add categories using admin page.</h2>
            </c:otherwise>
        </c:choose>
    </ul>
</div>