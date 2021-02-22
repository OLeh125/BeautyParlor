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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/asserts/css/edit.css" >

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
                <a class="nav_link" style="text-decoration: none" href="/admin/personnel"><fmt:message key="label.add.personnel"/></a>
                <a class="nav_link" style="text-decoration: none" href="/logout"><fmt:message key="label.sign.out"/></a>
            </nav>
        </div>
    </div>
</header>

<div class="container" style="position: absolute; top:0">
    <form id="date_picker_form" action="/admin" method="post">
        <h3 style="text-align: center;margin-top: -10px"><fmt:message key="label.title.choose.date"/></h3>
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
            <th>Id</th>
            <th><fmt:message key="label.orders.master"/></th>
            <th><fmt:message key="label.orders.client"/></th>
            <th><fmt:message key="label.orders.date"/></th>
            <th><fmt:message key="label.orders.time"/></th>
            <th><fmt:message key="label.orders.treatments"/></th>
            <th><fmt:message key="label.orders.price"/></th>
            <th><fmt:message key="label.orders.status"/></th>
            <th><fmt:message key="label.orders.cancel"/></th>
            <th><fmt:message key="label.orders.confirm"/></th>
            <th><fmt:message key="label.orders.edit"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${orders}" var="order">

            <tr>
                <form id = "admin__form" action="/admin" method="post">
                    <td><input  type="hidden" name="orderId" value="${order.id}">${order.id}</td>
                    <c:choose>
                        <c:when test="${sessionScope.locale == 'en'}">
                            <td><input  type="hidden" name="masterNameAndSurname" value="${order.master.nameEn} ${order.master.surnameEn}">${order.master.nameEn} ${order.master.surnameEn}</td>
                        </c:when>
                        <c:when test="${sessionScope.locale == 'ua'}">
                            <td><input  type="hidden" name="masterNameAndSurname" value="${order.master.nameUa} ${order.master.surnameUa}">${order.master.nameUa} ${order.master.surnameUa}</td>
                        </c:when>
                    </c:choose>
                    <c:choose>
                        <c:when test="${sessionScope.locale == 'en'}">
                            <td>${order.client.nameEn} ${order.client.surnameEn}</td>
                        </c:when>
                        <c:when test="${sessionScope.locale == 'ua'}">
                            <td>${order.client.nameUa} ${order.client.surnameUa}</td>
                        </c:when>
                    </c:choose>

                    <td><input  type="hidden" name="date" value="${order.treatmentExecutionDate}">${order.treatmentExecutionDate}</td>


                    <c:choose>
                        <c:when test="${orderId == order.id}">
                            <td ><div class="select">
                                <select name="chosenTime" >
                                    <option value="" selected disabled hidden>${order.treatmentExecutionTime}</option>
                                    <c:forEach items="${freeTime}" var="time">
                                        <option>${time}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            </td>
                        </c:when>
                        <c:when test="${orderId != order.id}">
                            <td class="times"><input  type="hidden" name="time"  value="${order.treatmentExecutionTime}">${order.treatmentExecutionTime}</td>
                        </c:when>
                    </c:choose>

                    <c:choose>
                        <c:when test="${sessionScope.locale == 'en'}">
                            <td>${order.treatment.nameEn}</td>
                        </c:when>
                        <c:when test="${sessionScope.locale == 'ua'}">
                            <td>${order.treatment.nameUa}</td>
                        </c:when>
                    </c:choose>

                    <td>${order.treatment.price}</td>
                    <c:choose>
                        <c:when test="${sessionScope.locale == 'en'}">
                            <td>${order.statusEn}</td>
                        </c:when>
                        <c:when test="${sessionScope.locale == 'ua'}">
                            <td>${order.statusUa}</td>
                        </c:when>
                    </c:choose>

                    <c:if test="${orderId == order.id}">
                        <td><input class="table__button" type="submit" style="text-decoration: none; padding:4px" value="submit"></td>
                    </c:if>
                    <c:if test="${orderId != order.id}">
                        <c:if test="${order.statusEn == 'SUBMITTED' }">
                            <td><input class="table__button" type="submit" style="text-decoration: none; padding:4px" name="isCanceled" value="<fmt:message key="label.orders.button.cancel"/>"></td>
                        </c:if>
                        <c:if test="${order.statusEn == 'SUBMITTED'}">
                            <td><input class="table__button" type="submit" style="text-decoration: none; padding:4px" name="isConfirmed" value="<fmt:message key="label.orders.button.confirm"/>"></td>
                        </c:if>

                        <c:if test="${order.statusEn == 'SUBMITTED'}">
                            <td class ="edits"><input class="table__button" type="submit"  name="isEdit" value="<fmt:message key="label.orders.button.edit"/>" style="text-decoration: none; padding:4px" ></td>
                        </c:if>
                    </c:if>
                </form>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src = "${pageContext.request.contextPath}/view/asserts/js/table_sort.js"></script>
<script src = "${pageContext.request.contextPath}/view/asserts/js/edit.js"></script>


</body>
</html>
