<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="new-link-content">
    <fieldset>
        <legend>Загрузить иконку для ссылки</legend>
        <form action="/admin.content.link.do" method="post" enctype="multipart/form-data">
            <label for="contentPosition">Ссылка:</label>
            <input type="hidden" value="create">
            <input id="linkValue" type="text" name="linkValue">
            <label for="contentPosition">Позиция:</label>
            <input id="contentPosition" type="text" name="contentPosition">
            <label for="isVisible">Отображать</label>
            <input type="checkbox" id="isVisible" name="isVisible" checked="true" value="visible">
            <label for="fileName">Укажите файл:</label>
            <input id="fileName" type="file" name="fileName" size="30"/><br/>
            <input type="submit" value="Загрузить"/>
        </form>
    </fieldset>
</div>
