<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="header">
    <div id="logo"><img src="https://themewagon.github.io/waggy/images/logo.png" alt="Logo"></div>
    <div id="nav">
        <div class="sub-nav"><a href="http://localhost:8080/waggyHome?action=showHome">Trang chủ</a></div>
        <div class="sub-nav"><a href="#">Giới thiệu</a></div>
        <div class="sub-nav"><a href="http://localhost:8080/waggyHome?action=showProducts">Shop</a></div>
        <div class="sub-nav"><a href="#">Liên hệ</a></div>
    </div>
    <div id="user" style="justify-content: flex-end;">
        <c:if test="${acc == null}">
        <div class="sub-user" style="flex-basis: 30%; border: none"><a href="http://localhost:8080/waggyHome?action=login">Đăng nhập</a></div>
        </c:if>
        <c:if test="${acc != null && acc.isSeller == 0}">
        <div class="sub-user" style="flex-basis: 30%; border: none">
            <a href="http://localhost:8080/cart?action=showCart&idC=${sessionScope.acc.idAcc}">
                <i class="fa-solid fa-cart-shopping" style="color: #7a3a10; font-size:18px ;  line-height: 60px;"></i>
                <span>${total_quantity}</span>
            </a>
        </div>
        </c:if>
        <c:if test="${acc.isSeller == 1}">
            <div class="sub-user" style="flex-basis: 30%; border: none">
                <a href="http://localhost:8080/waggyHome?action=showProductInManager">Quản lý</a>
            </div>
        </c:if>
        <c:if test="${acc != null}">
        <div class="sub-user" style="flex-basis: 30%;"><a href="http://localhost:8080/waggyHome?action=logout">Đăng xuất</a></div>

        </c:if>
    </div>
</div>
