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
    <h2>Card</h2>
</div>
<div class="row">
    <form:form method="post" action="/admin/card/getall">
        <div class="mb-3">
            <label for="" class="form-label">Loại thẻ:</label>
            <select class="form-control" name="classify" id="">
                <option value="all">Tất Cả</option>
                <c:forEach items="${listclassify}" var="xx">
                    <option value="${xx}">
                        <c:if test="${xx=='VT'}">Viettel</c:if>
                        <c:if test="${xx=='VN'}">Vina</c:if>
                        <c:if test="${xx=='MB'}">Mobi</c:if>
                        <c:if test="${xx=='VNMB'}">Vietnammobi</c:if>
                        <c:if test="${xx=='GMB'}">Gmobi</c:if>
                    </option>
                </c:forEach>
            </select>
        </div>


        <div class="mb-3">
            <label for="" class="form-label">Mệnh Giá:</label>
            <select class="form-control" name="value" id="">
                <option value="all">Tất Cả</option>
                <c:forEach items="${listvalue}" var="xx">

                    <option value="${xx}">
                        <c:if test="${xx=='K10'}">10.000</c:if>
                        <c:if test="${xx=='K20'}">20.000</c:if>
                        <c:if test="${xx=='K30'}">30.000</c:if>
                        <c:if test="${xx=='K50'}">50.000</c:if>
                        <c:if test="${xx=='K100'}">100.000</c:if>
                        <c:if test="${xx=='K200'}">200.000</c:if>
                        <c:if test="${xx=='K500'}">500.000</c:if>
                        <c:if test="${xx=='K1000'}">1.000.000</c:if>
                    </option>
                </c:forEach>
            </select>
        </div>
        <button type="submit"> tìm Kiếm</button>
    </form:form>

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
<c:if test="${!empty sessionScope.quantitycardd}">
    <div class="alert alert-warning">
            ${sessionScope.quantitycardd}
    </div>
    <c:remove var="quantitycardd" scope="session"/>
</c:if>
    <table class="table table-striped">

        <thead>
        <tr>

            <th scope="col">Id</th>
            <th scope="col">Seri</th>
            <th scope="col">Number</th>
            <th scope="col">Expiry</th>
            <th scope="col">Categori</th>

            <th scope="col" colspan="2">
                Manipulation
            </th>
        </tr>
        </thead>

        <tbody>

        <button type="submit" class="btn btn-danger"> Xóa</button>
        <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#exampleModal">
            Thêm mới
        </button>

            <%--        page.getContent()--%>
        <c:forEach items="${ page.getContent()}" var="xy">

            <%--            &lt;%&ndash;            ----------------------Fomr Cập Nhập-------------------------&ndash;%&gt;--%>


                <div class="modal fade" id="example${xy.id}" tabindex="-1" aria-labelledby="exampleModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Cập Nhâp Card</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <form:form action="/admin/card/update?id=${xy.id}" method="post">
                                    <div class="mb-3">
                                        <label for="" class="form-label">Seri:</label>
                                        <input type="text" name="seri" id="" class="form-control" placeholder=""
                                               value="${xy.seri}" aria-describedby="helpId">

                                    </div>
                                    <div class="mb-3">
                                        <label for="" class="form-label">Number:</label>
                                        <input type="text" name="number" id="" class="form-control" placeholder=""
                                               value="${xy.number}" aria-describedby="helpId">

                                    </div>
                                    <div class="mb-3">
                                        <label for="" class="form-label">Ngày Hết Hạn:</label>
                                        <input type="date"  min="" name="date" id="" class="form-control" placeholder=""
                                               aria-describedby="helpId" value="<fmt:formatDate pattern="yyyy-MM-dd"
                                                                                                   value="${xy.expiry}"/>"> tháng/ngày/năm

                                    </div>
                                    <div class="mb-3">
                                        <label for="" class="form-label">Loại thẻ:</label>
                                        <select class="form-control" name="categories" id="">
                                            <c:forEach items="${listcate}" var="xx">
                                                <option value="${xx.id}"
                                                        <c:if test="${xx.id==xy.categories.id}">selected</c:if> >
                                                        ${xx.name}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>



                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng
                                    </button>
                                    <button type="reset" class="btn btn-danger">Xóa Form</button>
                                    <button type="submit" class="btn btn-success">Cập Nhập</button>

                                </form:form>
                            </div>

                        </div>
                    </div>
                </div>
            <tr>

                <td>${xy.id }</td>
                <td>${xy.seri }</td>
                <td>${xy.number }</td>
                <td>
                    <fmt:formatDate pattern="dd-MM-yyyy"
                                    value="${xy.expiry}"/>
                </td>
                <td>${xy.categories.name }</td>


                <td>
                    <a class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#example${xy.id}">Sửa</a>
                </td>
                <td>
                    <a class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal${xy.id}">Xóa</a>

                </td>


            </tr>


            <%--            -----------------------------Form Hỏi Để Xóa 1 Entity ---------------------------%>
            <div class="modal fade" id="exampleModal${xy.id}" tabindex="-1" aria-labelledby="exampleModalLabel${xy.id}"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel${xy.id}">Xóa Categorie</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                            <a href="/admin/card/delete?id=${xy.id}">
                                <button type="button" class="btn btn-primary"> Xóa</button>
                            </a>
                        </div>
                    </div>
                </div>
            </div>

        </c:forEach>


        </tbody>

    </table>

<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Thêm mới Card</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form:form action="/admin/card/add" method="post">

                    <div class="mb-3">
                        <label for="" class="form-label">Seri:</label>
                        <input type="text" name="seri" id="" class="form-control" placeholder=""
                               aria-describedby="helpId">

                    </div>
                    <div class="mb-3">
                        <label for="" class="form-label">Số Thẻ:</label>
                        <input type="text" name="number" id="" class="form-control" placeholder=""
                               aria-describedby="helpId">

                    </div>
                    <div class="mb-3">
                        <label for="" class="form-label">Ngày Hết Hạn:</label>
                        <input type="date" pattern="dd/mm/yyyy" name="date" id="" class="form-control" placeholder=""
                               aria-describedby="helpId">

                    </div>


                    <div class="mb-3">
                        <label for="" class="form-label">Categori:</label>
                        <select class="form-control" name="categories" id="">
                            <c:forEach items="${listcate}" var="xx">
                                <option value="${xx.id}">
                                        ${xx.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>


                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                    <button type="reset" class="btn btn-danger">Xóa Form</button>
                    <button type="submit" class="btn btn-success">Thêm Mới</button>

                </form:form>
            </div>

        </div>
    </div>
</div>

<nav aria-label="Page navigation example">
    <ul class="pagination justify-content-end">
        <li class="page-item <c:if test="${page.getNumber() < 1 }">disabled</c:if> ">

            <a class="page-link" href="/admin/card/getall?pageNumber=${page.getNumber() - 1}"> < </a>

        </li>


        <li class="page-item"><a class="page-link" href="#">${ page.getNumber() + 1} / ${page.getTotalPages() }</a></li>


        <li class="page-item <c:if test="${page.getNumber() >= page.getTotalPages() - 1 }">disabled</c:if> ">
            <a class="page-link" href="/admin/card/getall?pageNumber=${page.getNumber() + 1}"> > </a>
        </li>
    </ul>
</nav>


<!-- Bootstrap JavaScript Libraries -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
        integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
        integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
        crossorigin="anonymous"></script>

</body>
</html>