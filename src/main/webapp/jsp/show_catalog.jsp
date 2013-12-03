<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="catalog-nav">
    <ul id="catalog-nav-ul">
        <c:set var="catalogCategoriesLength" value="${fn:length(catalogCategoriesList)}"/>
        <c:choose>
            <c:when test="${catalogCategoriesLength > 0}">
                <c:forEach items="${catalogCategoriesList}" var="catalogCategory">
                    <li>
                        <a href="${pageContext.request.contextPath}/index.do?catalogCategoryId=${catalogCategory.catalogCategoryId}">${catalogCategory.catalogCategoryName}</a>
                        <c:if test="${catalogCategory.catalogCategoryId eq param.catalogCategoryId}">

                            <c:set var="filtersLength" value="${fn:length(filtersList)}"/>
                            <c:choose>
                                <c:when test="${filtersLength > 0}">
                                    <form action="/index.do" method="post">
                                        <input type="hidden" name="catalogCategoryId" value="${param.catalogCategoryId}">
                                     <table>
                                    <c:forEach items="${filtersList}" var="catalogCategoryFilter">

                                    <tr>
                                        <td> ${catalogCategoryFilter.catalogCategoryFilterName} </td>
                                        <td></td>
                                    </tr>

                                             <c:forEach items="${catalogCategoryFilter.filterCriterions}" var="filterCriterion">
                                                 <tr>
                                                     <c:set var="index">criterion${filterCriterion.filterCriterionId}</c:set>
                                                <td><input type="checkbox" name="${filterCriterion.filterCriterionValue}" <c:if test="${requestScope[index] eq true}"> checked="checked"</c:if> value="checked"></td>
                                                <td>${filterCriterion.filterCriterionValue} </td>
                                                 </tr>
                                                 <tr>
                                                     <td>${index}</td>
                                                     <td>${requestScope[index]}</td>
                                                 </tr>
                                             </c:forEach>

                                    </c:forEach>
                                         <tr>
                                             <td><input type="submit" value="Показать"></td>
                                             <td></td>
                                         </tr>
                                     </table>
                                    </form>
                                </c:when>
                            </c:choose>

                        </c:if>
                    </li>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <h2>There are no catalog categories.&nbsp;You can add categories using admin page.</h2>
            </c:otherwise>
        </c:choose>
    </ul>
</div>
