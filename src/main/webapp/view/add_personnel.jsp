<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add personnel</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Kaushan+Script&family=Montserrat&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/asserts/css/signin_up.css" >
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="lables" />
<header class="header---main">
    <div class="container--header">
        <div class="header__inner--main">
            <div class="header__logo">Beauty</div>
            <nav class="nav">
                <a class="nav_link" href="/admin"><fmt:message key="label.link.orders"/></a>
                <a class="nav_link" href="/logout"><fmt:message key="label.sign.out"/></a>
            </nav>
        </div>
    </div>
</header>

<div class="container">

    <form action="/admin/personnel" method="post" class="form" id="createAccount">
        <h1 class="form__title"><fmt:message key="label.title.add.personnel"/></h1>
        <div class="form__message form__message--error"></div>
        <div class="form__input-group">
            <input type="text" class="form form__input" required pattern="[A-Za-z]{1,32}" autofocus placeholder="<fmt:message key="label.placeholder.name.en"/>" name="en_name">
            <div class="form__input-error-message">
            </div>
        </div>

        <div class="form__input-group">
            <input type="text" class="form form__input" required pattern="[А-ЩЬЮЯҐЄІЇа-щьюяґєії]{1,32}"  autofocus placeholder="<fmt:message key="label.placeholder.name.ua"/>" name="ua_name">
            <div class="form__input-error-message">
            </div>
        </div>

        <div class="form__input-group">
            <input type="text" class="form form__input" required pattern="[A-Za-z]{1,32}" autofocus placeholder="<fmt:message key="label.placeholder.surname.en"/>" name="en_surname">
            <div class="form__input-error-message">
            </div>
        </div>
        <div class="form__input-group">
            <input type="text" class="form form__input" required pattern="[А-ЩЬЮЯҐЄІЇа-щьюяґєії]{1,32}"   autofocus placeholder="<fmt:message key="label.placeholder.surname.ua"/>" name="ua_surname">
            <div class="form__input-error-message">
            </div>
        </div>
        <div class="form__input-group">
            <input type="email" class="form form__input" required autofocus placeholder="Email" name="email">
            <div class="form__input-error-message">

            </div>
        </div>
        <div class="form__input-group">
            <input type="password" class="form form__input" required pattern=".{2,}"  autofocus placeholder="<fmt:message key="label.placeholder.password"/>" name="r_password">
            <div class="form__input-error-message">
            </div>
        </div>
        <div class="form__input-group">
            <input type="password" class="form form__input" required pattern=".{2,}" autofocus placeholder="<fmt:message key="label.placeholder.conf.password"/>" name="r_c_password">
            <div class="form__input-error-message">
            </div>
        </div>

        <div class="radio">
            <input class="radio__input" type="radio" id="master" name="role" value="master">
            <label for="master" class="radio__label"><fmt:message key="label.radio.master"/></label>
        </div>
        <div class="radio">
            <input class="radio__input" type="radio" id="admin" name="role" value="admin" >
            <label for="admin" class="radio__label"><fmt:message key="label.radio.admin"/></label>
        </div>

        <button class="form__button" type="submit" ><fmt:message key="label.continue.button"/></button>
    </form>
</div>
</body>
</html>