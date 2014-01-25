<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="edit-item-image">
    <fieldset>
        <legend>Загрузить изображение</legend>

        <form action="/admin.catalog.item.do" method="post" enctype="multipart/form-data">
            <input type="hidden" name="itemId" value="${item.itemId}">
            <input type="hidden" name="oldImageFilePath" value="${item.itemImageFilePath}">
            <input type="hidden" name="oldThumbImageFilePath" value="${item.itemThumbImageFilePath}">
            <input type="hidden" name="action" value="edit">
            <label for="fileName">Укажите файл:</label>
            <input id="fileName" type="file" name="fileName" size="30"/><br/>
            <input type="submit" value="Загрузить"/>
        </form>
    </fieldset>
</div>