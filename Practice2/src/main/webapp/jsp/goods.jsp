<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%--
  Created by IntelliJ IDEA.
  User: dan
  Date: 25.06.2019
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<a href="/groups">Groups</a>
<a href="/goods">Goods</a>
<form action="" method="post">
    <input type="text" name="search">
    <input type="hidden" name="action" value="search">
    <input type="submit" value="search" class="page-link">
</form>
<table class="table">
    <thead>
    <tr>
        <th scope="col">Remove</th>
        <th scope="col">Name</th>
        <th scope="col">Group</th>
        <th scope="col">Price</th>
        <th scope="col">Amount</th>
        <th scope="col">Total price</th>
        <th scope="col">Update</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="good" items="${goods}">
        <tr>
            <th scope="row">
                <form action="" method="post">
                    <input type="submit" value="&times;" class="page-link mx-auto">
                    <input type="hidden" name="id" value="${good.id}">
                    <input type="hidden" name="action" value="delete">
                </form>
            </th>
            <td>${good.name}</td>
            <td>
                <c:forEach var="group" items="${groups}">
                    <c:if test="${group.id == good.groupId}">
                        ${group.name}
                    </c:if>
                </c:forEach>
            </td>
            <td>${good.price}</td>
            <td>${good.count}</td>
            <td>${good.count * good.price}</td>
            <td>
                <form action="" method="post">
                    <input type="submit" value="Edit" class="page-link mx-auto">
                    <input type="hidden" name="id" value="${good.id}">
                    <input type="hidden" name="action" value="edit">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="d-flex justify-content-center">
    <button type="button" class="btn btn-outline-primary btn-lg" data-toggle="modal" data-target="#AddGoodModal">
        +
    </button>
</div>

<jsp:include page="addgoodmodal.jsp"/>
<jsp:include page="footer.jsp"/>


