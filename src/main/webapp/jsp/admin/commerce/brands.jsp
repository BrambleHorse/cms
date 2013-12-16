<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="manage-brands">
    <h3><li><a href="/admin.catalog.brand.do?action=create">Добавить новый бренд</a></li></h3>
    <c:set var="brandsLength" value="${fn:length(brandsList)}"/>
    <c:if test="${brandsLength > 0}">
        <c:forEach items="${brandsList}" var="brand">

            <hr/>
        </c:forEach>
    </c:if>

</div>