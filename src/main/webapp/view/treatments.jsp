<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
    <title>Treatments</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Kaushan+Script&family=Montserrat&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/asserts/css/signin_up.css" >
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/asserts/css/client_orders.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/asserts/css/table_sort.css" >

</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="lables" />
<header class="header---main">
    <div class="container--header">
        <div class="header__inner--main">
            <div class="header__logo">Beauty</div>
            <nav class="nav">
                <a class="nav_link" href="/masters"><fmt:message key="label.masters"/></a>
                <a class="nav_link" href="/index"><fmt:message key="label.main.page"/></a>
                <a class="nav_link" href="/signInUp"><fmt:message key="label.sign.in.up"/></a>
            </nav>
        </div>
    </div>
</header>

<div class="table-box" style="width: 1000px; height: 300px">
    <table class="table table-sortable">
        <thead>
        <tr>
            <th><fmt:message key="label.treatments.name"/></th>
            <th><fmt:message key="label.price"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${treatments}" var="treatment">
            <tr>
                <c:choose>
                    <c:when test="${sessionScope.locale== 'en'}">
                        <td>${treatment.nameEn}</td>
                    </c:when>
                    <c:when test="${sessionScope.locale== 'ua'}">
                        <td>${treatment.nameUa}</td>
                    </c:when>
                </c:choose>
                <td>${treatment.price}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src = "${pageContext.request.contextPath}/view/asserts/js/table_sort.js"></script>
</body>
</html>
