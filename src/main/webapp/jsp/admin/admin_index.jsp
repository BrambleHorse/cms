<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin page</title>
</head>
<body>
    <jsp:include page="admin_header.jsp"/>
    <c:choose>
        <c:when test="${adminAction eq 'categories'}"><jsp:include page="categories.jsp"></jsp:include></c:when>
        <c:when test="${adminAction eq 'content'}"><jsp:include page="content.jsp"></jsp:include></c:when>
        <c:when test="${adminAction eq 'edit_category'}"><jsp:include page="edit_category.jsp"></jsp:include></c:when>
        <c:when test="${adminAction eq 'edit_image_content'}"><jsp:include page="edit_image_content.jsp"></jsp:include></c:when>
        <c:when test="${adminAction eq 'edit_table_content'}"><jsp:include page="edit_table_content.jsp"></jsp:include></c:when>
        <c:when test="${adminAction eq 'edit_text_content'}"><jsp:include page="edit_text_content.jsp"></jsp:include></c:when>
        <c:when test="${adminAction eq 'new_category'}"><jsp:include page="new_category.jsp"></jsp:include></c:when>
        <c:when test="${adminAction eq 'new_content'}"><jsp:include page="new_content.jsp"></jsp:include></c:when>
        <c:when test="${adminAction eq 'new_image_content'}"><jsp:include page="new_image_content.jsp"></jsp:include></c:when>
        <c:when test="${adminAction eq 'new_table_content'}"><jsp:include page="new_table_content.jsp"></jsp:include></c:when>
        <c:when test="${adminAction eq 'new_text_content'}"><jsp:include page="new_text_content.jsp"></jsp:include></c:when>
        <c:otherwise>

            <div class="admin-content">
                <ul>
                    <li><a href="/admin.categories.do">Управление категориями и контентом</a></li>
                    <li><a href="#">Управление учетными записями администраторов</a></li>
                </ul>
            </div>

        </c:otherwise>
    </c:choose>

<jsp:include page="admin_footer.jsp"/>
</body>
</html>