<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%--
  Created by IntelliJ IDEA.
  User: dan
  Date: 25.06.2019
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<a href="/groups">Groups</a>
<a href="/goods">Goods</a>
<table class="table">
    <thead>
    <tr>
        <th scope="col">Remove</th>
        <th scope="col">ID</th>
        <th scope="col">Group Name</th>
        <th scope="col">Total amount</th>
        <th scope="col">Total price</th>
        <th scope="col">Update</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="group" items="${groups}">
        <tr>
            <th scope="row">
                <form action="" method="post">
                    <input type="submit" value="&times;" class="page-link mx-auto">
                    <input type="hidden" name="id" value="${group.id}">
                    <input type="hidden" name="action" value="delete">
                </form>
            </th>
            <td>${group.id}</td>
            <td>${group.name}</td>
            <td>${group.getTotalCount()}</td>
            <td>${group.getTotalPrice()}</td>
            <td>
                <form action="" method="post">
                    <input type="submit" value="Edit" class="page-link mx-auto">
                    <input type="hidden" name="id" value="${group.id}">
                    <input type="hidden" name="action" value="edit">
                </form>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <th scope="row">Total:</th>
        <th></th>
        <th></th>
        <th>${tcount}</th>
        <th>${tprice}</th>
        <th></th>
    </tr>
    </tbody>
</table>
<div class="d-flex justify-content-center">
<button type="button" class="btn btn-outline-primary btn-lg" data-toggle="modal" data-target="#AddModal">
    +
</button>
</div>
<jsp:include page="addmodal.jsp"/>

<jsp:include page="footer.jsp"/>

