<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<nav>
    <ul id="nav">
        <c:set var="categoryLength" value="${fn:length(categoryList)}"/>
        <c:choose>
            <c:when test="${categoryLength > 0}">
                <c:forEach items="${categoryList}" var="category">
                    <li>
                        <a href="${pageContext.request.contextPath}/index.do?category=${category.id}">${category.name}</a>
                        <c:set var="subCategoryLength" value="${fn:length(category.childCategories)}"/>
                        <c:choose>
                            <c:when test="${subCategoryLength > 0}">
                                <ul>
                                    <c:forEach items="${category.childCategories}" var="subCategory">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/index.do?category=${subCategory.id}">${subCategory.name}</a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </c:when>
                            <c:otherwise>

                            </c:otherwise>
                        </c:choose>
                    </li>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <h2>There are no categories.&nbsp;You can add categories using admin page.</h2>
            </c:otherwise>
        </c:choose>
    </ul>
</nav>