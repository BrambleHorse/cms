<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/css/styles.css">
    <title>Admin page</title>
    <c:if test="${(adminAction eq 'new_wysiwyg_content') || (adminAction eq 'edit_wysiwyg_content')}">
        <script type="text/javascript" src="${pageContext.request.contextPath}/jsp/tinymce/tinymce.min.js"></script>
        <script type="text/javascript">
            tinymce.init({
                skin: "bramblehorse",
                theme: "modern",
                language: 'ru',
                plugins: [
                    "image", "table", "hr", "textcolor", "link"
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

    <%-- content --%>
    <c:when test="${adminAction eq 'categories'}">
        <jsp:include page="content/categories.jsp"></jsp:include>
    </c:when>
    <c:when test="${adminAction eq 'content'}">
        <jsp:include page="content/content.jsp"></jsp:include>
    </c:when>
    <c:when test="${adminAction eq 'links'}">
        <jsp:include page="content/links.jsp"></jsp:include>
    </c:when>
    <%-- commerce --%>
    <c:when test="${adminAction eq 'brands'}">
        <jsp:include page="commerce/brands.jsp"></jsp:include>
    </c:when>
    <c:when test="${adminAction eq 'admin_commerce'}">
        <jsp:include page="commerce/admin_commerce.jsp"></jsp:include>
    </c:when>
    <%-- security --%>
    <c:when test="${adminAction eq 'accounts'}">
        <jsp:include page="${pageContext.request.contextPath}/jsp/security/accounts.jsp"></jsp:include>
    </c:when>


    <%-- include edit pages --%>

    <%-- content --%>
    <c:when test="${adminAction eq 'edit_category'}">
        <jsp:include page="content/edit_category.jsp"></jsp:include>
    </c:when>
    <c:when test="${adminAction eq 'edit_image_content'}">
        <jsp:include page="content/edit_image.jsp"></jsp:include>
    </c:when>
    <c:when test="${adminAction eq 'edit_image_content_picture'}">
        <jsp:include page="content/edit_image_content.jsp"></jsp:include>
    </c:when>
    <c:when test="${adminAction eq 'edit_table_content'}">
        <jsp:include page="content/edit_table_content.jsp"></jsp:include>
    </c:when>
    <c:when test="${adminAction eq 'edit_text_content'}">
        <jsp:include page="content/edit_text_content.jsp"></jsp:include>
    </c:when>
    <c:when test="${adminAction eq 'edit_wysiwyg_content'}">
        <jsp:include page="content/edit_wysiwyg_content.jsp"></jsp:include>
    </c:when>
    <c:when test="${adminAction eq 'edit_link_content'}">
        <jsp:include page="content/edit_link_content.jsp"></jsp:include>
    </c:when>
    <c:when test="${adminAction eq 'edit_link'}">
        <jsp:include page="content/edit_link.jsp"></jsp:include>
    </c:when>
    <%-- commerce --%>
    <c:when test="${adminAction eq 'edit_brand'}">
        <jsp:include page="commerce/edit_brand.jsp"></jsp:include>
    </c:when>
    <%-- security --%>
    <c:when test="${adminAction eq 'edit_account'}">
        <jsp:include page="${pageContext.request.contextPath}/jsp/security/edit_account.jsp"></jsp:include>
    </c:when>

    <%-- include create pages --%>

    <%-- content --%>
    <c:when test="${adminAction eq 'new_category'}">
        <jsp:include page="content/new_category.jsp"></jsp:include>
    </c:when>
    <c:when test="${adminAction eq 'new_content'}">
        <jsp:include page="content/new_content.jsp"></jsp:include>
    </c:when>
    <c:when test="${adminAction eq 'new_image_content'}">
        <jsp:include page="content/new_image_content.jsp"></jsp:include>
    </c:when>
    <c:when test="${adminAction eq 'new_table_content'}">
        <jsp:include page="content/new_table_content.jsp"></jsp:include>
    </c:when>
    <c:when test="${adminAction eq 'new_text_content'}">
        <jsp:include page="content/new_text_content.jsp"></jsp:include>
    </c:when>
    <c:when test="${adminAction eq 'new_wysiwyg_content'}">
        <jsp:include page="content/new_wysiwyg_content.jsp"></jsp:include>
    </c:when>
    <%-- commerce --%>
    <c:when test="${adminAction eq 'new_brand'}">
        <jsp:include page="commerce/new_brand.jsp"></jsp:include>
    </c:when>
    <%--security--%>
    <c:when test="${adminAction eq 'new_account'}">
        <jsp:include page="${pageContext.request.contextPath}/jsp/security/new_account.jsp"></jsp:include>
    </c:when>

    <%-- include settings pages --%>

    <c:when test="${adminAction eq 'settings_links'}">
        <jsp:include page="content/settings_links.jsp"></jsp:include>
    </c:when>

    <c:otherwise>
        <jsp:include page="admin_content.jsp"></jsp:include>
    </c:otherwise>
</c:choose>

<jsp:include page="admin_footer.jsp"/>
</body>
</html>