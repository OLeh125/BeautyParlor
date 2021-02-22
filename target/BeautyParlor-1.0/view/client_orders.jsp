<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Orders</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/asserts/css/signin_up.css" >
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Kaushan+Script&family=Montserrat&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/asserts/css/client_orders.css" >
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/asserts/css/table_sort.css">

</head>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="lables" />
<body>
<header class="header---main">
    <div class="container--header">
        <div class="header__inner--main">
            <div class="header__logo">Beauty</div>
            <nav class="nav">
                <a class="nav_link" href="/client_order/reviews"><fmt:message key="label.reviews"/></a>
                <a class="nav_link" href="/client_order"><fmt:message key="label.link.order.n"/></a>
                <a class="nav_link" href="/logout"><fmt:message key="label.sign.out"/></a>

            </nav>
        </div>
    </div>
</header>

<div class="table-box" style="width: 1000px">
    <table class="table table-sortable">
        <thead>
        <tr>
            <th><fmt:message key="label.orders.master"/></th>
            <th><fmt:message key="label.orders.date"/></th>
            <th><fmt:message key="label.orders.time"/></th>
            <th><fmt:message key="label.orders.treatments"/></th>
            <th><fmt:message key="label.orders.price"/></th>
            <th><fmt:message key="label.orders.status"/></th>
            <th><fmt:message key="label.orders.action"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${orders}" var="order">
            <form action="/client_order/orders" method="post">
                <tr>
                    <c:choose>
                        <c:when test="${sessionScope.locale== 'en'}">
                            <td><input  type="hidden" name="masterNameAndSurname" value="${order.master.nameEn} ${order.master.surnameEn}">${order.master.nameEn} ${order.master.surnameEn}</td>
                        </c:when>
                        <c:when test="${sessionScope.locale== 'ua'}">
                            <td><input  type="hidden" name="masterNameAndSurname" value="${order.master.nameUa} ${order.master.surnameUa}">${order.master.nameUa} ${order.master.surnameUa}</td>
                        </c:when>
                    </c:choose>
                    <td><input  type="hidden" name="date" value="${order.treatmentExecutionDate}">${order.treatmentExecutionDate}</td>
                    <td><input  type="hidden" name="time" value="${order.treatmentExecutionTime}">${order.treatmentExecutionTime}</td>
                    <c:choose>
                        <c:when test="${sessionScope.locale== 'en'}">
                            <td>${order.treatment.nameEn}</td>
                        </c:when>
                        <c:when test="${sessionScope.locale== 'ua'}">
                            <td>${order.treatment.nameUa}</td>
                        </c:when>
                    </c:choose>

                    <td>${order.treatment.price}</td>
                    <c:choose>
                        <c:when test="${sessionScope.locale== 'en'}">
                            <td>${order.statusEn}</td>
                        </c:when>
                        <c:when test="${sessionScope.locale== 'ua'}">
                            <td>${order.statusUa}</td>
                        </c:when>
                    </c:choose>
                    <c:if test="${order.statusEn == 'CONFIRMED'}">
                        <td><input class="table__button" type="submit" value="<fmt:message key="label.button.pay"/>" name="isPaid"></td>
                    </c:if>
                </tr>
            </form>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src = "${pageContext.request.contextPath}/view/asserts/js/table_sort.js"></script>
</body>
</html>