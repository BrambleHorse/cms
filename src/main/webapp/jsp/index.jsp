<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel=stylesheet href="../jsp/css/style.css">
    <script type="text/javascript" src="../jsp/js/jquery-1.10.2.js"></script>
    <script>
        onload = function ()
        {
            for (var lnk = document.links, j = 0; j < lnk.length; j++)
                if (lnk [j].href == document.URL){lnk [j].style.background = 'blue'; lnk[j].style.color = 'white';}}
    </script>
</head>
<body>
<div class="wrapper">
    <jsp:include page="header.jsp"/>
    <div class="sidebar">yrtytryrtytryrtyrtyr</div>
    <div class="content">
        gdfgsdfgstrhjrjyt
    </div>
    <div class="clear"></div>
    <div class="clear-footer"></div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
