<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 12/10/24
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shop</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link
            href="https://fonts.googleapis.com/css2?family=Chilanka&amp;family=Montserrat:wght@300;400;500&amp;display=swap"
            rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="/style/shop.css"/>">
</head>

<body>
<div id="main">
    <jsp:include page="/waggy/header.jsp"></jsp:include>
    <div id="content">
        <div class="sub-content" id="nav-content">
            <div class="item-content" id="title">
                    <h3>${cate != null ? cate.nameC : "Tất cả sản phẩm"}</h3>
            </div>
            <div class="item-content" id="nav-search">
                <div class="list-search"><a href="#">Loại sản phẩm <i class="fa-solid fa-caret-down"></i></a>
                    <ul class="sub-nav-search">
                        <c:forEach var="item" items="${listC}">
                        <li><a href="http://localhost:8080/waggyHome?action=showProductsByCategory&idC=${item.idC}"> - ${item.nameC}</a></li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="list-search"><a href="#">Giá sản phẩm <i class="fa-solid fa-caret-down"></i></a>
                    <ul class="sub-nav-search">
                        <li><a href="http://localhost:8080/waggyHome?action=showProductsByPrice&min=5&max=20">5 - 20 $</a></li>
                        <li><a href="http://localhost:8080/waggyHome?action=showProductsByPrice&min=20&max=50">20 - 50 $</a></li>
                        <li><a href="http://localhost:8080/waggyHome?action=showProductsByPrice&min=50&max=100">50 - 100 $</a></li>
                        <li><a href="http://localhost:8080/waggyHome?action=showProductsByPrice&min=100&max=1000"> > 100 $ </a></li>
                    </ul>
                </div>
            </div>
            <div class="item-content" id="input-search">
                <form id="form-seach" action="http://localhost:8080/waggyHome?action=searchProduct" method="post">
                    <input type="text" placeholder="Tìm kiếm" name="name-search">
                    <button type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
                </form>
            </div>
        </div>
        <div class="sub-content" id="list-product">
            <!-- for o dayy  -->
            <c:forEach items="${list}" var="item" >
                <div class="item-product">
                    <div id="image"><img
                            src="${item.imgLink}"
                            alt=""></div>
                    <div id="info">
                        <span>${item.name}</span>
                        <span>${item.price} $</span>
                    </div>
                    <div id="add-tocart">
                        <a href="http://localhost:8080/cart?action=addToCart&idP=${item.id}">ADD TO CART</a>
                        <a href=""><i class="fa-regular fa-heart" style="color: red ; line-height: 46px ; font-size: 18px"></i></a>
                    </div>
                </div>
            </c:forEach>
            <!-- forr o dayy  -->
        </div>
    </div>
    <jsp:include page="/waggy/footer.jsp"></jsp:include>
</div>
</body>

</html>
