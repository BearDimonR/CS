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
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <c:if test="${edittype == 2}">
            <th scope="col">Group</th>
            <th scope="col">Price</th>
            <th scope="col">Amount</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="good" items="${goods}">
            <c:if test="${currentid == good.id}">
                <tr>
                    <td><input type="text" name="name" value="${good.name}">
                    </td>
                    <td>
                        <select name="groupId">
                            <c:forEach var="group" items="${groups}">
                                <option value="${group.id}"
                                        <c:if test="${group.id == good.groupId}">
                                            selected
                                        </c:if>
                                > ${group.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td><input type="number" name="price" value="${good.price}">
                    </td>
                    <td><input type="number" name="count" value="${good.count}">
                    </td>
                    <td>
                        <input type="submit" value="Save" class="page-link mx-auto">
                        <input type="hidden" name="action" value="save">
                    </td>
                </tr>
            </c:if>
        </c:forEach>
        </tbody>
        </c:if>
        <c:if test="${edittype == 1}">
        </tr>
        </thead>
        <tbody>
        <tr>
            <c:forEach var="group" items="${groups}">
            <c:if test="${group.id == currentid}">
                <td><input type="text" name="name" value="${group.name}">
                </td>
            </c:if>
            </c:forEach>
                <td>
                    <input type="submit" value="Save" class="page-link mx-auto">
                    <input type="hidden" name="action" value="save">
                </td>
        </tr>
        </tbody>
        </c:if>
    </table>
</form>

<jsp:include page="footer.jsp"/>


