<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!doctype html>
<html lang="en">

<%-- Head section --%>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/admin/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/admin/css/styles.css">
    <script src="${pageContext.request.contextPath}/jsp/admin/js/jquery-1.10.2.js"></script>
    <script src="${pageContext.request.contextPath}/jsp/admin/js/bootstrap.min.js"></script>
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
    <title>Bramblehorse-CMS</title>
</head>

<%-- Body section --%>
<body>
<div class="wrapper">
    <%-- Header include --%>
    <jsp:include page="admin_header.jsp"/>

        <%-- Main admin content container --%>
    <div class="container">
        <%-- include admin menu pages --%>
        <c:choose>

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
            <c:when test="${adminAction eq 'admin_commerce'}">
                <jsp:include page="commerce/admin_commerce.jsp"></jsp:include>
            </c:when>
            <c:when test="${adminAction eq 'brands'}">
                <jsp:include page="commerce/brands.jsp"></jsp:include>
            </c:when>
            <c:when test="${adminAction eq 'catalog_categories'}">
                <jsp:include page="commerce/catalog_categories.jsp"></jsp:include>
            </c:when>
            <c:when test="${adminAction eq 'catalog_category_filters'}">
                <jsp:include page="commerce/catalog_category_filters.jsp"></jsp:include>
            </c:when>
            <c:when test="${adminAction eq 'filter_criteria'}">
                <jsp:include page="commerce/filter_criterions.jsp"></jsp:include>
            </c:when>
            <c:when test="${adminAction eq 'items'}">
                <jsp:include page="commerce/items.jsp"></jsp:include>
            </c:when>
            <%-- security --%>
            <c:when test="${adminAction eq 'accounts'}">
                <jsp:include page="${pageContext.request.contextPath}/jsp/admin/accounts.jsp"></jsp:include>
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
            <c:when test="${adminAction eq 'edit_catalog_category'}">
                <jsp:include page="commerce/edit_catalog_category.jsp"></jsp:include>
            </c:when>
            <c:when test="${adminAction eq 'edit_catalog_category_filter'}">
                <jsp:include page="commerce/edit_catalog_category_filter.jsp"></jsp:include>
            </c:when>
            <c:when test="${adminAction eq 'edit_filter_criterion'}">
                <jsp:include page="commerce/edit_filter_criterion.jsp"></jsp:include>
            </c:when>
            <c:when test="${adminAction eq 'edit_item_description'}">
                <jsp:include page="commerce/edit_item_description.jsp"></jsp:include>
            </c:when>
            <c:when test="${adminAction eq 'edit_item_image'}">
                <jsp:include page="commerce/edit_item_image.jsp"></jsp:include>
            </c:when>
            <%-- security --%>
            <c:when test="${adminAction eq 'edit_account'}">
                <jsp:include page="${pageContext.request.contextPath}/jsp/admin/edit_account.jsp"></jsp:include>
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
            <c:when test="${adminAction eq 'new_catalog_category'}">
                <jsp:include page="commerce/new_catalog_category.jsp"></jsp:include>
            </c:when>
            <c:when test="${adminAction eq 'new_catalog_category_filter'}">
                <jsp:include page="commerce/new_catalog_category_filter.jsp"></jsp:include>
            </c:when>
            <c:when test="${adminAction eq 'new_filter_criterion'}">
                <jsp:include page="commerce/new_filter_criterion.jsp"></jsp:include>
            </c:when>
            <c:when test="${adminAction eq 'new_item_description'}">
                <jsp:include page="commerce/new_item_description.jsp"></jsp:include>
            </c:when>
            <c:when test="${adminAction eq 'new_item_image'}">
                <jsp:include page="commerce/new_item_image.jsp"></jsp:include>
            </c:when>
            <%--security--%>
            <c:when test="${adminAction eq 'new_account'}">
                <jsp:include page="${pageContext.request.contextPath}/jsp/admin/new_account.jsp"></jsp:include>
            </c:when>

            <%-- include settings pages --%>

            <c:when test="${adminAction eq 'settings_links'}">
                <jsp:include page="content/settings_links.jsp"></jsp:include>
            </c:when>

            <c:otherwise>
                <jsp:include page="admin_content.jsp"></jsp:include>
            </c:otherwise>
        </c:choose>

        <div class="clear-footer"></div>
    </div>
</div>

<%-- Footer include --%>
<jsp:include page="admin_footer.jsp" />

</body>
</html>