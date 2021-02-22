<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sing in / Sing up Form</title>
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
                <a class="nav_link" href="/treatments"><fmt:message key="label.services"/></a>
                <a class="nav_link" href="/masters"><fmt:message key="label.masters"/></a>
                <a class="nav_link" href="/index"><fmt:message key="label.main.page"/></a>
            </nav>
        </div>
    </div>
</header>

<div class="container">
    <form action="/signIn" method="post" class="form" id="login">
        <h1 class="form__title"><fmt:message key="label.sign.in.title"/></h1>
        <div class="form__message form__message--error"></div>
        <div class="form__input-group">
            <input type="text" class="form form__input" required pattern="[A-ZА-Яа-яa-zЄєї]{1,32}" name="name" autofocus placeholder="<fmt:message key="label.placeholder.name"/>">
            <div class="form__input-error-message">

            </div>
        </div>
        <div class="form__input-group">
            <input type="text" class="form form__input" required pattern="[A-ZА-Яа-яa-zЄєї]{1,32}" name="surname" autofocus placeholder="<fmt:message key="label.placeholder.surname"/>">
            <div class="form__input-error-message">

            </div>
        </div>

        <div class="form__input-group">
            <input type="password" class="form form__input" required pattern=".{2,}" name="password" id="password" autofocus placeholder="<fmt:message key="label.placeholder.password"/>">
            <div class="form__input-error-message">
            </div>
        </div>
        <button class="form__button" type="submit"><fmt:message key="label.continue.button"/></button>
        <p class="form__text">
            <a id="linkCreateAccount"  class="form__link"><fmt:message key="label.link.dont.have"/></a>
        </p>
    </form>

    <form action="/signUp" method="post" class="form form--hidden" id="createAccount">
        <h1 class="form__title"><fmt:message key="label.sign.up.title"/></h1>
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
            <input type="text" class="form form__input" required pattern="[А-ЩЬЮЯҐЄІЇа-щьюяґєії]{1,32}" autofocus placeholder="<fmt:message key="label.placeholder.surname.ua"/>" name="ua_surname">
            <div class="form__input-error-message">
            </div>
        </div>
        <div class="form__input-group">
            <input type="email" class="form form__input" required  autofocus placeholder="Email" name="email">
            <div class="form__input-error-message">

            </div>
        </div>
        <div class="form__input-group">
            <input type="password" class="form form__input" required pattern=".{2,}" autofocus placeholder="<fmt:message key="label.placeholder.password"/>" name="r_password">
            <div class="form__input-error-message">
            </div>
        </div>
        <div class="form__input-group">
            <input type="password" class="form form__input" required pattern=".{2,}" autofocus placeholder="<fmt:message key="label.placeholder.conf.password"/>" name="r_c_password">
            <div class="form__input-error-message">
            </div>
        </div>


        <button class="form__button" type="submit" ><fmt:message key="label.continue.button"/></button>
        <p class="form__text">
            <a id="linkLogin"  class="form__link"><fmt:message key="label.link.already.have"/></a>
        </p>
    </form>
</div>
<script src="${pageContext.request.contextPath}/view/asserts/js/signin_up.js"></script>
</body>
</html>
