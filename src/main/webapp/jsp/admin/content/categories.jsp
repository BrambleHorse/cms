<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="manage-categories">
    <h3> <li><a href="/admin.categories.do?action=create">Создать новую категорию</a></li></h3>

    <c:set var="categoryLength" value="${fn:length(categoryList)}"/>
    <c:choose>
        <c:when test="${categoryLength > 0}">
            <c:forEach items="${categoryList}" var="category">
                <li>
                    <a href="${pageContext.request.contextPath}/index.do?category=${category.id}">${category.name}</a> -->
                    <a href="/admin.categories.do?action=edit&categoryId=${category.id}">Редактировать категорию</a>
                    <a href="/admin.content.do?categoryId=${category.id}">Редактировать контент категории</a>
                    <a href="/admin.categories.do?action=delete&categoryId=${category.id}">Удалить категорию</a>
                    <c:set var="subCategoryLength" value="${fn:length(category.childCategories)}"/>
                    <c:choose>
                        <c:when test="${subCategoryLength > 0}">
                            <ul>
                                <c:forEach items="${category.childCategories}" var="subCategory">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/index.do?category=${subCategory.id}">${subCategory.name}</a> -->
                                        <a href="/admin.categories.do?action=edit&categoryId=${subCategory.id}">Редактировать категорию</a>
                                        <a href="/admin.content.do?categoryId=${subCategory.id}">Редактировать контент категории</a>
                                       <a href="/admin.categories.do?action=delete&categoryId=${subCategory.id}">Удалить категорию</a>
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
</div>
