<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link
            href="https://fonts.googleapis.com/css2?family=Chilanka&amp;family=Montserrat:wght@300;400;500&amp;display=swap"
            rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="/style/home.css"/>">
</head>
<body>
<div id="main">
    <jsp:include page="/waggy/header.jsp"></jsp:include>
    <div id="content">
        <div class="content-sub" id="content-img">
            <img src="https://themewagon.github.io/waggy/images/banner-img.png" alt="">
        </div>
        <div class="content-sub" id="content-introduce">
            <div class="introduce-title">
                <h3>Waggy</h3>
            </div>
            <div class="introduce-text">
                <p>Chào mừng đến với Waggy – nơi cung cấp áo quần, thức ăn, và phụ kiện cho thú cưng! Chúng tôi
                    mang đến những sản phẩm chất lượng, đa dạng từ thời trang đến đồ ăn dinh dưỡng và phụ kiện đáng
                    yêu,
                    giúp thú cưng của bạn luôn khỏe mạnh và xinh xắn. Ghé ngay Waggy để chọn cho bé cưng những
                    món
                    đồ tuyệt vời nhất!</p>
            </div>
        </div>
    </div>
    <jsp:include page="/waggy/footer.jsp"></jsp:include>
</div>
</body>

</html>
