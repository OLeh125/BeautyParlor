<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>Reviews</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Kaushan+Script&family=Montserrat&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/asserts/css/signin_up.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/asserts/css/client_reviews.css">
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="lables" />

<header class="header---main">
    <div class="container--header">
        <div class="header__inner--main">
            <div class="header__logo">Beauty</div>
            <nav class="nav">
                <a class="nav_link" href="/client_order/orders"><fmt:message key="label.my.orders"/></a>
                <a class="nav_link" href="/logout"><fmt:message key="label.sign.out"/></a>
            </nav>
        </div>
    </div>
</header>

<div class="main--container">
    <form action="/client_order/reviews" method="post">
        <h1 class="main__form__title"><fmt:message key="label.my.feedback"/></h1>
        <div class="form__input-group">
            <textarea rows="5" cols="100"  name="feedback" maxlength="320" autofocus placeholder="<fmt:message key="label.placeholder.feedback"/>"></textarea>
        </div>
        <button class="main__form__button" type="submit"><fmt:message key="label.button.leave"/></button>
    </form>
</div>

<div class="reviews__container">
    <h2 style="text-align: center"><fmt:message key="label.title.reviews"/></h2>
    <c:forEach items="${reviews}" var="review">
        <div>
            <c:choose>
                <c:when test="${sessionScope.locale == 'ua'}">
                    <h3 class="user__name">${review.client.nameUa} ${review.client.surnameUa}     <small style="font-size: 13px;font-weight: normal">${review.dateTimeFormat}</small></h3>
                </c:when>
                <c:when test="${sessionScope.locale == 'en'}">
                    <h3 class="user__name">${review.client.nameEn} ${review.client.surnameEn}    <small style="font-size: 13px;font-weight: normal">${review.dateTimeFormat}</small></h3>
                </c:when>
            </c:choose>
            <div class="content">
                <p>${review.content}</p>
            </div>
        </div>
    </c:forEach>
</div>


</body>
</html>
