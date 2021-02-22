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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/asserts/css/index.css">
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="lables" />
<header class="header---main">
    <div class="container--header">
        <div class="header__inner--main">
            <div class="header__logo">Beauty</div>
            <nav class="nav">
                <a class="nav_link" style="text-decoration: none" href="/treatments"><fmt:message key="label.services"/></a>
                <a class="nav_link" style="text-decoration: none" href="/masters"><fmt:message key="label.masters"/></a>
                <a class="nav_link" style="text-decoration: none" href="/signInUp"><fmt:message key="label.sign.in.up"/></a>
                <a class="nav_link" style="text-decoration: none" href="/index"><fmt:message key="label.main.page"/></a>
            </nav>
        </div>
    </div>
</header>

<div class="reviews__container" style="top:20%; padding-bottom: 0">
    <h2 style="text-align: center"><fmt:message key="label.reviews.title"/></h2>
    <form action="/signInUp" type="submit">
        <button class="form__button" style="margin-bottom: 25px; margin-top: 30px" ><fmt:message key="label.leave.review"/></button>
    </form>
    <c:forEach items="${reviews}" var="review">
        <div>
            <c:choose>
                <c:when test="${sessionScope.locale == 'ua'}">
                    <h3 class="user__name">${review.client.nameUa} ${review.client.surnameUa}    <small style="font-size: 13px; white-space: nowrap">${review.dateTimeFormat}</small></h3>
                </c:when>
                <c:when test="${sessionScope.locale == 'en'}">
                    <h3 class="user__name">${review.client.nameEn} ${review.client.surnameEn}     <small style="font-size: 13px; white-space: nowrap">${review.dateTimeFormat}</small></h3>
                </c:when>
            </c:choose>
            <div class="content">
                <p>${review.content}</p>
            </div>
        </div>
    </c:forEach>


    <nav style="padding-top: 50px;display: flex; justify-content: center;">
        <ul class="pagination">
            <c:if test="${currentPage != 1}">
                <li class="page-item"><a class="page-link" style="background: #333; color:#fff"
                                         href="reviews?&currentPage=${currentPage-1}"><fmt:message key="label.pagination.previous"/></a>
                </li>
            </c:if>

            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <li class="page-item active"><a class="page-link" style="background: #fff; color:#333; border-color: #333">
                                ${i} <span class="sr-only">(current)</span></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link" style="background: #333; color:#fff"
                                                 href="reviews?&currentPage=${i}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${currentPage lt noOfPages}">
                <li class="page-item"><a class="page-link" style="background: #333; color:#fff"
                                         href="reviews?&currentPage=${currentPage+1}"><fmt:message key="label.pagination.next"/></a>
                </li>
            </c:if>
        </ul>
    </nav>

</div>



</body>
</html>
