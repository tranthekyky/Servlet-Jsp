
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://fonts.googleapis.com/css2?family=Chilanka&amp;family=Montserrat:wght@300;400;500&amp;display=swap"
          rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="/style/cart.css"/>">
</head>

<body>
<div id="main">
    <jsp:include page="/waggy/header.jsp"></jsp:include>
    <div class="cart-container">
        <h2>${sessionScope.acc.getUsername()}</h2>
        <table class="cart-table">
            <thead>
            <tr>
                <th>Ảnh</th>
                <th>Tên sản phẩm</th>
                <th>Đơn giá</th>
                <th>Số lượng mua</th>
                <th>Thành tiền</th>
                <th> - </th>
            </tr>
            </thead>
            <c:if test="${text == true}" >
            <div class="alert alert-success" role="alert" >
                    Cập nhật số lượng thành công .
            </div>
            </c:if>
            <c:if test="${textD == true}" >
                <div class="alert alert-success" role="alert" >
                    Xoá sản phẩm thành công .
                </div>
            </c:if>
            <c:if test="${textP == true}" >
                <div class="alert alert-success" role="alert" >
                    Đặt hàng thành công .
                </div>
            </c:if>
            <c:if test="${textNull == true}" >
            <div class="alert alert-warning" role="alert">
                Chưa có sản phẩm trong giỏ hàng !
            </div>
            </c:if>
            <tbody id="cartItems">
            <c:forEach items="${productsInCart}" var="item">
            <tr data-id="1">
                <td><img src="${item.imgLink}" alt="" class="product-image"></td>
                <td>${item.name}</td>
                <td>${item.price} $</td>
                <td>
                    <form action="http://localhost:8080/cart?action=updateCart&idP=${item.id}" method="post">
                        <input type="number" value="${item.quantity}" min="0" class="quantity-input" name="quantityInCart">
                        <button class="update-btn" type="submit">Cập nhật giá</button>
                    </form>
                </td>
                <td class="item-total">${item.price * item.quantity} $</td>
                <td><button class="delete-btn" ><a href="http://localhost:8080/cart?action=deleteToCart&idP=${item.id}" style="color: white">Xóa</a></button></td>
            </tr>
            </c:forEach>
            </tbody>
        </table>

        <div class="cart-summary">
            <h3>Tổng đơn hàng : $<span id="grandTotal">${total_price}</span></h3>
        </div>
    </div>
    <div class="container mt-5" >
        <h2 class="text-center">Thông tin thanh toán</h2>
        <form action="http://localhost:8080/cart?action=payCart&idA=${sessionScope.acc.getIdAcc()}" method="POST" class="row g-3">
            <div class="col-md-6">
                <label for="customerName" class="form-label">Tên khách hàng</label>
                <input type="text" class="form-control" id="customerName" name="customerName" placeholder="Nhập tên của bạn" required>
            </div>
            <div class="col-md-6">
                <label for="customerEmail" class="form-label">Email</label>
                <input type="email" class="form-control" id="customerEmail" name="customerEmail" placeholder="you@example.com" required>
            </div>
            <div class="col-md-6">
                <label for="phoneNumber" class="form-label">Số điện thoại</label>
                <input type="tel" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="098...." required>
            </div>
            <div class="col-md-6">
                <label for="shippingAddress" class="form-label">Địa chỉ giao hàng</label>
                <input type="text" class="form-control" id="shippingAddress" name="shippingAddress" placeholder="Nhập địa chỉ của bạn" required>
            </div>
            <div class="col-md-6">
                <label for="paymentMethod" class="form-label">Phương thức thanh toán</label>
                <select class="form-select" id="paymentMethod" name="paymentMethod" required>
                    <option value="" selected disabled>Chọn phương thức</option>
                    <option value="credit_card">Thẻ tín dụng</option>
                    <option value="bank_transfer">Chuyển khoản ngân hàng</option>
                    <option value="cod">Thanh toán khi nhận hàng (COD)</option>
                </select>
            </div>
            <div class="col-12">
                <label for="notes" class="form-label">Ghi chú (Không bắt buộc)</label>
                <textarea class="form-control" id="notes" name="notes" rows="3" placeholder="Ghi chú thêm cho đơn hàng..."></textarea>
            </div>
            <div class="col-12">
                <button type="submit" class="btn btn-primary w-100">Xác nhận thanh toán</button>
            </div>
        </form>
    </div>



    <jsp:include page="/waggy/footer.jsp"></jsp:include>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>
