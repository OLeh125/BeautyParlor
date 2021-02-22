<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>Order Confirmation</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/asserts/css/signin_up.css" >
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/asserts/css/order_conf.css" >
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Kaushan+Script&family=Montserrat&display=swap" rel="stylesheet">

</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="lables" />
<header class="header---main">
    <div class="container--header">
        <div class="header__inner--main">
            <div class="header__logo">Beauty</div>
            <nav class="nav">
                <a class="nav_link" href="/client_order/reviews"><fmt:message key="label.reviews"/></a>
                <a class="nav_link" href="/client_order/orders"><fmt:message key="label.my.orders"/></a>
                <a class="nav_link" href="/logout"><fmt:message key="label.sign.out"/></a>

            </nav>
        </div>
    </div>
</header>

<div class="container">
    <form action="/client_order/conf" method="post">
        <h1 class="form__title"><fmt:message key="label.order.content"/></h1>
        <h3><fmt:message key="label.your.master"/> <input  type="hidden" name="masterNameAndSurname" value="${masterName} ${masterSurname}">${masterName} ${masterSurname}</h3>
        <h3><fmt:message key="label.you.ordered"/> ${treatmentName} <fmt:message key="label.which.price"/> ${treatmentPrice}</h3>
        <h3><fmt:message key="label.order.on"/> <input  type="hidden" name="date" value="${date}"> ${date}</h3>
        <h3><fmt:message key="label.order.at"/> <input  type="hidden" name="time" value="${time}"> ${time}</h3>
        <h3><fmt:message key="label.order.was.added"/> <a class="order__link" href="/client_order/orders" ><fmt:message key="label.order.your.orders"/></a> </h3>
        <h3><fmt:message key="label.order.you.able.to.pay.after"/></h3>
        <a class="order__button" href="/client_order" ><fmt:message key="label.button.one.more.order"/></a>
    </form>
</div>


</body>
</html>
