<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="edit-image-description">
    <form action="/admin.catalog.item.do" method="post">
        <input type="hidden" name="catalogCategoryId" value="${item.itemCategory.catalogCategoryId}">
        <input type="hidden" name="itemId" value="${item.itemId}">
        <input type="hidden" name="action" value="edit">
        <table class="admin-input">
            <tr>
                <td>Наименование товара:</td>
                <td><input type="text" name="itemName" value="${item.itemName}"></td>
            </tr>
            <tr>
                <td>Описание товара:</td>
                <td><textarea name="itemDescription" rows="12" cols="60">${item.itemDescription}</textarea></td>
            </tr>
            <tr>
                <td>Цена товара:</td>
                <td><input type="text" name="itemPrice" value="${item.itemPrice}"></td>
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
                                    <input type="checkbox" name="${criterion.filterCriterionValue}" value="${criterion.filterCriterionId}"
                                    <c:forEach items="${item.filterCriteria}" var="itemCriterion"><c:if test="${criterion.filterCriterionId eq itemCriterion.filterCriterionId}"> checked="checked" </c:if></c:forEach>  >
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
                                <c:set var="itemBrand" value="${item.itemBrand}"></c:set>
                                <input type="radio" id="brand" name="brandId"  value="${brand.brandId}" <c:if test="${itemBrand.brandId eq brand.brandId}">checked="checked" </c:if>  >
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