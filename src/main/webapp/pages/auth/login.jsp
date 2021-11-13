<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<jsp:include page="../elements/head.jsp" />
<body>
<jsp:include page="../elements/header.jsp" />
<div class="content">
    <div class="container login-container d-flex justify-content-center align-items-center">
        <form class="login-form" action="${pageContext.request.contextPath}/login" method="post">
            <c:forEach items="${pageContext.session.getAttribute('errors')}" var="item">
                <c:out value="${item}" /><br>
            </c:forEach>
            ${pageContext.session.removeAttribute('errors')}
            <div class="row d-flex justify-content-center">
                <h3>Sign in</h3>
            </div>
            <div class="row">
                <label for="login">Email or Phone</label>
                <input type="login" name="login" id="login">
            </div>
            <div class="row">
                <label for="password">Password</label>
                <input type="password" name="password" id="password">
            </div>
            <div class="row d-flex justify-content-center">
                <button type="submit" class="btn">Submit</button>
                <button type="button" class="btn"><a href="${pageContext.request.contextPath}/registration">Sign up</a></button>
            </div>
        </form>
    </div>
</div>
<jsp:include page="../elements/footer.jsp" />
</body>
</html>