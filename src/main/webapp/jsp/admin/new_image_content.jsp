<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

    <div class="new-image-content">
        <fieldset>
            <legend>Загрузить изображение</legend>
            <form action="/image.upload.do" method="post" enctype="multipart/form-data">
                <input type="hidden" name="categoryId" value="${param.categoryId}">
                <label for="contentPosition">Место изображения в списке:</label>
                <input id="contentPosition" type="text" name="contentPosition">
                <label for="fileName">Укажите файл:</label>
                <input id="fileName" type="file" name="fileName" size="30"/><br/>
                <input type="submit" value="Загрузить"/>
            </form>
        </fieldset>
    </div>
