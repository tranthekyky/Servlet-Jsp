
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm Sản Phẩm</title>
    <link rel="stylesheet" href="<c:url value="/style/create.css"/>">
</head>

<body>

<div class="form-container">
    <h2>Sửa Sản Phẩm</h2>
    <form action="http://localhost:8080/waggyHome?action=updateProduct&idP=${productU.id}" method="post">
        <label for="name">Tên sản phẩm:</label>
        <input value="${productU.name}" type="text" id="name" name="name-update" placeholder="Nhập tên sản phẩm" required>

        <label for="price">Giá:</label>
        <input value="${productU.price}" type="number" id="price" name="price-update" placeholder="Nhập giá sản phẩm" required>

        <label for="quantity">Số lượng:</label>
        <input value="${productU.quantity}" type="number" id="quantity" name="quantity-update" placeholder="Nhập số lượng sản phẩm" required>

        <label for="linkImg">Link hình ảnh:</label>
        <input value="${productU.imgLink}" type="text" id="linkImg" name="image-update" placeholder="Nhập link hình ảnh sản phẩm" required>

        <label for="description">Mô tả sản phẩm:</label>
        <input value="${productU.description}" type="text" id="description" name="description-update" placeholder="Nhập mô tả sản phẩm" required></input>

        <label for="category">Loại sản phẩm:</label>
        <select id="category" name="category-id" required>
            <option value="1">Áo quần</option>
            <option value="3">Phụ kiện</option>
            <option value="2">Thức ăn</option>
        </select>

        <div class="button-group">
            <button type="submit">Lưu</button>
            <button type="button" class="back-btn" onclick="history.back()">Quay lại</button>
        </div>
    </form>
</div>

</body>

</html>

