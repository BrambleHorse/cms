<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="new-image-description">
    <form id="admin-form" action="/admin.catalog.item.do" method="post">
        <input type="hidden" name="catalogCategoryId" value="${param.catalogCategoryId}">
        <input type="hidden" name="action" value="create">
        <table class="admin-input">
            <tr>
                <td>Наименование товара:</td>
                <td><input type="text" name="itemName" class="text-input"></td>
            </tr>
            <tr>
                <td>Описание товара:</td>
                <td><textarea name="itemDescription" rows="12" cols="60"></textarea></td>
            </tr>
            <tr>
                <td>Цена товара:</td>
                <td><input type="text" name="itemPrice" class="number-input"></td>
            </tr>
            <c:set var="filterLength" value="${fn:length(filterList)}"/>
            <c:choose>
                <c:when test="${filterLength > 0}">
                    <c:forEach items="${filterList}" var="filter">
                      <tr>
                          <td>${filter.catalogCategoryFilterName}</td>
                          <td></td>
                      </tr>
                        <c:forEach items="${filter.filterCriteria}" var="criterion">
                            <tr>
                                <td>${criterion.filterCriterionValue}</td>
                                <td>
                                    <input type="checkbox" name="${criterion.filterCriterionValue}" value="${criterion.filterCriterionId}">
                                </td>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                </c:when>
            </c:choose>
            <c:set var="brandLength" value="${fn:length(brandList)}"/>
            <c:choose>
                <c:when test="${brandLength > 0}">
                    <tr>
                        <td>Бренд</td>
                        <td></td>
                    </tr>
                    <c:forEach items="${brandList}" var="brand">
                         <tr>
                             <td></td>
                             <td>
                        <input type="radio" id="brand" name="brandId"  value="${brand.brandId}">
                        <p>${brand.brandName}</p>
                             </td>
                         </tr>
                    </c:forEach>
                </c:when>
            </c:choose>
            <tr>
                <td></td>
                <td><input type="submit" value="Продолжить"></td>
            </tr>
        </table>
    </form>
</div>
