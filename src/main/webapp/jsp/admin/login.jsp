<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!doctype html>
<html lang="en">
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
<body>
<jsp:include page="admin_header.jsp" />
<div class="wrapper">
    <div class="admin-login">
        <form method="POST" action="j_security_check">
            <table class="admin-login-input">
                <tr>
                    <td>Имя пользователя:</td>
                    <td><input type="text" name="j_username"></td>
                </tr>
                <tr>
                    <td>Пароль:</td>
                    <td><input type="password" name="j_password"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit"  value="Вход"></td>
                </tr>
            </table>
        </form>
    </div>
    </div>

    <div class="container">

                <div class="clear-container"></div>



    </div>

<jsp:include page="admin_footer.jsp" />

</body>
</html>








