<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="lables" />
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/asserts/css/home.css" >
    <link href="${pageContext.request.contextPath}/view/asserts/css/datetimepicker.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/asserts/css/signin_up.css" >

    <link href="https://fonts.googleapis.com/css2?family=Kaushan+Script&family=Montserrat&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment-with-locales.min.js"
            integrity="sha512-LGXaggshOkD/at6PFNcp2V2unf9LzFq6LE+sChH7ceMTDP0g2kn6Vxwgg7wkPP7AAtX+lmPqPdxB47A0Nz0cMQ=="
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/asserts/js/datetimepicker.js"></script>
    <style>
        body{ background: url("/view/asserts/images/intro.jpg") ; direction: ltr; font-family: 'Montserrat', sans-serif;}
    </style>
    <script type="text/javascript">
        $(document).ready( function () {
            $('#picker-no-time').dateTimePicker({ showTime: false, dateFormat: 'YYYY-MM-DD', title: 'Select Date',startD:'${orderDay}',isSent:'${send}'});
        })
    </script>
    <title>Order</title>
</head>
<body>
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
    <form id="date_picker_form" action="/client_order" method="post">
        <h1 class="form__title"><fmt:message key="label.title.order"/></h1>
        <div class="select">
            <select name="chosenTreatment" onchange='submitTreatment();'>
                <option value="" selected disabled hidden>${chosenT}</option>
                <c:forEach items="${treatments}" var="treatment">
                    <c:choose>
                        <c:when test="${sessionScope.locale == 'en'}">
                            <option >${treatment.nameEn} ${treatment.price}</option>
                        </c:when>
                        <c:when test="${sessionScope.locale == 'ua'}">
                            <option >${treatment.nameUa} ${treatment.price}</option>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </select>
        </div>
        <div class="select">
            <select name="chosenMaster" onchange='submitMaster();'>
                <option value="" selected disabled hidden>${chosenM}</option>
                <c:forEach items="${masters}" var="master">
                    <c:choose>
                        <c:when test="${sessionScope.locale == 'en'}">
                            <option>${master.nameEn} ${master.surnameEn}</option>
                        </c:when>
                        <c:when test="${sessionScope.locale == 'ua'}">
                            <option>${master.nameUa} ${master.surnameUa}</option>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </select>
        </div>
        <div style=" width: 385px; margin: 35px auto; padding-bottom:14px;  color: #54646b">
            <div id="picker-no-time"></div>
            <input type="hidden" id="result2" value="" name="orderDate"/>
        </div>
        <div class="select">
            <select name="chosenTime">
                <option value="" selected disabled hidden><fmt:message key="label.placeholder.free.time"/></option>
                <c:forEach items="${freeTime}" var="time">
                    <option>${time}</option>
                </c:forEach>
            </select>
        </div>
        <button class="form__button" type="submit" ><fmt:message key="label.continue.button"/></button>
    </form>

</div>


</body>
</html>
