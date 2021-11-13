<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<jsp:include page="../elements/head.jsp" />
<body>
<jsp:include page="../elements/header.jsp" />
<div class="content">
    <div class="container registration-container d-flex justify-content-center align-items-center">
        <form class="registration-form" action="${pageContext.request.contextPath}/registration" method="post">
            <c:forEach items="${pageContext.session.getAttribute('errors')}" var="item">
                <c:out value="${item}" /><br>
            </c:forEach>
            ${pageContext.session.removeAttribute('errors')}
            <div class="row d-flex justify-content-center">
                <h3>Sign up</h3>
            </div>
            <div class="row d-flex justify-content-between">
                <label for="surname">Surname</label>
                <input type="text" name="surname" id="surname">

                <label for="name">Name</label>
                <input type="text" name="name" id="name">

                <label for="patronymic">Patronymic</label>
                <input type="text" name="patronymic" id="patronymic">
            </div>
            <div class="row">
                <label for="email">Email</label>
                <input type="email" name="email" id="email">
            </div>
            <div class="row">
                <label for="phone">Phone</label>
                <input type="text" name="phone" id="phone">
            </div>
            <div class="row d-flex justify-content-between">
                <label for="password">Password</label>
                <input type="password" name="password" id="password">

                <label for="repeat_password">Repeat password</label>
                <input type="password" name="repeat_password" id="repeat_password">
            </div>
            <div class="row d-flex justify-content-center">
                <button type="submit" class="btn">Submit</button>
            </div>
        </form>
    </div>
</div>
<jsp:include page="../elements/footer.jsp" />
</body>
</html>