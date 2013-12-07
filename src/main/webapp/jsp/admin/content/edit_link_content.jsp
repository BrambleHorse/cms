<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="new-link-content">
    ${param.contentId}
    <fieldset>
        <legend>Загрузить иконку для ссылки</legend>
        <form action="/link.upload.do?linkAction=edit" method="post" enctype="multipart/form-data">
            <input type="hidden" name="contentId" value="${param.contentId}"/>
            <label for="fileName">Укажите файл:</label>
            <input id="fileName" type="file" name="fileName" size="30"/><br/>
            <input type="submit" value="Загрузить"/>
        </form>
    </fieldset>
</div>
