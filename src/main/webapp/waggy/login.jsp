<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link
            href="https://fonts.googleapis.com/css2?family=Chilanka&amp;family=Montserrat:wght@300;400;500&amp;display=swap"
            rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="/style/login.css"/>">
</head>
<body>
<div id="main">
    <jsp:include page="header.jsp"></jsp:include>

    <div id="login">
        <div id="form-login" class="item-login">
            <div class="form-title">
                <h2>Đăng nhập</h2>
            </div>
            <div class="form-input">
                <form action="http://localhost:8080/waggyHome?action=submitAccount" method="post">
                    <div class="item-form">
                        <p>${error}</p>
                        <input type="text" name="account" placeholder="Tài khoản">
                        <input type="password" name="password" placeholder="Mật khẩu">
                        <button type="submit">Đăng nhập</button>
                    </div>
                </form>
                <div class="chain">
                    <a href="http://localhost:8080/waggyHome?action=register">Đăng ký</a>
                </div>
            </div>
        </div>

    </div>
    <jsp:include page="footer.jsp"></jsp:include>
</div>
</body>

</html>
