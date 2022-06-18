<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">

<head>
    <title>Title</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS v5.0.2 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <style>
        .classfy {
            border: 1px solid gray
        }

        .value {
            border: 1px solid gray

        }

        .chon {
            border: 5px solid yellow;
        }
    </style>
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
                <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#exampleModalHotro"> <b>Hỗ trợ</b></a></li>

                <c:if test="${userdn.role=='ADMIN'}">
                    <li>
                        <hr class="dropdown-divider">
                    </li>
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
    <!-- Modal Hotro -->
    <div class="modal fade" id="exampleModalHotro" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Hỗ Trợ Chưa Nhận Được Thẻ</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form:form action="/user/suppot" method="post">

                        <div class="mb-3">
                            <label for="" class="form-label">Nhập Mã Hóa Đơn:</label>
                            <input type="text" name="idOrder" id="" class="form-control" placeholder=Nhập PassWord đã đăng kí"
                            aria-describedby="helpId">

                        </div>

                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Kiểm Tra</button>
                    </form:form>
                </div>

            </div>
        </div>
    </div>
</header>
<div>
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
<div>
    <div><b>Bước 1: </b>Chọn nhà mạng.</div>
    <div class="" style="margin-left: 10px;float:left;margin-top: 10px;">
        <a href="/user/home?classfy=VT" class="btn btn-default"><img
                class="classfy <c:if test="${classfyy=='VT'}">chon</c:if>"
                src="https://thanhtoanonline.vn/uploads/thumbs/type_card/1/300x0/10-49-54-21-03-2019-icon-viettel.png"
                alt=""></a>
        <a href="/user/home?classfy=MB" class="btn btn-default"><img
                class="classfy <c:if test="${classfyy=='MB'}">chon</c:if>"
                src="https://thanhtoanonline.vn/uploads/thumbs/type_card/2/300x0/01-51-19-21-03-2019-2.png"
                alt=""></a>
        <a href="/user/home?classfy=VN" class="btn btn-default"><img
                class="classfy <c:if test="${classfyy=='VN'}">chon</c:if>"
                src="https://thanhtoanonline.vn/uploads/thumbs/type_card/3/300x0/01-52-49-21-03-2019-2.png"
                alt=""></a>
        <a href="/user/home?classfy=VNMB" class="btn btn-default"><img
                class="classfy <c:if test="${classfyy=='VNMB'}">chon</c:if>"
                src="https://thanhtoanonline.vn/uploads/thumbs/type_card/5/300x0/01-54-03-21-03-2019-2.png"
                alt=""></a>
        <a href="/user/home?classfy=GMB" class="btn btn-default"><img
                class="classfy <c:if test="${classfyy=='GMB'}">chon</c:if>"
                src="https://thanhtoanonline.vn/uploads/thumbs/type_card/6/300x0/01-55-17-21-03-2019-2.png"
                alt=""></a>
    </div>
</div>
<br>
<br>
<br>
<br>
<br>
<div>
    <div><b>Bước 2: </b>Chọn mệnh giá thẻ.</div>
    <div style="margin-left: 10px;float:left;margin-top: 10px;">
        <a href="/user/home?classfy=${classfyy}&value=K10"
           class="btn btn-default value <c:if test="${value=='K10'}">chon</c:if>"
           style="<c:if test="${classfyy==''}">
                   pointer-events: none;
                   cursor: default;
                   </c:if>">10.000 vnđ</a>
        <a href="/user/home?classfy=${classfyy}&value=K20"
           class="btn btn-default value <c:if test="${value=='K20'}">chon</c:if>"
           style="<c:if test="${classfyy==''}">
                   pointer-events: none;
                   cursor: default;
                   </c:if>">20.000 vnđ</a>
        <a href="/user/home?classfy=${classfyy}&value=K30"
           class="btn btn-default value <c:if test="${value=='K30'}">chon</c:if>"
           style="<c:if test="${classfyy==''}">
                   pointer-events: none;
                   cursor: default;
                   </c:if>">30.000 vnđ</a>
        <a href="/user/home?classfy=${classfyy}&value=K50"
           class="btn btn-default value <c:if test="${value=='K50'}">chon</c:if>"
           style="<c:if test="${classfyy==''}">
                   pointer-events: none;
                   cursor: default;
                   </c:if>">50.000 vnđ</a>
        <a href="/user/home?classfy=${classfyy}&value=K100"
           class="btn btn-default value <c:if test="${value=='K100'}">chon</c:if>"
           style="<c:if test="${classfyy==''}">
                   pointer-events: none;
                   cursor: default;
                   </c:if>">100.000 vnđ</a>
        <a href="/user/home?classfy=${classfyy}&value=K200"
           class="btn btn-default value <c:if test="${value=='K200'}">chon</c:if>"
           style="<c:if test="${classfyy==''}">
                   pointer-events: none;
                   cursor: default;
                   </c:if>">200.000 vnđ</a>
        <a href="/user/home?classfy=${classfyy}&value=K500"
           class="btn btn-default value <c:if test="${value=='K500'}">chon</c:if>"
           style="<c:if test="${classfyy==''}">
                   pointer-events: none;
                   cursor: default;
                   </c:if>">500.000 vnđ</a>
        <a href="/user/home?classfy=${classfyy}&value=K1000"
           class="btn btn-default value <c:if test="${value=='K1000'}">chon</c:if>"
           style="<c:if test="${classfyy==''}">
                   pointer-events: none;
                   cursor: default;
                   </c:if>">1.000.000 vnđ</a>
    </div>
</div>
<br>
<br>

<div>


    <form:form action="/user/addcart?classfy=${classfy}&value=${value}" method="post">

    <div style="margin-top:10px">
    <div><b>Bước 3: </b>Nhập email nhận thẻ.</div>
    <input type="email" name="mailSend"
    <c:if test="${value=='all'}">
        disabled
    </c:if> placeholder="nhập email">
    <br>
    <small id="helpId" class="text-muted">Vui lòng nhập chính xác. Không hỗ trợ nếu nhập sai mail</small>
    </div>
    <br>
    <button type="submit" class="btn btn-success" style="margin-top: 50px;">Thêm Vào Giỏ Hàng</button>

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