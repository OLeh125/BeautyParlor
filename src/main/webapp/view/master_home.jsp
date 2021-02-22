<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>Orders</title>
    <link href="${pageContext.request.contextPath}/view/asserts/css/datetimepicker.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment-with-locales.min.js" integrity="sha512-LGXaggshOkD/at6PFNcp2V2unf9LzFq6LE+sChH7ceMTDP0g2kn6Vxwgg7wkPP7AAtX+lmPqPdxB47A0Nz0cMQ==" crossorigin="anonymous"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/asserts/js/datetimepicker.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/asserts/css/signin_up.css" >
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/asserts/css/home.css" >
    <link href="https://fonts.googleapis.com/css2?family=Kaushan+Script&family=Montserrat&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/asserts/css/client_orders.css" >
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/asserts/css/table_sort.css" >
    <style>
        body{ background: url("/view/asserts/images/intro.jpg") ; direction: ltr; font-family: 'Montserrat', sans-serif; }
    </style>
    <script type="text/javascript">
        $(document).ready( function () {
            $('#picker').dateTimePicker();
            $('#picker-no-time').dateTimePicker({ showTime: false, dateFormat: 'YYYY-MM-DD', title: 'Select Date',startD:'${orderDay}',isSent:'${send}'});
        })
    </script>
</head>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="lables" />
<body>
<header class="header---main">
    <div class="container--header">
        <div class="header__inner--main">
            <div class="header__logo">Beauty</div>
            <nav class="nav">
                <a class="nav_link" href="/logout"><fmt:message key="label.sign.out" /></a>
            </nav>
        </div>
    </div>
</header>

<div class="container" style="position: absolute; top:0">
    <form id="date_picker_form" action="/master_home" method="post">
        <h3 style="text-align: center;margin-top: -10px"><fmt:message key="label.title.choose.date" /></h3>
        <div style=" width: 385px; margin: 35px auto; padding-bottom:14px;  color: #54646b">
            <div id="picker-no-time"></div>
            <input type="hidden" id="result2" value="" name="orderDate"/>
        </div>
    </form>
</div>

<div class="table-box" style="width: 1000px;  position: absolute; top:400px">
    <table class="table table-sortable">
        <thead>
        <tr>
            <th><fmt:message key="label.orders.time" /></th>
            <th><fmt:message key="label.orders.client" /></th>
            <th><fmt:message key="label.orders.date" /></th>
            <th><fmt:message key="label.orders.or.time" /></th>
            <th><fmt:message key="label.orders.treatments" /></th>
            <th><fmt:message key="label.orders.price" /></th>
            <th><fmt:message key="label.orders.status" /></th>
            <th><fmt:message key="label.orders.action" /></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${schedule}" var="sch">
            <form action="/master_home" method="post">
                <tr>
                    <td style="padding:10px">${sch.key}</td>
                    <c:choose>
                        <c:when test="${sch.value != null }">
                            <c:choose>
                                <c:when test="${sessionScope.locale == 'en'}">
                                    <td>${sch.value.client.nameEn} ${sch.value.client.surnameEn}</td>
                                </c:when>
                                <c:when test="${sessionScope.locale == 'ua'}">
                                    <td>${sch.value.client.nameUa} ${sch.value.client.surnameUa}</td>
                                </c:when>
                            </c:choose>
                            <td><input  type="hidden" name="date" value="${sch.value.treatmentExecutionDate}">${sch.value.treatmentExecutionDate}</td>
                            <td><input  type="hidden" name="time" value="${sch.value.treatmentExecutionTime}">${sch.value.treatmentExecutionTime}</td>
                            <c:choose>
                                <c:when test="${sessionScope.locale == 'en'}">
                                    <td>${sch.value.treatment.nameEn}</td>
                                </c:when>
                                <c:when test="${sessionScope.locale == 'ua'}">
                                    <td>${sch.value.treatment.nameUa}</td>
                                </c:when>
                            </c:choose>
                            <td>${sch.value.treatment.price}</td>
                            <c:choose>
                                <c:when test="${sessionScope.locale == 'en'}">
                                    <td>${sch.value.statusEn}</td>
                                </c:when>
                                <c:when test="${sessionScope.locale == 'ua'}">
                                    <td>${sch.value.statusUa}</td>
                                </c:when>
                            </c:choose>
                            <c:if test="${(sch.value.statusEn == 'PAID' ) && (sch.value.treatmentExecutionDate == currentDate)}">
                                <td><input class="table__button" type="submit" name="isDone" value="done"></td>
                            </c:if>

                        </c:when>
                    </c:choose>
                </tr>
            </form>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src = "${pageContext.request.contextPath}/view/asserts/js/table_sort.js"></script>
</body>
</html>
