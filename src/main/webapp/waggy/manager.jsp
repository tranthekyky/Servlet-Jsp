
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manager</title>
    <link rel="stylesheet" href="<c:url value="/style/manager.css"/>">
</head>

<body>
    <jsp:include page="/waggy/header.jsp"></jsp:include>
    <nav class="search-bar">
        <form action="/searchInManager" method="get" >
            <a href="http://localhost:8080/waggyHome?action=showFormAddProduct" >Thêm mới</a>
            <label>ID:</label>
            <input type="number" name="id" placeholder="ID sản phẩm" >

            <label for="category">Loại:</label>
            <select id="category" name="cate" >
                <option value="">-- Chọn loại sản phẩm --</option>
                <option value="2">Thức ăn</option>
                <option value="1">Quần áo</option>
                <option value="3">Phụ kiện</option>
            </select>

            <label >Giá:</label>
            <input type="number" name="min" placeholder="Giá tối thiểu" >
            <input type="number" name="max" placeholder="Giá tối đa" >


            <label for="searchName">Tìm theo tên:</label>
            <input type="text" id="searchName" name="name" placeholder="Tên sản phẩm" >

            <button type="submit">Tìm kiếm</button>
        </form>
    </nav>
    <p id="demo" style="color: #4CAF50 ; padding-left:70px ">${notification}</p>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Tên</th>
            <th>Giá</th>
            <th>Số lượng</th>
            <th>Ảnh</th>
            <th>Mô tả</th>
            <th>Loại</th>
            <th>Xử lý</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listManager}" var="item">
        <tr>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.price} $</td>
            <td>${item.quantity}</td>
            <td><img src="${item.imgLink}" alt="Product 1" width="50"></td>
            <td>${item.description}</td>
            <c:forEach items="${listCManager}" var="itemC">
                <c:if test="${itemC.idC == item.categoryId}">
                <td>${itemC.nameC}</td>
                </c:if>
                <c:if test="${itemC.idC == productById.categoryId}">
                    <td>${itemC.nameC}</td>
                </c:if>
            </c:forEach>
            <td>
                <button class="edit"><a href="http://localhost:8080/waggyHome?action=showFormUpdateProduct&idP=${item.id}" style=" color: white;">Edit</a></button>
                <button class="delete"><a href="http://localhost:8080/waggyHome?action=deleteProduct&idP=${item.id}" style=" color: white;">Delete</a></button>
            </td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
    <jsp:include page="/waggy/footer.jsp"></jsp:include>
<%--    <script>--%>
<%--        function myFunction(id) {--%>
<%--            let isConfirm = confirm("Bạn có muốn xoá sản phẩm có ID = " + id);--%>
<%--            if (isConfirm) {--%>
<%--                text = "Xoá thành công !!";--%>
<%--            }--%>
<%--            document.getElementById("demo").innerHTML = text;--%>
<%--        }--%>
<%--    </script>--%>
</body>

</html>
