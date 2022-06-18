
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>ADMIN</title>

    <!-- Bootstrap CSS v5.0.2 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"  integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>
<body>
<%--<table class="table table-striped">--%>

<%--    <thead>--%>
<%--    <tr>--%>
<%--        <th><input type="checkbox" onClick="toggle(this)" /></th>--%>
<%--        <th scope="col">Id</th>--%>
<%--        <th scope="col">Fullname</th>--%>
<%--        <th scope="col">Email</th>--%>
<%--        <th scope="col">SDT</th>--%>
<%--        <th scope="col">role</th>--%>
<%--        <th scope="col">creatdate</th>--%>
<%--        <th scope="col" colspan="2">Thao Tác</th>--%>
<%--    </tr>--%>
<%--    </thead>--%>

<%--    <tbody>--%>
<%--    <form:form method="post" action="/users/deletetick" >--%>
<%--        <button class="btn btn-danger" > Xóa </button>--%>
<%--        <c:forEach items="${user}" var="xy">--%>
<%--            <tr>--%>
<%--                <td>--%>
<%--                    <div class="form-check">--%>
<%--                        <input type="checkbox" class="form-check-input" name="cb" id=""--%>
<%--                               value="${xy.id }"> <label class="form-check-label"--%>
<%--                                                         for=""> </label>--%>
<%--                    </div>--%>
<%--                </td>--%>
<%--                <td>${xy.id }</td>--%>
<%--                <td>${xy.fullname }</td>--%>
<%--                <td>${xy.email}</td>--%>
<%--                <td>${xy.sdt}</td>--%>
<%--                <td>${xy.type }</td>--%>
<%--                <td><fmt:formatDate pattern="dd-MM-yyyy"--%>
<%--                                    value="${xy.createdDate}" /></td>--%>
<%--                <td><a class="btn btn-warning" href="/users/update/${xy.id}">Sửa</a></td>--%>
<%--                <td><a class="btn btn-danger" href="/users/delete/${xy.id}">Xóa</a></td>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>
<%--    </form:form>--%>
<%--    </tbody>--%>

<%--</table>--%>
<a  class="btn btn-success" href="${url}">xác nhận thanh toán</a>






</body>
</html>