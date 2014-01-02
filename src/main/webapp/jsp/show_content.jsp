<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:choose>

    <c:when test="${contentValue eq 'content'}">

        <div class="content">
            <c:set var="contentLength" value="${fn:length(contentList)}"/>
            <c:choose>
                <c:when test="${contentLength > 0}">
                    <c:forEach items="${contentList}" var="content">

                        <c:choose>
                            <c:when test="${content.type eq 'TABLE'}">
                                <table class="content-sheet">${content.htmlTable}</table>
                            </c:when>
                            <c:when test="${content.type eq 'TEXT'}">
                                <p class="content-text"> ${content.text} </p>
                            </c:when>
                            <c:when test="${content.type eq 'IMAGE'}">
                                <p>${content.imagePath}</p>
                                <img src="${content.imagePath}"/>
                            </c:when>
                            <c:when test="${content.type eq 'WYSIWYG'}">
                                ${content.wysiwygData}
                            </c:when>
                            <c:otherwise>

                            </c:otherwise>
                        </c:choose>

                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <jsp:include page="under_construction.jsp"></jsp:include>
                </c:otherwise>
            </c:choose>
        </div>

    </c:when>

    <c:when test="${contentValue eq 'catalog'}">

        <div class="catalog-content">

            <c:set var="itemsLength" value="${fn:length(itemsSet)}"/>
            <c:choose>
                <c:when test="${itemsLength > 0}">
                    <c:forEach items="${itemsSet}" var="catalogItem">

                        <ul class="catalog-list">

                        <li> <h4> <a href="#">${catalogItem.itemName}</a> </h4> </li>
                        <li> <img src="${catalogItem.itemImagePath}" alt="no image"/> </li>
                        <li>Производитель: ${catalogItem.itemBrand.brandName}</li>
                        <li> <b>Цена: ${catalogItem.itemPrice} </b></li>
                        <li>Описание: ${catalogItem.itemDescription}</li>
                       </ul>

                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <h2>There are no items.&nbsp;You can add items using admin page.</h2>
                </c:otherwise>
            </c:choose>

        </div>

    </c:when>

</c:choose>


