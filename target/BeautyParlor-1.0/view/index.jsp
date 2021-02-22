<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Kaushan+Script&family=Montserrat&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/asserts/css/index.css">
    <title>Beauty</title>
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="lables" />

<header class="header">
    <div class="container">
        <div class="header__inner">
            <div class="header__logo">Beauty</div>
            <nav class="nav">
                <form action="/index" method="post">
                <a class="nav_link" href="/treatments"><fmt:message key="label.services" /></a>
                <a class="nav_link" href="/masters"><fmt:message key="label.masters" /></a>
                <a class="nav_link" href="/signInUp"  ><fmt:message key="label.sign.in.up" /></a>
                <input type="submit" style="padding-top: 4px;font-size: 20px;font-family: 'Montserrat', sans-serif;text-transform: uppercase;"
                       class="nav_link" hidden value="${sessionScope.locale}">
                </form>
            </nav>
        </div>
    </div>
</header>

<div class="intro">
    <div class="container">
        <div class="intro__inner">
            <h1 class="intro__title"><fmt:message key="label.intro.title" /></h1>
            <a class="btn" href="/reviews"><fmt:message key="label.reviews" /></a>
        </div>
    </div>
</div>

</body>
</html>