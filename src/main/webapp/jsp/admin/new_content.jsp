<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

    <div class="new-content">
                <form action="/admin.content.do" method="GET">
                    <input type="hidden" name="action" value="create">
                    <input type="hidden" name="categoryId" value="${param.categoryId}">
                    <table class="admin-input">
                        <tr>
                            <td>Тип контента:</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><input type="radio" name="contentType" checked="checked" value="WYSIWYG"></td>
                            <td>Ввод через WYSIWYG редактор</td>
                        </tr>
                        <tr>
                            <td><input type="radio" name="contentType" value="TEXT"></td>
                            <td>Текст</td>
                        </tr>
                        <tr>
                            <td><input type="radio" name="contentType" value="TABLE"></td>
                            <td>Таблица</td>

                        </tr>
                        <tr>
                            <td><input type="radio" name="contentType" value="IMAGE"></td>
                            <td>Изображение</td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" value="Продолжить"></td>
                        </tr>
                    </table>
                </form>
    </div>
