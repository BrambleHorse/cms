<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="edit-image-content">

        <fieldset>
            <legend>Загрузить новое изображение</legend>
            <form action="/admin.content.image.do" method="post" enctype="multipart/form-data">
                <input type="hidden" name="categoryId" value="${param.categoryId}">
                <input type="hidden" name="action" value="edit">
                <input type="hidden" name="contentId" value="${content.contentId}" />
                <input type="hidden" name="contentPosition" value="${content.contentPosition}" />
                <input type="hidden" name="oldImageFilePath" value="${content.imageFilePath}">
                <input type="hidden" name="oldThumbImageFilePath" value="${content.thumbImageFilePath}">
                <input type="hidden" name="isVisible" value="${isVisible}">
                <label for="fileName">Укажите файл:</label>
                <input id="fileName" type="file" name="fileName" size="30" /><br/>
                <input type="submit" value="Загрузить" />
            </form>
        </fieldset>

</div>
