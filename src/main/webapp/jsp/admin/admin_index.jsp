<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin page</title>
    <c:if test="${(adminAction eq 'new_wysiwyg_content') || (adminAction eq 'edit_wysiwyg_content')}">
        <script type="text/javascript" src="${pageContext.request.contextPath}/jsp/tinymce/tinymce.min.js"></script>
        <script type="text/javascript">
            tinymce.init({
                skin: "bramblehorse",
                theme: "modern",
                language : 'ru',
                plugins: [
                    "image","table","hr","textcolor","link"
                ],
                image_list: [
                    <c:forEach items="${availableImages}" var="image">
                    {title: '${image.imageName}', value: '${image.imagePath}'},
                    </c:forEach>
                ],
                image_advtab: true,
                selector: "textarea",
                width: 800,
                height: 500
            });
        </script>
    </c:if>
</head>
<body>
    <jsp:include page="admin_header.jsp"/>
    <c:choose>

        <%-- include admin menu pages --%>

        <c:when test="${adminAction eq 'categories'}"><jsp:include page="categories.jsp"></jsp:include></c:when>
        <c:when test="${adminAction eq 'content'}"><jsp:include page="content.jsp"></jsp:include></c:when>
        <c:when test="${adminAction eq 'links'}"><jsp:include page="links.jsp"></jsp:include></c:when>
        <c:when test="${adminAction eq 'accounts'}"><jsp:include page="accounts.jsp"></jsp:include></c:when>

        <%-- include edit pages --%>

        <c:when test="${adminAction eq 'edit_category'}"><jsp:include page="edit_category.jsp"></jsp:include></c:when>
        <c:when test="${adminAction eq 'edit_image_content'}"><jsp:include page="edit_image_content.jsp"></jsp:include></c:when>
        <c:when test="${adminAction eq 'edit_table_content'}"><jsp:include page="edit_table_content.jsp"></jsp:include></c:when>
        <c:when test="${adminAction eq 'edit_text_content'}"><jsp:include page="edit_text_content.jsp"></jsp:include></c:when>
        <c:when test="${adminAction eq 'edit_wysiwyg_content'}"><jsp:include page="edit_wysiwyg_content.jsp"></jsp:include></c:when>
        <c:when test="${adminAction eq 'edit_link_content'}"><jsp:include page="edit_link_content.jsp"></jsp:include></c:when>
        <c:when test="${adminAction eq 'edit_link'}"><jsp:include page="edit_link.jsp"></jsp:include></c:when>
        <c:when test="${adminAction eq 'edit_account'}"><jsp:include page="edit_account.jsp"></jsp:include></c:when>

        <%-- include create pages --%>

        <c:when test="${adminAction eq 'new_category'}"><jsp:include page="new_category.jsp"></jsp:include></c:when>
        <c:when test="${adminAction eq 'new_content'}"><jsp:include page="new_content.jsp"></jsp:include></c:when>
        <c:when test="${adminAction eq 'new_image_content'}"><jsp:include page="new_image_content.jsp"></jsp:include></c:when>
        <c:when test="${adminAction eq 'new_table_content'}"><jsp:include page="new_table_content.jsp"></jsp:include></c:when>
        <c:when test="${adminAction eq 'new_text_content'}"><jsp:include page="new_text_content.jsp"></jsp:include></c:when>
        <c:when test="${adminAction eq 'new_wysiwyg_content'}"><jsp:include page="new_wysiwyg_content.jsp"></jsp:include></c:when>
        <c:when test="${adminAction eq 'new_account'}"><jsp:include page="new_account.jsp"></jsp:include></c:when>

        <%-- include settings pages --%>

        <c:when test="${adminAction eq 'settings_links'}"><jsp:include page="settings_links.jsp"></jsp:include></c:when>

        <c:otherwise>

            <div class="admin-content">
                <ul>
                    <li><a href="/admin.categories.do">Управление категориями и контентом</a></li>
                    <li><a href="/admin.content.do?mode=links">Управление ссылками</a></li>
                    <li><a href="/admin.accounts.do">Управление учетными записями администраторов</a></li>
                </ul>
            </div>

        </c:otherwise>
    </c:choose>

<jsp:include page="admin_footer.jsp"/>
</body>
</html>