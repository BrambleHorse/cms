<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Бетон</title>
    <link rel="stylesheet" href="../jsp/css/styles.css">
    <!--[if lt IE 9]>
    <script>
        document.createElement('header');
        document.createElement('nav');
        document.createElement('section');
        document.createElement('article');
        document.createElement('aside');
        document.createElement('footer');
    </script>
    <![endif]-->
</head>
<body>
<div class="wrapper">
    <header>
        <div class="logo">
            <img src="../jsp/images/logo.png" alt="">
            <div>производственная группа</div>
        </div>
        <div class="phone">
            8 (812) 677-28-96
        </div>
    </header>
    <nav>
        <ul class="nav-top">
            <c:set var="categoryLength" value="${fn:length(categoryList)}"/>
            <c:choose>
                <c:when test="${categoryLength > 0}">
                    <c:forEach items="${categoryList}" var="category">
                        <li><a href="${pageContext.request.contextPath}/index.do?category=${category.id}">${category.name}</a>
                        <c:set var="subCategoryLength" value="${fn:length(category.childCategories)}"/>
                        <c:choose>
                            <c:when test="${subCategoryLength > 0}">
                               <ul>
                              <c:forEach items="${category.childCategories}" var="subCategory">
                              <li><a href="${pageContext.request.contextPath}/index.do?category=${subCategory.id}">${subCategory.name}</a></li>
                              </c:forEach>
                               </ul>
                            </c:when>
                            <c:otherwise>

                            </c:otherwise>
                        </c:choose>
                            </li>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <h2>There are no categories.&nbsp;You can add categories using admin page.</h2>
                </c:otherwise>
            </c:choose>
        </ul>
    </nav>
    <div class="content-wrap">
        <div class="slider">
            <img src="../jsp/images/slide4.jpg" alt="">
        </div>
        <div class="side-r">
            <ul class="side-nav">
                <li><a href="#" class="nav-title"> Навигация по сайту</a></li>
                <li><a href="#"> О компании</a></li>
                <li><a href="#"> Прайс-лист</a></li>
                <li><a href="#"> Наши преимущества</a></li>
                <li><a href="#"> Опыт поставок</a></li>
                <li><a href="#"> Персонал</a></li>
                <li><a href="#"> Калькулятор бетона и металла</a></li>
                <li><a href="#"> Полезная информация</a></li>

            </ul>
            <div class="weather">
                <img src="../jsp/images/weather.png" alt="">
            </div>
            <div class="promo">
                <img src="../jsp/images/promo.png" alt="">
            </div>
        </div>
        <div class="content">
            <c:set var="contentLength" value="${fn:length(contentList)}"/>
            <c:choose>
                <c:when test="${contentLength > 0}">
                    <c:forEach items="${contentList}" var="content">

                        <c:if test="${content.type eq 'TABLE'}">
                            <table class="content-sheet">${content.htmlTable}</table>
                        </c:if>
                        <c:if test="${content.type eq 'TEXT'}">
                           <p class="content-text"> ${content.text} </p>
                        </c:if>
                        <c:if test="${content.type eq 'IMAGE'}">
                            <img src="${content.imagePath}"/>
                        </c:if>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <h2>There are no content.&nbsp;You can add content using admin page.</h2>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="clear-container"></div>
        <div class="table-wrap">
            <div class="ribbon"></div>
            <table class="info-block">
                <tr>
                    <td>
                        <div class="info-wrap">
                            <div class="title">Информация</div>
                            <ul>
                                <li><a href="#">Бетон товарный</a></li>
                                <li><a href="#">Металлопрокат</a></li>
                                <li><a href="#">Виды щебня</a></li>
                                <li><a href="#">Виды песка</a></li>
                            </ul>
                        </div>
                    </td>
                    <td>
                        <div class="info-wrap">
                            <div class="title">Информация</div>
                            <ul>
                                <li><a href="#">Бетон товарный</a></li>
                                <li><a href="#">Металлопрокат</a></li>
                                <li><a href="#">Виды щебня</a></li>
                                <li><a href="#">Виды песка</a></li>
                            </ul>
                        </div>
                    </td>
                    <td>
                        <div class="info-wrap last">
                            <div class="title">Информация</div>
                            <ul>
                                <li><a href="#">Бетон товарный</a></li>
                                <li><a href="#">Металлопрокат</a></li>
                                <li><a href="#">Виды щебня</a></li>
                                <li><a href="#">Виды песка</a></li>
                            </ul>
                        </div>
                    </td>

                </tr>
            </table>
        </div>
    </div>



    <div class="wrapper-clear-bot clear-container"></div>
</div>
<footer>
    <div class="footer-img"></div>
    <div>Copyright © 2012 ЛенАвтоБетон. Все права защищены.
        Разработка сайта 47Studio.
        Г. Санкт-Петербург, ул. Лисичанская, д. 6, офис № 542. Тел.: 8 (812) 677-28-96</div>
</footer>
</body>
</html>