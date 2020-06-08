<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dan
  Date: 25.06.2019
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<body>
<div class="d-flex justify-content-center" style="margin: 15%">
    <form action="" method="post">
        <input type="text" name="login">
        <br>
        <input type="password" name="password" class="mt-2">
        <br>
        <input type="submit" class="mt-3 btn btn-primary">
    </form>
</div>
</body>
<jsp:include page="footer.jsp"/>
