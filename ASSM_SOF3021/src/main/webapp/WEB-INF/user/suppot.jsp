<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>ADMIN</title>

    <!-- Bootstrap CSS v5.0.2 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script language="JavaScript">
        function toggle(source) {
            checkboxes = document.getElementsByName('cbdelete');
            for (var i = 0; i < checkboxes.length; i++) {
                checkboxes[i].checked = source.checked;
            }
        }
    </script>
</head>
<body>
<header class="row" style="background:yellow">
    <div class="col-4 row">

        <div class="col-5" style="width: 88px;">
            <a href="/user/home">
                <img src="/img/logo.png" height="100" alt="">
            </a>
        </div>

        <div class="col-7">
            <h4 class="" style="font-family: Comic Sans MS; line-height: 4.2; ">Thẻ Siêu Tốc</h4>
        </div>

    </div>
    <div class="col-6">

    </div>
    <div class="col-2" style=" padding-top: 25px;">
        <div class="btn-group d-flex">
            <button type="button" class="btn btn-success dropdown-toggle" data-bs-toggle="dropdown"
                    aria-expanded="false">
                <c:if test="${userdn==null}">
                    <b>Tài Khoản</b>
                </c:if>
                <c:if test="${userdn!=null}">
                    <b>${userdn.userName}</b>
                </c:if>

            </button>
            <ul class="dropdown-menu" style="width:100%; ">
                <c:if test="${userdn==null}">
                    <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#exampleModallogin"> <b>Đăng Nhập</b></a></li>
                    <li><a class="dropdown-item" href="#"><b>Đăng Kí</b> </a></li>
                    <li><a class="dropdown-item" href="#"><b>Quên Mật Khẩu</b> </a></li>
                </c:if>
                <c:if test="${userdn!=null}">
                    <li><a class="dropdown-item" href="#"><b>Thông Tin</b></a></li>
                    <li><a class="dropdown-item" href="#"><b>Đổi Mật Khẩu</b> </a></li>
                </c:if>
                <li>
                    <hr class="dropdown-divider">
                </li>
                <li><a class="dropdown-item" href="/user/cart"><b>Giỏ hàng</b> </a></li>
                <c:if test="${userdn!=null}"> <li><a class="dropdown-item" href="/user/cart"><b>Lịch sử đơn hàng</b> </a></li></c:if>
                <li>
                    <hr class="dropdown-divider">
                </li>
                <c:if test="${userdn.role=='ADMIN'}">
                    <li><a class="dropdown-item" href="/admin/categori/getall"><b>Quản lý Categori</b></a></li>
                    <li><a class="dropdown-item" href="/admin/card/getall"><b>Quản lý Card</b> </a></li>
                    <li><a class="dropdown-item" href="#"><b>Quản lý Order</b> </a></li>
                    <li><a class="dropdown-item" href="/admin/user/getall"><b>Quản lý User</b> </a></li>
                </c:if>

                <li>
                    <hr class="dropdown-divider">
                </li>
                <li><a class="dropdown-item" href="#"><b>Đăng Xuất</b></a></li>
            </ul>

        </div>


    </div>
    <!-- Modal login -->
    <div class="modal fade" id="exampleModallogin" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form:form action="/user/login" method="post">
                        <div class="mb-3">
                            <label for="" class="form-label">Tài Khoản</label>
                            <input type="text" name="tk" id="" class="form-control" placeholder="Nhập UserName hoặc Email đã đăng kí"
                                   aria-describedby="helpId">
                        </div>
                        <div class="mb-3">
                            <label for="" class="form-label">Mật Khẩu</label>
                            <input type="text" name="mk" id="" class="form-control" placeholder=Nhập PassWord đã đăng kí"
                            aria-describedby="helpId">
                        </div>

                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Đăng Nhập</button>
                    </form:form>
                </div>

            </div>
        </div>
    </div>
</header>

<div class="text-center">
    <h2>Chi Tiết Hóa Đơn Cần Suppot</h2>
</div>

<c:if test="${!empty sessionScope.error}">
    <div class="alert alert-danger">
            ${sessionScope.error}
    </div>
    <c:remove var="error" scope="session"/>
</c:if>
<c:if test="${!empty sessionScope.success}">
    <div class="alert alert-success">
            ${sessionScope.success}
    </div>
    <c:remove var="success" scope="session"/>
</c:if>


<div style="margin-left: 30%;float: left;">
    <form:form method="post" action="/user/suppot?idOrderr=${bill.carts.idOrder}">
        <b>Mã Hóa Đơn:</b> <p>${bill.carts.idOrder}</p>
        <b>Tên Thẻ:</b> <p>${bill.cards.categories.name}</p>

        <b>Số Lượng:</b> <p>${bill.carts.quantity}</p>

        <b>Email nhận thẻ:</b> <p>${bill.carts.emailSend}</p>

        <b>Tổng Tiền Phải Thanh Toán:</b> <p>${bill.carts.priceOrder} VNĐ</p>



        <button type="submit" class="btn btn-warning">Gửi Lại Thẻ Vào Mail</button>
    </form:form>

</div>





<!-- Bootstrap JavaScript Libraries -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
        integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
        integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
        crossorigin="anonymous"></script>

</body>
</html>